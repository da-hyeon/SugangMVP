package com.dutch_pay.hdh.sugangmvp.ui.fragment.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.data.model.MyConsent;
import com.dutch_pay.hdh.sugangmvp.databinding.ListMyconsentItemBinding;

import java.util.ArrayList;


public class SearchClassRegistrationListAdapter extends BaseAdapter {
    //    private List<NoticeListModel> mArList;
    private ArrayList<MyConsent.MyConsentListResultData> mArList;
    private Context mContext;

    public SearchClassRegistrationListAdapter(Context context, ArrayList<MyConsent.MyConsentListResultData> list) {
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
        ListMyconsentItemBinding mBinding;

        if (convertView == null) {
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.list_myconsent_item, parent, false);
            v = mBinding.getRoot();
        } else {
            mBinding = (ListMyconsentItemBinding) v.getTag();
        }

        final MyConsent.MyConsentListResultData myConsentListResultData = mArList.get(position);

        mBinding.tvLBNo.setText(myConsentListResultData.getNo());
        mBinding.tvLBArea.setText(myConsentListResultData.getLB_area());
        if ("1".equals(myConsentListResultData.getLB_req())) {
            mBinding.tvLBReq.setText("필수");
        } else {
            mBinding.tvLBReq.setText("선택");
        }
        mBinding.tvLBSubject.setText(Html.fromHtml("<u>" + myConsentListResultData.getLB_subject() + "</u>"));
        mBinding.tvLBStime.setText(myConsentListResultData.getLB_stime() + " 교시");
        mBinding.tvLBWeek.setText(myConsentListResultData.getLB_week());
        mBinding.tvLBRoom.setText(myConsentListResultData.getLB_room());
        mBinding.tvRXName.setText(myConsentListResultData.getRX_name());
        mBinding.tvLBMax.setText(myConsentListResultData.getLB_max() + " 명");
        mBinding.tvLBMin.setText(myConsentListResultData.getLB_min() + " 명");
        if ("-".equals(myConsentListResultData.getOrder_cnt())) {
            mBinding.tvOrderCnt.setText(myConsentListResultData.getOrder_cnt());
        } else {
            mBinding.tvOrderCnt.setText(myConsentListResultData.getOrder_cnt() + " 명");
        }
        mBinding.tvOBDate.setText(myConsentListResultData.getOB_date());
        mBinding.tvOBIp.setText(myConsentListResultData.getOB_ip());

        v.setTag(mBinding);
        return v;
    }

}
