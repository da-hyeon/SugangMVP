package com.dutch_pay.hdh.sugangmvp.ui.activity.entrance;

import android.content.Intent;

public interface EntranceContract {
    interface View{

        //show
        void showLoading();
        void showToast(String content);
        void showActivity(Intent intent);
        void showAnimation();

        //hide
        void hideLoading();

        //remove
        void removeActivity();
    }
    interface Presenter{
        void entranceActivity();
        void onRequestPermissionsResult(int requestCode, int[] grantResults);
    }
}
