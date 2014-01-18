package com.example.myapplication;

import android.app.ActionBar;
import android.app.Fragment;
import android.view.View;

import static android.app.ActionBar.Tab;

/**
 * Created by ludwang on 14-1-18.
 */
public class TabFactory {

    private MainActivity activity;
    private View tabView;
    private ActionBar actionBar;

    public TabFactory(MainActivity activity) {
        this.activity = activity;
        this.tabView = activity.findViewById(R.id.tab_view);
        this.actionBar = activity.getActionBar();
    }

    public Tab createCategoryTab() {
        return createTab(tabView,
                actionBar,
                Constants.TabTags.CATEGORY,
                R.drawable.ic_category,
                Constants.FragmentTags.CATEGORY,
                CategoryFragment.class);
    }

    public Tab createHistoryTab() {
        return createTab(tabView,
                actionBar,
                Constants.TabTags.HISTORY,
                R.drawable.ic_favorite,
                Constants.FragmentTags.HISTORY,
                EmojiHistoryFragment.class);
    }

    public Tab createLatestTab() {
        return createTab(tabView,
                actionBar,
                Constants.TabTags.LATEST,
                R.drawable.ic_latest,
                Constants.FragmentTags.LATEST,
                LatestEmojiFragment.class);
    }

    private <T extends Fragment> Tab createTab(View tabView, ActionBar actionBar, String tabTag,
                                               int iconId, String fragmentTag, Class<T> fragmentClass) {
        return actionBar.newTab()
                .setIcon(iconId)
                .setTag(tabTag)
                .setTabListener(new TabListener<T>(activity, tabView, fragmentTag, fragmentClass));
    }

}
