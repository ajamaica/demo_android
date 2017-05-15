package com.brounie.sayer.Adapter;

/**
 * Created by ajamaica on 10/04/17.
 */

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.brounie.sayer.Activities.PerfilColorActivity;

import java.util.ArrayList;
import java.util.List;

public class CustomOnItemSelectedListener implements OnItemSelectedListener {

    PerfilColorActivity main;

    public CustomOnItemSelectedListener(Activity context) {

        this.main = (PerfilColorActivity) context;


    }




    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        /*Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();*/

            main.acomulados = new ArrayList<>();
            main.selectSpiner = parent.getItemAtPosition(pos).toString();
            main.total();
           // main.adapterGz.notifyDataSetChanged();




    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}