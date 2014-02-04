package com.bitime.emoji;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bitime.emoji.fragment.CategoryFragment;
import com.bitime.emoji.fragment.ListEmojiFragment;
import com.bitime.emoji.fragment.RankPopupDialogFragment;
import com.bitime.emoji.helper.HistoryDbHelper;
import com.bitime.emoji.helper.PreferencesHelper;
import com.bitime.emoji.setting.SettingsActivity;
import com.bitime.emoji.tab.AbstractTabActivity;
import com.bitime.emoji.tab.TabFactory;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Arrays;
import java.util.List;

import static com.bitime.emoji.CategoryConfig.EmojiCategory;
import static com.bitime.emoji.Constants.FragmentTags;
import static com.bitime.emoji.Constants.TabTags;

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
        initActionBar();
        loadAdView();
        new RankPopupDialogFragment(this).show();

    }

    /**
     * init action bar with navigation tab
     */
    private void initActionBar() {
        TabFactory tabFactory = new TabFactory(this);
        ActionBar actionBar = getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.addTab(tabFactory.createCategoryTab());
        actionBar.addTab(tabFactory.createHistoryTab());
        actionBar.addTab(tabFactory.createLatestTab());
    }

    /**
     * try to load admob view, if load failed then hidden admob view
     */
    private void loadAdView() {
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = getAdRequestBuilder().build();
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                mAdView.setVisibility(View.GONE);
                Log.i(this.getClass().getSimpleName(), "load ads failed");
                super.onAdFailedToLoad(errorCode);
            }

        });

        mAdView.loadAd(adRequest);
    }

    private void setNotification() {
        if (!PreferencesHelper.isShowNotification(getBaseContext())) {
            return;
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(getResources().getString(R.string.notification_title))
                .setContentText(getResources().getString(R.string.notification_text));
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
        TypedArray array = getResources().obtainTypedArray(R.array.test_machine_ids);
        for (int i = 0; i < array.length(); i++) {
            String machine = array.getString(i);
            adRequestBuilder.addTestDevice(machine);
        }
        array.recycle();
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
            case R.id.clear_history:
                HistoryDbHelper.getInstance(getBaseContext()).clearHistory();
                Toast.makeText(getBaseContext(), getResources().getString(R.string.clear_history_success), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCategorySelected(EmojiCategory category) {
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
