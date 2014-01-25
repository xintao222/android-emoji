package com.bitime.emoji.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bitime.emoji.Constants;

/**
 * Created by ludwang on 14-1-25.
 */
public class PreferencesHelper {

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
}
