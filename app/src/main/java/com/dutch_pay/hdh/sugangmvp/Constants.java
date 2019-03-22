package com.dutch_pay.hdh.sugangmvp;

public class Constants {

    public static final boolean USIM = true;
    public static final boolean DUMMY = false;
    public static final boolean IS_DEV = false;
    public static final boolean IS_AUTH = true;


    public static final int PHONE_TYPE = 1;
    public static final String GCM_TOKEN = "gcm";




    public static final String INSERT ="insert";
    public static final String DELETE ="delete";


    //개인정보처리 방침

    public static final String KEY_TERMS ="terms";

    /** Badge 데이터 키 값 */
    public static final String BADGER_COUNT = "badgerCount";
    public static final String KEY_RECENTLY_AUTH_LIST = "authList";
    public static final String KEY_PARENT_AUTH = "parentAuth";
    public static final String BASE_URL = "baseUrl";

    public static final String KEY_CONSENT_NO = "consentNo";       //수강신청 인덱스 키값
    public static final String KEY_SATISFACTION_NO = "satisfactionNo";       //수업만조도 인덱스 키값
    public static final String KEY_SID_NO = "sidNo";       //공지사항 인덱스 키값


    /**
     * API URL
     * */

    public static final String AUTH_CONFIRM_URL = "/sugang/app_modules/student/auth/auth_confirm.php";
    public static final String SERVER_CALLBACK_KEY = "itmain";
    public static String SERVER_URL = ".cafe24.com/"; //2부터 url뒤에 /를 입력해야 합니다.
    //서버메뉴경로
    public static final String SERVER_CONSENT_LIST_PATH = "/sugang/app_modules/student/consentlist.php"; //신청강좌목록
    public static final String SERVER_CONSENT_DETAIL_PATH = "/sugang/app_modules/student/orderlist.php"; //신청강좌상세
    public static final String SERVER_ORDER_SUBMIT_PATH = "/sugang/app_modules/student/order_submit.php"; //수강신청
    public static final String SERVER_MY_CONSENT_LIST_PATH = "/sugang/app_modules/student/myconsentlist.php"; //나의신청강좌
    public static final String SERVER_MY_CONSENT_DETAIL_PATH = "/sugang/app_modules/student/myorderlist.php"; //나의신청강좌상세
    public static final String SERVER_SV_VOTE_INPUT_PATH = "/sugang/app_modules/student/sv_vote_input.php"; //설문조사 목록
    public static final String SERVER_SV_VOTE_SUBMIT_PATH = "/sugang/app_modules/student/sv_vote_submit.php"; //설문 저장

    public static final String SERVER_POLL_STUDENT_PATH = "/sugang/app_modules/student/poll_student.php"; //수업만족도 목록
    public static final String SERVER_POLL_VOTE_INPUT_PATH = "/sugang/app_modules/student/poll_vote_input.php"; //수업만족도 목록
    public static final String SERVER_POLL_VOTE_SUBMIT_PATH = "/sugang/app_modules/student/poll_vote_submit.php"; //수업만족도 저장

    public static final String SERVER_SDAL_VIEW_PATH = "/sugang/app_modules/student/sdal_view.php"; //수강일정표
    public static final String SERVER_TIME_BOX_PATH = "/sugang/app_modules/student/time_box.php"; //수강 시간표

    public static final String SERVER_BOARD_LIST_PATH = "/sugang/app_modules/student/boardlist.php"; //공지사항목록



}