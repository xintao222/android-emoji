package com.bitime.emoji.setting;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.NumberPicker;

/**
 * Created by ludwang on 14-1-25.
 */
public class NumberPickerPreference extends DialogPreference {
    private NumberPicker np;
    Integer initialValue = 20;

    public NumberPickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);
        setDialogIcon(null);
    }

    @Override
    protected View onCreateDialogView() {
        np = new NumberPicker(getContext());
        return (np);
    }


    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            persistInt(np.getValue());
        }
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);
        np.setMaxValue(60);
        np.setMinValue(1);
        np.setValue(initialValue);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        if (restorePersistedValue) {
            initialValue = this.getPersistedInt(initialValue);
        } else {
            initialValue = (Integer) defaultValue;
            persistInt(initialValue);
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getInt(index, 20);
    }

}
