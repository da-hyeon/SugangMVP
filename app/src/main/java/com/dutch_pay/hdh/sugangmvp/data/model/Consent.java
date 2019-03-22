package com.dutch_pay.hdh.sugangmvp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Consent {

    private String name;
    private ArrayList<ConsentListResultData> data;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ConsentListResultData> getData() {
        return data;
    }

    public void setData(ArrayList<ConsentListResultData> data) {
        this.data = data;
    }


    public static class ConsentListResultData implements Parcelable{

        private String err_code;
        private String err_msg;

        private String no;              //글번호
        private String LB_no;         //제목
        private String LB_stime;        //내용
        private String LB_week;      //학년반번
        private String LB_party;     //작성일
        private String LB_area;  //1-통신,2-건의
        private String LB_req;
        private String LB_subject;
        private String LB_told;
        private String LB_room;
        private String RX_name;
        private String LB_level;
        private String LB_score;
        private String LB_max;
        private String order_cnt;
        private String order_con;
        private String req_ordercnt;
        private String req_totcnt;
        private String grade;
        private String LB_sw;

        public ConsentListResultData(){

        }

        protected ConsentListResultData(Parcel in) {
            err_code = in.readString();
            err_msg = in.readString();
            no = in.readString();
            LB_no = in.readString();
            LB_stime = in.readString();
            LB_week = in.readString();
            LB_party = in.readString();
            LB_area = in.readString();
            LB_req = in.readString();
            LB_subject = in.readString();
            LB_told = in.readString();
            LB_room = in.readString();
            RX_name = in.readString();
            LB_level = in.readString();
            LB_score = in.readString();
            LB_max = in.readString();
            order_cnt = in.readString();
            order_con = in.readString();
            req_ordercnt = in.readString();
            req_totcnt = in.readString();
            grade = in.readString();
            LB_sw = in.readString();
        }

        public static final Creator<ConsentListResultData> CREATOR = new Creator<ConsentListResultData>() {
            @Override
            public ConsentListResultData createFromParcel(Parcel in) {
                return new ConsentListResultData(in);
            }

            @Override
            public ConsentListResultData[] newArray(int size) {
                return new ConsentListResultData[size];
            }
        };

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

        public String getLB_party() {
            return LB_party;
        }

        public void setLB_party(String LB_party) {
            this.LB_party = LB_party;
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

        public String getLB_told() {
            return LB_told;
        }

        public void setLB_told(String LB_told) {
            this.LB_told = LB_told;
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

        public String getLB_level() {
            return LB_level;
        }

        public void setLB_level(String LB_level) {
            this.LB_level = LB_level;
        }

        public String getLB_score() {
            return LB_score;
        }

        public void setLB_score(String LB_score) {
            this.LB_score = LB_score;
        }

        public String getLB_max() {
            return LB_max;
        }

        public void setLB_max(String LB_max) {
            this.LB_max = LB_max;
        }

        public String getOrder_cnt() {
            return order_cnt;
        }

        public void setOrder_cnt(String order_cnt) {
            this.order_cnt = order_cnt;
        }

        public String getOrder_con() {
            return order_con;
        }

        public void setOrder_con(String order_con) {
            this.order_con = order_con;
        }

        public String getReq_ordercnt() {
            return req_ordercnt;
        }

        public void setReq_ordercnt(String req_ordercnt) {
            this.req_ordercnt = req_ordercnt;
        }

        public String getReq_totcnt() {
            return req_totcnt;
        }

        public void setReq_totcnt(String req_totcnt) {
            this.req_totcnt = req_totcnt;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getLB_sw() {
            return LB_sw;
        }

        public void setLB_sw(String LB_sw) {
            this.LB_sw = LB_sw;
        }

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
            parcel.writeString(LB_stime);
            parcel.writeString(LB_week);
            parcel.writeString(LB_party);
            parcel.writeString(LB_area);
            parcel.writeString(LB_req);
            parcel.writeString(LB_subject);
            parcel.writeString(LB_told);
            parcel.writeString(LB_room);
            parcel.writeString(RX_name);
            parcel.writeString(LB_level);
            parcel.writeString(LB_score);
            parcel.writeString(LB_max);
            parcel.writeString(order_cnt);
            parcel.writeString(order_con);
            parcel.writeString(req_ordercnt);
            parcel.writeString(req_totcnt);
            parcel.writeString(grade);
            parcel.writeString(LB_sw);
        }
    }
}
