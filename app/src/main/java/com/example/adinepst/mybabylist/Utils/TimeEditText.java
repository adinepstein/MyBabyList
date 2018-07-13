package com.example.adinepst.mybabylist.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TimeEditText extends android.support.v7.widget.AppCompatEditText {

    int hour;
    int minute;
    int second;

    public void setDate(int h, int m, int s){
        second = s;
        minute = m;
        hour = h;
    }

    private void readTime(){
        String txt =getText().toString();
        String[] arr = txt.split(":");
        if (arr.length == 2){
            hour = Integer.parseInt(arr[0]);
            minute = Integer.parseInt(arr[1]);
            second = 00;
        }
    }

    public TimeEditText(Context context) {
        super(context);
    }

    public TimeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            TimeDialogFragment fragment = new TimeDialogFragment();
            readTime();
            fragment.setDate(hour,minute,second);
            fragment.listener = new TimeDialogFragment.TimeDialogFragmentListener() {
                @Override
                public void onTimeSet(int h, int m, int s) {
                    String hour= Integer.toString(h);
                    String minute= Integer.toString(m);
                    String second= Integer.toString(s);
                    setText(  hour + ":" + minute + ":" + second);
                }
            };
            fragment.show(((Activity)getContext()).getFragmentManager(),"");
        }
        return true;
    }
}
