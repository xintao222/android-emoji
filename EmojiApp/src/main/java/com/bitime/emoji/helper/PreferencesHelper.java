package com.bitime.emoji.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.bitime.emoji.Constants;

/**
 * Created by ludwang on 14-1-25.
 */
public class PreferencesHelper {
    private static long WEEK = 7 * 24 * 60 * 60 * 1000;
    private static String CLICK_BEHAVIOR = "click_behavior";
    private static String COPY_TIP = "copy_tip";
    private static String HIDDEN_APP = "hidden_app";
    private static String NOTIFICATION_ICON = "notification_icon";
    private static String historySizePrefKey = "historySize";

    public static Integer getHistorySize(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getInt(historySizePrefKey, 20);
    }

    public static boolean isSendAsClickBehavior(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String clickBehavior = sharedPref.getString(CLICK_BEHAVIOR, Constants.CLICK_BEHAVIOR_SEND);
        return Constants.CLICK_BEHAVIOR_SEND.equals(clickBehavior);
    }

    public static boolean isNeedCopyTip(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getBoolean(COPY_TIP, true);
    }

    public static boolean isSetHiddenApp(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getBoolean(HIDDEN_APP, true);
    }


    public static boolean isShowNotification(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getBoolean(NOTIFICATION_ICON, true);
    }

    public static void disablePopupPreference(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit().putBoolean("popup_preference", false);
        sharedPref.edit().commit();
    }

    public static void updateLatestPopupTime(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit().putLong("popup_preference_time", System.currentTimeMillis());
        sharedPref.edit().commit();
    }

    public static boolean isShowPopupPreference(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        if (!sharedPref.getBoolean("popup_preference", true)) {
            return false;
        }
        long totalUsedTimes = sharedPref.getLong("total_used_times", 0);
        Log.i(PreferencesHelper.class.getSimpleName(), "current total used time is : " + totalUsedTimes);
        if (totalUsedTimes < 20) {
            return false;
        }
        long lastDisplayTime = sharedPref.getLong("popup_preference_time", System.currentTimeMillis());
        if (lastDisplayTime + WEEK > System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    public static void increaseTotalUsedTimes(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        long totalUsedTimes = sharedPref.getLong("total_used_times", 0);
        sharedPref.edit().putLong("total_used_times", ++totalUsedTimes);
        sharedPref.edit().commit();
    }
}
