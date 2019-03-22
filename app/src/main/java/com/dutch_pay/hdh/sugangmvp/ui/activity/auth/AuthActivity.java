package com.dutch_pay.hdh.sugangmvp.ui.activity.auth;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.databinding.ActivityAuthBinding;
import com.dutch_pay.hdh.sugangmvp.ui.activity.BaseActivity;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;


public class AuthActivity extends BaseActivity implements AuthContract.View {


    private ActivityAuthBinding mBinding;
    private AuthContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        mBinding.setAuthActivity(this);

        initData();

        mBinding.tvConfirm.setOnClickListener(v ->
                mPresenter.clickConfirm(mBinding.etSchoolId, mBinding.etMemberId, mBinding.etPassword)
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new AuthPresenter(this, this);
    }

    /**
     * Toast 보이기
     */
    @Override
    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }


    /**
     * Dialog 보여주기
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
        startActivity(intent);
    }

    /**
     * Animation 보여주기
     */
    @Override
    public void showAnimation() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    /**
     * DialogPositiveButton 추가하기
     */
    @Override
    public void addDialogPositiveButton(CommonDialog build) {
        build.setPositiveButton("확인", (dialogInterface, i) ->
            mPresenter.clickAuthDialogPositiveButton(build)
        );
    }

    /**
     * DialogNegativeButton 추가하기
     */
    @Override
    public void addDialogNegativeButton(CommonDialog build) {
        build.setNegativeButton("취소", (dialog, which) ->
            mPresenter.clickAuthDialogNegativeButton(build)
        );
    }

    /**
     * DialogTitle 변경하기
     */
    @Override
    public void changeDialogTitle(CommonDialog build , String title) {
        build.setTitle(title);
    }

    /**
     * DialogMessage 변경하기
     */
    @Override
    public void changeDialogMessage(CommonDialog build , String content) {
        build.setMessage(content);
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
        finish();
    }
}
