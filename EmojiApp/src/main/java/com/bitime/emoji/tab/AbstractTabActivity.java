package com.bitime.emoji.tab;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import static android.app.ActionBar.Tab;

/**
 * Created by ludwang on 14-1-11.
 */
public abstract class AbstractTabActivity extends Activity {

    protected abstract List<String> getTabTags();

    // Tab back stacks
    private HashMap<String, Stack<String>> backStacks;

    public HashMap<String, Stack<String>> getBackStacks() {
        return backStacks;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            backStacks = (HashMap<String, Stack<String>>) savedInstanceState.getSerializable("stacks");
        } else {
            backStacks = new HashMap<String, Stack<String>>();
            for (String tab : getTabTags()) {
                backStacks.put(tab, new Stack<String>());
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    protected void onResume() {
        super.onResume();
        Tab tab = getActionBar().getSelectedTab();
        Stack<String> backStack = backStacks.get(tab.getTag());
        if (!backStack.isEmpty()) {
            // Restore topmost fragment (e.g. after application switch)
            String fragmentTag = backStack.peek();
            Fragment fragment = getFragmentManager().findFragmentByTag(fragmentTag);
            if (fragment.isDetached()) {
                getFragmentManager().beginTransaction()
                        .attach(fragment)
                        .commit();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    protected void onPause() {
        super.onPause();
        // Select proper stack
        Tab tab = getActionBar().getSelectedTab();
        Stack<String> backStack = backStacks.get(tab.getTag());
        if (!backStack.isEmpty()) {
            // Detach topmost fragment otherwise it will not be correctly displayed
            // after orientation change
            String tag = backStack.peek();
            Fragment fragment = getFragmentManager().findFragmentByTag(tag);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(fragment);
            ft.commit();
        }
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore selected tab
        int saved = savedInstanceState.getInt("tab", 0);
        if (saved != getActionBar().getSelectedNavigationIndex()) {
            getActionBar().setSelectedNavigationItem(saved);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save selected tab and all back stacks
        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
        outState.putSerializable("stacks", backStacks);
    }


    @Override
    public void onBackPressed() {
        // Select proper stack
        Tab tab = getActionBar().getSelectedTab();
        Stack<String> backStack = backStacks.get(tab.getTag());
        String topFragmentTag = backStack.pop();

        if (backStack.isEmpty()) {
            // Let application finish
            super.onBackPressed();
        } else {
            Fragment fragment = getFragmentManager().findFragmentByTag(topFragmentTag);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            // Remove topmost fragment from back stack and forget it
            ft.remove(fragment);
            showFragment(backStack, ft);
            ft.commit();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showFragment(Stack<String> backStack, FragmentTransaction ft) {
        // Peek topmost fragment from the stack
        String tag = backStack.peek();
        Fragment fragment = getFragmentManager().findFragmentByTag(tag);
        // and attach it
        ft.attach(fragment);
    }
}
