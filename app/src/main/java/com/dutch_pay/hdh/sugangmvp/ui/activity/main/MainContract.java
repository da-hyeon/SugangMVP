package com.dutch_pay.hdh.sugangmvp.ui.activity.main;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

public interface MainContract {

    interface View{
        //show
        void showFragment(FragmentTransaction fragmentTransaction , int index);
        void showDialog(CommonDialog build);
        void showActivity(Intent intent);
        void showAnimation();
        void showToast(String content);

        //add
        void addFragment(FragmentTransaction fragmentTransaction , int index);
        void addDialogPositiveButton(CommonDialog build);
        void addDialogNegativeButton(CommonDialog build);

        //hide
        void hideFragment(FragmentTransaction fragmentTransaction , int index);

        //change
        void changeAppBarTitle(String title);
        void changeDialogTitle(CommonDialog build , String title);
        void changeDialogMessage(CommonDialog build , String content);

        //commit
        void commitFragment(FragmentTransaction fragmentTransaction);

        //remove
        void removeFragment(FragmentTransaction fragmentTransaction , Fragment fragment);
        void removeDialog(CommonDialog build);
        void removeActivity();
    }

    interface Presenter{
        void clickAuthButton();
        void clickAuthDialogPositiveButton(CommonDialog build);
        void clickAuthDialogNegativeButton(CommonDialog build);
        void clickMenu(int index);

        void disableShiftMode(BottomNavigationView view);

        void clickBackPressed();
    }

}
