package com.brounie.sayer.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.brounie.sayer.MainActivity;
import com.brounie.sayer.R;
import com.brounie.sayer.Utils.Version;
import com.parse.ParseConfig;
import com.parse.ParseException;

/**
 * Created by ajamaica on 21/03/17.
 */

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ActivitySplash.this);
                    //boolean visto  = preferences.getBoolean("visto",false);
                    Intent intent = new Intent(ActivitySplash.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
