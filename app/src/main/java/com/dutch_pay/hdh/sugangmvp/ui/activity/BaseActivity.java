package com.dutch_pay.hdh.sugangmvp.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Window;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.databinding.DialogLodingBinding;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Objects;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Admin on 2017-11-20.
 */

public class BaseActivity extends AppCompatActivity {

    private Dialog dialog;
    private AVLoadingIndicatorView mAvLoadingIndicatorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Dialog(BaseActivity.this, android.R.style.Theme_Dialog);
        dialog.setCancelable(false);
        DialogLodingBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_loding, null, false);

        mAvLoadingIndicatorView = mBinding.progress;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(mBinding.getRoot());
        dialog.setCanceledOnTouchOutside(false);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0, R.anim.slide_right_out);
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void showDialog() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void hideDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Deprecated
    public AVLoadingIndicatorView getAvLoadingIndicatorView() {
        return mAvLoadingIndicatorView;
    }
    @Deprecated
    public void setAvLoadingIndicatorView(AVLoadingIndicatorView mAvLoadingIndicatorView) {
        this.mAvLoadingIndicatorView = mAvLoadingIndicatorView;
    }
}
