package com.dutch_pay.hdh.sugangmvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.DatePicker;


public class AppDatePicker extends DatePicker {
    private Runnable r;
    private OnDateChangedListener list;

    public AppDatePicker(Context context) {
        super(context);
        r = new Runnable() {
            public void run() {
                list.onDateChanged(AppDatePicker.this, getYear(), getMonth(), getDayOfMonth());
                check();
            }
        };
    }

    public AppDatePicker(Context context, AttributeSet attr) {
        super(context, attr);
        r = new Runnable() {
            public void run() {
                list.onDateChanged(AppDatePicker.this, getYear(), getMonth(), getDayOfMonth());
                check();
            }
        };
    }

    @Override
    public void init(int year, int month, int date, OnDateChangedListener list) {
        super.init(year, month, date, list);
        this.list = list;
    }

    private void check() {
        postDelayed(r, 200);
    }

    @Override
    public void onAttachedToWindow() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
            check();
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
            removeCallbacks(r);
        super.onDetachedFromWindow();
    }
}

