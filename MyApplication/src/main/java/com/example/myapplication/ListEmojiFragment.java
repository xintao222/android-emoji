package com.example.myapplication;

import android.content.res.TypedArray;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ludwang on 14-1-11.
 */
public class ListEmojiFragment extends AbstractEmojiListFragment {
    private String category;

    public ListEmojiFragment(String category) {
        this.category = category;
    }

    @Override
    protected List<String> getEmojiList() {
        Log.i(this.getClass().getSimpleName(), String.format("render category for '%s'", category));
        List<String> emojiList = new ArrayList<String>();
        TypedArray list = findList(category);
        for (int index = 0; index < list.length(); index++) {
            emojiList.add(list.getText(index).toString());
        }
        return emojiList;
    }

    private TypedArray findList(String category) {
        if ("smile".equals(category)) {
            return getResources().obtainTypedArray(R.array.smile_list);
        } else if ("cry".equals(category)) {
            return getResources().obtainTypedArray(R.array.cry_list);
        }

        return null;
    }
}