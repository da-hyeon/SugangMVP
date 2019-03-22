package com.dutch_pay.hdh.sugangmvp.ui.activity.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.Toast;

import com.dutch_pay.hdh.sugangmvp.MyApplication;
import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.data.model.ParentAuth;
import com.dutch_pay.hdh.sugangmvp.databinding.ActivityMainBinding;
import com.dutch_pay.hdh.sugangmvp.ui.activity.BaseActivity;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;
import com.dutch_pay.hdh.sugangmvp.ui.fragment.notice.NoticeExpandableFragment;
import com.dutch_pay.hdh.sugangmvp.ui.fragment.registration.ClassRegistrationFragment;
import com.dutch_pay.hdh.sugangmvp.ui.fragment.satisfaction.ClassSatisfactionLevelFragment;
import com.dutch_pay.hdh.sugangmvp.ui.fragment.search.SearchClassRegistrationFragment;
import com.dutch_pay.hdh.sugangmvp.ui.fragment.survey.SurveyFragment;

import java.lang.reflect.Field;

public class MainActivity extends BaseActivity implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding mBinding;
    private MainContract.Presenter mPresenter;

    private MyApplication myApplication;

    private Fragment mTabFragment[];

    private Fragment mDetailFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setMainActivity(this);

        initData();

        //재인증 클릭
        mBinding.layoutMainBody.tvAuth.setOnClickListener(v ->
            mPresenter.clickAuthButton()
        );

    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        myApplication = MyApplication.getInstance();
        myApplication.setActivity(this);

        mTabFragment = new Fragment[]{
                new NoticeExpandableFragment(),
                new ClassRegistrationFragment(),
                new SearchClassRegistrationFragment(),
                new SurveyFragment(),
                new ClassSatisfactionLevelFragment()
        };

        mPresenter = new MainPresenter(this, mTabFragment, this , getSupportFragmentManager());
        mPresenter.disableShiftMode(mBinding.layoutMainBody.bnMenuView);
        //메인프래그먼트
        mPresenter.clickMenu(0);
        mBinding.layoutMainBody.bnMenuView.setOnNavigationItemSelectedListener(this);
    }

    /**
     * 메뉴 클릭
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNotice:
                mPresenter.clickMenu(0);
                break;
            case R.id.menuClassReg:
                mPresenter.clickMenu(1);
                break;
            case R.id.menuSearchClassReg:
                mPresenter.clickMenu(2);
                break;
            case R.id.menuSurvey:
                mPresenter.clickMenu(3);
                break;
            case R.id.menuSatisfactionLevel:
                mPresenter.clickMenu(4);
                break;
        }
        return true;
    }

    /**
     * 뒤로가기 클릭
     */
    @Override
    public void onBackPressed() {
        mPresenter.clickBackPressed();
    }

    /**
     * Fragment 보여주기
     */
    @Override
    public void showFragment(FragmentTransaction fragmentTransaction , int index ) {
        fragmentTransaction.show(mTabFragment[index]);
    }

    /**
     * Dialog 보여주기
     */
    @Override
    public void showDialog(CommonDialog build) {
        build.show();
    }

    /**
     * Activity 보여주기
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
     * Toast 보여주기
     */
    @Override
    public void showToast(String content) {
        Toast.makeText(this, content , Toast.LENGTH_SHORT).show();
    }

    /**
     * Fragment 추가하기
     */
    @Override
    public void addFragment(FragmentTransaction fragmentTransaction , int index ) {
        fragmentTransaction.add(R.id.contentContainer, mTabFragment[index], mTabFragment[index].getClass().getName());
    }

    /**
     * DialogPositiveButton 추가하기
     */
    @Override
    public void addDialogPositiveButton(CommonDialog build) {
        build.setPositiveButton("확인", (dialogInterface, i) -> {
            mPresenter.clickAuthDialogPositiveButton(build);
        });
    }

    /**
     * DialogNegativeButton 추가하기
     */
    @Override
    public void addDialogNegativeButton(CommonDialog build) {
        build.setNegativeButton("취소", (dialog, which) -> {
            mPresenter.clickAuthDialogNegativeButton(build);
        });
    }

    /**
     * Fragment 숨기기
     */
    @Override
    public void hideFragment(FragmentTransaction fragmentTransaction , int index ) {
        fragmentTransaction.hide(mTabFragment[index]);
    }

    /**
     * AppBarTitle 변경하기
     */
    @Override
    public void changeAppBarTitle(String title) {
        mBinding.layoutMainBody.tvTitle.setText(title);
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
     * Fragment 변경알리기
     */
    @Override
    public void commitFragment( FragmentTransaction fragmentTransaction) {
        fragmentTransaction.commit();
    }

    /**
     * Fragment 제거하기
     */
    @Override
    public void removeFragment(FragmentTransaction fragmentTransaction , Fragment fragment) {
        fragmentTransaction.remove(fragment);
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
