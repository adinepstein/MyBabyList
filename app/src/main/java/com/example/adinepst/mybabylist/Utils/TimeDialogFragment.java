package com.example.adinepst.mybabylist.Utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class TimeDialogFragment extends DialogFragment {
    TimeDialogFragmentListener listener;
    int hour;
    int minute;
    int second;

    public void setDate(int y, int m, int d) {
        hour = y;
        minute = m;
        second = d;
    }

    interface TimeDialogFragmentListener{
        void onTimeSet(int h, int m, int s);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if (listener != null){
                    listener.onTimeSet(i,i1,0);
                }
            }
        }, hour,minute,true);
        return dialog;
    }
}
