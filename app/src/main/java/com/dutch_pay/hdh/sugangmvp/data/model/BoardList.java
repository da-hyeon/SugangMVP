package com.dutch_pay.hdh.sugangmvp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class BoardList {

    private String name;
    private ArrayList<BoardListResultData> data;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<BoardListResultData> getData() {
        return data;
    }

    public void setData(ArrayList<BoardListResultData> data) {
        this.data = data;
    }


    public static class BoardListResultData implements Parcelable {

        private String err_code;
        private String err_msg;

        private String no;              //글번호
        private String sid;         //제목
        private String grp;        //내용
        private String seq;      //학년반번
        private String lev;     //작성일
        private String subject;  //1-통신,2-건의
        private String content;
        private String file_name1;
        private String file_size1;
        private String file_name2;
        private String file_size2;
        private String visit;
        private String name;
        private String stime;
        private String TotRecord;


        public BoardListResultData() {

        }

        protected BoardListResultData(Parcel in) {
            err_code = in.readString();
            err_msg = in.readString();
            no = in.readString();
            sid = in.readString();
            grp = in.readString();
            seq = in.readString();
            lev = in.readString();
            subject = in.readString();
            content = in.readString();
            file_name1 = in.readString();
            file_size1 = in.readString();
            file_name2 = in.readString();
            file_size2 = in.readString();
            visit = in.readString();
            name = in.readString();
            stime = in.readString();
            TotRecord = in.readString();
        }

        public static final Creator<BoardListResultData> CREATOR = new Creator<BoardListResultData>() {
            @Override
            public BoardListResultData createFromParcel(Parcel in) {
                return new BoardListResultData(in);
            }

            @Override
            public BoardListResultData[] newArray(int size) {
                return new BoardListResultData[size];
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

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getGrp() {
            return grp;
        }

        public void setGrp(String grp) {
            this.grp = grp;
        }

        public String getSeq() {
            return seq;
        }

        public void setSeq(String seq) {
            this.seq = seq;
        }

        public String getLev() {
            return lev;
        }

        public void setLev(String lev) {
            this.lev = lev;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFile_name1() {
            return file_name1;
        }

        public void setFile_name1(String file_name1) {
            this.file_name1 = file_name1;
        }

        public String getFile_size1() {
            return file_size1;
        }

        public void setFile_size1(String file_size1) {
            this.file_size1 = file_size1;
        }

        public String getFile_name2() {
            return file_name2;
        }

        public void setFile_name2(String file_name2) {
            this.file_name2 = file_name2;
        }

        public String getFile_size2() {
            return file_size2;
        }

        public void setFile_size2(String file_size2) {
            this.file_size2 = file_size2;
        }

        public String getVisit() {
            return visit;
        }

        public void setVisit(String visit) {
            this.visit = visit;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getTotRecord() {
            return TotRecord;
        }

        public void setTotRecord(String totRecord) {
            this.TotRecord = totRecord;
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
            parcel.writeString(sid);
            parcel.writeString(grp);
            parcel.writeString(seq);
            parcel.writeString(lev);
            parcel.writeString(subject);
            parcel.writeString(content);
            parcel.writeString(file_name1);
            parcel.writeString(file_size1);
            parcel.writeString(file_name2);
            parcel.writeString(file_size2);
            parcel.writeString(visit);
            parcel.writeString(name);
            parcel.writeString(stime);
            parcel.writeString(TotRecord);
        }
    }
}
