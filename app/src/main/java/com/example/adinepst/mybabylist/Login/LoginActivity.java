package com.example.adinepst.mybabylist.Login;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.adinepst.mybabylist.R;
import com.example.adinepst.mybabylist.UserDetailsFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            LoginFragment fragment = new LoginFragment();
            FragmentTransaction tran = getSupportFragmentManager().beginTransaction();
            tran.add(R.id.login_frame, fragment);
            tran.addToBackStack("");
            tran.commit();
        }
    }
}
