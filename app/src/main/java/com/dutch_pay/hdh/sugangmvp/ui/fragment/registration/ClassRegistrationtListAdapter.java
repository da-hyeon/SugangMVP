package com.dutch_pay.hdh.sugangmvp.ui.fragment.registration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch_pay.hdh.sugangmvp.R;
import com.dutch_pay.hdh.sugangmvp.data.model.Consent;
import com.dutch_pay.hdh.sugangmvp.databinding.ListConsentItemBinding;

import java.util.ArrayList;


public class ClassRegistrationtListAdapter extends BaseAdapter {

    //    private List<NoticeListModel> mArList;
    private ArrayList<Consent.ConsentListResultData> mArList;
    private Context mContext;


    ClassRegistrationtListAdapter(Context context, ArrayList<Consent.ConsentListResultData> list) {
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
        ListConsentItemBinding mBinding;
        if (convertView == null) {
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext) , R.layout.list_consent_item, parent , false);
            v = mBinding.getRoot();
        }
        else {
            mBinding = (ListConsentItemBinding) v.getTag();
        }
        
        final Consent.ConsentListResultData consentListResultData = mArList.get(position);

        mBinding.tvLBStime.setText(consentListResultData.getLB_stime()+" 교시");
        mBinding.tvLBWeek.setText(consentListResultData.getLB_week());
        mBinding.tvLBParty.setText(consentListResultData.getLB_party());
        mBinding.tvLBArea.setText(consentListResultData.getLB_area());
        if ("1".equals(consentListResultData.getLB_req())) {
            mBinding.tvLBReq.setText("필수");
        } else {
            mBinding.tvLBReq.setText("선택");
        }
        mBinding.tvLBSubject.setText(Html.fromHtml("<u>" + consentListResultData.getLB_subject() + "</u>"));
        mBinding.tvLBTold.setText(consentListResultData.getLB_told());
        mBinding.tvLBRoom.setText(consentListResultData.getLB_room());
        mBinding.tvRXName.setText(consentListResultData.getRX_name());
        mBinding.tvLBLevel.setText(consentListResultData.getLB_level());
        mBinding.tvLBScore.setText(consentListResultData.getLB_score());
        mBinding.tvLBMax.setText(consentListResultData.getLB_max()+" 명");
        if ("-".equals(consentListResultData.getOrder_cnt())) {
            mBinding.tvOrderCnt.setText(consentListResultData.getOrder_cnt());
        } else {
            mBinding.tvOrderCnt.setText(consentListResultData.getOrder_cnt()+" 명");
        }
        if ("마감".equals(consentListResultData.getOrder_con())) {
            mBinding.llDecLine.setVisibility(View.VISIBLE);
        } else {
            mBinding.llDecLine.setVisibility(View.GONE);
        }
        if ("1".equals(consentListResultData.getLB_sw())) {
            mBinding.tvGrade.setBackgroundColor(mContext.getResources().getColor(R.color.btnThirdColor));
        } else {
            mBinding.tvGrade.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
        }
        mBinding.tvOrderCon.setText(consentListResultData.getOrder_con());
        mBinding.tvGrade.setText(consentListResultData.getGrade());
        
        v.setTag(mBinding);
        return v;
    }
}
