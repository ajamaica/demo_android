package com.brounie.sayer.App;

import android.app.Activity;
import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ajamaica on 21/03/17.
 */

public class App extends Application {


   // public ParseObject historial;

    @Override
    public void onCreate(){
        super.onCreate();


        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("BrounieApp")
                .clientKey("C4suYZKkyRMYPGR7fEae")
                .server("https://sayer.brounieapps.com/parse/")
                .build()
        );

        ParseInstallation.getCurrentInstallation().saveInBackground();





    }



}
