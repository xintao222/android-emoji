package com.bitime.emoji.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bitime.emoji.helper.EmojiHistory.EmojiHistoryEntry.EMOJI_VALUE;
import static com.bitime.emoji.helper.EmojiHistory.EmojiHistoryEntry.LATEST_DATE;
import static com.bitime.emoji.helper.EmojiHistory.EmojiHistoryEntry.TABLE_NAME;
import static com.bitime.emoji.helper.EmojiHistory.EmojiHistoryEntry.USED_COUNT;
import static com.bitime.emoji.helper.EmojiHistory.EmojiHistoryEntry._ID;

/**
 * 数据库的辅助类
 * <p/>
 * Created by Jun Wang on 14-1-9.
 */
public class HistoryDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "EmojiHistory.db";

    private static final String TEXT_TYPE = "TEXT";
    private static final String INT_TYPE = "INT";

    private static final String SQL_CREATE_TABLE =
            String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY, %s %s, %s  %s, %s %s )",
                    TABLE_NAME, _ID, EMOJI_VALUE, TEXT_TYPE, USED_COUNT, INT_TYPE, LATEST_DATE, INT_TYPE);

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private HistoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static Map<Context, HistoryDbHelper> dbHelperMap = new HashMap<Context, HistoryDbHelper>();

    public static HistoryDbHelper getInstance(Context context) {
        HistoryDbHelper dbHelper = dbHelperMap.get(context);
        if (dbHelper == null) {
            dbHelper = new HistoryDbHelper(context);
            dbHelperMap.put(context, dbHelper);
            Log.w(HistoryDbHelper.class.getSimpleName(), "create HistoryDbHelper ");
        }
        return dbHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void updateCount(String emoji) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {EMOJI_VALUE, USED_COUNT};
        Cursor cursor = db.query(TABLE_NAME, projection, EMOJI_VALUE + "=?", new String[]{emoji}, null, null, null, "1");
        if (cursor.moveToFirst()) {
            int count = cursor.getInt(cursor.getColumnIndexOrThrow(USED_COUNT));
            updateHistory(emoji, count + 1);
        } else {
            Log.i(this.getClass().getSimpleName(), String.format("no history for '%s'", emoji));
            insertHistory(emoji);
        }
    }

    public List<String> listSortedHistory(Integer historySize) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {EMOJI_VALUE, USED_COUNT};
        String sortOrder = USED_COUNT + " DESC";

        List<String> sortedHistory = new ArrayList<String>();
        Cursor cursor = db.query(TABLE_NAME, projection, null, null, null, null, sortOrder, String.valueOf(historySize));
        while (cursor.moveToNext()) {
            sortedHistory.add(cursor.getString(cursor.getColumnIndexOrThrow(EMOJI_VALUE)));
        }
        return sortedHistory;
    }

    public List<String> listLatestUsedEmoji(Integer historySize) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {EMOJI_VALUE, LATEST_DATE};
        String sortOrder = LATEST_DATE + " DESC";

        List<String> sortedHistory = new ArrayList<String>();
        Cursor cursor = db.query(TABLE_NAME, projection, null, null, null, null, sortOrder, String.valueOf(historySize));
        while (cursor.moveToNext()) {
            sortedHistory.add(cursor.getString(cursor.getColumnIndexOrThrow(EMOJI_VALUE)));
        }
        return sortedHistory;
    }

    private void insertHistory(String emoji) {
        Log.i(this.getClass().getSimpleName(), String.format("insert history for '%s'", emoji));
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMOJI_VALUE, emoji);
        values.put(USED_COUNT, 1);
        values.put(LATEST_DATE, System.currentTimeMillis());
        db.insert(TABLE_NAME, null, values);
    }

    private void updateHistory(String emoji, int count) {
        Log.i(this.getClass().getSimpleName(), String.format("update count for '%s' with count '%d' ", emoji, count));
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(USED_COUNT, count);
        values.put(LATEST_DATE, System.currentTimeMillis());
        db.update(TABLE_NAME, values, EMOJI_VALUE + "=?", new String[]{emoji});
    }

    public void clearHistory(){
        Log.i(this.getClass().getSimpleName(), String.format("clear history now..."));
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
}
