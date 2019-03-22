package com.dutch_pay.hdh.sugangmvp.data.model;

import java.util.ArrayList;

public class Result {
    private String name;
    private ArrayList<ResultData> data;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ResultData> getData() {
        return data;
    }

    public void setData(ArrayList<ResultData> data) {
        this.data = data;
    }

    public static class ResultData {
        private String err_code;
        private String err_msg;

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

    }
}
