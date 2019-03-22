package com.dutch_pay.hdh.sugangmvp.ui.activity.entrance;

import android.annotation.TargetApi;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.databinding.ActivityEntranceBinding;
import com.dutch_pay.hdh.sugangmvp.ui.activity.BaseActivity;


public class EntranceActivity extends BaseActivity implements EntranceContract.View{


    private ActivityEntranceBinding mBinding;
    private EntranceContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_entrance);
        mBinding.setEntranceActivity(this);

        initData();

        //입장절차 시작
        mPresenter.entranceActivity();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new EntrancePresenter(this , this , this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       mPresenter.onRequestPermissionsResult(requestCode , grantResults);
    }

    /**
     * 로딩뷰 보이기
     */
    @Override
    public void showLoading() {
        mBinding.progress.setVisibility(View.VISIBLE);
    }

    /**
     * Toast 보이기
     */
    @Override
    public void showToast(String content) {
        Toast.makeText(this, content , Toast.LENGTH_SHORT).show();
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
     * 로딩뷰 숨기기
     */
    @Override
    public void hideLoading() {
        mBinding.progress.setVisibility(View.INVISIBLE);
    }

    /**
     * Activity 제거하기
     */
    @Override
    public void removeActivity() {
        finish();
    }

}
