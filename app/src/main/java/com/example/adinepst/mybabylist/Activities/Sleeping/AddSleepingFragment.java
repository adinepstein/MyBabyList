package com.example.adinepst.mybabylist.Activities.Sleeping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.adinepst.mybabylist.Model.Model;

import com.example.adinepst.mybabylist.R;
import com.example.adinepst.mybabylist.Utils.SleepingData;



public class AddSleepingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ENDDATE= "ARG_ENDDATE";
    private static final String ARG_ENDTIME= "ARG_ENDTIME";
    private static final String ARG_DATE = "ARG_DATE";
    private static final String ARG_TIME = "ARG_TIME";
    private static final String ARG_COMMENT = "ARG_Comment";


    private EditText endDate;
    private EditText endTime;
    private EditText date;
    private EditText time;
    private EditText comment;

    public static AddSleepingFragment newInstance() {
        AddSleepingFragment fragment = new AddSleepingFragment();
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.add_sleeping, container, false);
        endDate= view.findViewById(R.id.sleeping_ET_endDate);
        endTime = view.findViewById(R.id.sleeping_ET_endTime);
        date = view.findViewById(R.id.sleeping_ET_date);
        time = view.findViewById(R.id.sleeping_ET_time);
        comment= view.findViewById(R.id.sleeping_ET_comment);


        ImageButton submitBT=view.findViewById(R.id.sleeping_IB_submit);
        submitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startDate= date.getText().toString();
                String startTime = time.getText().toString();
                String endDateS = endDate.getText().toString();
                String endTimeS = endTime.getText().toString();
                String com= comment.getText().toString();
                SleepingData sd= new SleepingData(startDate,startTime,endDateS,endTimeS,com);
                Model.instance.addSleepingData(sd);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

            updateInstanceState(savedInstanceState);

            return view;
    }

    private void updateInstanceState(Bundle savedInstanceState ){
        if(savedInstanceState!=null){
            String ed= savedInstanceState.getString(ARG_ENDDATE);
            if (ed!=null)
                endDate.setText(ed);
            String et= savedInstanceState.getString(ARG_ENDTIME);
            if (et!=null)
                endDate.setText(et);
            String d = savedInstanceState.getString(ARG_DATE);
            if (d!=null)
                date.setText(d);
            String t = savedInstanceState.getString(ARG_TIME);
            if (t!=null)
                time.setText(t);
            String c = savedInstanceState.getString(ARG_COMMENT);
            if (c!=null)
                comment.setText(c);


        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void  onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putString(ARG_ENDTIME, endTime.getText().toString());
        bundle.putString(ARG_DATE, date.getText().toString());
        bundle.putString(ARG_TIME, time.getText().toString());
        bundle.putString(ARG_ENDDATE, endDate.getText().toString());
        bundle.putString(ARG_COMMENT, comment.getText().toString());
    }




}
