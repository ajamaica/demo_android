package com.brounie.sayer.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by ajamaica on 24/03/17.
 */

public class Version {



    Context context;

    int version = 0;
    int versionDollar = 0;
    float dollar = 0.0f;


    public Version(Context c){
        super();
        this.context = c;
    }


    public void setVariableVersion(int version){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("version", version);
        editor.apply();
        editor.commit();
        this.version = version;
    }
    public int getVariableVersion(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        int version = preferences.getInt("version",0);
        return version;
    }


    public void setVariableVersionDollar(int versionDollar){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("versionDollar", versionDollar);
        editor.apply();
        editor.commit();
        this.versionDollar = versionDollar;
    }
    public int getVariableVersionDollar(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        int versionDollar = preferences.getInt("versionDollar",0);
        return versionDollar;
    }



    public void setVariableDollar(float dollar){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("dollar", dollar);
        editor.apply();
        editor.commit();
        this.dollar = dollar;
    }
    public float getVariableDollar(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        float versionDollar = preferences.getFloat("dollar",0);
        return versionDollar;
    }


}
