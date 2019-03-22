package com.dutch_pay.hdh.sugangmvp.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.data.model.BoardList;


public class CommentView extends LinearLayout {

    TextView tvNewsNoticeNum;
    TextView tvNewsNoticeTitle;
    TextView tvNewsNoticeName;
    TextView tvNewsNoticeDate;
    TextView tvNewsNoticeVisit;
    private BoardList.BoardListResultData mCommentListResultData;
    private Context mContext;


    public CommentView(Context context, BoardList.BoardListResultData commentListData) {
        super(context);

        this.mContext = context;
        this.mCommentListResultData = commentListData;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_comment_data, this, true);
        init();
    }

    private void init() {
        tvNewsNoticeNum = (TextView) findViewById(R.id.tvNewsNoticeNum);
        tvNewsNoticeTitle = (TextView) findViewById(R.id.tvNewsNoticeTitle);
        tvNewsNoticeName = (TextView) findViewById(R.id.tvNewsNoticeName);
        tvNewsNoticeDate = (TextView) findViewById(R.id.tvNewsNoticeDate);
        tvNewsNoticeVisit = (TextView) findViewById(R.id.tvNewsNoticeVisit);

        tvNewsNoticeNum.setText(mCommentListResultData.getNo());
        tvNewsNoticeTitle.setText(mCommentListResultData.getSubject());
        tvNewsNoticeName.setText(mCommentListResultData.getName());
        tvNewsNoticeDate.setText(mCommentListResultData.getStime());
        tvNewsNoticeVisit.setText(mCommentListResultData.getVisit());
    }

    public BoardList.BoardListResultData getBoardListResultData () {
        if (mCommentListResultData != null ) {
            return mCommentListResultData;
        }
        return null;
    }
}
