package com.example.adinepst.mybabylist.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adinepst.mybabylist.MainActivity;
import com.example.adinepst.mybabylist.MyApplication;
import com.example.adinepst.mybabylist.R;
import com.example.adinepst.mybabylist.UserDetailsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_EMAIL = "email";
    private static final String ARG_PASSWORD = "password";

    // TODO: Rename and change types of parameters
    private EditText emailET;
    private EditText passwordET;
    private Button loginBt;
    private TextView registerBt;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout, container, false);
        emailET = view.findViewById(R.id.login_ET_email);
        passwordET = view.findViewById(R.id.login_ET_password);
        loginBt = view.findViewById(R.id.login_IB_login);
        registerBt = view.findViewById(R.id.login_IB_register);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(getActivity());


        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterFragment fragment = new RegisterFragment();
                FragmentTransaction tran = getActivity().getSupportFragmentManager().beginTransaction();
                tran.replace(R.id.login_frame, fragment);
                tran.addToBackStack("");
                tran.commit();
            }
        });

        updateInstanceState(savedInstanceState);
        return view;
    }
    private void loginUser(){

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
        progressDialog.setMessage("Login user...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
            }
        });
    }
    private void updateInstanceState(Bundle savedInstanceState ){
        if(savedInstanceState!=null){
            String email= savedInstanceState.getString(ARG_EMAIL);
            emailET.setText(email);

            String password = savedInstanceState.getString(ARG_PASSWORD);
            passwordET.setText(password);

        }
    }


    @Override
    public void  onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putString(ARG_EMAIL, emailET.getText().toString());
        bundle.putString(ARG_PASSWORD, passwordET.getText().toString());
            }




}
