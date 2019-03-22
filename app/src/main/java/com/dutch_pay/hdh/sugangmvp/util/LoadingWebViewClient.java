package com.dutch_pay.hdh.sugangmvp.util;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dutch_pay.hdh.sugangmvp.ui.activity.BaseActivity;


public class LoadingWebViewClient extends WebViewClient {

    private BaseActivity mBaseActivity;


    public LoadingWebViewClient(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
        mBaseActivity.showDialog();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        mBaseActivity.hideDialog();
    }
}
