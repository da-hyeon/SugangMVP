package com.dutch_pay.hdh.sugangmvp.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dutch_pay.hdh.sugangmvp.R;


public class SearchFieldTypeView extends LinearLayout {

    private Context mContext;
    private LinearLayout llSearchFieldType;

    private String mSubjectType;
    private int mCodeForSubjectType;
    private OnReceiveMessageListener mListener;

    public SearchFieldTypeView(Context context, OnReceiveMessageListener listener) {
        super(context);

        this.mContext = context;
        this.mListener = listener;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_search_field_type, this, true);
        init();
    }

    public void SendSearchFieldTypeViewDefault() {
        mSubjectType = "제목";
        mCodeForSubjectType = 1;
        sendMessage();
    }



    private void init() {
        llSearchFieldType = (LinearLayout) findViewById(R.id.llSearchFieldType);
        for (int i = 0; i < llSearchFieldType.getChildCount(); i++) {
            View childView = llSearchFieldType.getChildAt(i);

            if (childView instanceof TextView) {
                TextView textView = ((TextView) childView);
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSubjectType = ((TextView)v).getText().toString().trim();
                        mCodeForSubjectType =  Integer.parseInt(v.getTag().toString());
                        sendMessage();
                    }
                });
            }
        }
    }

    private void sendMessage() {
        mListener.onReceive(mSubjectType, mCodeForSubjectType);
    }

    public interface OnReceiveMessageListener {
        void onReceive(String message, int code);
    }
}
