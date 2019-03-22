package com.dutch_pay.hdh.sugangmvp.ui.activity.terms;

import android.content.Context;
import android.content.Intent;

import com.dutch_pay.hdh.sugangmvp.Constants;
import com.dutch_pay.hdh.sugangmvp.ui.activity.main.MainActivity;
import com.dutch_pay.hdh.sugangmvp.util.SimpleStore;

public class TermsPresenter implements TermsContract.Presenter {

    private TermsContract.View mView;
    private Context mContext;

    private boolean checkTOS[];
    private boolean checkAllTOS;

    /**
     * 생성자
     */
    TermsPresenter(TermsContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        checkTOS = new boolean[2];
        checkAllTOS = false;
    }

    /**
     * 전체동의 클릭
     */
    @Override
    public void clickAllcheckBox() {
        checkAllTOS = !checkAllTOS;
        mView.changeAllTOS(checkAllTOS);
        for (int i = 0; i < checkTOS.length; i++) {
            checkTOS[i] = checkAllTOS;
            mView.changeTOS(i, checkAllTOS);
        }
    }

    /**
     * 약관동의 클릭
     */
    @Override
    public void clickCheckBox(int index) {
        checkTOS[index] = !checkTOS[index];
        mView.changeTOS(index, checkTOS[index]);

        checkAllTOS = true;
        for(boolean check: checkTOS){
            if (!check) {
                checkAllTOS = false;
            }
        }
        mView.changeAllTOS(checkAllTOS);
    }

    /**
     * 다음버튼 클릭
     */
    @Override
    public void clickTermsConfirm() {
        if(termsValidation()) {
            SimpleStore.put(Constants.KEY_TERMS, true);
            Intent intent = new Intent(mContext, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mView.showActivity(intent);
            mView.removeActivity();
            mView.showAnimation();
        }
    }

    /**
     * 미동의 항목 알아내기
     */
    private boolean termsValidation() {
        if (!checkTOS[0] && !checkTOS[1]) {
            mView.showToast("개인정보 수집,이용 동의와 위 학생의 학부모님 방과후학교 운영시스템의 수강신청에 동의하십시오.");
            return false;
        } else{
            if (!checkTOS[0]) {
                mView.showToast("개인정보 수집,이용 동의에 동의하십시오.");
                return false;
            } else if (!checkTOS[1]) {
                mView.showToast("위 학생의 학부모님 방과후학교 운영시스템의 수강신청 동의 하십시오.");
                return false;
            }
        }
        return true;
    }

}
