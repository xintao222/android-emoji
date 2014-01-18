package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ludwang on 14-1-18.
 */
public class CategoryConfig {

    public static List<String> getCategoryList() {
        List<String> categoryList = new ArrayList<String>();
        for (EmojiCategory category : EmojiCategory.values()) {
            categoryList.add(category.name());
        }

        return categoryList;
    }

    public static int getResourceId(String category) {
        return EmojiCategory.valueOf(category).resourceId;
    }

    enum EmojiCategory {
        smile(R.array.smile_list),
        love(R.array.love_list),
        cry(R.array.cry_list),
        helpless(R.array.helpless_list),
        shy(R.array.shy_list),
        surprise(R.array.surprise_list),
        angry(R.array.angry_list),
        evil(R.array.evil_list);
        private int resourceId;

        EmojiCategory(int resourceId) {
            this.resourceId = resourceId;
        }
    }
}
