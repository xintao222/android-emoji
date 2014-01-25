package com.bitime.emoji.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bitime.emoji.CategoryConfig;
import com.bitime.emoji.R;

import static com.bitime.emoji.CategoryConfig.EmojiCategory;

/**
 * Created by ludwang on 14-1-9.
 */
public class CategoryFragment extends Fragment {

    public interface OnCategorySelectedListener {
        public void onCategorySelected(String category);
    }

    private OnCategorySelectedListener onCategorySelectedListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_grid_view, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.grid_view);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this.getActivity().getBaseContext(),
                CategoryConfig.get2LineMap(getResources()),
                R.layout.emoji_category_row,
                new String[]{"First Line", "Second Line"},
                new int[]{android.R.id.text1, android.R.id.text2});
        gridView.setAdapter(new EmojiCategoryAdapter(this.getActivity().getBaseContext()));
        gridView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        gridView.setOnItemClickListener(new ClickListener(this));

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onCategorySelectedListener = (OnCategorySelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCategorySelectedListener");
        }
    }

    @Override
    public void onResume() {
        getActivity().getActionBar().setTitle(getResources().getString(R.string.tab_category));
        super.onResume();
    }

    class EmojiCategoryAdapter implements ListAdapter {


        private Context context;

        EmojiCategoryAdapter(Context context) {
            this.context = context;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int i) {
            return true;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {
            return EmojiCategory.values().length;
        }

        @Override
        public Object getItem(int i) {
            return EmojiCategory.values()[i];
        }

        @Override
        public long getItemId(int i) {

            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            EmojiCategory category = (EmojiCategory) getItem(i);
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view1 = mInflater.inflate(R.layout.emoji_category_row, viewGroup, false);
            TextView viewText1 = (TextView) view1.findViewById(android.R.id.text1);
            viewText1.setText(getResources().getString(category.getNameResourceId()));

            TextView textView2 = (TextView) view1.findViewById(android.R.id.text2);
            textView2.setText(category.getDescription());
            return view1;
        }

        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }

    class ClickListener implements AdapterView.OnItemClickListener {
        private CategoryFragment host;

        ClickListener(CategoryFragment host) {
            this.host = host;
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            EmojiCategory category = (EmojiCategory) adapterView.getItemAtPosition(i);
            host.onCategorySelectedListener.onCategorySelected(category.name());
        }
    }
}
