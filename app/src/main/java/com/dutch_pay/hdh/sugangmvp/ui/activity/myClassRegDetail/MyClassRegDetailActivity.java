package com.dutch_pay.hdh.sugangmvp.ui.activity.myClassRegDetail;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.data.model.MyConsentDetail;
import com.dutch_pay.hdh.sugangmvp.databinding.ActivityMyClassRegDetailBinding;
import com.dutch_pay.hdh.sugangmvp.ui.activity.BaseActivity;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

public class MyClassRegDetailActivity extends BaseActivity implements MyClassRegDetailContract.View {

    private ActivityMyClassRegDetailBinding mBinding;
    private MyClassRegDetailContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_class_reg_detail);
        mBinding.setMyClassRegDetail(this);

        initData();

        mBinding.ivBack.setOnClickListener(v ->
                mPresenter.clickBack()
        );

        mBinding.fbClassWeek.setOnClickListener(v ->
                mPresenter.clickClassWeek()
        );

        mBinding.fbClassConfirm.setOnClickListener(v ->
                mPresenter.setOrderDelete()
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new MyClassRegDetailPresenter(this, this, this);
        mPresenter.getMyConsentDetail();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        mPresenter.clickBack();
    }

    @Override
    public void showParentDialog() {
        showDialog();
    }

    @Override
    public void showBeforeActivity() {
        super.onBackPressed();
    }

    @Override
    public void showDialog(CommonDialog build) {
        build.show();
    }

    @Override
    public void showClassConfirm() {
        mBinding.fbClassConfirm.setVisibility(View.VISIBLE);
    }

    @Override
    public void addDialogPositiveButton(CommonDialog build  , boolean isBack) {
        build.setPositiveButton("확인", (dialogInterface, i) ->
                mPresenter.clickAuthDialogPositiveButton(build , isBack)
        );
    }

    @Override
    public void addDialogNegativeButton(CommonDialog build) {

    }

    @Override
    public void changeDialogTitle(CommonDialog build, String title) {
        build.setTitle(title);
    }

    @Override
    public void changeDialogMessage(CommonDialog build, String content) {
        build.setMessage(content);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void changeAllText(MyConsentDetail.MyConsentDetailResultData data) {
        mBinding.tvLBSubject.setText(data.getLB_subject());
        mBinding.tvLBRoom.setText(data.getLB_room());
        mBinding.tvSLBGrade.setText(data.getS_LB_grade());
        mBinding.tvLBMax.setText(data.getLB_max() + " 명");
        mBinding.tvSLBArea.setText(data.getS_LB_area());
        mBinding.tvLBMin.setText(data.getLB_min() + " 명");
        mBinding.tvLBTold.setText(data.getLB_told());
        mBinding.tvLBLevel.setText(data.getLB_level());
        mBinding.tvLBAddcost.setText(data.getLB_addcost());
        mBinding.tvRXName.setText(data.getRX_name());
        mBinding.tvSLBTime.setText(data.getS_LB_time() + " 교시");
        mBinding.tvLBContent.setText(data.getLB_content());
        mBinding.tvLBBook.setText(data.getLB_book());
    }

    @Override
    public void hideClassConfirm() {
        mBinding.fbClassConfirm.setVisibility(View.GONE);
    }

    @Override
    public void hideParentDialog() {
        hideDialog();
    }

    @Override
    public void removeDialog(CommonDialog build) {
        build.dismiss();
    }
}
