package com.dutch_pay.hdh.sugangmvp.ui.fragment.notice;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.ExpandableListView;

import com.dutch_pay.hdh.sugangmvp.Constants;
import com.dutch_pay.hdh.sugangmvp.MyApplication;
import com.dutch_pay.hdh.sugangmvp.data.model.BoardList;
import com.dutch_pay.hdh.sugangmvp.data.model.ParentAuth;
import com.dutch_pay.hdh.sugangmvp.ui.activity.noticeDetail.NoticeDetailActivity;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;
import com.dutch_pay.hdh.sugangmvp.ui.view.SearchFieldTypeView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeExpandablePresenter implements NoticeExpandableContract.Presenter{

    private NoticeExpandableContract.View mView;
    private Context mContext;

    private MyApplication mMyApplication;
    private ArrayList<BoardList.BoardListResultData> mBoardListResultDataArrayList;
    private ArrayList<BoardList.BoardListResultData> mCommentListResultDataArrayList;
    private NoticeExpandableListAdapter mNoticeListAdapter;

    //검색구분필드
    private String mSearchField;

    /**
     * 생성자
     */
    NoticeExpandablePresenter(NoticeExpandableContract.View mView, Context mContext ) {
        this.mView = mView;
        this.mContext = mContext;
        mMyApplication = MyApplication.getInstance();

        new SearchFieldTypeView(mContext, (message, code) -> {
            mSearchField = message;
            mView.changeSearchField(message);
        }).SendSearchFieldTypeViewDefault();
    }

    /**
     * SearchField 클릭
     */
    @Override
    public void clickSearchField() {
        final CommonDialog build = new CommonDialog(mContext);
        build.setCancelable(false);
        mView.changeDialogTitle( build,"검색구분");
        build.setView(new SearchFieldTypeView(mContext, (message, code) -> {
            mSearchField = message;
            mView.changeSearchField(message);
            mView.removeDialog(build);
        }));
        mView.showDialog(build);
    }

    /**
     * NoticeGroup 클릭
     */
    @Override
    public void clickNoticeGroup(int groupPosition) {
        Intent intent = new Intent(mContext, NoticeDetailActivity.class);
        intent.putExtra(Constants.KEY_SID_NO, mNoticeListAdapter.getGroup(groupPosition));
        mView.showActivity(intent);
        mView.showAnimation();
    }

    /**
     * NoticeChild 클릭
     */
    @Override
    public void clickNoticeChild() {

    }

    /**
     * BoardListData 받아오기
     */
    @Override
    public void getBoardListData(String row , String content , ExpandableListView listView) {
        ParentAuth mParentAuth = MyApplication.getInstance().getParentAuth();
        Call<BoardList> boardList = MyApplication.getRestAdapter(mMyApplication.getBASE_URL(), false).getBoardList(
                Constants.SERVER_CALLBACK_KEY
                , mParentAuth.getSchool_id()
                , mParentAuth.getStudent_id()
                , mParentAuth.getStudent_no()
                , mParentAuth.getStudent_passwd()
                , "0"
                , getSearchField(mSearchField)
                , content.trim()
                , row
        );
        boardList.enqueue(new Callback<BoardList>() {

            @Override
            public void onResponse(@NonNull Call<BoardList> call, @NonNull Response<BoardList> response) {
                if(response.body() != null) {
                    if (!"0".equals(response.body().getData().get(0).getErr_code())) {
                        if ("".equals(response.body().getData().get(0).getErr_msg())) {
                            mView.showNoSearchText();
                            mView.hideNotice();
                            if (mBoardListResultDataArrayList != null) {
                                mBoardListResultDataArrayList.clear();
                            }
                            if (mCommentListResultDataArrayList != null) {
                                mCommentListResultDataArrayList.clear();
                            }
                        } else {
                            dialogForm(response.body().getData().get(0).getErr_msg());
                        }
                    } else {
                        if (response.body().getData().get(1) == null) {
                            mView.showNoSearchText();
                            mView.hideNotice();
                            if (mBoardListResultDataArrayList != null) {
                                mBoardListResultDataArrayList.clear();
                                mNoticeListAdapter.notifyDataSetChanged();
                            }
                            if (mCommentListResultDataArrayList != null) {
                                mCommentListResultDataArrayList.clear();
                            }
                        } else {
                            mView.hideNoSearchText();
                            mView.showNotice();
                            mBoardListResultDataArrayList = new ArrayList<>();
                            mCommentListResultDataArrayList = new ArrayList<>();
                            for (int i = 1; i < response.body().getData().size(); i++) {
                                if ("0".equals(response.body().getData().get(i).getLev())) {
                                    mBoardListResultDataArrayList.add(response.body().getData().get(i));
                                } else {
                                    mCommentListResultDataArrayList.add(response.body().getData().get(i));
                                }
                            }
                            mNoticeListAdapter = new NoticeExpandableListAdapter(mContext, mBoardListResultDataArrayList, mCommentListResultDataArrayList);
                            listView.setAdapter(mNoticeListAdapter);
                            mNoticeListAdapter.expandGroup(listView);
                        }
                    }
                } else {
                    dialogForm("response.body() is null");
                }
            }

            @Override
            public void onFailure(@NonNull Call<BoardList> call, @NonNull Throwable t) {
                dialogForm(t.getMessage());
            }
        });
    }

    /**
     * DialogPositiveButton 클릭
     */
    @Override
    public void clickDialogPositiveButton(CommonDialog build) {
        mView.removeDialog(build);
    }

    /**
     * DialogNegativeButton 클릭
     */
    @Override
    public void clickDialogNegativeButton(CommonDialog build) {

    }

    private void dialogForm(String content){
        final CommonDialog build = new CommonDialog(mContext);
        build.setCancelable(true);
        mView.changeDialogTitle(build, "알림");
        mView.changeDialogMessage(build ,content);
        mView.addDialogPositiveButton(build);
        mView.showDialog(build);
    }

    /**
     * 서버형식에 맞춰서 변환
     */
    private String getSearchField(String field) {
        switch (field) {
            case "제목":
                return "subject";
            case "내용":
                return "content";
            case "이름":
                return "name";
        }
        return "";
    }
}
