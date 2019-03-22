package com.dutch_pay.hdh.sugangmvp.data.model;

import java.util.ArrayList;

public class SvVoteInput {

    private String name;
    private ArrayList<SvVoteInputResultData> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SvVoteInputResultData> getData() {
        return data;
    }

    public void setData(ArrayList<SvVoteInputResultData> data) {
        this.data = data;
    }

    public static class SvVoteInputResultData {

        //에러
        private String err_code;
        private String err_msg;

        //서브젝트
        private String title;
        private String SS_date;
        private String TotalNum;
        private String OldVote;

        //설문정보
        private String no;
        private String q_no;
        private String f_no;
        private String q_memo;
        private String f_memo;
        private String et_memo;
        private String f_type;
        private String r_cnt;
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

        public String getOldVote() {
            return OldVote;
        }

        public void setOldVote(String oldVote) {
            OldVote = oldVote;
        }

        public String getTotalNum() {
            return TotalNum;
        }

        public void setTotalNum(String totalNum) {
            TotalNum = totalNum;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getQ_no() {
            return q_no;
        }

        public void setQ_no(String q_no) {
            this.q_no = q_no;
        }

        public String getF_no() {
            return f_no;
        }

        public void setF_no(String f_no) {
            this.f_no = f_no;
        }

        public String getQ_memo() {
            return q_memo;
        }

        public void setQ_memo(String q_memo) {
            this.q_memo = q_memo;
        }

        public String getF_memo() {
            return f_memo;
        }

        public void setF_memo(String f_memo) {
            this.f_memo = f_memo;
        }

        public String getEt_memo() {
            return et_memo;
        }

        public void setEt_memo(String et_memo) {
            this.et_memo = et_memo;
        }
        public String getF_type() {
            return f_type;
        }

        public void setF_type(String f_type) {
            this.f_type = f_type;
        }

        public String getR_cnt() {
            return r_cnt;
        }

        public void setR_cnt(String r_cnt) {
            this.r_cnt = r_cnt;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }
    }
}
