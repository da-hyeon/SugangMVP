package com.dutch_pay.hdh.sugangmvp.data.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ParentAuth {

    private String id;
    private String server_jsonp_addr;
    private String school_id;       //학교아이디
    private String student_no;      //학생 고유번호
    private String student_id;      //학생아이디
    private String student_name;    //학생이름
    private String student_passwd;  //학생비밀번호
    private String system_name;     //시스템 이름
    private String system_version;  //시스템 버전
    private String system_sys;      //권한

    public ParentAuth() {

    }

    public ParentAuth(String id, String server_jsonp_addr,  String school_id,  String student_no
                    , String student_id, String student_name, String student_passwd, String system_name,String system_version, String system_sys) {
        this.id = id;
        this.server_jsonp_addr = server_jsonp_addr;
        this.school_id = school_id;
        this.student_no = student_no;
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_passwd = student_passwd;
        this.system_name = system_name;
        this.system_version = system_version;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServer_jsonp_addr() {
        return server_jsonp_addr;
    }

    public void setServer_jsonp_addr(String server_jsonp_addr) {
        this.server_jsonp_addr = server_jsonp_addr;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }


    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_passwd() {
        return student_passwd;
    }

    public void setStudent_passwd(String student_passwd) {
        this.student_passwd = student_passwd;
    }

    public String getSystem_name() {
        return system_name;
    }

    public void setSystem_name(String system_name) {
        this.system_name = system_name;
    }

    public String getSystem_version() {
        return system_version;
    }

    public void setSystem_version(String system_version) {
        this.system_version = system_version;
    }



    public JSONObject toJsonObject() {
        JSONObject object = new JSONObject();

        try {
            object.put("id", this.id);
            object.put("server_jsonp_addr", this.server_jsonp_addr);
            object.put("school_id", this.school_id);
            object.put("student_no", this.student_no);
            object.put("student_id", this.student_id);
            object.put("student_name", this.student_name);
            object.put("student_passwd", this.student_passwd);
            object.put("system_name", this.system_name);
            object.put("system_version", this.system_version);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }

    public void parseFromJson(JSONObject object) {
        if (object == null) {
            return;
        }
        try {
            this.id = object.getString("id");
            this.server_jsonp_addr = object.getString("server_jsonp_addr");
            this.school_id = object.getString("school_id");
            this.student_no = object.getString("student_no");
            this.student_id = object.getString("student_id");
            this.student_name = object.getString("student_name");
            this.student_passwd = object.getString("student_passwd");
            this.system_name = object.getString("system_name");
            this.system_version = object.getString("system_version");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
