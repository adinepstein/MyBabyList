package com.example.adinepst.mybabylist.Login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adinepst.mybabylist.MainActivity;
import com.example.adinepst.mybabylist.Model.Model;

import com.example.adinepst.mybabylist.MyApplication;
import com.example.adinepst.mybabylist.R;
import com.example.adinepst.mybabylist.UserDetailsFragment;
import com.example.adinepst.mybabylist.Utils.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

    private TextView nameET;
    private TextView   idET;
    private TextView   birthDateET;
    private TextView   motherNameET;
    private TextView   fatherNameET;
    private RadioGroup sexRG;
    private TextView   emailET;
    private TextView   passwordET;
    private String     imageUrl;
    private ImageButton submitBT;
    private RadioButton boyRB;
    private RadioButton girlRB;
    private Bitmap imageBitmap;
    private ImageButton babyImage;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

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
        fatherNameET=view.findViewById(R.id.register_ET_fathersName);
        sexRG=view.findViewById(R.id.register_RG_sex);
        emailET=view.findViewById(R.id.register_ET_email);
        passwordET = view.findViewById(R.id.register_ET_password);
        submitBT= view.findViewById(R.id.register_IM_register);
        boyRB = view.findViewById(R.id.register_RB_boy);
        girlRB = view.findViewById(R.id.register_RB_girl);
        babyImage= view.findViewById(R.id.register_IB_babyImage);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(getActivity());
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
        submitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] emailName=emailET.getText().toString().split("@");
                final UserData ud = new UserData(nameET.getText().toString(), idET.getText().toString(), getSexString(), birthDateET.getText().toString(), motherNameET.getText().toString(), fatherNameET.getText().toString(), emailName[0], null);
                if (imageBitmap != null) {
                    Model.instance.saveImage(imageBitmap, new Model.SaveImageListener() {
                        @Override
                        public void onDone(String url) {
                            //save student obj
                            imageUrl = url;
                            ud.setImageUrl(imageUrl);
                            registerUser(ud);
                        }
                    });}
                    else {
                    registerUser(ud);
                }
            }});

        updateInstanceState(savedInstanceState);

        return view;
    }

    private void registerUser(final UserData ud){

        String email= emailET.getText().toString().trim();
        String password= passwordET.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(MyApplication.context,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(MyApplication.context,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Register user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Model.instance.addUser(ud);
                    progressDialog.dismiss();
                    getActivity().getSupportFragmentManager().popBackStack();

                }
            }
        });
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
