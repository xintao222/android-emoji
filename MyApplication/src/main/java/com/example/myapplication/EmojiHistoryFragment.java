package com.example.myapplication;

import java.util.List;

/**
 * Created by ludwang on 14-1-11.
 */
public class EmojiHistoryFragment extends AbstractEmojiListFragment {

    @Override
    protected List<String> getEmojiList() {
        EmojiHistoryDbHelper dbHelper = new EmojiHistoryDbHelper(getActivity().getBaseContext());
        return dbHelper.listSortedHistory();
    }

}
