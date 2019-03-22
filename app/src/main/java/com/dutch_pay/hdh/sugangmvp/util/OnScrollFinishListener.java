package com.dutch_pay.hdh.sugangmvp.util;

import android.widget.AbsListView;

public abstract class OnScrollFinishListener implements AbsListView.OnScrollListener {

    int mCurrentScrollState;
    int mCurrentVisibleItemCount;
    boolean isCheck;
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        mCurrentScrollState = scrollState;
        if (isScrollCompleted()) {
            onScrollFinished(isScrollCompleted());
            if(!view.canScrollVertically(1)) {
                onScrollLast(true);
            } else {
                onScrollLast(false);
            }

        } else {
            onScrollFinished(isScrollCompleted());
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mCurrentVisibleItemCount = visibleItemCount;
    }

    private boolean isScrollCompleted() {
        return mCurrentVisibleItemCount > 0 && mCurrentScrollState == SCROLL_STATE_IDLE;
    }

    protected abstract void onScrollFinished(boolean isCheck);
    protected abstract void onScrollLast(boolean isCheck);

}