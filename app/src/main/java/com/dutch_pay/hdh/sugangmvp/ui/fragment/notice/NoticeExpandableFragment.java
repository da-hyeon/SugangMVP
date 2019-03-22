package com.dutch_pay.hdh.sugangmvp.ui.fragment.notice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.databinding.FragmentNoticeExpandableBinding;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

public class NoticeExpandableFragment extends Fragment implements NoticeExpandableContract.View{

    private FragmentNoticeExpandableBinding mBinding;
    private NoticeExpandableContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notice_expandable, container, false);

        initData();

        mBinding.tvSearchField.setOnClickListener(v ->
                mPresenter.clickSearchField()
        );

        mBinding.fbRefresh.setOnClickListener(v ->
                mPresenter.getBoardListData("0", mBinding.etSearchStr.getText().toString() , mBinding.elNotice)
        );

        mBinding.fbSearch.setOnClickListener(v ->
                mPresenter.getBoardListData("0", mBinding.etSearchStr.getText().toString() , mBinding.elNotice)
        );

        mBinding.elNotice.setOnGroupClickListener((expandableListView, view, groupPosition, l) -> {
            mPresenter.clickNoticeGroup(groupPosition);
            return true;
        });

        mBinding.elNotice.setOnChildClickListener((expandableListView, view, groupPosition, childPosition, l) -> {
            mPresenter.clickNoticeChild();
            return true;
        });

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new NoticeExpandablePresenter(this , getContext() );
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getBoardListData("0", mBinding.etSearchStr.getText().toString() , mBinding.elNotice);
    }

    /**
     * Notice 보이기
     */
    @Override
    public void showNotice() {
        mBinding.elNotice.setVisibility(View.VISIBLE);
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
     * DialogPositiveButton 추가하기
     */
    @Override
    public void addDialogPositiveButton(CommonDialog build) {
        build.setPositiveButton("확인", (dialogInterface, i) ->
                mPresenter.clickDialogPositiveButton(build)
        );
    }

    /**
     * DialogNegativeButton 추가하기
     */
    @Override
    public void addDialogNegativeButton(CommonDialog build) {
    }

    /**
     * DialogTitle 변경하기
     */
    @Override
    public void changeDialogTitle(CommonDialog build, String title) {
        build.setTitle(title);
    }

    /**
     * DialogMessage 변경하기
     */
    @Override
    public void changeDialogMessage(CommonDialog build, String content) {
        build.setMessage(content);
    }

    /**
     * SearchField 변경하기
     */
    @Override
    public void changeSearchField(String content) {
        mBinding.tvSearchField.setText(content);
    }

    /**
     * Notice 숨기기
     */
    @Override
    public void hideNotice() {
        mBinding.elNotice.setVisibility(View.GONE);
    }

    /**
     * NoSearchText 숨기기
     */
    @Override
    public void hideNoSearchText() {
        mBinding.tvNoSearchText.setVisibility(View.GONE);
    }

    /**
     * Dialog 제거하기
     */
    @Override
    public void removeDialog(CommonDialog build) {
        build.dismiss();
    }

    /**
     * Activity 제거하기
     */
    @Override
    public void removeActivity() {
        getActivity().finish();
    }
}
