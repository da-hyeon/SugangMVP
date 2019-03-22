package com.dutch_pay.hdh.sugangmvp.ui.activity.classRegDetail;

import com.dutch_pay.hdh.sugangmvp.data.model.ConsentDetail;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;

public interface ClassRegDetailContract {
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
        void changeAllText(ConsentDetail.ConsentDetailResultData data);

        //hide
        void hideParentDialog();
        void hideClassConfirm();

        //remove
        void removeDialog(CommonDialog build);

    }
    interface Presenter{
        void getConsentDetail();
        void setOrderSubmit();

        void clickBack();
        void clickClassWeek();
        void clickAuthDialogPositiveButton(CommonDialog build  , boolean isBack);
        void clickAuthDialogNegativeButton(CommonDialog build);
    }
}
