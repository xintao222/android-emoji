package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by ludwang on 14-1-1.
 */
public class SettingsActivity extends Activity {

    public static String CLICK_BEHAVIOR = "click_behavior";


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
