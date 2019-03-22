package com.dutch_pay.hdh.sugangmvp.ui.fragment.notice;

import android.content.Intent;
import android.widget.ExpandableListView;

import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

public interface NoticeExpandableContract {

    interface View{
        //show
        void showNotice();
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
        void changeSearchField(String content);

        //hide
        void hideNotice();
        void hideNoSearchText();

        //remove
        void removeDialog(CommonDialog build);
        void removeActivity();
    }

    interface Presenter{
        void clickSearchField();
        void clickNoticeGroup(int groupPosition);
        void clickNoticeChild();

        void getBoardListData(String row, String content , ExpandableListView listView);

        void clickDialogPositiveButton(CommonDialog build);
        void clickDialogNegativeButton(CommonDialog build);
    }

}
