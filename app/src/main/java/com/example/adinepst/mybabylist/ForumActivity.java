package com.example.adinepst.mybabylist;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.example.adinepst.mybabylist.Model.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.adinepst.mybabylist.Utils.PostData;

public class ForumActivity extends AppCompatActivity {
    private EditText commentET;
    private RadioGroup parentRG;
//    private RadioButton motherRB;
//    private RadioButton fatherRB;
    private ImageButton postIB;
    private String parentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_layout);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_add_post,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ForumActivity.this);
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
            PostData pd= new PostData(com,parentType,dateToStr,null,null);
            Model.instance.addPost(pd);
            } });
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        return true;

//        return super.onOptionsItemSelected(item);
    }
}
