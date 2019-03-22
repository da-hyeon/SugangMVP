package com.dutch_pay.hdh.sugangmvp.data.model;

import java.util.ArrayList;

public class PollVoteInput {

    private String name;
    private ArrayList<PollVoteInputResultData> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PollVoteInputResultData> getData() {
        return data;
    }

    public void setData(ArrayList<PollVoteInputResultData> data) {
        this.data = data;
    }


    public static class PollVoteInputResultData {

        //에러
        private String err_code;
        private String err_msg;




        //서브젝트
        private String title;
        private String SS_date;
        private String TotalNum;


        private String LB_subject;
        private String LB_grade;
        private String RX_name;
        private String LB_time;
        private String s_LB_week;
        private String mode;


        private String no;
        private String FB_type;
        private String RCNT_q;
        private String RCNT_t;
        private String memo;
        private String f_result;
        private String f1;
        private String f2;
        private String f3;
        private String f4;
        private String f5;
        private String t_memo;
        private boolean isCheck;


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

        public String getSS_date() {
            return SS_date;
        }

        public void setSS_date(String SS_date) {
            this.SS_date = SS_date;
        }

        public String getTotalNum() {
            return TotalNum;
        }

        public void setTotalNum(String totalNum) {
            TotalNum = totalNum;
        }

        public String getLB_subject() {
            return LB_subject;
        }

        public void setLB_subject(String LB_subject) {
            this.LB_subject = LB_subject;
        }

        public String getLB_grade() {
            return LB_grade;
        }

        public void setLB_grade(String LB_grade) {
            this.LB_grade = LB_grade;
        }

        public String getRX_name() {
            return RX_name;
        }

        public void setRX_name(String RX_name) {
            this.RX_name = RX_name;
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

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getFB_type() {
            return FB_type;
        }

        public void setFB_type(String FB_type) {
            this.FB_type = FB_type;
        }

        public String getRCNT_q() {
            return RCNT_q;
        }

        public void setRCNT_q(String RCNT_q) {
            this.RCNT_q = RCNT_q;
        }

        public String getRCNT_t() {
            return RCNT_t;
        }

        public void setRCNT_t(String RCNT_t) {
            this.RCNT_t = RCNT_t;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getF_result() {
            return f_result;
        }

        public void setF_result(String f_result) {
            this.f_result = f_result;
        }

        public String getF1() {
            return f1;
        }

        public void setF1(String f1) {
            this.f1 = f1;
        }

        public String getF2() {
            return f2;
        }

        public void setF2(String f2) {
            this.f2 = f2;
        }

        public String getF3() {
            return f3;
        }

        public void setF3(String f3) {
            this.f3 = f3;
        }

        public String getF4() {
            return f4;
        }

        public void setF4(String f4) {
            this.f4 = f4;
        }

        public String getF5() {
            return f5;
        }

        public void setF5(String f5) {
            this.f5 = f5;
        }

        public String getT_memo() {
            return t_memo;
        }

        public void setT_memo(String t_memo) {
            this.t_memo = t_memo;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }
    }
}
