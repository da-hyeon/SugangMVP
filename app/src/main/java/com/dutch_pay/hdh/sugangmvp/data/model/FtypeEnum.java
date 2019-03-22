package com.dutch_pay.hdh.sugangmvp.data.model;


import java.util.ArrayList;

public enum FtypeEnum {
      A(1, 1, "아주불만")
    , B(2, 2, "불만")
    , C(3, 3, "보통")
    , D(4,  4, "만족")
    , E(5, 5, "아주만족");


    private int mIndex;
    private int mClassId;
    private String mClassName;

    FtypeEnum(int index, int classId, String className){
        this.mIndex = index;
        this.mClassName = className;
        this.mClassId = classId;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    public int getClassId() {
        return mClassId;
    }

    public void setClassId(int mClassId) {
        this.mClassId = mClassId;
    }

    public String getClassName() {
        return mClassName;
    }

    public void setClassName(String mClassName) {
        this.mClassName = mClassName;
    }

    public static FtypeEnum getClassFromIndex(int index){
        for(FtypeEnum type : FtypeEnum.values()){
            if(type.getIndex() == index){
                return type;
            }
        }

        return null;
    }
    public static FtypeEnum getClassFromSeq(int connActivityId){
        for(FtypeEnum type : FtypeEnum.values()){
            if(type.getClassId() == connActivityId){
                return type;
            }
        }

        return null;
    }

    public static ArrayList<String> getRelatedNameArray(){
        ArrayList<String> areaNameList = new ArrayList();
        for(FtypeEnum type : FtypeEnum.values()){
            areaNameList.add(type.getClassName());
        }
        return areaNameList;
    }

}
