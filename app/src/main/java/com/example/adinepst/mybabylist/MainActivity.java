package com.example.adinepst.mybabylist;



import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_WRITE_STORAGE = 1;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_activity,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        boolean switched=false;
//        Fragment fragment=null;
//        switch (id) {
//            case R.id.menu_MI_add_feeding:
//                fragment = new AddFeedingFragment();
//                switched=true;
//                break;
//            case R.id.menu_MI_add_sleeping:
//                fragment = new AddSleepingFragment();
//                switched=true;
//                break;
//            case R.id.menu_MI_add_diaper:
//                fragment = new AddDiaperFragment();
//                switched=true;
//                break;
//        }
//        if (switched){
//            FragmentTransaction tran = getSupportFragmentManager().beginTransaction();
//            tran.replace(R.id.main_frame, fragment);
//            tran.addToBackStack(" ");
//            tran.commit();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
