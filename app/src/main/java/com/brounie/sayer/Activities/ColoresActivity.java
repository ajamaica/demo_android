package com.brounie.sayer.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.brounie.sayer.Adapter.AdapterColor;
import com.brounie.sayer.Bd.SayerSQLiteHelper;
import com.brounie.sayer.R;


import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ColoresActivity extends AppCompatActivity {


    SayerSQLiteHelper db = new SayerSQLiteHelper(this);
    public List coloresLocal;

    private StickyListHeadersListView coloresList;
    private AdapterColor adapterColor;

    private ImageView icon_izq;
    private TextView tittle;
    private EditText search;
    private String objectId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colores);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        search = (EditText) findViewById(R.id.search);


        coloresList = (StickyListHeadersListView) findViewById(R.id.coloresList);
        icon_izq = (ImageView) findViewById(R.id.icon_izq);
        tittle = (TextView) findViewById(R.id.tittle);
        tittle.setText(getResources().getString(R.string.tittle_color));


        icon_izq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            objectId = bundle.getString("objectId");

            coloresLocal = db.getColores(objectId);
            fillUpList(coloresLocal);
        }




        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String busqueda = s.toString();
                if(busqueda != null || !search.equals("")){
                    coloresLocal = db.getColoresSearch(objectId,busqueda);
                }else{
                    coloresLocal = db.getColores(objectId);
                }
                fillUpList(coloresLocal);
            }
        });
    }


    private void fillUpList(List coloresLocal){
        adapterColor = new AdapterColor(this,coloresLocal);
        coloresList.setAdapter(adapterColor);

    }



}
