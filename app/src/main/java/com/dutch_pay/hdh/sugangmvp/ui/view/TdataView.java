package com.dutch_pay.hdh.sugangmvp.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dutch_pay.hdh.sugangmvp.R;


public class TdataView extends LinearLayout {

    private EditText etT_memo;
    private TextView tvMemo;
    private TextView tvVoteNo;
    private Context mContext;
    private String mRCNT_t;
    private String mMemo;
    private String mTmemo;

    public TdataView(Context context, String memo, String t_memo, String rcnt_t) {
        super(context);

        this.mContext = context;
        this.mMemo = memo;
        this.mTmemo = t_memo;
        this.mRCNT_t = rcnt_t;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_t_data, this, true);
        init();
    }

    private void init() {
        tvMemo = (TextView) findViewById(R.id.tvMemo);
        tvVoteNo = (TextView) findViewById(R.id.tvVoteNo);
        etT_memo = (EditText) findViewById(R.id.etT_memo);
        if (!"".equals(mTmemo)) {
            etT_memo.setText(mTmemo);
            etT_memo.setFocusableInTouchMode(false);
            etT_memo.setClickable(false);
            etT_memo.setFocusable(false);
        }
        tvMemo.setText(mMemo);

    }

    public String getT_memo () {
       if (etT_memo != null && !"".equals(etT_memo.getText().toString())) {
           return etT_memo.getText().toString().trim();
       }
       return "";
    }

    public String getRcnt_t () {
        if (mRCNT_t != null && !"".equals(mRCNT_t)) {
            return mRCNT_t;
        }
        return "";
    }
}
