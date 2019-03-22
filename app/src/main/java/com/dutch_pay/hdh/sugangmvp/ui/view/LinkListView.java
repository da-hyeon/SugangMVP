package com.dutch_pay.hdh.sugangmvp.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dutch_pay.hdh.sugangmvp.R;

public class LinkListView extends LinearLayout implements View.OnClickListener{

    private Context mContext;
    private String mLinkUrl;
    private TextView tvNoticeLinkName;
    private LinearLayout llNoticeLinkPage;


    public LinkListView(Activity context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public LinkListView(Activity context, LayoutParams params, String linkUrl) {
        super(context);
        // TODO Auto-generated constructor stub
        mContext = context;
        mLinkUrl = linkUrl;
        init(context, params);
    }

    private void init(Context context, LayoutParams params) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.view_list_link, null);

        if (params == null) {
            params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        layout.setLayoutParams(params);

        tvNoticeLinkName = (TextView) layout.findViewById(R.id.tvNoticeLinkName);
        llNoticeLinkPage = (LinearLayout) layout.findViewById(R.id.llNoticeLinkPage);

        tvNoticeLinkName.setText(mLinkUrl);
        llNoticeLinkPage.setOnClickListener(this);

        this.addView(layout);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llNoticeLinkPage:
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mLinkUrl)));
                break;
        }
    }
}
