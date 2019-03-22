package com.dutch_pay.hdh.sugangmvp.ui.activity.entrance;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;

import com.dutch_pay.hdh.sugangmvp.Constants;
import com.dutch_pay.hdh.sugangmvp.data.manager.RecentlyParentAuthManager;
import com.dutch_pay.hdh.sugangmvp.data.model.ParentAuth;
import com.dutch_pay.hdh.sugangmvp.ui.activity.auth.AuthActivity;
import com.dutch_pay.hdh.sugangmvp.ui.activity.main.MainActivity;
import com.dutch_pay.hdh.sugangmvp.util.PermissionUtil;

import java.util.ArrayList;

public class EntrancePresenter implements EntranceContract.Presenter{

    private EntranceContract.View mView;
    private Context mContext;
    private Activity mActivity;

    EntrancePresenter(EntranceContract.View mView, Context mContext, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    @Override
    public void entranceActivity() {
        mView.showLoading();
        new Handler().postDelayed(() -> {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                mView.hideLoading();

                if (Constants.IS_DEV) {
                    //startActivity
                    Intent intent = new Intent(mContext, AuthActivity.class);
                    moveActivityForm(intent);
                }  else {
                    /**
                     * 인증여부 판단후 진입
                     * */
                    if (loadRecentlyInfo()) {
                        Intent intent = new Intent(mContext, MainActivity.class);
                        moveActivityForm(intent);

                    } else {
                        Intent intent = new Intent(mContext, AuthActivity.class);
                        moveActivityForm(intent);
                    }
                }
            } else {
                if (checkPermission()) {
                    mView.hideLoading();
                    if (Constants.IS_DEV) {
                        Intent intent = new Intent(mContext, AuthActivity.class);
                        moveActivityForm(intent);
                    } else {
                        /**
                         * 인증여부 판단후 진입
                         * */
                        if (loadRecentlyInfo()) {
                            Intent intent = new Intent(mContext, MainActivity.class);
                            moveActivityForm(intent);
                        } else {
                            Intent intent = new Intent(mContext, AuthActivity.class);
                            moveActivityForm(intent);
                        }
                    }
                }
            }
        }, 3000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 99:
                if (PermissionUtil.verifyPermissions(grantResults)) {
                    /**
                     * 인증여부 판단후 진입
                     * */
                    if (loadRecentlyInfo()) {
                        Intent intent = new Intent(mContext, MainActivity.class);
                        moveActivityForm(intent);
                    } else {
                        Intent intent = new Intent(mContext, AuthActivity.class);
                        moveActivityForm(intent);
                    }
                } else {
                    mView.showToast("필요한 권한을 설정해 주세요.앱이 종료됩니다.");
                    mView.removeActivity();
                }
                break;
        }
    }

    /**
     * 인증여부 판단
     */
    private boolean loadRecentlyInfo() {
        RecentlyParentAuthManager recentlyMan = RecentlyParentAuthManager.getInstance();
        ArrayList<ParentAuth> arRecently = recentlyMan.getRecentlyList();
        return arRecently.size() > 0;
    }

    /**
     * 액티비티 이동 폼
     */
    private void moveActivityForm(Intent intent){
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mView.showActivity(intent);
        mView.removeActivity();
        mView.showAnimation();
    }

    /**
     * 권한설정
     */
    @TargetApi(Build.VERSION_CODES.M)
    private boolean checkPermission() {
        if (mActivity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || mActivity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ) {
            // Should we show an explanation?
            if (mActivity.shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
                // Explain to the user why we need to write the permission.
                //Toast.makeText(this, "Read/Write external storage", Toast.LENGTH_SHORT).show();
            }
            mActivity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    99);
            return false;
        } else {
            return true;
        }
    }
}
