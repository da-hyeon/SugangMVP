package com.dutch_pay.hdh.sugangmvp.ui.fragment.registration;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.databinding.FragmentClassRegistrationBinding;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

public class ClassRegistrationFragment extends Fragment implements ClassRegistrationContract.View{

    private FragmentClassRegistrationBinding mBinding;
    private ClassRegistrationContract.Presenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_class_registration, container, false);

        initData();

        mBinding.lvClassReg.setOnItemClickListener((adapterView, view, position, l) -> {
            mPresenter.clickClassRegItem(position);
        });

        mBinding.fbRefresh.setOnClickListener(v->
                mPresenter.getConsentList(mBinding.lvClassReg)
        );

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new ClassRegistrationPresenter(this ,getContext() , getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getConsentList(mBinding.lvClassReg);
    }

    /**
     * ClassReg 보이기
     */
    @Override
    public void showClassReg() {
        mBinding.lvClassReg.setVisibility(View.VISIBLE);
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
     * ClassReg 숨기기
     */
    @Override
    public void hideClassReg() {
        mBinding.lvClassReg.setVisibility(View.GONE);
    }

    /**
     * SearchText 숨기기
     */
    @Override
    public void hideNoSearchText() {
        mBinding.tvNoSearchText.setVisibility(View.GONE);
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
}
