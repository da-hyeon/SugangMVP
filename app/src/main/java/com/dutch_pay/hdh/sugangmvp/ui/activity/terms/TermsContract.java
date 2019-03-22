package com.dutch_pay.hdh.sugangmvp.ui.activity.terms;

import android.content.Intent;

public interface TermsContract {

    interface View {

        //show
        void showActivity(Intent intent);
        void showAnimation();
        void showToast(String content);

        //change
        void changeAllTOS(boolean state);
        void changeTOS(int index, boolean state);
        //remove
        void removeActivity();
    }

    interface Presenter{
        void clickAllcheckBox();
        void clickCheckBox(int index);
        void clickTermsConfirm();
    }

}
