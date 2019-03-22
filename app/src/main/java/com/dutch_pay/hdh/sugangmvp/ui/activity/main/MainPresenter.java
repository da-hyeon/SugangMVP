package com.dutch_pay.hdh.sugangmvp.ui.activity.main;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dutch_pay.hdh.sugangmvp.Constants;
import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.data.manager.RecentlyParentAuthManager;
import com.dutch_pay.hdh.sugangmvp.ui.activity.entrance.EntranceActivity;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;
import com.dutch_pay.hdh.sugangmvp.util.SimpleStore;

import java.lang.reflect.Field;

public class MainPresenter implements MainContract.Presenter{

    private MainContract.View mView;
    private Fragment mTabFragment[];
    private Context mContext;
    private FragmentManager mFragmentManager;
    private long mLastTime;

    /**
     * 생성자
     */
    MainPresenter(MainContract.View mView, Fragment mTabFragment[], Context mContext, FragmentManager fragmentManager) {
        this.mView = mView;
        this.mTabFragment = mTabFragment;
        this.mContext = mContext;
        mFragmentManager = fragmentManager;
    }

    /**
     * 재인증버튼 클릭 이벤트 처리
     */
    @Override
    public void clickAuthButton() {
        final CommonDialog build = new CommonDialog(mContext);
        build.setCancelable(false);
        mView.changeDialogTitle(build , "알림");
        mView.changeDialogMessage(build , "현재 인증정보가 삭제되고 \n재시작됩니다 진행하시겠습니까?");
        mView.addDialogPositiveButton(build);
        mView.addDialogNegativeButton(build);
        mView.showDialog(build);
    }

    /**
     * Dialog 확인버튼 클릭 이벤트 처리
     */
    @Override
    public void clickAuthDialogPositiveButton(CommonDialog build) {
        RecentlyParentAuthManager manager = RecentlyParentAuthManager.getInstance();
        manager.clearRecentlyInfo();
        SimpleStore.remove(Constants.KEY_TERMS);

        Intent intent = new Intent(mContext, EntranceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mView.showActivity(intent);
        mView.removeActivity();
        mView.showAnimation();

        mView.removeDialog(build);
    }

    /**
     * Dialog 취소버튼 클릭 이벤트 처리
     */
    @Override
    public void clickAuthDialogNegativeButton(CommonDialog build) {
        mView.removeDialog(build);
    }

    /**
     * 메뉴 클릭 이벤트 처리
     */
    @Override
    public void clickMenu(int index) {
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.slide_left_in, 0, 0, R.anim.slide_right_out);


        if (mTabFragment[index].isAdded()) {
            mView.showFragment(mFragmentTransaction, index);
        } else {
            mView.addFragment(mFragmentTransaction,index);
        }

        for( int i = 0; i < mTabFragment.length; i++){
            if(!mTabFragment[i].equals(mTabFragment[index]) && mTabFragment[i].isAdded()){
                mView.hideFragment(mFragmentTransaction, i);
            }
        }

        mView.commitFragment(mFragmentTransaction);

        switch (index){
            case 0:
                mView.changeAppBarTitle("공지사항");
                break;
            case 1:
                mView.changeAppBarTitle("수강신청");
                break;
            case 2:
                mView.changeAppBarTitle("나의신청내역");
                break;
            case 3:
                mView.changeAppBarTitle("설문조사");
                break;
            case 4:
                mView.changeAppBarTitle("수업만족도조사");
                break;
        }
    }

    /**
     * BottomNavigationMenuView 초기화
     */
    @Override
    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            //Timber.e(e, "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            //Timber.e(e, "Unable to change value of shift mode");
        }
    }

    /**
     * 뒤로가기 클릭 이벤트 처리
     */
    @Override
    public void clickBackPressed() {
        if (mLastTime + 2000 < System.currentTimeMillis() || mLastTime == 0) {
            mView.showToast("한 번 더 누르면 종료됩니다.");
            mLastTime = System.currentTimeMillis();
        } else {
            mView.removeActivity();
        }
    }
}
