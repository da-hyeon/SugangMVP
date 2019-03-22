package com.dutch_pay.hdh.sugangmvp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PollStudent {

    private String name;
    private ArrayList<PollStudentResultData> data;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PollStudentResultData> getData() {
        return data;
    }

    public void setData(ArrayList<PollStudentResultData> data) {
        this.data = data;
    }


    public static class PollStudentResultData implements Parcelable{

        private String err_code;
        private String err_msg;

        private String title;
        private String content;
        private String SS_date;


        private String no;              //글번호
        private String LB_no;         //제목
        private String LB_grade;        //내용
        private String LB_time;      //학년반번
        private String s_LB_week;     //작성일
        private String LB_subject;  //1-통신,2-건의
        private String RX_name;
        private String isPoll;


        public PollStudentResultData(){

        }

        protected PollStudentResultData(Parcel in) {
            err_code = in.readString();
            err_msg = in.readString();
            no = in.readString();
            LB_no = in.readString();
            LB_grade = in.readString();
            LB_time = in.readString();
            s_LB_week = in.readString();
            LB_subject = in.readString();
            RX_name = in.readString();
            isPoll = in.readString();
        }

        public static final Creator<PollStudentResultData> CREATOR = new Creator<PollStudentResultData>() {
            @Override
            public PollStudentResultData createFromParcel(Parcel in) {
                return new PollStudentResultData(in);
            }

            @Override
            public PollStudentResultData[] newArray(int size) {
                return new PollStudentResultData[size];
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSS_date() {
            return SS_date;
        }

        public void setSS_date(String SS_date) {
            this.SS_date = SS_date;
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

        public String getLB_grade() {
            return LB_grade;
        }

        public void setLB_grade(String LB_grade) {
            this.LB_grade = LB_grade;
        }

        public String getLB_time() {
            return LB_time;
        }

        public void setLB_time(String LB_time) {
            this.LB_time = LB_time;
        }

        public String getS_LB_week() {
            return s_LB_week;
        }

        public void setS_LB_week(String s_LB_week) {
            this.s_LB_week = s_LB_week;
        }

        public String getLB_subject() {
            return LB_subject;
        }

        public void setLB_subject(String LB_subject) {
            this.LB_subject = LB_subject;
        }

        public String getRX_name() {
            return RX_name;
        }

        public void setRX_name(String RX_name) {
            this.RX_name = RX_name;
        }

        public String getIsPoll() {
            return isPoll;
        }

        public void setIsPoll(String isPoll) {
            this.isPoll = isPoll;
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
            parcel.writeString(LB_grade);
            parcel.writeString(LB_time);
            parcel.writeString(s_LB_week);
            parcel.writeString(LB_subject);
            parcel.writeString(RX_name);
            parcel.writeString(isPoll);
        }
    }
}
