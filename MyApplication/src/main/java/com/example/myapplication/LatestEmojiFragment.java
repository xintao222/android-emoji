package com.example.myapplication;

import java.util.List;

/**
 * Created by ludwang on 14-1-11.
 */
public class LatestEmojiFragment extends AbstractEmojiListFragment {

    @Override
    protected List<String> getEmojiList() {
        EmojiHistoryDbHelper dbHelper = EmojiHistoryDbHelper.getInstance(getActivity().getBaseContext());
        return dbHelper.listLatestUsedEmoji(PreferencesHelper.getHistorySize(getActivity().getBaseContext()));
    }

    @Override
    public void onResume() {
        getActivity().getActionBar().setTitle(getResources().getString(R.string.tab_latest));
        super.onResume();
    }
}
