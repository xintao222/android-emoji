package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Arrays;
import java.util.List;

import static com.example.myapplication.Constants.FragmentTags;
import static com.example.myapplication.Constants.TabTags;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends AbstractTabActivity implements CategoryFragment.OnCategorySelectedListener {
    private AdView mAdView;

    @Override
    protected List<String> getTabTags() {
        return Arrays.asList(TabTags.CATEGORY, TabTags.HISTORY, TabTags.LATEST);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabFactory tabFactory = new TabFactory(this);
        ActionBar actionBar = getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.addTab(tabFactory.createCategoryTab());
        actionBar.addTab(tabFactory.createHistoryTab());
        actionBar.addTab(tabFactory.createLatestTab());

        mAdView = (AdView) findViewById(R.id.adView);
        mAdView.loadAd(getAdRequestBuilder().build());

    }


    private void setNotification() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if (!sharedPref.getBoolean(SettingsActivity.NOTIFICATION_ICON, true)) {
            return;
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Jun Wang")
                .setContentText("Hello World!");
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);
        mBuilder.setContentIntent(PendingIntent.getActivity(getBaseContext(), 1, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT));
        Notification notification = mBuilder.build();
        notification.flags |= Notification.FLAG_NO_CLEAR;
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(11, notification);
    }

    private AdRequest.Builder getAdRequestBuilder() {
        // Create an ad request.
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        // Optionally populate the ad request builder.
        adRequestBuilder.addTestDevice(getResources().getString(R.string.test_machine_id));
        return adRequestBuilder;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.back_home:
                ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
                MyNavigationUtils.displayHome(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    public void onCategorySelected(String category) {
        Fragment categoryFragment = getFragmentManager().findFragmentByTag(FragmentTags.CATEGORY);
        Fragment listEmojiFragment = new ListEmojiFragment().setCategory(category);
        getFragmentManager().beginTransaction()
                .detach(categoryFragment)
                .add(categoryFragment.getId(), listEmojiFragment, FragmentTags.EMOJI_LIST)
                .commit();

        getBackStacks().get(TabTags.CATEGORY).push(FragmentTags.EMOJI_LIST);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdView.resume();
        setNotification();
    }

    @Override
    public void onPause() {
        // Pause the AdView.
        mAdView.pause();

        super.onPause();
    }

    @Override
    public void onDestroy() {
        // Destroy the AdView.
        mAdView.destroy();
        super.onDestroy();
    }
}
