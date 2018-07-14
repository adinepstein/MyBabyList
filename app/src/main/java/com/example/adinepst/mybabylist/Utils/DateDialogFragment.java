package com.example.adinepst.mybabylist.Utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Date;

public class DateDialogFragment extends DialogFragment {
    DateDialogFragmentListener listener;
    int year;
    int month;
    int day;

    public DateDialogFragment() {

    }

    public void setDate(int y, int m, int d) {
        year = y;
        month = m;
        day = d;
    }

    interface DateDialogFragmentListener{
        void onDateSet(int y, int m, int d);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date d= new Date();
        year=d.getYear()+1900;
        month=d.getMonth();
        day=d.getDay()+8;

        Dialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                if (listener != null){
                    listener.onDateSet(i,i1,i2);
                }
            }
        }, year,month,day);

        return dialog;
    }
}
