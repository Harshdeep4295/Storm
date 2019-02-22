package com.storm.Activities;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.storm.Fragment.ImageListDisplayFragment;
import com.storm.R;

public class MainActivity extends AppCompatActivity {


    FrameLayout fragmentPlaceholder;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentPlaceholder=(FrameLayout)findViewById(R.id.my_place_holder);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FragmentManager manager=getSupportFragmentManager();

        Fragment fragment=manager.findFragmentByTag("imageFragment");

        if(fragment==null)
        {
            fragment=new ImageListDisplayFragment(this);
            switchFragment(fragment, false, null);
        }
    }

    public void switchFragment(Fragment fragment, boolean animation, View itemView)
    {
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.my_place_holder, fragment);

        if(!fragmentPopped) {
            fragmentTransaction.addToBackStack(backStateName);

            if(animation) {

                fragmentTransaction.addSharedElement(itemView, itemView.getTransitionName());
            }

        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed(){
        if(getSupportFragmentManager().getBackStackEntryCount()>1){
            super.onBackPressed();

        }else{
            finish();
        }
    }
}
