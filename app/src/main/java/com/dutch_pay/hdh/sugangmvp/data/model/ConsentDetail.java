package com.dutch_pay.hdh.sugangmvp.data.model;

import java.util.ArrayList;

public class ConsentDetail {

    private String name;
    private ArrayList<ConsentDetailResultData> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ConsentDetailResultData> getData() {
        return data;
    }

    public void setData(ArrayList<ConsentDetailResultData> data) {
        this.data = data;
    }

    public static class ConsentDetailResultData {

        private String err_code;
        private String err_msg;

        private String no;
        private String LB_no;
        private String LB_subject;
        private String LB_room;
        private String s_LB_grade;
        private String LB_max;
        private String LB_min;
        private String s_LB_area;
        private String LB_told;
        private String LB_level;
        private String LB_addcost;
        private String RX_name;
        private String s_LB_time;
        private String week_map;
        private String LB_content;
        private String LB_book;
        private String LB_file;
        private String s_LB_score;
        private String mode;


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

        public String getLB_subject() {
            return LB_subject;
        }

        public void setLB_subject(String LB_subject) {
            this.LB_subject = LB_subject;
        }

        public String getLB_room() {
            return LB_room;
        }

        public void setLB_room(String LB_room) {
            this.LB_room = LB_room;
        }

        public String getS_LB_grade() {
            return s_LB_grade;
        }

        public void setS_LB_grade(String s_LB_grade) {
            this.s_LB_grade = s_LB_grade;
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

        public String getS_LB_area() {
            return s_LB_area;
        }

        public void setS_LB_area(String s_LB_area) {
            this.s_LB_area = s_LB_area;
        }

        public String getLB_told() {
            return LB_told;
        }

        public void setLB_told(String LB_told) {
            this.LB_told = LB_told;
        }

        public String getLB_level() {
            return LB_level;
        }

        public void setLB_level(String LB_level) {
            this.LB_level = LB_level;
        }

        public String getLB_addcost() {
            return LB_addcost;
        }

        public void setLB_addcost(String LB_addcost) {
            this.LB_addcost = LB_addcost;
        }

        public String getRX_name() {
            return RX_name;
        }

        public void setRX_name(String RX_name) {
            this.RX_name = RX_name;
        }

        public String getS_LB_time() {
            return s_LB_time;
        }

        public void setS_LB_time(String s_LB_time) {
            this.s_LB_time = s_LB_time;
        }

        public String getWeek_map() {
            return week_map;
        }

        public void setWeek_map(String week_map) {
            this.week_map = week_map;
        }

        public String getLB_content() {
            return LB_content;
        }

        public void setLB_content(String LB_content) {
            this.LB_content = LB_content;
        }

        public String getLB_book() {
            return LB_book;
        }

        public void setLB_book(String LB_book) {
            this.LB_book = LB_book;
        }

        public String getLB_file() {
            return LB_file;
        }

        public void setLB_file(String LB_file) {
            this.LB_file = LB_file;
        }

        public String getS_LB_score() {
            return s_LB_score;
        }

        public void setS_LB_score(String s_LB_score) {
            this.s_LB_score = s_LB_score;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

    }
}
