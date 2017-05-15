package com.brounie.sayer.Activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.brounie.sayer.Adapter.AdapterArmadora;
import com.brounie.sayer.Adapter.AdapterFormula;
import com.brounie.sayer.Adapter.CustomOnItemSelectedListener;
import com.brounie.sayer.Bd.SayerSQLiteHelper;
import com.brounie.sayer.MainActivity;
import com.brounie.sayer.Models.Base;
import com.brounie.sayer.Models.Formula;
import com.brounie.sayer.R;
import com.brounie.sayer.Utils.Version;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;



public class PerfilColorActivity extends AppCompatActivity {




    private ImageView icon_izq;
    private TextView tittle;
    private TextView fondo;


    private String colorCode;
    private String colorName;
    private String fondoLocal;
    private String hexadecimal;
    private ImageView logo;
    private EditText cantidad;

    public String milAndLt = "100";
    private Spinner spinnerMlLt;
    public  String selectSpiner = null;
    public SayerSQLiteHelper db = new SayerSQLiteHelper(this);
    public List formulasLocal;
    private RecyclerView formulas;
    public AdapterFormula adapterGz;
    private TextView totalPrice,totalUsd;

    public ArrayList<Float> acomulados = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_color);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        icon_izq = (ImageView) findViewById(R.id.icon_izq);
        tittle = (TextView) findViewById(R.id.tittle);
        cantidad = (EditText) findViewById(R.id.cantidad);
        logo = (ImageView) findViewById(R.id.icono);
        fondo = (TextView) findViewById(R.id.fondo);
        totalPrice = (TextView) findViewById(R.id.total);
        totalUsd = (TextView) findViewById(R.id.totalUsd);

        formulas = (RecyclerView) findViewById(R.id.formulas);
        formulas.setNestedScrollingEnabled(false);
        spinnerMlLt = (Spinner) findViewById(R.id.spinnerMlLt);
        spinnerMlLt.setOnItemSelectedListener(new CustomOnItemSelectedListener(PerfilColorActivity.this));




        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            colorName = bundle.getString("colorName");
            hexadecimal = bundle.getString("hexadecimal");
            colorCode = bundle.getString("colorCode");
            formulasLocal = db.getFormula(colorCode);
            fondoLocal = bundle.getString("fondoLocal");

            fondo.setText(fondoLocal);
            tittle.setText(colorName);
            fillUp(formulasLocal);
            total();
        }



        icon_izq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Drawable myIcon = getResources().getDrawable(R.drawable.colorbase);
        myIcon.setColorFilter(android.graphics.Color.parseColor(hexadecimal), PorterDuff.Mode.SRC_ATOP);
        logo.setImageDrawable(myIcon);


        cantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                acomulados = new ArrayList<>();
                milAndLt = s.toString();
                total();
                //adapterGz.notifyDataSetChanged();

            }
        });

    }



    private void fillUp(List formulasLocal){

        adapterGz = new AdapterFormula(formulasLocal,PerfilColorActivity.this);
        RecyclerView.LayoutManager mLayoutManager =  new LinearLayoutManager(PerfilColorActivity.this);
        formulas.setLayoutManager(mLayoutManager);
        formulas.setItemAnimator(new DefaultItemAnimator());
        formulas.setAdapter(adapterGz);


    }

    public void total(){


        double total = 0.0;
        Float acoulado = 0.0f;

        if(milAndLt != null && !milAndLt.equals("")){
            for(int i = 0; i < formulasLocal.size(); i++){
                Formula formula = (Formula) formulasLocal.get(i);
                Base base = (Base)  db.getNameBase(formula.getBase());
                total = total + (base.getPrecio() * (procentaje(formula)/1000) );

                if(selectSpiner == null){
                    acoulado += procentaje(formula);
                    acomulados.add( acoulado);
                }else{
                    acoulado += procentaje(formula);
                    acomulados.add( acoulado);
                }
            }
        }else{
            for(int i = 0; i < formulasLocal.size(); i++){
                 Formula formula = (Formula) formulasLocal.get(i);
                 Base base = (Base)  db.getNameBase(formula.getBase());
                 total = total + (base.getPrecio() * (formula.getPorc()/1000) );

                if(selectSpiner == null){
                    acoulado += procentaje(formula);
                    acomulados.add( acoulado);
                }else{
                    acoulado += procentaje(formula);
                    acomulados.add( acoulado);
                }
            }
        }

        Version versionLocal = new Version(PerfilColorActivity.this);

      /*  BigDecimal totalFinal = new
                BigDecimal(total).setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal totalFinalDolar = new
                BigDecimal((total/versionLocal.getVariableDollar())).setScale(2,BigDecimal.ROUND_HALF_UP);

        DecimalFormat formateador = new DecimalFormat("###,###.##");

        formateador.format(totalFinal*/

        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormat format = new DecimalFormat("###,###.##");

        totalPrice.setText("MX $"+format.format(Double.parseDouble(df.format(total))));
        totalUsd.setText("USD $"+format.format(Double.parseDouble(df.format(total/versionLocal.getVariableDollar()))));

        adapterGz.notifyDataSetChanged();

    }




    private float procentaje(Formula formula){

        float porcentaje = 0;
        if(selectSpiner != null){
            if(milAndLt != null && !milAndLt.equals("")){
                if(selectSpiner.equals("ml")){
                    porcentaje = (float)(formula.getPorc()*Double.parseDouble(milAndLt))/100;
                }
                if(selectSpiner.equals("Lt")){
                    porcentaje = (float) ((formula.getPorc()*Double.parseDouble(milAndLt))/100) * 1000;
                }
            }else{
                porcentaje = (float) (formula.getPorc());
            }
        }else{
            if(milAndLt != null && !milAndLt.equals("")) {
                porcentaje = (float) (formula.getPorc() * Double.parseDouble(milAndLt)) / 100;
            }else{
                porcentaje = (float) (formula.getPorc());
            }
        }

        return porcentaje;


    }



}
