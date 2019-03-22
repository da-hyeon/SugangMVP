package com.dutch_pay.hdh.sugangmvp.ui.fragment.search;

import android.content.Intent;
import android.widget.ListView;

import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

public interface SearchClassRegistrationContract {

    interface View{
        //show
        void showSearchClassReg();
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
        void hideSearchClassReg();
        void hideNoSearchText();

        //remove
        void removeDialog(CommonDialog build);
        void removeActivity();
    }
    interface Presenter{
        void getMyConsentList(ListView listView);
        void getTimeBox();
        void getSdalView();

        void clickSearchClassReg(int position);
        void clickDialogPositiveButton(CommonDialog build);
        void clickDialogNegativeButton(CommonDialog build);
    }

}
