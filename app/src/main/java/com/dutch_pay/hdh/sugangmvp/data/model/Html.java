package com.dutch_pay.hdh.sugangmvp.data.model;

import java.util.ArrayList;

public class Html {
    private String name;
    private ArrayList<HtmlResultData> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<HtmlResultData> getData() {
        return data;
    }

    public void setData(ArrayList<HtmlResultData> data) {
        this.data = data;
    }

    public static class HtmlResultData {
        private String err_code;
        private String err_msg;
        private String html_tag;

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

        public String getHtml_tag() {
            return html_tag;
        }

        public void setHtml_tag(String html_tag) {
            this.html_tag = html_tag;
        }
    }
}
