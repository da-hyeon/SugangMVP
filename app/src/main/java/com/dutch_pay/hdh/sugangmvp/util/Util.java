package com.dutch_pay.hdh.sugangmvp.util;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mocha on 2016-07-28.
 */
public class Util {


    /**
     * 앱설치여부
     */

    public static boolean isInstalledApp(Context context, String packageName) {
        List<PackageInfo> packageList = context.getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for (PackageInfo info : packageList) {
            if (info != null && info.packageName != null && info.packageName.equals(packageName))
                return true;
        }
        return false;
    }


    public static String getMasking(String name){
        name = name.replaceAll(name.substring(1,2), "*");
        return name;
    }

    /**
     * ANIMATION
     * 숨기기
     */
    public static void hideAnimationRow(final ViewGroup viewGroup) {
        int startHeight = viewGroup.getMeasuredHeight();
        Trace.d("VISIBLE= " + startHeight);

        ValueAnimator anim = ValueAnimator.ofInt(startHeight, 0);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
                layoutParams.height = val;
                viewGroup.setLayoutParams(layoutParams);
            }
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                viewGroup.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.setDuration(300);
        anim.start();
    }

    /**
     * ANIMATION
     * 보여주기
     */

    public static void showAnimationRow(final ViewGroup viewGroup) {
        viewGroup.setVisibility(View.VISIBLE);
        viewGroup.measure(0, 0);
        Trace.d("GONE= " + viewGroup.getMeasuredHeight());

        ValueAnimator anim = ValueAnimator.ofInt(0, viewGroup.getMeasuredHeight());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
                layoutParams.height = val;
                viewGroup.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(300);
        anim.start();
    }


    /**
     * email주소
     * 유효성검사
     */
    public static boolean isEmail(String email) {
        if (email == null) return false;
        boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
        return b;
    }


    /**
     * 핸드폰번호
     * 유효성검사
     */
    public static boolean isValidCellPhoneNumber(String cellphoneNumber) {
        boolean returnValue = false;
        Log.i("cell", cellphoneNumber);
        String regex = "^\\s*(010|011|012|013|014|015|016|017|018|019)(-|\\)|\\s)*(\\d{3,4})(-|\\s)*(\\d{4})\\s*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(cellphoneNumber);
        if (m.matches()) {
            returnValue = true;
        }
        return returnValue;
    }


    /**
     * 일반전화번호
     * 유효성검사
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        boolean returnValue = false;
        String regex = "^\\s*(02|031|032|033|041|042|043|051|052|053|054|055|061|062|063|064|070)?(-|\\)|\\s)*(\\d{3,4})(-|\\s)*(\\d{4})\\s*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNumber);
        if (m.matches()) {
            returnValue = true;
        }
        return returnValue;
    }


    /**
     * 금액 처리
     *
     * @param won
     */
    public static String Comma_won(String won) {
        int inValues = Integer.parseInt(won);
        DecimalFormat Commas = new DecimalFormat("#,###");
        String result_int = (String) Commas.format(inValues);
        return result_int;
    }

    /**
     * 금액 처리
     *
     * @param won
     */
    public static String DecodeComma_won(String won) {
        int inValues = Integer.parseInt(won);
        String result_int = String.valueOf(inValues).replace(",", "");
        return result_int;
    }


    /**
     * 네트워크 연결 상태 확인
     *
     * @param context 컨텍스트
     * @return boolean 네트워크 연결 여부
     */
    public static boolean isNetworkStatus(Context context) {
        Trace.d("check network!");

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo_mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo netInfo_wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo netInfo_lte = cm.getNetworkInfo(ConnectivityManager.TYPE_WIMAX);

        if ((netInfo_mobile == null) && (netInfo_wifi == null) && (netInfo_lte == null)) {
            return false;
        }
        try {
            if ((netInfo_mobile != null) && (netInfo_mobile.getState() == NetworkInfo.State.CONNECTED)) {
                Trace.d("check mobile!");
                return true;
            } else if ((netInfo_wifi != null) && (netInfo_wifi.getState() == NetworkInfo.State.CONNECTED)) {
                Trace.d("check wifi!");
                return true;
            } else if ((netInfo_lte != null) && (netInfo_lte.getState() == NetworkInfo.State.CONNECTED)) {
                Trace.d("check lte!");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static RSAPublicKey getPublicKey(InputStream inputStream) throws Exception {

        byte[] keyBytes = _toByteArray(inputStream);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(spec);
    }

    private static byte[] _toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[8192];
        while ((nRead = input.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }


    public static void showMessageBox(Context context, String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

        TextView messageView = (TextView) dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);

        TextView titleView = (TextView) dialog.findViewById(context.getResources().getIdentifier("alertTitle", "id", "android"));
        if (titleView != null) {
            titleView.setGravity(Gravity.CENTER);
        }
    }

    public static void showToast(Context context, String msg)
    {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
