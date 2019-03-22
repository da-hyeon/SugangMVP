package com.dutch_pay.hdh.sugangmvp.ui.fragment.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.dutch_pay.hdh.sugangmvp.Constants;
import com.dutch_pay.hdh.sugangmvp.MyApplication;
import com.dutch_pay.hdh.sugangmvp.data.model.Consent;
import com.dutch_pay.hdh.sugangmvp.data.model.ParentAuth;
import com.dutch_pay.hdh.sugangmvp.ui.activity.classRegDetail.ClassRegDetailActivity;
import com.dutch_pay.hdh.sugangmvp.ui.activity.main.MainActivity;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassRegistrationPresenter implements ClassRegistrationContract.Presenter{

    private ClassRegistrationContract.View mView;
    private Context mContext;
    private MainActivity mMainActivity;
    private MyApplication myApplication;

    private ClassRegistrationtListAdapter mConsentListAdapter;
    private ArrayList<Consent.ConsentListResultData> mConsentListResultDataArrayList = new ArrayList<>();

    /**
     * 생성자
     */
    ClassRegistrationPresenter(ClassRegistrationContract.View mView, Context mContext , Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        mMainActivity = (MainActivity) mActivity;
        myApplication = MyApplication.getInstance();
    }

    /**
     * ConsentList 가져오기
     */
    @Override
    public void getConsentList(ListView listView) {
        mMainActivity.showDialog();
        ParentAuth mParentAuth = MyApplication.getInstance().getParentAuth();
        Call<Consent> consentList = MyApplication.getRestAdapter(myApplication.getBASE_URL(), false).getConsent(
                Constants.SERVER_CALLBACK_KEY
                , mParentAuth.getSchool_id()
                , mParentAuth.getStudent_id()
                , mParentAuth.getStudent_no()
                , mParentAuth.getStudent_passwd()
        );
        consentList.enqueue(new Callback<Consent>() {

            @Override
            public void onResponse(@NonNull Call<Consent> call, @NonNull Response<Consent> response) {
                if (response.body() != null) {
                    if (!"0".equals(response.body().getData().get(0).getErr_code())) {
                        dialogForm(response.body().getData().get(0).getErr_msg());
                    } else {
                        if (response.body().getData().get(1) == null) {
                            mView.showNoSearchText();
                            mView.hideClassReg();

                            dialogForm("검색된 항목이 없습니다.");

                            if (mConsentListResultDataArrayList != null) {
                                mConsentListResultDataArrayList.clear();
                                if (mConsentListAdapter != null) {
                                    mConsentListAdapter.notifyDataSetChanged();
                                }
                            }
                        } else {
                            mView.hideNoSearchText();
                            mView.showClassReg();
                            mConsentListResultDataArrayList = new ArrayList<>();
                            for (int i = 1; i < response.body().getData().size(); i++) {
                                mConsentListResultDataArrayList.add(response.body().getData().get(i));
                            }
                            mConsentListAdapter = new ClassRegistrationtListAdapter(mMainActivity, mConsentListResultDataArrayList);
                            listView.setAdapter(mConsentListAdapter);
                        }
                    }
                    mMainActivity.hideDialog();
                } else {
                    dialogForm("response.body() is null");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Consent> call, @NonNull Throwable t) {
                mMainActivity.hideDialog();
                dialogForm(t.getMessage());
            }
        });
    }

    @Override
    public void clickClassRegItem(int position) {
        Intent intent = new Intent( mContext, ClassRegDetailActivity.class);
        intent.putExtra(Constants.KEY_CONSENT_NO, mConsentListResultDataArrayList.get(position).getLB_no());
        mView.showActivity(intent);
        mView.showAnimation();
    }

    @Override
    public void clickDialogPositiveButton(CommonDialog build) {
        mView.removeDialog(build);
    }

    @Override
    public void clickDialogNegativeButton(CommonDialog build) {

    }

    private void dialogForm(String content){
        final CommonDialog build = new CommonDialog(mMainActivity);
        build.setCancelable(true);
        mView.changeDialogTitle(build, "알림");
        mView.changeDialogMessage(build ,content);
        mView.addDialogPositiveButton(build);
        mView.showDialog(build);
    }

}
