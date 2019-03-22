package com.dutch_pay.hdh.sugangmvp.ui.fragment.notice;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.data.model.BoardList;
import com.dutch_pay.hdh.sugangmvp.databinding.ViewCommentDataBinding;
import com.dutch_pay.hdh.sugangmvp.databinding.ViewListNoticeBinding;

import java.util.ArrayList;

public class NoticeExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<BoardList.BoardListResultData> mGroup;
    private ArrayList<BoardList.BoardListResultData> mChild;

    NoticeExpandableListAdapter(Context context, ArrayList<BoardList.BoardListResultData> group, ArrayList<BoardList.BoardListResultData> child) {
        this.mContext = context;
        this.mGroup = group;
        this.mChild = child;
    }

    @Override
    public BoardList.BoardListResultData getChild(int groupPosition, int childPosition) {
        ArrayList<BoardList.BoardListResultData> childArray = new ArrayList<>();
        for (int i = 0; i < mChild.size(); i++) {
            if (getGroup(groupPosition).getGrp().equals(mChild.get(i).getGrp())) {
                BoardList.BoardListResultData child = mChild.get(i);
                childArray.add(child);
            }
        }
        return childArray.get(childPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(final int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewCommentDataBinding mChildBinding;
        if (convertView == null) {
            mChildBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext) , R.layout.view_comment_data, parent , false);
            v = mChildBinding.getRoot();
        } else {
            mChildBinding = (ViewCommentDataBinding) v.getTag();
        }
        final BoardList.BoardListResultData child = getChild(listPosition, expandedListPosition);
        mChildBinding.tvNewsNoticeNum.setText(child.getNo());
        mChildBinding.tvNewsNoticeTitle.setText(child.getSubject());
        mChildBinding.tvNewsNoticeName.setText(child.getName());
        mChildBinding.tvNewsNoticeDate.setText(child.getStime());
        mChildBinding.tvNewsNoticeVisit.setText(child.getVisit());

        v.setTag(mChildBinding);
        return v;
    }


    @Override
    public int getChildrenCount(int listPosition) {
        BoardList.BoardListResultData group = getGroup(listPosition);
        int count = 0;
        for (int i = 0; i < mChild.size(); i++) {
            if (group.getGrp().equals(mChild.get(i).getGrp())) {
                count++;
            }
        }
        return count;
    }

    void expandGroup(ExpandableListView expandableListView) {
        for (int i = 0; i < getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
    }

    @Override
    public BoardList.BoardListResultData getGroup(int groupPosition) {
        return mGroup.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.mGroup.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;

        ViewListNoticeBinding mGroupBinding;
        if (convertView == null) {
            mGroupBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext) , R.layout.view_list_notice, parent , false);
            v = mGroupBinding.getRoot();

        } else {
            mGroupBinding = (ViewListNoticeBinding) v.getTag();
        }
        final BoardList.BoardListResultData group = getGroup(groupPosition);
        mGroupBinding.tvNewsNoticeNum.setText(group.getNo());
        mGroupBinding.tvNewsNoticeTitle.setText(group.getSubject());
        mGroupBinding.tvNewsNoticeName.setText(group.getName());
        mGroupBinding.tvNewsNoticeDate.setText(group.getStime());
        mGroupBinding.tvNewsNoticeVisit.setText(group.getVisit());

        v.setTag(mGroupBinding);
        return v;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

}
