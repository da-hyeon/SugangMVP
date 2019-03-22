package com.dutch_pay.hdh.sugangmvp.ui.activity.noticeDetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.databinding.ActivityNoticeDetailBinding;
import com.dutch_pay.hdh.sugangmvp.ui.activity.BaseActivity;
import com.dutch_pay.hdh.sugangmvp.ui.view.LinkListView;
import com.dutch_pay.hdh.sugangmvp.util.LoadingWebViewClient;

public class NoticeDetailActivity extends BaseActivity implements NoticeDetailContract.View {

    private ActivityNoticeDetailBinding mBinding;
    private NoticeDetailContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notice_detail);
        mBinding.setDetailActivity(this);

        initData();

        mBinding.ivBack.setOnClickListener(v ->
            mPresenter.clickBack()
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new NoticeDetailPresenter(this,  this);
        mPresenter.getData();
        mPresenter.initWebView();
    }

    /**
     * 뒤로가기 클릭
     */
    @Override
    public void onBackPressed() {
        mPresenter.clickBack();
    }

    /**
     * 이전 액티비티 보여주기
     */
    @Override
    public void showBeforeActivity() {
        super.onBackPressed();
    }

    /**
     * WebView 보여주기
     */
    @Override
    public void showPopupWebBox() {
        mBinding.wvPopupWebBox.setWebViewClient(new LoadingWebViewClient(NoticeDetailActivity.this));
        mBinding.wvPopupWebBox.getSettings().setDefaultTextEncodingName("UTF-8");
        mBinding.wvPopupWebBox.getSettings().setBuiltInZoomControls(true);
        mBinding.wvPopupWebBox.getSettings().setSupportZoom(true);
        mBinding.wvPopupWebBox.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    /**
     * LinkContent 추가하기
     */
    @Override
    public void addLinkContent(LinkListView linkListView) {
        mBinding.llLinkContent.addView(linkListView);
    }

    /**
     * PopupWebBox 받기
     */
    @Override
    public void changePopupWebBoxData(String data, String mimeType, String encoding) {
        mBinding.wvPopupWebBox.loadData(data, mimeType, encoding);
    }
}
