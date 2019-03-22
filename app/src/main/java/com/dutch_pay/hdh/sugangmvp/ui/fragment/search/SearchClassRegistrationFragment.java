package com.dutch_pay.hdh.sugangmvp.ui.fragment.search;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.databinding.FragmentSearchClassRegistrationBinding;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

public class SearchClassRegistrationFragment extends Fragment implements SearchClassRegistrationContract.View{

    private FragmentSearchClassRegistrationBinding mBinding;
    private SearchClassRegistrationContract.Presenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_class_registration, container, false);
        initData();

        mBinding.lvSearchClassReg.setOnItemClickListener((adapterView, view, position, l) ->
                mPresenter.clickSearchClassReg(position)
        );

        mBinding.fbRefresh.setOnClickListener(v ->
                mPresenter.getMyConsentList(mBinding.lvSearchClassReg)
        );

        mBinding.fbTimeBox.setOnClickListener(v ->
                mPresenter.getTimeBox()
        );

        mBinding.fbCourseSchedule.setOnClickListener(v ->
                mPresenter.getSdalView()
        );

        return mBinding.getRoot();
    }
    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new SearchClassRegistrationPresenter(this , getContext() , getActivity());
    }

    /**
     * SearchClassReg 보이기
     */
    @Override
    public void showSearchClassReg() {
        mBinding.lvSearchClassReg.setVisibility(View.VISIBLE);
    }

    /**
     * NoSearchText 보이기
     */
    @Override
    public void showNoSearchText() {
        mBinding.tvNoSearchText.setVisibility(View.VISIBLE);
    }

    /**
     * Dialog 보이기
     */
    @Override
    public void showDialog(CommonDialog build) {
        build.show();
    }
    /**
     * Activity 보이기
     */
    @Override
    public void showActivity(Intent intent) {
        getActivity().startActivity(intent);
    }

    /**
     * Animation 보이기
     */
    @Override
    public void showAnimation() {
        getActivity().overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }
    /**
     * DialogPositiveButton 클릭
     */
    @Override
    public void addDialogPositiveButton(CommonDialog build) {
        build.setPositiveButton("확인", (dialogInterface, i) ->
                mPresenter.clickDialogPositiveButton(build)
        );
    }

    /**
     * DialogNegativeButton 클릭
     */
    @Override
    public void addDialogNegativeButton(CommonDialog build) {
        build.setPositiveButton("취소", (dialogInterface, i) ->
                mPresenter.clickDialogNegativeButton(build)
        );
    }

    /**
     * DialogTitle 변경
     */
    @Override
    public void changeDialogTitle(CommonDialog build, String title) {
        build.setTitle(title);
    }

    /**
     * DialogMessage 변경
     */
    @Override
    public void changeDialogMessage(CommonDialog build, String content) {
        build.setMessage(content);
    }

    /**
     * SearchClassReg 숨기기
     */
    @Override
    public void hideSearchClassReg() {
        mBinding.lvSearchClassReg.setVisibility(View.GONE);
    }

    /**
     * SearchText 숨기기
     */
    @Override
    public void hideNoSearchText() {
        mBinding.lvSearchClassReg.setVisibility(View.GONE);
    }

    /**
     * Dialog 제거
     */
    @Override
    public void removeDialog(CommonDialog build) {
        build.dismiss();
    }

    /**
     * Activity 제거
     */
    @Override
    public void removeActivity() {
        getActivity().finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getMyConsentList(mBinding.lvSearchClassReg);
    }
}
