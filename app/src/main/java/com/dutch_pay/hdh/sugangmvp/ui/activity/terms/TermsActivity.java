package com.dutch_pay.hdh.sugangmvp.ui.activity.terms;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.databinding.ActivityTermsBinding;
import com.dutch_pay.hdh.sugangmvp.ui.activity.BaseActivity;

import java.util.ArrayList;

public class TermsActivity extends BaseActivity implements TermsContract.View {

    private ActivityTermsBinding mBinding;
    private TermsContract.Presenter mPresenter;

    private ArrayList<CheckBox> checkBoxArrayList = new ArrayList<>();
    private ArrayList<TextView> tvArrayList = new ArrayList<>();

    private ColorStateList oldColors;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_terms);
        mBinding.setTermsActivity(this);

        initData();

        //Liner클릭
        mBinding.checkLiner.setOnClickListener(v ->
                mPresenter.clickAllcheckBox()
        );

        //전체동의 클릭
        mBinding.allcheckbox.setOnClickListener(v ->
                mPresenter.clickAllcheckBox()
        );

        //약관동의 클릭
        for (int i = 0; i < checkBoxArrayList.size(); i++) {
            int finalI = i;
            checkBoxArrayList.get(i).setOnClickListener(v ->
                    mPresenter.clickCheckBox(finalI)
            );
        }

        //동의하고 시작하기 클릭
        mBinding.tvTermsConfirm.setOnClickListener(v ->
                mPresenter.clickTermsConfirm()
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        oldColors = mBinding.termsTitle2.getTextColors();
        mBinding.termsTitle2.setText(Html.fromHtml("<u>" + mBinding.termsTitle2.getText().toString() + "</u>"));
        mBinding.termsTitle4.setText(Html.fromHtml("<u>" + mBinding.termsTitle4.getText().toString() + "</u>"));

        checkBoxArrayList.add(mBinding.checkbox2);
        checkBoxArrayList.add(mBinding.checkbox4);

        tvArrayList.add(mBinding.termsTitle2);
        tvArrayList.add(mBinding.termsTitle4);

        mPresenter = new TermsPresenter(this, this);
    }

    @Override
    public void showActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void showAnimation() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeAllTOS(boolean state) {
        mBinding.allcheckbox.setChecked(state);
        if (state) {
            mBinding.allTitle1.setTextColor(Color.parseColor("#3b3b45"));
        } else {
            mBinding.allTitle1.setTextColor(oldColors);
        }
    }

    @Override
    public void changeTOS(int index, boolean state) {
        checkBoxArrayList.get(index).setChecked(state);
        if (state) {
            tvArrayList.get(index).setTextColor(Color.parseColor("#3b3b45"));
        } else {
            tvArrayList.get(index).setTextColor(oldColors);
        }
    }

    @Override
    public void removeActivity() {
        finish();
    }
}
