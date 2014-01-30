package com.bitime.emoji.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.bitime.emoji.MyNavigationUtils;
import com.bitime.emoji.R;
import com.bitime.emoji.helper.PreferencesHelper;

/**
 * Created by Jun Wang on 14-1-30.
 */
public class RankPopupDialogFragment extends DialogFragment {

    private Activity activity;

    public RankPopupDialogFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.rank_popup_message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MyNavigationUtils.gotoMarket(getActivity());
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PreferencesHelper.disablePopupPreference(getActivity());
                    }
                })
                .setNeutralButton(R.string.rank_popup_next_time, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //DO NOTHING
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void show() {
        if (!PreferencesHelper.isShowPopupPreference(activity)) {
            return;
        }
        PreferencesHelper.updateLatestPopupTime(activity);
        super.show(activity.getFragmentManager(), "rank");
    }
}
