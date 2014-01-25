package com.bitime.emoji.fragment;

import android.util.Log;

import com.bitime.emoji.CategoryConfig;
import com.bitime.emoji.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jun Wang on 14-1-11.
 */
public class ListEmojiFragment extends AbstractEmojiListFragment {
    private String category;

    public ListEmojiFragment() {
    }

    public ListEmojiFragment setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    protected List<String> getEmojiList() {
        Log.i(this.getClass().getSimpleName(), String.format("render category for '%s'", category));
        String[] resources = CategoryConfig.getListResourceId(category);
        return Arrays.asList(resources);
    }

    @Override
    public void onResume() {
        getActivity().getActionBar().setTitle(getResources().getString(R.string.tab_category) + " | " +
                getResources().getString(CategoryConfig.getNameResourceId(category)));
        super.onResume();
    }
}