package com.example.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

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

        SimpleAdapter simpleAdapter = new SimpleAdapter(this.getActivity().getBaseContext(), CategoryConfig.get2LineMap(),
                R.layout.emoji_category_row,
                new String[]{"First Line", "Second Line"},
                new int[]{android.R.id.text1, android.R.id.text2});
        gridView.setAdapter(simpleAdapter);
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
        getActivity().getActionBar().setTitle("category");
        super.onResume();
    }

    class ClickListener implements AdapterView.OnItemClickListener {
        private CategoryFragment host;

        ClickListener(CategoryFragment host) {
            this.host = host;
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Map<String, String> item = (Map<String, String>) adapterView.getItemAtPosition(i);
            final String category = item.get("First Line");
            host.onCategorySelectedListener.onCategorySelected(category);
        }
    }
}
