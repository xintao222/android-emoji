package com.bitime.emoji.fragment;

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
        getActivity().getActionBar().setTitle(getResources().getString(R.string.tab_history));
        super.onResume();
    }
}
