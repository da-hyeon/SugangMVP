package com.dutch_pay.hdh.sugangmvp.ui.fragment.satisfaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.data.model.PollStudent;
import com.dutch_pay.hdh.sugangmvp.databinding.ListPollStudentItemBinding;

import java.util.ArrayList;


public class ClassSatisfactionLevelListAdapter extends BaseAdapter {
    //    private List<NoticeListModel> mArList;
    private ArrayList<PollStudent.PollStudentResultData> mArList;
    private Context mContext;


    ClassSatisfactionLevelListAdapter(Context context, ArrayList<PollStudent.PollStudentResultData> list) {
        super();
        this.mContext = context;
        this.mArList = list;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mArList.get(position);
    }


    @Override
    public int getCount() {
        return mArList.size();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ListPollStudentItemBinding mBinding;
        if (convertView == null) {
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext) , R.layout.list_poll_student_item, parent , false);
            v = mBinding.getRoot();
        } else {
            mBinding = (ListPollStudentItemBinding) v.getTag();
        }
        final PollStudent.PollStudentResultData consentListResultData = mArList.get(position);
        mBinding.tvLBGrade.setText(consentListResultData.getLB_grade() + " 학년");
        mBinding.tvLBTime.setText(consentListResultData.getLB_time() + " 교시");
        mBinding.tvSLBWeek.setText(consentListResultData.getS_LB_week());
        mBinding.tvLBSubject.setText(Html.fromHtml("<u>" + consentListResultData.getLB_subject() + "</u>"));
        mBinding.tvRXName.setText(consentListResultData.getRX_name());
        mBinding.tvIspoll.setText(consentListResultData.getIsPoll());


        v.setTag(mBinding);
        return v;
    }
}
