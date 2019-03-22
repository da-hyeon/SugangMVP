package com.dutch_pay.hdh.sugangmvp.ui.fragment.satisfaction;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.dutch_pay.hdh.sugangmvp.Constants;
import com.dutch_pay.hdh.sugangmvp.MyApplication;
import com.dutch_pay.hdh.sugangmvp.data.model.ParentAuth;
import com.dutch_pay.hdh.sugangmvp.data.model.PollStudent;
import com.dutch_pay.hdh.sugangmvp.ui.activity.main.MainActivity;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassSatisfactionLevelPresenter implements ClassSatisfactionLevelContract.Presenter {

    private ClassSatisfactionLevelContract.View mView;
    private Context mContext;

    private MainActivity mMainActivity;
    private MyApplication myApplication;
    private ClassSatisfactionLevelListAdapter mPollStudentListAdapter;
    private ArrayList<PollStudent.PollStudentResultData> mConsentListResultDataArrayList = new ArrayList<>();

    /**
     * 생성자
     */
    ClassSatisfactionLevelPresenter(ClassSatisfactionLevelContract.View mView, Context mContext , Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;

        mMainActivity = (MainActivity) mActivity;
        myApplication = MyApplication.getInstance();
    }

    /**
     * StudentList 받아오기
     */
    @Override
    public void getPollStudentList(ListView listView) {
        mMainActivity.showDialog();
        ParentAuth mParentAuth = MyApplication.getInstance().getParentAuth();
        Call<PollStudent> consentList = MyApplication.getRestAdapter(myApplication.getBASE_URL(), false).getPollStudent(
                Constants.SERVER_CALLBACK_KEY
                , mParentAuth.getSchool_id()
                , mParentAuth.getStudent_id()
                , mParentAuth.getStudent_no()
                , mParentAuth.getStudent_passwd()
        );
        consentList.enqueue(new Callback<PollStudent>() {

            @Override
            public void onResponse(@NonNull Call<PollStudent> call, @NonNull Response<PollStudent> response) {
                if(response.body() != null) {
                    if (!"0".equals(response.body().getData().get(0).getErr_code())) {
                        dialogForm(response.body().getData().get(0).getErr_msg());
                    } else {
                        if (response.body().getData().get(1) == null) {
                            mView.showNoSearchText();
                            mView.hideClassReg();
                            dialogForm("검색된 항목이 없습니다.");
                            if (mConsentListResultDataArrayList != null) {
                                mConsentListResultDataArrayList.clear();
                                if (mPollStudentListAdapter != null) {
                                    mPollStudentListAdapter.notifyDataSetChanged();
                                }
                            }
                        } else {
                            mView.hideNoSearchText();
                            mView.showClassReg();

                            mConsentListResultDataArrayList = new ArrayList<>();

                            mView.changeTitle(response.body().getData().get(1).getTitle());
                            mView.changeDate(response.body().getData().get(1).getSS_date().replace(" → ", "\n"));
                            mView.changeContent(response.body().getData().get(1).getContent());

                            for (int i = 2; i < response.body().getData().size(); i++) {
                                mConsentListResultDataArrayList.add(response.body().getData().get(i));
                            }
                            mPollStudentListAdapter = new ClassSatisfactionLevelListAdapter(mMainActivity, mConsentListResultDataArrayList);
                            listView.setAdapter(mPollStudentListAdapter);
                        }
                    }
                } else {
                    dialogForm("response.body() is null");
                }
                mMainActivity.hideDialog();
            }

            @Override
            public void onFailure(Call<PollStudent> call, Throwable t) {
                mMainActivity.hideDialog();
                dialogForm(t.getMessage());
            }
        });
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

    private void dialogForm(String content){
        final CommonDialog build = new CommonDialog(mMainActivity);
        build.setCancelable(true);
        mView.changeDialogTitle(build, "알림");
        mView.changeDialogMessage(build ,content);
        mView.addDialogPositiveButton(build);
        mView.showDialog(build);
    }

}
