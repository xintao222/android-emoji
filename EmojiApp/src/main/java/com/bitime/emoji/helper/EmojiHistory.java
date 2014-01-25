package com.bitime.emoji.helper;

import android.provider.BaseColumns;

/**
 * Created by Jun Wang on 14-1-9.
 */
public class EmojiHistory {

    public static class EmojiHistoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "EMOJI_HISTORY";
        public static final String EMOJI_VALUE = "EMOJI_VALUE";
        public static final String USED_COUNT = "USED_COUNT";
        public static final String LATEST_DATE = "LATEST_DATE";
    }
}
