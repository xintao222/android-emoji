package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static List<Map<String, String>> get2LineMap() {
        List<Map<String, String>> lines = new ArrayList<Map<String, String>>();
        for (EmojiCategory emojiCategory : EmojiCategory.values()) {
            Map<String, String> line = new HashMap<String, String>();
            line.put("First Line", emojiCategory.name());
            line.put("Second Line", emojiCategory.description);
            lines.add(line);
        }

        return lines;
    }

    public static int getResourceId(String category) {
        return EmojiCategory.valueOf(category).resourceId;
    }

    enum EmojiCategory {
        smile(R.array.smile_list, "d(d＇∀＇)"),
        love(R.array.love_list, "(*´з｀*)"),
        cry(R.array.cry_list, "(〒︿〒)"),
        helpless(R.array.helpless_list, "(´ﾟдﾟ`)"),
        shy(R.array.shy_list, "(ﾉ∀`*)"),
        surprise(R.array.surprise_list, "(ﾟд⊙)"),
        angry(R.array.angry_list, "(￣ε(#￣)"),
        evil(R.array.evil_list, "(☄◣ω◢)☄");

        private int resourceId;
        private String description;

        EmojiCategory(int resourceId, String description) {
            this.resourceId = resourceId;
            this.description = description;
        }
    }
}
