package com.dutch_pay.hdh.sugangmvp.ui.fragment.satisfaction;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.databinding.FragmentClassSatisfactionLevelBinding;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

public class ClassSatisfactionLevelFragment extends Fragment implements ClassSatisfactionLevelContract.View {

    private FragmentClassSatisfactionLevelBinding mBinding;
    private ClassSatisfactionLevelContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_class_satisfaction_level, container, false);

        initData();

        mBinding.fbRefresh.setOnClickListener(v ->
               mPresenter.getPollStudentList(mBinding.lvClassReg)
        );

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new ClassSatisfactionLevelPresenter(this , getContext() , getActivity());
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
     * Title 변경
     */
    @Override
    public void changeTitle(String title) {
        mBinding.tvTitle.setText(title);
    }

    /**
     * date 변경
     */
    @Override
    public void changeDate(String date) {
        mBinding.tvSSDate.setText(date);
    }

    /**
     * Content 변경
     */
    @Override
    public void changeContent(String content) {
        mBinding.tvContent.setText(content);
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

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getPollStudentList(mBinding.lvClassReg);
    }
}
