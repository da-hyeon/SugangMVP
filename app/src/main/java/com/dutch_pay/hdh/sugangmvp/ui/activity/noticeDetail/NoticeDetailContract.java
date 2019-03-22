package com.dutch_pay.hdh.sugangmvp.ui.activity.noticeDetail;

import com.dutch_pay.hdh.sugangmvp.ui.view.LinkListView;

public interface NoticeDetailContract {
    interface View{
        //show
        void showBeforeActivity();
        void showPopupWebBox();

        //add
        void addLinkContent(LinkListView linkListView);

        //change
        void changePopupWebBoxData(String data, String mimeType, String encoding);
    }

    interface Presenter{
        void getData();
        void initWebView();

        void clickBack();
    }
}
