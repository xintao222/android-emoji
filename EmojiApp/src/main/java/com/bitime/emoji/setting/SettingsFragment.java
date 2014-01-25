package com.bitime.emoji.setting;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.bitime.emoji.R;

/**
 * Created by ludwang on 14-1-1.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
