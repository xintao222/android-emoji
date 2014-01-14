package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Arrays;
import java.util.List;

import static android.app.ActionBar.Tab;
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

        View tabView = findViewById(R.id.tab_view);

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.addTab(createTab(tabView, actionBar, TabTags.CATEGORY, FragmentTags.CATEGORY, CategoryFragment.class));
        actionBar.addTab(createTab(tabView, actionBar, TabTags.HISTORY, FragmentTags.HISTORY, EmojiHistoryFragment.class));
        actionBar.addTab(createTab(tabView, actionBar, TabTags.LATEST, FragmentTags.LATEST, LatestEmojiFragment.class));

        mAdView = (AdView) findViewById(R.id.adView);
        mAdView.loadAd(getAdRequestBuilder().build());
        setNotification();
    }


    private void setNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Jun Wang")
                .setContentText("Hello World!");
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);
        mBuilder.setContentIntent(PendingIntent.getActivity(getBaseContext(), 1, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT));
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(11, mBuilder.build());
    }

    private AdRequest.Builder getAdRequestBuilder() {
        // Create an ad request.
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        // Optionally populate the ad request builder.
        adRequestBuilder.addTestDevice(getResources().getString(R.string.test_machine_id));
        return adRequestBuilder;
    }

    private <T extends Fragment> Tab createTab(View tabView, ActionBar actionBar, String tabTag,
                                               String fragmentTag, Class<T> fragmentClass) {
        return actionBar.newTab()
                .setText(tabTag)
                .setTag(tabTag)
                .setTabListener(new TabListener<T>(this, tabView, fragmentTag, fragmentClass));
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
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    public void onCategorySelected(String category) {
        Fragment categoryFragment = getFragmentManager().findFragmentByTag(FragmentTags.CATEGORY);
        Fragment listEmojiFragment = new ListEmojiFragment(category);
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
