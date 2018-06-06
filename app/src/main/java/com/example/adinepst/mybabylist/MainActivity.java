package com.example.adinepst.mybabylist;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            UserDetailsFragment fragment = new UserDetailsFragment();
            FragmentTransaction tran = getSupportFragmentManager().beginTransaction();
            tran.add(R.id.main_frame, fragment);
            tran.addToBackStack("");
            tran.commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_add_activity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean switched=false;
        Fragment fragment=null;
        switch (id) {
            case R.id.menu_MI_add_feeding:
                fragment = new AddFeedingFragment();
                switched=true;
                break;
            case R.id.menu_MI_add_sleeping:
                fragment = new AddSleepingFragment();
                switched=true;
                break;
            case R.id.menu_MI_add_diaper:
                fragment = new AddDiaperFragment();
                switched=true;
                break;
        }
        if (switched){
            FragmentTransaction tran = getSupportFragmentManager().beginTransaction();
            tran.replace(R.id.main_frame, fragment);
            tran.addToBackStack(" ");
            tran.commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
