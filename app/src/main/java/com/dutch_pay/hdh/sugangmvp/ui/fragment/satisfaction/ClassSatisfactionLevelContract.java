package com.dutch_pay.hdh.sugangmvp.ui.fragment.satisfaction;

import android.widget.ListView;

import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

public interface ClassSatisfactionLevelContract {

    interface View{
        //show
        void showClassReg();
        void showNoSearchText();
        void showDialog(CommonDialog build);

        //add
        void addDialogPositiveButton(CommonDialog build);
        void addDialogNegativeButton(CommonDialog build);

        //change
        void changeDialogTitle(CommonDialog build , String title);
        void changeDialogMessage(CommonDialog build , String content);
        void changeTitle(String title);
        void changeDate(String date);
        void changeContent(String content);

        //hide
        void hideClassReg();
        void hideNoSearchText();

        //remove
        void removeDialog(CommonDialog build);
    }

    interface Presenter{
        void getPollStudentList(ListView listView);

        void clickDialogPositiveButton(CommonDialog build);
        void clickDialogNegativeButton(CommonDialog build);
    }

}
