package com.dutch_pay.hdh.sugangmvp.ui.activity.myClassRegDetail;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.dutch_pay.hdh.sugangmvp.Constants;
import com.dutch_pay.hdh.sugangmvp.MyApplication;
import com.dutch_pay.hdh.sugangmvp.data.model.MyConsentDetail;
import com.dutch_pay.hdh.sugangmvp.data.model.ParentAuth;
import com.dutch_pay.hdh.sugangmvp.data.model.Result;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;
import com.dutch_pay.hdh.sugangmvp.ui.view.HtmlView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyClassRegDetailPresenter implements MyClassRegDetailContract.Presenter {

    private MyClassRegDetailContract.View mView;
    private Context mContext;

    //인덱스번호
    private String mNo;

    private ParentAuth mParentAuth;
    private MyApplication myApplication;
    private MyConsentDetail.MyConsentDetailResultData mMyConsentDetailResultData;

    MyClassRegDetailPresenter(MyClassRegDetailContract.View mView, Context mContext, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;

        mNo = mActivity.getIntent().getStringExtra(Constants.KEY_CONSENT_NO);
        if ("".equals(mNo)) {
            this.mView.showBeforeActivity();
        }

        myApplication = MyApplication.getInstance();
        mParentAuth = MyApplication.getInstance().getParentAuth();

    }

    /**
     * 수강신청 상세데이터 요청
     */
    @Override
    public void getMyConsentDetail() {
        mView.showParentDialog();

        Call<MyConsentDetail> myConsentDetail = MyApplication.getRestAdapter(myApplication.getBASE_URL(), false).getMyConsentDetail(
                Constants.SERVER_CALLBACK_KEY
                , mParentAuth.getSchool_id()
                , mParentAuth.getStudent_id()
                , mParentAuth.getStudent_no()
                , mParentAuth.getStudent_passwd()
                , mNo
        );

        myConsentDetail.enqueue(new Callback<MyConsentDetail>() {
            @Override
            public void onResponse(@NonNull Call<MyConsentDetail> call, @NonNull Response<MyConsentDetail> response) {
                if (response.body() != null) {
                    if (!"0".equals(response.body().getData().get(0).getErr_code())) {
                        dialogForm(response.body().getData().get(0).getErr_msg());
                    } else {
                        mMyConsentDetailResultData = response.body().getData().get(1);
                        mView.changeAllText(mMyConsentDetailResultData);

                        if ("금지".equals(mMyConsentDetailResultData.getMode())) {
                            mView.hideClassConfirm();
                        } else {
                            mView.showClassConfirm();
                        }
                    }
                } else {
                    dialogForm("response.body() is null");
                }
                mView.hideParentDialog();
            }


            @Override
            public void onFailure(@NonNull Call<MyConsentDetail> call, @NonNull Throwable t) {
                mView.hideParentDialog();
                dialogForm(t.getMessage());
            }
        });
    }

    /**
     * 수강신청삭제 요청
     */
    @Override
    public void setOrderDelete() {
        mView.showParentDialog();
        Call<Result> orderDelete = MyApplication.getRestAdapter(myApplication.getBASE_URL(), false).setOrderDelete(
                Constants.SERVER_CALLBACK_KEY
                , Constants.DELETE
                , mParentAuth.getSchool_id()
                , mParentAuth.getStudent_id()
                , mParentAuth.getStudent_no()
                , mParentAuth.getStudent_passwd()
                , mNo
        );

        orderDelete.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
                if (response.body() != null) {
                    if (!"0".equals(response.body().getData().get(0).getErr_code())) {
                        dialogForm(response.body().getData().get(0).getErr_msg());
                    } else {
                        dialogForm(mMyConsentDetailResultData.getLB_subject() + " 과목\n신청이 삭제되었습니다");
                    }
                } else {
                    dialogForm("response.body() is null");
                }
                mView.hideParentDialog();
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {
                mView.hideParentDialog();
                dialogForm(t.getMessage());
            }
        });
    }

    @Override
    public void clickBack() {
        mView.showBeforeActivity();
    }

    @Override
    public void clickClassWeek() {
        if (mMyConsentDetailResultData != null) {
            final CommonDialog build = new CommonDialog(mContext);
            build.setCancelable(false);
            build.setView(new HtmlView(mContext, mMyConsentDetailResultData.getWeek_map()));
            mView.changeDialogTitle(build, "강좌요일");
            mView.addDialogPositiveButton(build, false);
            mView.showDialog(build);
        }
    }

    @Override
    public void clickAuthDialogPositiveButton(CommonDialog build, boolean isBack) {
        mView.removeDialog(build);
        if (isBack)
            mView.showBeforeActivity();
    }

    @Override
    public void clickAuthDialogNegativeButton(CommonDialog build) {
        mView.removeDialog(build);
    }

    private void dialogForm(String content) {
        final CommonDialog build = new CommonDialog(mContext);
        build.setCancelable(false);
        mView.changeDialogTitle(build, "알림");
        mView.changeDialogMessage(build, content);
        mView.addDialogPositiveButton(build, true);
        mView.showDialog(build);
    }

}
