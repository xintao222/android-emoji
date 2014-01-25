package com.example.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;

/**
 * 导航的辅助工具类
 * <p/>
 * Created by ludwang on 14-1-15.
 */
public class MyNavigationUtils {

    public static void displayHome(Fragment fragment) {
        Context context = fragment.getActivity().getBaseContext();
        if (!PreferencesHelper.isSetHiddenApp(context)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        fragment.startActivity(intent);
    }

    public static void displayHome(Activity activity) {
        if (!PreferencesHelper.isSetHiddenApp(activity.getBaseContext())) {
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
