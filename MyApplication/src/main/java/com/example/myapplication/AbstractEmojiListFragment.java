package com.example.myapplication;

import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ludwang on 14-1-11.
 */
public abstract class AbstractEmojiListFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_view, container, false);

        ListView listView = (ListView) view.findViewById(R.id.list_view);
        ListAdapter adapter = new ArrayAdapter(this.getActivity().getBaseContext(), R.layout.emoji_item_row, getEmojiList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ClickListener(this));

        return view;
    }

    protected abstract List<String> getEmojiList();

    class ClickListener implements AdapterView.OnItemClickListener {
        private Fragment fragment;

        ClickListener(Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            final String emoji = (String) adapterView.getItemAtPosition(i);

            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
            String clickBehavior = sharedPref.getString(SettingsActivity.CLICK_BEHAVIOR, Constants.CLICK_BEHAVIOR_SEND);
            updateCount(emoji);
            if (Constants.CLICK_BEHAVIOR_SEND.equals(clickBehavior)) {
                MyNavigationUtils.displayMsgApp(fragment, emoji);
            } else {
                ClipboardManager clipboard = (ClipboardManager) view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setPrimaryClip(ClipData.newPlainText("emoji", emoji));
                String toastText = getResources().getString(R.string.copied_toast_text);
                Toast.makeText(fragment.getActivity().getBaseContext(), toastText, Toast.LENGTH_SHORT).show();
                MyNavigationUtils.displayHome(fragment);
            }
        }

        private void updateCount(String emoji) {
            EmojiHistoryDbHelper dbHelper = EmojiHistoryDbHelper.getInstance(getActivity().getBaseContext());
            dbHelper.updateCount(emoji);
        }
    }
}
