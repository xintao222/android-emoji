package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.*;

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
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.addTab(createTab(tabView, actionBar, TabTags.CATEGORY, FragmentTags.CATEGORY, CategoryFragment.class));
        actionBar.addTab(createTab(tabView, actionBar, TabTags.HISTORY, FragmentTags.HISTORY, EmojiHistoryFragment.class));
        actionBar.addTab(createTab(tabView, actionBar, TabTags.LATEST, FragmentTags.LATEST, LatestEmojiFragment.class));

        mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(getResources().getString(R.string.ad_unit_id));
        // Create an ad request.
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

        // Optionally populate the ad request builder.
        adRequestBuilder.addTestDevice("FCA35BE16BC430A8FFA13D0F90FE18A7");


        LinearLayout adsView = (LinearLayout) findViewById(R.id.ads_view);
        adsView.addView(mAdView);
        mAdView.loadAd(adRequestBuilder.build());

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
