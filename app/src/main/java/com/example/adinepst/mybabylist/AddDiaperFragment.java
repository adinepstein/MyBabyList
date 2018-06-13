package com.example.adinepst.mybabylist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.adinepst.mybabylist.Model.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.example.adinepst.mybabylist.Utils.DiaperChangingData;
import com.example.adinepst.mybabylist.Utils.FeedingData;
import com.example.adinepst.mybabylist.Utils.UserData;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddDiaperFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddDiaperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDiaperFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TYPE = "ARG_TYPE";
    private static final String ARG_DATE = "ARG_DATE";
    private static final String ARG_TIME = "ARG_TIME";
    private static final String ARG_NOW = "ARG_NOW";
    private static final String ARG_COMMENT = "ARG_Comment";


    private RadioGroup type;
    private RadioButton poop;
    private RadioButton urine;
    private EditText date;
    private EditText time;
    private CheckBox now;
    private EditText comment;

    public AddDiaperFragment() {
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
        View view=inflater.inflate(R.layout.add_diaper_changing, container, false);
        type= view.findViewById(R.id.diaperChanging_RG_type);
        date = view.findViewById(R.id.diaperChanging_ET_date);
        time = view.findViewById(R.id.diaperChanging_ET_time);
        comment= view.findViewById(R.id.diaperChanging_ET_comment);
        now= view.findViewById(R.id.diaperChanging_CB_now);
        poop = view.findViewById(R.id.diaperChanging_RB_poop);
        urine = view.findViewById(R.id.diaperChanging_RB_urine);
        ImageButton submitBT=view.findViewById(R.id.diaperChanging_IB_submit);
        submitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm");
                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                String dateToStr;
                if (now.isChecked()){
                    Date curDate = new Date();
                    dateToStr= format.format(curDate);
                }
                else{
                    dateToStr= date.getText().toString() + " " + time.getText().toString();
                }
                int selected=type.getCheckedRadioButtonId();
                String diaperType=null;
                if(selected==R.id.diaperChanging_RB_poop)
                    diaperType="poop";
                else if(selected==R.id.diaperChanging_RB_urine)
                    diaperType ="urine";
                String com=comment.getText().toString();
                String [] dateSplit=dateToStr.split(" ");
                DiaperChangingData dcd= new DiaperChangingData(dateSplit[0],dateSplit[1],diaperType,com);
                UserData ud= new UserData("bb","22222","bb","bb","bb","bb","bb","bb");
                Model.instance.addActivityData(dcd,ud);
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });

            updateInstanceState(savedInstanceState);

            return view;
    }

    private void updateInstanceState(Bundle savedInstanceState ){
        if(savedInstanceState!=null){
            String a= savedInstanceState.getString(ARG_TYPE);
            if (a!=null) {
                if (a == "poop")
                    poop.setChecked(true);
                else
                    urine.setChecked(true);
            }
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

        int selected=type.getCheckedRadioButtonId();
        if(selected == R.id.diaperChanging_RB_urine)
            bundle.putString(ARG_TYPE, "urine");
        else if (selected==R.id.diaperChanging_RB_poop)
            bundle.putString(ARG_TYPE, "poop");

        bundle.putString(ARG_DATE, date.getText().toString());
        bundle.putString(ARG_TIME, time.getText().toString());
        bundle.putString(ARG_NOW, s);
        bundle.putString(ARG_COMMENT, comment.getText().toString());
    }




}
