package com.bitime.emoji.setting;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.bitime.emoji.MyNavigationUtils;
import com.bitime.emoji.R;

/**
 * Created by Jun Wang on 14-1-1.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        Preference button = findPreference("ranking_in_setting");
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                MyNavigationUtils.gotoMarket(getActivity());
                return true;
            }
        });
    }
}
