package com.dutch_pay.hdh.sugangmvp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MyConsent {

    private String name;
    private ArrayList<MyConsentListResultData> data;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MyConsentListResultData> getData() {
        return data;
    }

    public void setData(ArrayList<MyConsentListResultData> data) {
        this.data = data;
    }


    public static class MyConsentListResultData implements Parcelable {

        private String err_code;
        private String err_msg;

        private String no;
        private String LB_no;
        private String LB_area;
        private String LB_req;
        private String LB_subject;
        private String LB_stime;
        private String LB_week;
        private String LB_room;
        private String RX_name;
        private String LB_max;
        private String LB_min;
        private String order_cnt;
        private String OB_ip;
        private String OB_date;
        private String LB_msg;
        private String OB_pay;

        public MyConsentListResultData() {

        }

        public String getErr_code() {
            return err_code;
        }

        public void setErr_code(String err_code) {
            this.err_code = err_code;
        }

        public String getErr_msg() {
            return err_msg;
        }

        public void setErr_msg(String err_msg) {
            this.err_msg = err_msg;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getLB_no() {
            return LB_no;
        }

        public void setLB_no(String LB_no) {
            this.LB_no = LB_no;
        }

        public String getLB_area() {
            return LB_area;
        }

        public void setLB_area(String LB_area) {
            this.LB_area = LB_area;
        }

        public String getLB_req() {
            return LB_req;
        }

        public void setLB_req(String LB_req) {
            this.LB_req = LB_req;
        }

        public String getLB_subject() {
            return LB_subject;
        }

        public void setLB_subject(String LB_subject) {
            this.LB_subject = LB_subject;
        }

        public String getLB_stime() {
            return LB_stime;
        }

        public void setLB_stime(String LB_stime) {
            this.LB_stime = LB_stime;
        }

        public String getLB_week() {
            return LB_week;
        }

        public void setLB_week(String LB_week) {
            this.LB_week = LB_week;
        }

        public String getLB_room() {
            return LB_room;
        }

        public void setLB_room(String LB_room) {
            this.LB_room = LB_room;
        }

        public String getRX_name() {
            return RX_name;
        }

        public void setRX_name(String RX_name) {
            this.RX_name = RX_name;
        }

        public String getLB_max() {
            return LB_max;
        }

        public void setLB_max(String LB_max) {
            this.LB_max = LB_max;
        }

        public String getLB_min() {
            return LB_min;
        }

        public void setLB_min(String LB_min) {
            this.LB_min = LB_min;
        }

        public String getOrder_cnt() {
            return order_cnt;
        }

        public void setOrder_cnt(String order_cnt) {
            this.order_cnt = order_cnt;
        }

        public String getOB_ip() {
            return OB_ip;
        }

        public void setOB_ip(String OB_ip) {
            this.OB_ip = OB_ip;
        }

        public String getOB_date() {
            return OB_date;
        }

        public void setOB_date(String OB_date) {
            this.OB_date = OB_date;
        }

        public String getLB_msg() {
            return LB_msg;
        }

        public void setLB_msg(String LB_msg) {
            this.LB_msg = LB_msg;
        }

        public String getOB_pay() {
            return OB_pay;
        }

        public void setOB_pay(String OB_pay) {
            this.OB_pay = OB_pay;
        }

        protected MyConsentListResultData(Parcel in) {
            err_code = in.readString();
            err_msg = in.readString();
            no = in.readString();
            LB_no = in.readString();
            LB_area = in.readString();
            LB_req = in.readString();
            LB_subject = in.readString();
            LB_stime = in.readString();
            LB_week = in.readString();
            LB_room = in.readString();
            RX_name = in.readString();
            LB_max = in.readString();
            LB_min = in.readString();
            order_cnt = in.readString();
            OB_ip = in.readString();
            OB_date = in.readString();
            LB_msg = in.readString();
            OB_pay = in.readString();

        }

        public static final Creator<MyConsentListResultData> CREATOR = new Creator<MyConsentListResultData>() {
            @Override
            public MyConsentListResultData createFromParcel(Parcel in) {
                return new MyConsentListResultData(in);
            }

            @Override
            public MyConsentListResultData[] newArray(int size) {
                return new MyConsentListResultData[size];
            }
        };


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(err_code);
            parcel.writeString(err_msg);
            parcel.writeString(no);
            parcel.writeString(LB_no);
            parcel.writeString(LB_area);
            parcel.writeString(LB_req);
            parcel.writeString(LB_subject);
            parcel.writeString(LB_stime);
            parcel.writeString(LB_week);
            parcel.writeString(LB_room);
            parcel.writeString(RX_name);
            parcel.writeString(LB_max);
            parcel.writeString(LB_min);
            parcel.writeString(order_cnt);
            parcel.writeString(OB_ip);
            parcel.writeString(OB_date);
            parcel.writeString(LB_msg);
            parcel.writeString(OB_pay);
        }
    }
}
