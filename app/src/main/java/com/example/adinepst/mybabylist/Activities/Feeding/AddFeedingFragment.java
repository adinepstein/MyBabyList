package com.example.adinepst.mybabylist.Activities.Feeding;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.adinepst.mybabylist.Model.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.example.adinepst.mybabylist.R;
import com.example.adinepst.mybabylist.Utils.FeedingData;



public class AddFeedingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_AMOUNT = "ARG_AMOUNT";
    private static final String ARG_DATE = "ARG_DATE";
    private static final String ARG_TIME = "ARG_TIME";
    private static final String ARG_NOW = "ARG_NOW";
    private static final String ARG_COMMENT = "ARG_Comment";


    private EditText amount;
    private EditText date;
    private EditText time;
    private CheckBox now;
    private EditText comment;

    public static AddFeedingFragment newInstance() {
        AddFeedingFragment fragment = new AddFeedingFragment();
        return fragment;
    }

    public AddFeedingFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public interface AddFeedingDelegate{
        void onSubmit(FeedingData fd);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.add_feeding, container, false);
        amount= view.findViewById(R.id.feading_ET_amount);
        date = view.findViewById(R.id.feading_ET_date);
        time = view.findViewById(R.id.feading_ET_time);
        comment= view.findViewById(R.id.feading_ET_comment);
        now= view.findViewById(R.id.feeding_CB_now);

        ImageButton submitBT=view.findViewById(R.id.feading_IB_submit);
        submitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy kk:mm:ss");
                format.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
                String dateToStr;
                if (now.isChecked()){

                    Date curDate = new Date();
                    dateToStr= format.format(curDate);
                }
                else{
                    dateToStr= date.getText().toString() + " " + time.getText().toString();
                }
                String [] dateSplit= dateToStr.split(" ");
                double amt= Double.parseDouble(amount.getText().toString());
                FeedingData fd= new FeedingData(dateSplit[0],dateSplit[1],amt,comment.getText().toString());
                Model.instance.addFeedingData(fd);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

            updateInstanceState(savedInstanceState);

            return view;
    }

    private void updateInstanceState(Bundle savedInstanceState ){
        if(savedInstanceState!=null){
            String a= savedInstanceState.getString(ARG_AMOUNT);
            if (a!=null)
                amount.setText(a);
            String d = savedInstanceState.getString(ARG_DATE);
            if (d!=null)
                date.setText(d);
            String t = savedInstanceState.getString(ARG_TIME);
            if (t!=null)
                time.setText(t);
            String c = savedInstanceState.getString(ARG_COMMENT);
            if (c!=null)
                comment.setText(c);
            String n= savedInstanceState.getString(ARG_NOW);
            if (n!=null){
                if (n=="true")
                    now.setChecked(true);
                else
                    now.setChecked(false);
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void  onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        String s= Boolean.toString(now.isChecked());
        bundle.putString(ARG_AMOUNT, amount.getText().toString());
        bundle.putString(ARG_DATE, date.getText().toString());
        bundle.putString(ARG_TIME, time.getText().toString());
        bundle.putString(ARG_NOW, s);
        bundle.putString(ARG_COMMENT, comment.getText().toString());
    }




}
