package com.example.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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
        View view = inflater.inflate(R.layout.activity_list_view, container, false);

        List<String> category = readCategory();
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        ListAdapter adapter = new ArrayAdapter(container.getContext(), R.layout.emoji_category_row, category);

        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new ClickListener(this));

        return view;
    }

    private List<String> readCategory() {
        Resources res = getResources();
        List<String> categoryList = new ArrayList<String>();
        TypedArray category = res.obtainTypedArray(R.array.category);
        for (int index = 0; index < category.length(); index++) {
            categoryList.add(category.getText(index).toString());
        }
        return categoryList;
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

    class ClickListener implements AdapterView.OnItemClickListener {
        private  CategoryFragment host;

        ClickListener(CategoryFragment host) {
            this.host = host;
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            final String category = (String) adapterView.getItemAtPosition(i);
            host.onCategorySelectedListener.onCategorySelected(category);
        }
    }
}
