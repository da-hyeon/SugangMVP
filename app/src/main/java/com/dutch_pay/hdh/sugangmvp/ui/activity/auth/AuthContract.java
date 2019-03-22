package com.dutch_pay.hdh.sugangmvp.ui.activity.auth;

import android.content.Intent;
import android.widget.EditText;

import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

public interface AuthContract {

    interface View{
        //show
        void showToast(String content);
        void showDialog(CommonDialog build);
        void showActivity(Intent intent);
        void showAnimation();

        //add
        void addDialogPositiveButton(CommonDialog build);
        void addDialogNegativeButton(CommonDialog build);

        //chagne
        void changeDialogTitle(CommonDialog build , String title);
        void changeDialogMessage(CommonDialog build , String content);

        //remove
        void removeDialog(CommonDialog build);
        void removeActivity();
    }
    interface Presenter{
        void clickConfirm(EditText etSchoolId , EditText etMemberId, EditText etPassword);
        void clickAuthDialogPositiveButton(CommonDialog build);
        void clickAuthDialogNegativeButton(CommonDialog build);
    }
}
