package com.dutch_pay.hdh.sugangmvp.ui.fragment.registration;

import android.content.Intent;
import android.widget.ListView;

import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

public interface ClassRegistrationContract {

    interface View{
        //show
        void showClassReg();
        void showNoSearchText();
        void showDialog(CommonDialog build);
        void showActivity(Intent intent);
        void showAnimation();

        //add
        void addDialogPositiveButton(CommonDialog build);
        void addDialogNegativeButton(CommonDialog build);

        //change
        void changeDialogTitle(CommonDialog build , String title);
        void changeDialogMessage(CommonDialog build , String content);

        //hide
        void hideClassReg();
        void hideNoSearchText();

        //remove
        void removeDialog(CommonDialog build);
        void removeActivity();
    }

    interface Presenter{
        void getConsentList(ListView listView);

        void clickClassRegItem(int position);
        void clickDialogPositiveButton(CommonDialog build);
        void clickDialogNegativeButton(CommonDialog build);
    }

}
