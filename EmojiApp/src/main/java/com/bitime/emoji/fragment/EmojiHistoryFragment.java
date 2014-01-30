package com.bitime.emoji.fragment;

import android.app.ActionBar;

import com.bitime.emoji.R;
import com.bitime.emoji.helper.HistoryDbHelper;
import com.bitime.emoji.helper.PreferencesHelper;

import java.util.List;

/**
 * Created by Jun Wang on 14-1-11.
 */
public class EmojiHistoryFragment extends AbstractEmojiListFragment {

    @Override
    protected List<String> getEmojiList() {
        HistoryDbHelper dbHelper = HistoryDbHelper.getInstance(getActivity().getBaseContext());
        return dbHelper.listSortedHistory(PreferencesHelper.getHistorySize(getActivity().getBaseContext()));
    }

    @Override
    public void onResume() {
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setTitle(getResources().getString(R.string.tab_history));
        actionBar.setSubtitle(null);
        super.onResume();
    }
}
