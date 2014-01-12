package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.view.View;

import java.util.Stack;

/**
 * Created by ludwang on 14-1-8.
 */
public class TabListener<T extends Fragment> implements ActionBar.TabListener {

    private Fragment mFragment;
    private final MainActivity mActivity;
    private final View mContainer;
    private final String mTag;
    private final Class<T> mClass;

    public TabListener(MainActivity activity, View container, String tag, Class<T> clz) {
        mActivity = activity;
        mContainer = container;
        mTag = tag;
        mClass = clz;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Stack<String> backStack = mActivity.getBackStacks().get(tab.getTag());
        if (backStack.isEmpty()) {
            mFragment = Fragment.instantiate(mActivity, mClass.getName());
            addFragment(tab, mFragment, fragmentTransaction);
        } else {
            String fragmentTag = backStack.peek();
            Fragment fragment = mActivity.getFragmentManager().findFragmentByTag(fragmentTag);
            fragmentTransaction.attach(fragment);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // Select proper stack
        Stack<String> backStack = mActivity.getBackStacks().get(tab.getTag());
        // Get topmost fragment
        String fragmentTag = backStack.peek();
        Fragment fragment = mActivity.getFragmentManager().findFragmentByTag(fragmentTag);
        // Detach it
        fragmentTransaction.detach(fragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void addFragment(ActionBar.Tab tab, Fragment fragment, FragmentTransaction ft) {
        // Select proper stack
        Stack<String> backStack = mActivity.getBackStacks().get(tab.getTag());

        // Animate transfer to new fragment
        // Get topmost fragment
        if (!backStack.isEmpty()) {
            String tag = backStack.peek();
            Fragment top = mActivity.getFragmentManager().findFragmentByTag(tag);
            ft.detach(top);
        }
        // Add fragment to back stack with unique tag
        ft.add(R.id.tab_view, fragment, mTag);
        backStack.push(mTag);
    }

}
