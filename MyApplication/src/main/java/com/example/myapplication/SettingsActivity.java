package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

/**
 * App设置的activity
 * <p/>
 * Created by ludwang on 14-1-1.
 */
public class SettingsActivity extends Activity {

    public static String CLICK_BEHAVIOR = "click_behavior";
    public static String COPY_TIP = "copy_tip";
    public static String HIDDEN_APP = "hidden_app";
    public static String NOTIFICATION_ICON = "notification_icon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 实现navigation up的时候一定要override这个方法
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
