package com.dutch_pay.hdh.sugangmvp.service;


import com.dutch_pay.hdh.sugangmvp.Constants;
import com.dutch_pay.hdh.sugangmvp.data.model.Auth;
import com.dutch_pay.hdh.sugangmvp.data.model.BoardList;
import com.dutch_pay.hdh.sugangmvp.data.model.Consent;
import com.dutch_pay.hdh.sugangmvp.data.model.ConsentDetail;
import com.dutch_pay.hdh.sugangmvp.data.model.Html;
import com.dutch_pay.hdh.sugangmvp.data.model.MyConsent;
import com.dutch_pay.hdh.sugangmvp.data.model.MyConsentDetail;
import com.dutch_pay.hdh.sugangmvp.data.model.PollStudent;
import com.dutch_pay.hdh.sugangmvp.data.model.PollVoteInput;
import com.dutch_pay.hdh.sugangmvp.data.model.Result;
import com.dutch_pay.hdh.sugangmvp.data.model.SvVoteInput;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RestInterFace {

    /**
     * 회원가입인증Api
     *
     * @param school_id
     * @param user_id
     * @param passwd
     * @param phon_type
     */

    @GET(Constants.AUTH_CONFIRM_URL)
    Call<Auth> authConfirm(@Query("itmain") String itmain
            , @Query("school_id") String school_id
            , @Query("user_id") String user_id
            , @Query("passwd") String passwd
            , @Query("reg_id") String reg_id
            , @Query("phon_type") int phon_type);


    /**
     * 수강신청목록Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     */

    @GET(Constants.SERVER_CONSENT_LIST_PATH)
    Call<Consent> getConsent(@Query("itmain") String itmain
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd);


    /**
     * 수강신청상세 Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     * @param LB_no
     */

    @GET(Constants.SERVER_CONSENT_DETAIL_PATH)
    Call<ConsentDetail> getConsentDetail(@Query("itmain") String itmain
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd
            , @Query("LB_no") String LB_no);


    /**
     * 수강신청 Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     * @param LB_no
     */

    @GET(Constants.SERVER_ORDER_SUBMIT_PATH)
    Call<Result> setOrderSubmit(@Query("itmain") String itmain
            , @Query("mode") String mode
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd
            , @Query("LB_no") String LB_no);

    /**
     * 수강신청삭제 Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     * @param LB_no
     */

    @GET(Constants.SERVER_ORDER_SUBMIT_PATH)
    Call<Result> setOrderDelete(@Query("itmain") String itmain
            , @Query("mode") String mode
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd
            , @Query("LB_no") String LB_no);


    /**
     * 나의수강신청목록Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     */

    @GET(Constants.SERVER_MY_CONSENT_LIST_PATH)
    Call<MyConsent> getMyConsent(@Query("itmain") String itmain
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd);


    /**
     * 나의수강신청상세 Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     * @param LB_no
     */

    @GET(Constants.SERVER_MY_CONSENT_DETAIL_PATH)
    Call<MyConsentDetail> getMyConsentDetail(@Query("itmain") String itmain
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd
            , @Query("LB_no") String LB_no);


    /**
     * 설문조사목록 Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     */
    @GET(Constants.SERVER_SV_VOTE_INPUT_PATH)
    Call<SvVoteInput> getSvVoteInput(
            @Query("itmain") String itmain
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd);


    /**
     * 설문조사저장 Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     * @param sv_data
     */
    @GET(Constants.SERVER_SV_VOTE_SUBMIT_PATH)
    Call<Result> setSurveySubmit(
            @Query("itmain") String itmain
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd
            , @Query("sv_data") String sv_data);

    /**
     * 수업만족도 강좌목록 Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     */
    @GET(Constants.SERVER_POLL_STUDENT_PATH)
    Call<PollStudent> getPollStudent(
            @Query("itmain") String itmain
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd);

    /**
     * 수업만족도 조사목록 Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     * @param LB_no
     */
    @GET(Constants.SERVER_POLL_VOTE_INPUT_PATH)
    Call<PollVoteInput> getPollVoteInput(
            @Query("itmain") String itmain
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd
            , @Query("LB_no") String LB_no);


    /**
     * 수업만족도 등록 Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     */
    @GET(Constants.SERVER_POLL_VOTE_SUBMIT_PATH)
    Call<Result> setPollVoteSubmit(
            @Query("itmain") String itmain
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd
            , @Query("LB_no") String LB_no
            , @Query("q_data") String q_data
            , @Query("t_data") String t_data);

    /**
     * 수강시간표 가져오기 Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     */
    @GET(Constants.SERVER_TIME_BOX_PATH)
    Call<Html> getTimeBox(
            @Query("itmain") String itmain
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd);

    /**
     * 수강일정표 가져오기 Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     */
    @GET(Constants.SERVER_SDAL_VIEW_PATH)
    Call<Html> getSdalView(
            @Query("itmain") String itmain
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd);


    /**
     * 공지사항목록 가져오기 Api
     *
     * @param itmain
     * @param school_id
     * @param student_id
     * @param student_no
     * @param auth_passwd
     * @param sid
     * @param Field
     * @param str
     * @param StartRow
     */
    @GET(Constants.SERVER_BOARD_LIST_PATH)
    Call<BoardList> getBoardList(
            @Query("itmain") String itmain
            , @Query("school_id") String school_id
            , @Query("student_id") String student_id
            , @Query("student_no") String student_no
            , @Query("auth_passwd") String auth_passwd
            , @Query("sid") String sid
            , @Query("Field") String Field
            , @Query("str") String str
            , @Query("StartRow") String StartRow
    );
}
