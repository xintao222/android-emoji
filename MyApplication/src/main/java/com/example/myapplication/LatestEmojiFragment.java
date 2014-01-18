package com.example.myapplication;

import java.util.List;

/**
 * Created by ludwang on 14-1-11.
 */
public class LatestEmojiFragment extends AbstractEmojiListFragment {

    @Override
    protected List<String> getEmojiList() {
        EmojiHistoryDbHelper dbHelper = EmojiHistoryDbHelper.getInstance(getActivity().getBaseContext());
        return dbHelper.listLatestUsedEmoji();
    }

    @Override
    public void onResume() {
        getActivity().getActionBar().setTitle(Constants.TabTags.LATEST);
        super.onResume();
    }
}
