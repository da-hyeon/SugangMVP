package com.dutch_pay.hdh.sugangmvp.ui.activity.noticeDetail;

import android.app.Activity;
import android.os.Build;

import com.dutch_pay.hdh.sugangmvp.Constants;
import com.dutch_pay.hdh.sugangmvp.MyApplication;
import com.dutch_pay.hdh.sugangmvp.data.model.BoardList;
import com.dutch_pay.hdh.sugangmvp.ui.view.LinkListView;
import com.dutch_pay.hdh.sugangmvp.util.Trace;

public class NoticeDetailPresenter implements NoticeDetailContract.Presenter {

    private NoticeDetailContract.View mView;
    private Activity mActivity;
    private MyApplication mMyApplication;

    private BoardList.BoardListResultData mNoticeListResultData;

    /**
     * 생성자
     */
    NoticeDetailPresenter(NoticeDetailContract.View mView,  Activity mActivity) {
        this.mView = mView;
        this.mActivity = mActivity;

        mMyApplication = MyApplication.getInstance();

    }

    /**
     * 데이터 받아오기
     */
    @Override
    public void getData() {
        mNoticeListResultData = mActivity.getIntent().getParcelableExtra(Constants.KEY_SID_NO);
        if (mNoticeListResultData == null) {
            mView.showBeforeActivity();
        }
        Trace.e("데이터확인"+mNoticeListResultData.getSubject());
    }

    /**
     * WebView 초기화
     */
    @Override
    public void initWebView() {
        if (!"0".equals(mNoticeListResultData.getFile_size1())) {
            LinkListView linkListView = new LinkListView(mActivity, null , mMyApplication.getBASE_URL()+mNoticeListResultData.getFile_name1());
            mView.addLinkContent(linkListView);
        }
        if (!"0".equals(mNoticeListResultData.getFile_size2())) {
            LinkListView linkListView = new LinkListView(mActivity, null , mMyApplication.getBASE_URL()+mNoticeListResultData.getFile_name2());
            mView.addLinkContent(linkListView);
        }
        mView.showPopupWebBox();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mView.changePopupWebBoxData(mNoticeListResultData.getContent().replace("</style>", "body{margin:0;}</style>"), "text/html; charset=UTF-8", null);
        } else {
            mView.changePopupWebBoxData(mNoticeListResultData.getContent().replace("</style>", "body{margin:0;}</style>"), "text/html", "UTF-8");
        }
    }

    /**
     * Back 클릭
     */
    @Override
    public void clickBack() {
        mView.showBeforeActivity();
    }
}
