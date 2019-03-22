package com.dutch_pay.hdh.sugangmvp.ui.fragment.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.dutch_pay.hdh.sugangmvp.Constants;
import com.dutch_pay.hdh.sugangmvp.MyApplication;
import com.dutch_pay.hdh.sugangmvp.data.model.Html;
import com.dutch_pay.hdh.sugangmvp.data.model.MyConsent;
import com.dutch_pay.hdh.sugangmvp.data.model.ParentAuth;
import com.dutch_pay.hdh.sugangmvp.ui.activity.main.MainActivity;
import com.dutch_pay.hdh.sugangmvp.ui.activity.myClassRegDetail.MyClassRegDetailActivity;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;
import com.dutch_pay.hdh.sugangmvp.ui.view.HtmlView;
import com.dutch_pay.hdh.sugangmvp.util.Trace;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchClassRegistrationPresenter implements SearchClassRegistrationContract.Presenter {

    private SearchClassRegistrationContract.View mView;
    private Context mContext;

    private MainActivity mMainActivity;
    private MyApplication myApplication;
    private ParentAuth mParentAuth;
    private SearchClassRegistrationListAdapter mMyConsentListAdapter;
    private ArrayList<MyConsent.MyConsentListResultData> mMyConsentListResultDataArrayList = new ArrayList<>();

    SearchClassRegistrationPresenter(SearchClassRegistrationContract.View mView, Context mContext, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;

        mMainActivity = (MainActivity) mActivity;
        myApplication = MyApplication.getInstance();
    }

    @Override
    public void getMyConsentList(ListView listView) {
        mMainActivity.showDialog();
        mParentAuth = MyApplication.getInstance().getParentAuth();
        Call<MyConsent> myConsentList = MyApplication.getRestAdapter(myApplication.getBASE_URL(), false).getMyConsent(
                Constants.SERVER_CALLBACK_KEY
                , mParentAuth.getSchool_id()
                , mParentAuth.getStudent_id()
                , mParentAuth.getStudent_no()
                , mParentAuth.getStudent_passwd()
        );
        myConsentList.enqueue(new Callback<MyConsent>() {

            @Override
            public void onResponse(@NonNull Call<MyConsent> call, @NonNull Response<MyConsent> response) {
                if (response.body() != null) {
                    if (!"0".equals(response.body().getData().get(0).getErr_code())) {
                        dialogForm(response.body().getData().get(0).getErr_msg());
                    } else {
                        if (response.body().getData().get(1) == null) {
                            mView.showNoSearchText();
                            mView.hideSearchClassReg();

                            dialogForm("검색된 항목이 없습니다.");

                            if (mMyConsentListResultDataArrayList != null) {
                                mMyConsentListResultDataArrayList.clear();
                                if (mMyConsentListAdapter != null) {
                                    mMyConsentListAdapter.notifyDataSetChanged();
                                }
                            }
                        } else {
                            mView.hideNoSearchText();
                            mView.showSearchClassReg();

                            mMyConsentListResultDataArrayList = new ArrayList<>();
                            for (int i = 1; i < response.body().getData().size(); i++) {
                                mMyConsentListResultDataArrayList.add(response.body().getData().get(i));
                            }
                            mMyConsentListAdapter = new SearchClassRegistrationListAdapter(mMainActivity, mMyConsentListResultDataArrayList);
                            listView.setAdapter(mMyConsentListAdapter);
                        }
                    }
                } else {
                    dialogForm("response.body() is null");
                }
                mMainActivity.hideDialog();
            }

            @Override
            public void onFailure(@NonNull Call<MyConsent> call, @NonNull Throwable t) {
                mMainActivity.hideDialog();
                dialogForm(t.getMessage());
            }
        });
    }

    @Override
    public void getTimeBox() {
        mMainActivity.showDialog();
        mParentAuth = MyApplication.getInstance().getParentAuth();
        Call<Html> timeBox = MyApplication.getRestAdapter(myApplication.getBASE_URL(), false).getTimeBox(
                Constants.SERVER_CALLBACK_KEY
                , mParentAuth.getSchool_id()
                , mParentAuth.getStudent_id()
                , mParentAuth.getStudent_no()
                , mParentAuth.getStudent_passwd()
        );
        timeBox.enqueue(new Callback<Html>() {

            @Override
            public void onResponse(@NonNull Call<Html> call, @NonNull Response<Html> response) {
                if (response.body() != null) {
                    if (!"0".equals(response.body().getData().get(0).getErr_code())) {
                        dialogForm(response.body().getData().get(0).getErr_msg());
                    } else {
                        if (response.body().getData().get(1) == null) {
                            dialogForm("검색된 항목이 없습니다.");
                        } else {

                            final CommonDialog build = new CommonDialog(mMainActivity);
                            build.setCancelable(false);
                            build.setView(new HtmlView(mMainActivity, response.body().getData().get(1).getHtml_tag()));
                            mView.changeDialogTitle(build, "수강시간표");
                            mView.addDialogPositiveButton(build);
                            mView.showDialog(build);

                        }
                    }
                } else {
                    dialogForm("response.body() is null");
                }
                mMainActivity.hideDialog();
            }

            @Override
            public void onFailure(@NonNull Call<Html> call, @NonNull Throwable t) {
                Trace.e("테스트");
                mMainActivity.hideDialog();
                dialogForm(t.getMessage());
            }
        });
    }

    @Override
    public void getSdalView() {
        mMainActivity.showDialog();
        mParentAuth = MyApplication.getInstance().getParentAuth();
        Call<Html> sdalView = MyApplication.getRestAdapter(myApplication.getBASE_URL(), false).getSdalView(
                Constants.SERVER_CALLBACK_KEY
                , mParentAuth.getSchool_id()
                , mParentAuth.getStudent_id()
                , mParentAuth.getStudent_no()
                , mParentAuth.getStudent_passwd()
        );
        sdalView.enqueue(new Callback<Html>() {

            @Override
            public void onResponse(@NonNull Call<Html> call, @NonNull Response<Html> response) {
                if (response.body() != null) {
                    if (!"0".equals(response.body().getData().get(0).getErr_code())) {
                        dialogForm(response.body().getData().get(0).getErr_msg());
                    } else {
                        if (response.body().getData().get(1) == null) {
                            dialogForm("검색된 항목이 없습니다.");
                        } else {

                            final CommonDialog build = new CommonDialog(mMainActivity);
                            build.setCancelable(false);
                            build.setView(new HtmlView(mMainActivity, response.body().getData().get(1).getHtml_tag()));
                            mView.changeDialogTitle(build, "수강일정표");
                            mView.addDialogPositiveButton(build);
                            mView.showDialog(build);
                        }
                    }
                } else {
                    dialogForm("response.body() is null");
                }
                mMainActivity.hideDialog();
            }

            @Override
            public void onFailure(@NonNull Call<Html> call, @NonNull Throwable t) {
                mMainActivity.hideDialog();
                dialogForm(t.getMessage());
            }
        });
    }

    @Override
    public void clickSearchClassReg(int position) {
        Intent intent = new Intent(mContext , MyClassRegDetailActivity.class);
        intent.putExtra(Constants.KEY_SATISFACTION_NO, mMyConsentListResultDataArrayList.get(position).getLB_no());
        mView.showActivity(intent);
        mView.showAnimation();
    }

    /**
     * DialogPositiveButton 클릭
     */
    @Override
    public void clickDialogPositiveButton(CommonDialog build) {
        mView.removeDialog(build);
    }

    /**
     * DialogNegativeButton 클릭
     */
    @Override
    public void clickDialogNegativeButton(CommonDialog build) {
        mView.removeDialog(build);
    }

    private void dialogForm(String content) {
        final CommonDialog build = new CommonDialog(mMainActivity);
        build.setCancelable(true);
        mView.changeDialogTitle(build, "알림");
        mView.changeDialogMessage(build, content);
        mView.addDialogPositiveButton(build);
        mView.showDialog(build);
    }
}
