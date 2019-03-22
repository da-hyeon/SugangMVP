package com.dutch_pay.hdh.sugangmvp.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;


public class CommonUtil {


    /**
     * 금액 처리
     *
     * @param won
     */
    public static String getIncodeCommaWon(String won) {
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
    public static String getDecodeCommaWon(String won) {
        int inValues = Integer.parseInt(won);
        String result_int = String.valueOf(inValues).replace(",", "");
        return result_int;
    }


    public static boolean isJumin(String str) {
        return Pattern.matches("^\\d{6}-[1-4]\\d{6}", str);
    }


    public static double parseDouble(String value) {
        if (value == null || value.isEmpty()) {
            return 0;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int parseInt(String value) {
        if (value == null || value.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(value, 10);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static String parseListDate(String timeData) {
        if (timeData == null || timeData.isEmpty() || timeData.length() < 8) {
            return "error";
        }

        String year = timeData.substring(0, 4);
        String month = timeData.substring(4, 6);
        String day = timeData.substring(6, 8);

        return String.format("%s.%s.%s", year, month, day);
    }

    @SuppressLint("SimpleDateFormat")
    public static String parseListTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        Date date = new Date(time);
        return sdf.format(date);
    }

    public static String parseFullTime(String timeData) {
        if (timeData == null || timeData.isEmpty() || timeData.length() < 12) {
            return "error";
        }

        String year = timeData.substring(0, 4);
        String month = timeData.substring(4, 6);
        String day = timeData.substring(6, 8);
        String hour = timeData.substring(8, 10);
        String min = timeData.substring(10, 12);

        return String.format("%s년%s월%s일 %s:%s", year, month, day, hour, min);
    }

    public static String parseTimeFull(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM월dd일 HH:mm분", Locale.KOREA);
        Date date = new Date(time);
        return sdf.format(date);
    }


    @SuppressLint("SimpleDateFormat")
    public static String parseFullTime(long time, String Symbol) {
        SimpleDateFormat sdf = null;
        switch (Symbol) {
            case "년":
                sdf = new SimpleDateFormat("yyyy년MM월dd일", Locale.KOREA);
                break;
            case "월":
                sdf = new SimpleDateFormat("MM월dd일", Locale.KOREA);
                break;
            case "시간":
                sdf = new SimpleDateFormat("hh:mm", Locale.KOREA);
                break;
            case "/":
                sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA);
                break;
            case ".":
                sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
                break;
            case "number":
                sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
                break;
            case "-":
                sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
                break;
            case "초":
                sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.KOREA);
                break;
        }

        Date date = new Date(time);
        return sdf.format(date);
    }


    public static String parseByTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm", Locale.KOREA);
        Date date = new Date(time);
        return sdf.format(date);
    }

    public static String parseByTimeCheck(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("hhmm", Locale.KOREA);
        Date date = new Date(time);
        return sdf.format(date);
    }

    public static long parseByTimeStringToLong(String date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        try {
            d = f.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d.getTime();
    }


    public static boolean isOutsideCity2(String addr1, String addr2) {
        if (addr1 == null || addr2 == null) {
            return false;
        }
        String[] arAddr1 = addr1.split(" ");
        String[] arAddr2 = addr2.split(" ");

        if (arAddr1.length > 0 && arAddr2.length > 0) {
            String str1 = arAddr1[0];
            String str2 = arAddr2[0];

            if (str1.equals(str2)) {
                if (str1.equals("서울") || str1.equals("인천")
                        || str1.equals("대전") || str1.equals("부산")
                        || str1.equals("대구") || str1.equals("광주")
                        || str1.equals("울산") || str1.equals("세종")) {
                    return false;
                } else {
                    if (arAddr1.length > 1 && arAddr2.length > 1) {
                        str1 = arAddr1[1].substring(0, 2);
                        str2 = arAddr2[1].substring(0, 2);
                        if (str1.equals(str2)) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
            } else {
                return true;
            }
        }

        return false;
    }

    public static void setImageToImageViewOnUiThread(final Activity activity, final ImageView view, final Bitmap bitmap) {
        if (null == view) {
            return;
        }

        if (null != activity) {
            activity.runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            view.setImageBitmap(bitmap);
                        }
                    });
        }
    }

    public static void setImageToImageViewOnUiThread(final Activity activity, final ImageView view, final int drawableResourceID) {
        if (null == view) {
            return;
        }

        if (null != activity) {
            activity.runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            view.setImageResource(drawableResourceID);
                        }
                    });
        }
    }


    public static void showMessageBox(Context context, String msg) {
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

    public static String Calculate_Distance(long distance) {
        return (distance / 1000) + " Km";
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void makeToastOnUiThread(final Activity activity, final String msg, final int time) {
        if (null == msg) {
            return;
        }

        if (null != activity) {
            activity.runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, msg, time).show();
                        }
                    });
        }
    }


    public static void setVisibilityViewOnUiThread(final Activity activity, final View view, final int visibility) {
        if (null == view) {
            return;
        }

        if (null != activity) {
            activity.runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            view.setVisibility(visibility);
                        }
                    });
        }
    }

    public static void setTextToTextViewOnUiThread(final Activity activity, final TextView textView, final String text) {
        if (null == textView) {
            return;
        }

        if (null != activity) {
            activity.runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(text);
                        }
                    });
        }
    }

    public static RadioButton getCheckedRadioButton(RadioGroup radioGroup) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View child = radioGroup.getChildAt(i);

            if (child instanceof RadioButton) {
                RadioButton childBtn = (RadioButton) child;
                if (childBtn.isChecked()) {
                    return childBtn;
                }
            }
        }

        return null;
    }

    /**
     * 두 날짜 사이의 차이 계산식
     *
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public static long diffOfDate(String begin, String end) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        Date beginDate = null;
        try {
            beginDate = formatter.parse(begin);
            Date endDate = formatter.parse(end);
            long diff = endDate.getTime() - beginDate.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);

            return diffDays;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String assembleDate(int date) {
        String assemble = String.valueOf(date);

        String year = assemble.substring(0, 4);
        String month = assemble.substring(4, 6);
        String day = assemble.substring(6, 8);

        return year + "." + month + "." + day;
    }
}
