package com.example.adinepst.mybabylist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.adinepst.mybabylist.Model.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

import Utils.PostData;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForumFragment extends Fragment {

    private EditText commentET;
    private RadioGroup parentRG;
    //    private RadioButton motherRB;
//    private RadioButton fatherRB;
    private ImageButton postIB;
    private String parentType;


    public ForumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.forum_layout, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_add_post,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view= getLayoutInflater().inflate(R.layout.add_post_layout,null);
        commentET= view.findViewById(R.id.addPost_ET_comment);
        parentRG= view.findViewById(R.id.addPost_RG_fatherMother);
//        motherRB = view.findViewById(R.id.addPost_RB_mother);
//        fatherRB = view.findViewById(R.id.addPost_RB_father);
        postIB = view.findViewById(R.id.addPost_IB_post);

        postIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String com = commentET.getText().toString();
                int selected=parentRG.getCheckedRadioButtonId();
                parentType=null;
                if(selected==R.id.addPost_RB_mother)
                    parentType="mother";
                else if(selected==R.id.addPost_RB_father)
                    parentType ="father";

                SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm");
                Date curDate = new Date();
                String dateToStr= format.format(curDate);
                //TODO- add picture
                PostData pd= new PostData(com,parentType,dateToStr,null);
                Model.instance.addPost(pd);
            } });
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        return true;

//        return super.onOptionsItemSelected(item);
    }



}
