package com.example.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 导航的辅助工具类
 * <p/>
 * Created by ludwang on 14-1-15.
 */
public class MyNavigationUtils {

    public static void displayHome(Fragment fragment) {
        Context context = fragment.getActivity().getBaseContext();
        if (!isSetHiddenApp(context)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        fragment.startActivity(intent);
    }

    private static boolean isSetHiddenApp(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getBoolean(SettingsActivity.HIDDEN_APP, true);
    }

    public static void displayHome(Activity activity) {
        if (!isSetHiddenApp(activity.getBaseContext())) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public static void displayMsgApp(Fragment fragment, String msg) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        intent.setType("text/plain");
        fragment.startActivity(intent);
    }
}
