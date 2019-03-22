package com.dutch_pay.hdh.sugangmvp.ui.activity.myClassRegDetail;

import com.dutch_pay.hdh.sugangmvp.data.model.MyConsentDetail;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

public interface MyClassRegDetailContract {
    interface View{
        //show
        void showParentDialog();
        void showBeforeActivity();
        void showDialog(CommonDialog build);
        void showClassConfirm();

        //add
        void addDialogPositiveButton(CommonDialog build , boolean isBack );
        void addDialogNegativeButton(CommonDialog build);

        //chagne
        void changeDialogTitle(CommonDialog build , String title);
        void changeDialogMessage(CommonDialog build , String content);
        void changeAllText(MyConsentDetail.MyConsentDetailResultData data);

        //hide
        void hideClassConfirm();
        void hideParentDialog();

        //remove
        void removeDialog(CommonDialog build);

    }
    interface Presenter{
        void getMyConsentDetail();
        void setOrderDelete();

        void clickBack();
        void clickClassWeek();
        void clickAuthDialogPositiveButton(CommonDialog build  , boolean isBack);
        void clickAuthDialogNegativeButton(CommonDialog build);
    }
}
