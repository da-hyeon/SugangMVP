package com.dutch_pay.hdh.sugangmvp.ui.view;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.dutch_pay.hdh.sugangmvp.R;


public class HtmlView extends LinearLayout {

    private Context mContext;
    private WebView wvClassWeek;
    private String mHtml;

    public HtmlView(Context context, String html) {
        super(context);
        this.mContext = context;
        this.mHtml = html;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_html, this, true);
        init();
    }


    private void init() {
        wvClassWeek = (WebView) findViewById(R.id.wvClassWeek);
        wvClassWeek.getSettings().setDefaultTextEncodingName("UTF-8");
        wvClassWeek.getSettings().setBuiltInZoomControls(true);
        wvClassWeek.getSettings().setSupportZoom(true);
        wvClassWeek.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            wvClassWeek.loadData(mHtml.replace("</style>", "body{margin:0;}</style>"), "text/html; charset=UTF-8", null);
        } else {
            wvClassWeek.loadData(mHtml.replace("</style>", "body{margin:0;}</style>"), "text/html", "UTF-8");
        }
    }
}
