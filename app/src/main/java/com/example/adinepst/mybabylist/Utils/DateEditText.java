package com.example.adinepst.mybabylist.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class DateEditText extends android.support.v7.widget.AppCompatEditText {

    int year;
    int month;
    int day;

    public void setDate(int y, int m, int d){
        year = y;
        month = m;
        day = d;
    }

    private void readDate(){
        String txt =getText().toString();
        String[] arr = txt.split("/");
        if (arr.length == 3){
            day = Integer.parseInt(arr[0]);
            month = Integer.parseInt(arr[1]);
            year = Integer.parseInt(arr[2]);
        }
    }

    public DateEditText(Context context) {
        super(context);
    }

    public DateEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DateEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            DateDialogFragment fragment = new DateDialogFragment();
            readDate();
            fragment.setDate(year,month,day);
            fragment.listener = new DateDialogFragment.DateDialogFragmentListener() {
                @Override
                public void onDateSet(int y, int m, int d) {
                    String year= Integer.toString(y);
                    String month= Integer.toString(m+1);
                    String day= Integer.toString(d);
                    setText(  day + "-" + month + "-" + year);
                }
            };
            fragment.show(((Activity)getContext()).getFragmentManager(),"");
        }
        return true;
    }
}
