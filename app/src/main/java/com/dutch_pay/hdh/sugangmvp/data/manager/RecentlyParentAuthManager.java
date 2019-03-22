package com.dutch_pay.hdh.sugangmvp.data.manager;




import com.dutch_pay.hdh.sugangmvp.Constants;
import com.dutch_pay.hdh.sugangmvp.data.model.ParentAuth;
import com.dutch_pay.hdh.sugangmvp.util.SimpleStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecentlyParentAuthManager {
    private static RecentlyParentAuthManager ourInstance = new RecentlyParentAuthManager();

    public static RecentlyParentAuthManager getInstance() {
        return ourInstance;
    }

    ArrayList <ParentAuth> mArList;

    private RecentlyParentAuthManager() {
        loadParentAuthList();
    }

//    private final static Comparator<LocationInfo> recentlyLocationInfoComparator = new Comparator<LocationInfo>() {
//        private final Collator collator = Collator.getInstance();
//
//        // asc
//        @Override
//        public int compare(LocationInfo object1, LocationInfo object2) {
//            return (object1.getTime() > object2.getTime() ? 1 : -1);
//        }
//    };

    private void saveParentAuthList()
    {
        //Collections.sort(mArList, recentlyLocationInfoComparator);

        if (mArList.size() > 0) {
            JSONArray jsonArray = new JSONArray();

            for (int i=0; i<mArList.size(); i++) {
                ParentAuth info = mArList.get(i);
                jsonArray.put(info.toJsonObject());
            }
            SimpleStore.put(Constants.KEY_RECENTLY_AUTH_LIST, jsonArray.toString());
        }
    }

    private void loadParentAuthList()
    {
        mArList = new ArrayList();

        String strList = SimpleStore.get(Constants.KEY_RECENTLY_AUTH_LIST);
        if( strList != null && strList.isEmpty() == false ) {
            try {
                JSONArray jsonArray = new JSONArray(strList);

                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);

                    ParentAuth info = new ParentAuth();
                    info.parseFromJson(object);

                    mArList.add(info);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void addRecentlyInfo(ParentAuth info)
    {
        if (info == null)
            return;

        String id = info.getStudent_id().trim();

        for (ParentAuth tempInfo : mArList) {
            if (id.equals(tempInfo.getStudent_id())) {

                // 삭제 후
                mArList.remove(tempInfo);
                // 첫번째 추가
                mArList.add(0, info);

                saveParentAuthList();
                return;
            }
        }
        mArList.add(0, info);
        saveParentAuthList();
    }

    public void delRecentlyInfo(ParentAuth info)
    {
        if (info == null)
            return;

        mArList.remove(info);

        saveParentAuthList();
    }

    public void clearRecentlyInfo() {
        mArList.clear();
        SimpleStore.remove(Constants.KEY_RECENTLY_AUTH_LIST);
    }

    public ArrayList<ParentAuth> getRecentlyList()
    {
        return mArList;
    }

    public ParentAuth getLastRecentlyInfo()
    {
        if (mArList.size() > 0) {
            return mArList.get(mArList.size() - 1);
        }
        else {
            return null;
        }
    }
}
