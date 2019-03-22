package com.dutch_pay.hdh.sugangmvp.ui.activity.classRegDetail;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.dutch_pay.hdh.sugangmvp.Constants;
import com.dutch_pay.hdh.sugangmvp.MyApplication;
import com.dutch_pay.hdh.sugangmvp.data.model.ConsentDetail;
import com.dutch_pay.hdh.sugangmvp.data.model.ParentAuth;
import com.dutch_pay.hdh.sugangmvp.data.model.Result;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;
import com.dutch_pay.hdh.sugangmvp.ui.view.HtmlView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassRegDetailPresenter implements ClassRegDetailContract.Presenter {

    private ClassRegDetailContract.View mView;
    private Context mContext;

    private ParentAuth mParentAuth;
    private MyApplication myApplication;
    private ConsentDetail.ConsentDetailResultData mConsentDetailResultData;

    //인덱스번호
    private String mNo;


    ClassRegDetailPresenter(ClassRegDetailContract.View mView, Context mContext, Activity mActivity) {
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
    public void getConsentDetail() {
        mView.showParentDialog();
        Call<ConsentDetail> consentDetail = MyApplication.getRestAdapter(myApplication.getBASE_URL(), false).getConsentDetail(
                Constants.SERVER_CALLBACK_KEY
                , mParentAuth.getSchool_id()
                , mParentAuth.getStudent_id()
                , mParentAuth.getStudent_no()
                , mParentAuth.getStudent_passwd()
                , mNo
        );

        consentDetail.enqueue(new Callback<ConsentDetail>() {
            @Override
            public void onResponse(@NonNull Call<ConsentDetail> call, @NonNull Response<ConsentDetail> response) {
                if (response.body() != null) {
                    if (!"0".equals(response.body().getData().get(0).getErr_code())) {
                        dialogForm(response.body().getData().get(0).getErr_msg() , true);
                    } else {
                        mConsentDetailResultData = response.body().getData().get(1);
                        mView.changeAllText(mConsentDetailResultData);

                        if ("금지".equals(mConsentDetailResultData.getMode())) {
                            mView.hideClassConfirm();
                        } else {
                            mView.showClassConfirm();
                        }
                    }
                } else {
                    dialogForm("response.body() is null", true);
                }
                mView.hideParentDialog();
            }

            @Override
            public void onFailure(@NonNull Call<ConsentDetail> call, @NonNull Throwable t) {
                mView.hideParentDialog();
                dialogForm(t.getMessage() , false);
            }
        });
    }


    /**
     * 수강신청 요청
     */
    @Override
    public void setOrderSubmit() {
        mView.showParentDialog();
        Call<Result> orderSubmit = MyApplication.getRestAdapter(myApplication.getBASE_URL(), false).setOrderSubmit(
                Constants.SERVER_CALLBACK_KEY
                , Constants.INSERT
                , mParentAuth.getSchool_id()
                , mParentAuth.getStudent_id()
                , mParentAuth.getStudent_no()
                , mParentAuth.getStudent_passwd()
                , mNo
        );

        orderSubmit.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
                if (response.body() != null) {
                    if (!"0".equals(response.body().getData().get(0).getErr_code())) {
                        dialogForm(response.body().getData().get(0).getErr_msg() , true);
                    } else {
                        dialogForm(mConsentDetailResultData.getLB_subject() + " 과목\n신청이 완료되었습니다" , true);
                    }
                } else {
                    dialogForm("response.body() is null" , true);
                }
                mView.hideParentDialog();
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {
                mView.hideParentDialog();
                dialogForm(t.getMessage() , false);
            }
        });
    }

    @Override
    public void clickBack() {
        mView.showBeforeActivity();
    }

    @Override
    public void clickClassWeek() {
        if (mConsentDetailResultData != null) {
            final CommonDialog build = new CommonDialog(mContext);
            build.setCancelable(false);
            build.setView(new HtmlView(mContext, mConsentDetailResultData.getWeek_map()));
            mView.changeDialogTitle(build, "강좌요일");
            mView.addDialogPositiveButton(build ,false);
            mView.showDialog(build);
        }
    }

    @Override
    public void clickAuthDialogPositiveButton(CommonDialog build , boolean isBack) {
        mView.removeDialog(build);
        if(isBack)
            mView.showBeforeActivity();

    }

    @Override
    public void clickAuthDialogNegativeButton(CommonDialog build) {
        mView.removeDialog(build);
    }


    private void dialogForm(String content , boolean isBack) {
        final CommonDialog build = new CommonDialog(mContext);
        build.setCancelable(false);
        mView.changeDialogTitle(build, "알림");
        mView.changeDialogMessage(build, content);
        mView.addDialogPositiveButton(build , isBack);
        mView.showDialog(build);
    }
}
