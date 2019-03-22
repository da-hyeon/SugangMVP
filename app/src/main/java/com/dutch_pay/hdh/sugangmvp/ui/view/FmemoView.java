package com.dutch_pay.hdh.sugangmvp.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dutch_pay.hdh.sugangmvp.R;


public class FmemoView extends LinearLayout {

    private Context mContext;
    private Button btConfirm;
    private TextView tvF_memo;
    private EditText etMemo;
    private OnReceiveMessageListener mListener;
    private String mMemo;
    private String mWriteMemo;
    private String mFmemo;

    public FmemoView(Context context, String writeMemo, String fMemo,OnReceiveMessageListener listener) {
        super(context);
        this.mContext = context;
        this.mListener = listener;
        this.mWriteMemo = writeMemo;
        this.mFmemo = fMemo;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_fmemo, this, true);
        init();
    }


    private void init() {
        btConfirm = (Button) findViewById(R.id.btConfirm);
        etMemo = (EditText) findViewById(R.id.etMemo);
        tvF_memo = (TextView) findViewById(R.id.tvF_memo);
        etMemo.setText(mWriteMemo);
        tvF_memo.setText(mFmemo);
        btConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mMemo = etMemo.getText().toString();
                sendMessage();
            }
        });
    }
    private void sendMessage() {
        mListener.onReceive(mMemo);
    }

    public interface OnReceiveMessageListener {
        void onReceive(String message);
    }

}
