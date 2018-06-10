package com.example.adinepst.mybabylist;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.adinepst.mybabylist.Model.Model;

import Utils.SleepingData;
import Utils.UserData;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private static final String ARG_NAME= "ARG_NAME";
    private static final String ARG_ID= "ARG_ID";
    private static final String ARG_SEX = "ARG_SEX";
    private static final String ARG_BIRTHDATE = "ARG_BIRTHDATE";
    private static final String ARG_MOTHERNAME = "ARG_MOTHERNAME";
    private static final String ARG_FATHERNAME = "ARG_FATHERNAME";
    private static final String ARG_EMAIL = "ARG_EMAIL";
    private static final String ARG_IMAGEURL = "ARG_IMAGEURL";

    private EditText   nameET;
    private EditText   idET;
    private EditText   birthDateET;
    private EditText   motherNameET;
    private EditText   fatherNameET;
    private RadioGroup sexRG;
    private EditText   emailET;
    private EditText   passwordET;
    private String     imageUrl;
    private ImageButton submitBT;
    private RadioButton boyRB;
    private RadioButton girlRB;
    private Bitmap imageBitmap;
    private ImageButton babyImage;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.register_layout, container, false);
        nameET=view.findViewById(R.id.register_ET_name);
        idET=view.findViewById(R.id.register_ET_id);
        birthDateET=view.findViewById(R.id.register_ET_dateBirth);
        motherNameET=view.findViewById(R.id.register_ET_mothersName);
        fatherNameET=view.findViewById(R.id.register_ET_fatherName);
        sexRG=view.findViewById(R.id.register_RG_sex);
        emailET=view.findViewById(R.id.register_ET_email);
        passwordET = view.findViewById(R.id.register_ET_password);
        submitBT= view.findViewById(R.id.register_IM_register);
        boyRB = view.findViewById(R.id.register_RB_boy);
        girlRB = view.findViewById(R.id.register_RB_girl);
        babyImage= view.findViewById(R.id.user_details_IV_babyImage);
        submitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final UserData ud = new UserData(nameET.getText().toString(), idET.getText().toString(), getSexString(), birthDateET.getText().toString(), motherNameET.getText().toString(), fatherNameET.getText().toString(), emailET.getText().toString(), null);
                if (imageBitmap != null) {
                    Model.instance.saveImage(imageBitmap, new Model.SaveImageListener() {
                        @Override
                        public void onDone(String url) {
                            //save student obj
                            imageUrl = url;
                            ud.setImageUrl(imageUrl);
                            Model.instance.addUser(ud);
                        }
                    });}

                    UserDetailsFragment fragment = new UserDetailsFragment();
                    FragmentTransaction tran = getActivity().getSupportFragmentManager().beginTransaction();
                    tran.replace(R.id.main_frame, fragment);
                    tran.addToBackStack(" ");
                    tran.commit();
                babyImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent takePictureIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                        }
                    }
                });
            }});
        updateInstanceState(savedInstanceState);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE &&
                resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            babyImage.setImageBitmap(imageBitmap);
        }
    }

    private String getSexString(){
        String sex=null;
        int selected=sexRG.getCheckedRadioButtonId();
        if(selected==R.id.register_RB_boy)
            sex="boy";
        else if(selected==R.id.register_RB_girl)
            sex ="girl";
        return sex;
    }

    private void updateInstanceState(Bundle savedInstanceState ){
        if(savedInstanceState!=null){
            String n= savedInstanceState.getString(ARG_NAME);
            if (n!=null)
                nameET.setText(n);
            String id= savedInstanceState.getString(ARG_ID);
            if (id!=null)
                idET.setText(id);
            String bd = savedInstanceState.getString(ARG_BIRTHDATE);
            if (bd!=null)
                birthDateET.setText(bd);
            String mn = savedInstanceState.getString(ARG_MOTHERNAME);
            if (mn!=null)
                motherNameET.setText(mn);
            String fn = savedInstanceState.getString(ARG_FATHERNAME);
            if (fn!=null)
                fatherNameET.setText(fn);
            String s= savedInstanceState.getString(ARG_SEX);
            if (s!=null) {
                if (s == "boy")
                    boyRB.setChecked(true);
                else
                    girlRB.setChecked(true);
            }
            String e=savedInstanceState.getString(ARG_EMAIL);
            if (e!=null)
                emailET.setText(e);


        }
    }

    @Override
    public void  onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putString(ARG_NAME, nameET.getText().toString());
        bundle.putString(ARG_ID, idET.getText().toString());
        bundle.putString(ARG_BIRTHDATE, birthDateET.getText().toString());
        bundle.putString(ARG_MOTHERNAME, motherNameET.getText().toString());
        bundle.putString(ARG_FATHERNAME, fatherNameET.getText().toString());
        bundle.putString(ARG_EMAIL, emailET.getText().toString());
        int selected=sexRG.getCheckedRadioButtonId();
        if(selected == R.id.register_RB_boy)
            bundle.putString(ARG_SEX, "boy");
        else if (selected==R.id.register_RB_girl)
            bundle.putString(ARG_SEX, "girl");
    }

}
