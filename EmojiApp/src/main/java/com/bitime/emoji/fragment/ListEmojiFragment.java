package com.bitime.emoji.fragment;

import android.util.Log;

import com.bitime.emoji.CategoryConfig;
import com.bitime.emoji.R;

import java.util.Arrays;
import java.util.List;

import static com.bitime.emoji.CategoryConfig.EmojiCategory;

/**
 * Created by Jun Wang on 14-1-11.
 */
public class ListEmojiFragment extends AbstractEmojiListFragment {
    private EmojiCategory category;

    public ListEmojiFragment() {
    }

    public ListEmojiFragment setCategory(EmojiCategory category) {
        this.category = category;
        return this;
    }

    @Override
    protected List<String> getEmojiList() {
        Log.i(this.getClass().getSimpleName(), String.format("render category for '%s'", category));
        String[] resources = category.getResources();
        return Arrays.asList(resources);
    }

    @Override
    public void onResume() {
        getActivity().getActionBar().setTitle(getResources().getString(R.string.tab_category) + " | " +
                getResources().getString(category.getNameResourceId()));
        super.onResume();
    }
}