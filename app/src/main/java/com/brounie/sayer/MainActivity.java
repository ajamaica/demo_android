package com.brounie.sayer;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brounie.sayer.Adapter.AdapterArmadora;
import com.brounie.sayer.Bd.SayerSQLiteHelper;
import com.brounie.sayer.Models.Armadora;
import com.brounie.sayer.Models.Base;
import com.brounie.sayer.Models.Color;
import com.brounie.sayer.Models.Formula;
import com.brounie.sayer.Utils.Version;
import com.parse.FindCallback;
import com.parse.ParseConfig;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends AppCompatActivity {

    SayerSQLiteHelper db = new SayerSQLiteHelper(this);
    public List armadorasLocal;

    private StickyListHeadersListView armadorasList;
    private AdapterArmadora adapterArmadora;

    private ImageView icon_izq;
    private TextView tittle;
    private View loading;
    private EditText search;

    ArrayList<String> armadorasLocalImg = new ArrayList<>();

    int versionLocalGlobal;
    int versionParseGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        search = (EditText) findViewById(R.id.search);
        loading = (View) findViewById(R.id.loading);
        armadorasList = (StickyListHeadersListView) findViewById(R.id.armadorasList);
        icon_izq = (ImageView) findViewById(R.id.icon_izq);
        tittle = (TextView) findViewById(R.id.tittle);
        tittle.setText(getResources().getString(R.string.tittle_armadora));
        icon_izq.setVisibility(View.INVISIBLE);

        //arreglo de imagenes
        String[] armadoras = getResources().getStringArray(R.array.armadoras);
        for(int i = 0 ; i < armadoras.length; i++){
            armadorasLocalImg.add(armadoras[i]);
        }

        Version versionLocal = new Version(MainActivity.this);

        if(versionLocal.getVariableVersion() != 0){//base cargada
            armadorasLocal = db.getArmadoras();
            fillUpList(armadorasLocal,armadorasLocalImg);
        }else{
            loading.setVisibility(View.VISIBLE);
        }


        new db().execute(""); //revisa si hay nueva version o no
        new dollar().execute(""); //revisa si el precio del dollar cambio

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String busqueda = s.toString();

                if(busqueda != null || !search.equals("")){
                    armadorasLocal = db.getArmadorasSearch(busqueda);
                }else{
                    armadorasLocal = db.getArmadoras();
                }

                fillUpList(armadorasLocal,armadorasLocalImg);
            }
        });

    }

    private void getArmadoras(final ArrayList<String> armadorasLocalImg, final int version,
                              final int versionParse){

        versionLocalGlobal = version;
        versionParseGlobal = versionParse;

        loading.setVisibility(View.VISIBLE);
        final ParseQuery<ParseObject>  query = ParseQuery.getQuery("Armadora");
        query.orderByAscending("mfg_name");
        if(version != 0 ){
          query.whereEqualTo("version",versionParse);
        }
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {


                if(e == null){

                    if(version == 0 ){
                        if(objects.size() != 0){
                            ParseObject[]    armadoras = objects.toArray(new ParseObject[objects.size()]);
                            ParseObject armadora;
                            for(int i = 0 ; i < armadoras.length; i++){
                                armadora = armadoras[i];
                                db.createArmadora(new Armadora(armadora.getString("mfg"),armadora.getString("mfg_name"),armadora.getObjectId()));
                            }
                            armadorasLocal = db.getArmadoras();
                            fillUpList(armadorasLocal,armadorasLocalImg);
                            getColors();
                        }
                    }else{

                        if(objects.size() != 0){
                            ParseObject[]    armadoras = objects.toArray(new ParseObject[objects.size()]);
                            ParseObject armadora;
                            for(int i = 0 ; i < armadoras.length; i++){
                                armadora = armadoras[i];
                                db.createArmadora(new Armadora(armadora.getString("mfg"),armadora.getString("mfg_name"),armadora.getObjectId()));
                            }
                            armadorasLocal = db.getArmadoras();
                            fillUpList(armadorasLocal,armadorasLocalImg);
                        }

                        getColors();
                    }
                }



            }
        });


    }

    private void getColors(){
        loading.setVisibility(View.VISIBLE);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Color");
        query.setLimit(2000);
        if(versionLocalGlobal != 0 ){
            query.whereEqualTo("version",versionParseGlobal);
        }
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(e == null) {
                    System.out.println("tamaño colores"+objects.size());
                    ParseObject[]  colores = objects.toArray(new ParseObject[objects.size()]);
                    new saveColors().execute(colores);
                }




            }
        });

    }

    private void getBases(){
        loading.setVisibility(View.VISIBLE);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Base");
        query.setLimit(2000);
        if(versionLocalGlobal != 0 ){
            query.whereEqualTo("version",versionParseGlobal);
        }
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(e == null) {
                        System.out.println("tamaño bases"+objects.size());
                        ParseObject[]  bases = objects.toArray(new ParseObject[objects.size()]);
                        new saveBases().execute(bases);
                }




            }
        });

    }

    private void getFormula(){
        loading.setVisibility(View.VISIBLE);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Formula");
        if(versionLocalGlobal != 0 ){
            query.whereEqualTo("version",versionParseGlobal);
        }
        query.setLimit(12000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(e == null) {
                        System.out.println("tamaño Formula"+objects.size());
                        ParseObject[]  formulas = objects.toArray(new ParseObject[objects.size()]);
                        new saveFormulas().execute(formulas);
                }
            }
        });

    }

    private void fillUpList(List armadoras,ArrayList<String> armadorasLocalImg){

        adapterArmadora = new AdapterArmadora(this,armadoras,armadorasLocalImg);
        armadorasList.setAdapter(adapterArmadora);


    }

    private class db extends AsyncTask<String, Void, String> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected String doInBackground(String... params) {

            ParseConfig parseConfig = null;
            int version = 0;
            try {
                parseConfig = ParseConfig.get();
                version = parseConfig.getInt("version");

            } catch (ParseException e) {
                e.printStackTrace(); //no internet
                version = -1;
            }

            return ""+version;
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(String result) {

            loading.setVisibility(View.GONE);

            Version versionLocal = new Version(MainActivity.this);

            int verParse = Integer.parseInt(result);

            if(verParse != -1){
                if(verParse != versionLocal.getVariableVersion()){ //las versiones son diferentes consulta
                    //db.onUpgrade(db.getWritableDatabase(), 1, 1);

                    getArmadoras(armadorasLocalImg,versionLocal.getVariableVersion(),verParse);
                    versionLocal.setVariableVersion(verParse);
                }
            }else{

                if(versionLocal.getVariableVersion() == 0){ //entro por primera vez y no cargo nada
                    LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast, (ViewGroup) MainActivity.this.findViewById(R.id.toast_layout_root));
                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("Inténtelo más tarde.");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 150);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
                }
            }
        }

    }

    private class saveColors extends AsyncTask<ParseObject[], Void, String> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected String doInBackground(ParseObject[]... colores) {

            ParseObject[]    coloresdoInBackground = colores[0];
            ParseObject color;
            for(int i = 0 ; i < coloresdoInBackground.length; i++){
                color = coloresdoInBackground[i];
                db.createColor(new Color(color.getObjectId(),color.getInt("idSayer"),
                        color.getParseObject("armadora").getObjectId(),color.getString("colorCode"),
                        color.getString("colorNode"),color.getString("colCode"),color.getString("colorName"),
                        color.getString("sayer"),color.getString("stdNbr"),color.getInt("firstYear"),
                        color.getInt("lastYear"),color.getString("variation"),color.getString("hexadecimal"),color.getString("armadoraSayer")));
            }


            return "successful";
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(String result) {
            loading.setVisibility(View.GONE);
            getBases();

        }

    }

    private class saveBases extends AsyncTask<ParseObject[], Void, String> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected String doInBackground(ParseObject[]... bases) {

            ParseObject[]    basesdoInBackground = bases[0];
            ParseObject base;
            for(int i = 0 ; i < basesdoInBackground.length; i++){
                base = basesdoInBackground[i];

                db.createBase(new Base(base.getObjectId(),base.getDouble("precio"),
                        base.getString("material"),base.getInt("id"),base.getInt("peso"),
                        base.getString("textoMat"),base.getString("materialName")));
            }


            return "successful";
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(String result) {
            loading.setVisibility(View.GONE);
            getFormula();
        }

    }

    private class saveFormulas extends AsyncTask<ParseObject[], Void, String> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected String doInBackground(ParseObject[]... formulas) {


            ParseObject[]   formulasdoInBackground = formulas[0];
            ParseObject formula;
            for(int i = 0 ; i < formulasdoInBackground.length; i++){
                formula = formulasdoInBackground[i];

                db.createFormula(new Formula(formula.getObjectId(),formula.getInt("id"),
                        formula.getInt("precioP"),formula.getString("linea"),formula.getString("fondoSayer")
                ,formula.getDouble("porc"),formula.getString("colorCode"),formula.getString("baseSayer")
                ,formula.getInt("pero"),formula.getInt("costomer"),formula.getInt("acomulado")
                ,formula.getParseObject("armadora").getObjectId(),formula.getString("armadoraSayer")));
            }


            return "successful";
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(String result) {
            loading.setVisibility(View.GONE);
        }

    }

    private class dollar extends AsyncTask<String, Void, String> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected String doInBackground(String... params) {

            ParseConfig parseConfig = null;
            int version = 0;
            double dollar = 0.0;
            try {
                parseConfig = ParseConfig.get();
                version = parseConfig.getInt("versionDollar");
                dollar = parseConfig.getDouble("dollar");
            } catch (ParseException e) {
                e.printStackTrace();
                version = -1;
            }

            return ""+version+"-"+dollar;
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(String result) {

            System.out.println("DOLLAR: "+result);
            String[] results = result.split("-");

            Version versionLocal = new Version(MainActivity.this);

            int verLocal = Integer.parseInt(results[0]);

            if(verLocal != -1){
                if(verLocal != versionLocal.getVariableVersionDollar()){
                    versionLocal.setVariableVersionDollar(verLocal);
                    versionLocal.setVariableDollar(Float.parseFloat(results[1]));

                }
            }
        }

    }

}
