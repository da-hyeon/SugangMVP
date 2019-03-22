package com.dutch_pay.hdh.sugangmvp.ui.activity.auth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.dutch_pay.hdh.sugangmvp.Constants;
import com.dutch_pay.hdh.sugangmvp.MyApplication;
import com.dutch_pay.hdh.sugangmvp.data.manager.RecentlyParentAuthManager;
import com.dutch_pay.hdh.sugangmvp.data.model.Auth;
import com.dutch_pay.hdh.sugangmvp.data.model.ParentAuth;
import com.dutch_pay.hdh.sugangmvp.ui.activity.main.MainActivity;
import com.dutch_pay.hdh.sugangmvp.ui.activity.terms.TermsActivity;
import com.dutch_pay.hdh.sugangmvp.ui.dialog.CommonDialog;
import com.dutch_pay.hdh.sugangmvp.util.SimpleStore;
import com.dutch_pay.hdh.sugangmvp.util.Trace;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("Registered")
public class AuthPresenter extends AuthActivity implements AuthContract.Presenter {

    private AuthContract.View mView;
    private Context mContext;
    private MyApplication mMyApplication;

    /**
     * 생성자
     */
    public AuthPresenter(AuthContract.View mView, Context mContext ) {
        this.mView = mView;
        this.mContext = mContext;
        mMyApplication = MyApplication.getInstance();
    }

    /**
     * Confirm 클릭 이벤트 처리
     */
    @Override
    public void clickConfirm(EditText etSchoolId , EditText etMemberId, EditText etPassword) {
        if (Constants.IS_DEV) {
            if (SimpleStore.getBoolean(Constants.KEY_TERMS)) {
                Intent intent = new Intent(mContext, MainActivity.class);
                moveActivityForm(intent);
            } else {
                Intent intent = new Intent( mContext, TermsActivity.class);
                moveActivityForm(intent);
            }
        } else {
            /**
             * 인증요청
             * */
            if (validateData(etSchoolId , etMemberId, etPassword)) {
                setAuth(etSchoolId , etMemberId, etPassword);
            }
        }
    }

    /**
     * Dialog 확인버튼 클릭 이벤트 처리
     */
    @Override
    public void clickAuthDialogPositiveButton(CommonDialog build) {
        mView.removeDialog(build);
    }

    /**
     * Dialog 취소버튼 클릭 이벤트 처리
     */
    @Override
    public void clickAuthDialogNegativeButton(CommonDialog build) {
        mView.removeDialog(build);
    }

    /**
     * 액티비티 이동 폼
     */
    private void moveActivityForm(Intent intent){
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mView.showActivity(intent);
        mView.removeActivity();
        mView.showAnimation();
    }

    /**
     * 아이디 확인
     */
    private boolean validateData(EditText etSchoolId , EditText etMemberId, EditText etPassword) {
        if (etSchoolId == null || etSchoolId.getText().toString().trim().isEmpty() || etSchoolId.getText().toString().trim().isEmpty()) {
            mView.showToast("학교아이디를 입력하세요.");
            return false;
        }
        if (etMemberId == null || etMemberId.getText().toString().trim().matches("")) {
            mView.showToast("아이디를 입력하세요.");
            return false;
        }

        if (etPassword == null || etPassword.getText().toString().trim().matches("")) {
            mView.showToast("비밀번호를 입력하세요.");
            return false;
        }
        if (!etPassword.getText().toString().trim().equals(etPassword.getText().toString().trim())) {
            mView.showToast("비밀번호가 일치하지 않습니다. 확인 후 다시 입력하세요.");
            return false;
        }

        return true;
    }

    /**
     * 서버 인증요청
     */
    private void setAuth(EditText etSchoolId , EditText etMemberId, EditText etPassword) {
        Trace.e("url 테스트"+etSchoolId.getText().toString().trim());
        showDialog();
        Call<Auth> auth = MyApplication
                .getRestAdapter("http://"+etSchoolId.getText().toString().trim()+Constants.SERVER_URL, true)
                .authConfirm(Constants.SERVER_CALLBACK_KEY
                        , etSchoolId.getText().toString().trim()
                        , etMemberId.getText().toString().trim()
                        , etPassword.getText().toString().trim()
                        , SimpleStore.get(Constants.GCM_TOKEN)
                        , Constants.PHONE_TYPE);

        auth.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(@NonNull Call<Auth> call, @NonNull Response<Auth> response) {
                hideDialog();
                try {
                    if ("error1".equals(response.body().getResult())) {
                        dialogForm("알림" ,"정보가 올바르지 않습니다. \n인증번호와 학사년도 및 회원아이디, 비밀번호를 확인하시기 바랍니다.");
                    } else {
                        Auth auth = response.body();
                        ParentAuth mParentAuth = new ParentAuth(
                                "1"
                                , auth.getServer_jsonp_addr()
                                , etSchoolId.getText().toString().trim()
                                , auth.getStudent_no()
                                , etMemberId.getText().toString().trim()
                                , auth.getStudent_name()
                                , etPassword.getText().toString().trim()
                                , auth.getSystem_name()
                                , auth.getSystem_version()
                                , auth.getSystem_sys());

                        /**
                         * 회원목록 리스트에 저장 (로컬)
                         * */
                        RecentlyParentAuthManager recentlyParentAuthManager = RecentlyParentAuthManager.getInstance();
                        recentlyParentAuthManager.addRecentlyInfo(mParentAuth);
                        mMyApplication.setParentAuth(mParentAuth);
                        mMyApplication.setBASE_URL("http://"+etSchoolId.getText().toString().trim()+Constants.SERVER_URL);
                        Trace.e("비밀번호" + mMyApplication.getParentAuth().getStudent_passwd());
                        Trace.e("비밀번호" + etPassword.getText().toString().trim());

                        Intent intent = new Intent( mContext, TermsActivity.class);
                        moveActivityForm(intent);
                    }

                }catch (Exception e) {
                    dialogForm("알림" ,"학교아이디를 정확하게 입력해주세요." );
                }
            }

            @Override
            public void onFailure(@NonNull Call<Auth> call, @NonNull Throwable t) {
                hideDialog();
                dialogForm("알림" ,t.getMessage());
            }
        });
    }

    private void dialogForm(String title , String content){
        final CommonDialog build = new CommonDialog(mContext);
        build.setCancelable(true);
        mView.changeDialogTitle(build, title);
        mView.changeDialogMessage(build ,content);
        mView.addDialogPositiveButton(build);
        mView.showDialog(build);
    }
}
