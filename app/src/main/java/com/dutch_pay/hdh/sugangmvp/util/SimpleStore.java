package com.dutch_pay.hdh.sugangmvp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.dutch_pay.hdh.sugangmvp.MyApplication;


public class SimpleStore {
	private static SharedPreferences m_Pref = null;
	private static MyApplication myApplication;

	private static void open() {
		myApplication = MyApplication.getInstance();
		if (m_Pref == null) {
			m_Pref = myApplication.getSharedPreferences("sugang_global_store", Context.MODE_PRIVATE);
		}
	}

	public static void put(String key, String value) {
		open();
		Editor editor = m_Pref.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static void put(String key, long value) {
		open();
		Editor editor = m_Pref.edit();
		editor.putLong(key, value);
		editor.commit();
	}


	public static void put(String key, int value) {
		open();
		Editor editor = m_Pref.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static void put(String key, boolean value) {
		open();
		Editor editor = m_Pref.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static String get(String key) {
		open();
		return m_Pref.getString(key, "").trim();
	}

	public static boolean getBoolean(String key) {
		open();
		return m_Pref.getBoolean(key, false);
	}

	public static boolean getDefaultTrueBoolean(String key) {
		open();
		return m_Pref.getBoolean(key, true);
	}

	public static int getInt(String key) {
		open();
		return m_Pref.getInt(key, 0);
	}


	public static long getLong(String key) {
		open();
		return m_Pref.getLong(key, 0);
	}

	public static void remove(String key) {
		open();
		Editor editor = m_Pref.edit();
		editor.remove(key);
		editor.commit();
	}
}
