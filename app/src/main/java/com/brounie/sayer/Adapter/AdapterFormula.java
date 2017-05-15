package com.brounie.sayer.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brounie.sayer.Activities.PerfilColorActivity;
import com.brounie.sayer.Bd.SayerSQLiteHelper;
import com.brounie.sayer.Models.Base;
import com.brounie.sayer.Models.Color;
import com.brounie.sayer.Models.Formula;
import com.brounie.sayer.R;
import com.bumptech.glide.Glide;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by ajamaica on 06/04/17.
 */

public class AdapterFormula extends RecyclerView.Adapter<AdapterFormula.MyViewHolder> {

    private Activity context;
    private List data;
    PerfilColorActivity main;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,porcNew,porc,material;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txtName);
            porcNew = (TextView) view.findViewById(R.id.porcNew);
            porc = (TextView) view.findViewById(R.id.porc);
            material = (TextView) view.findViewById(R.id.material);
        }
    }

    public AdapterFormula(List data,Activity context) {
        this.data = data;
        this.context=context;
        this.main = (PerfilColorActivity) context;




    }

    @Override
    public AdapterFormula.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_formula, parent, false);

        return new AdapterFormula.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterFormula.MyViewHolder holder, final int position) {
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        SayerSQLiteHelper db = new SayerSQLiteHelper(context);
        final Formula formula = (Formula) data.get(position);
        Base base = (Base)  db.getNameBase(formula.getBase());

        if(db.getNameBase(formula.getBase()) != null){
            holder.name.setText(""+base.getTextoMat());
        }

        holder.material.setText(""+base.getMaterialName());


        if(main.milAndLt != null && !main.milAndLt.equals("")){

            BigDecimal porcentajeFinal = new
                    BigDecimal(procentaje(formula)).setScale(3,BigDecimal.ROUND_HALF_UP);
            holder.porc.setText(""+formateador.format(porcentajeFinal));

            if(position == 0){
                BigDecimal acomulado = new BigDecimal(main.acomulados.get(position)).setScale(2,BigDecimal.ROUND_HALF_UP);
                holder.porcNew.setText(""+formateador.format(acomulado));
            }else{
                BigDecimal acomulado = new BigDecimal(main.acomulados.get(position)).setScale(2,BigDecimal.ROUND_HALF_UP);
                holder.porcNew.setText(""+formateador.format(acomulado));
            }

        }else{

            BigDecimal porcFinal = new BigDecimal(porcentajeNoSpinner(formula)).setScale(2,BigDecimal.ROUND_HALF_UP);
            holder.porc.setText(""+formateador.format(porcFinal));
            if(position == 0){
                BigDecimal acomulado = new BigDecimal(main.acomulados.get(position)).setScale(2,BigDecimal.ROUND_HALF_UP);
                holder.porcNew.setText(""+formateador.format(acomulado));
            }else{
                BigDecimal acomulado = new BigDecimal(main.acomulados.get(position)).setScale(2,BigDecimal.ROUND_HALF_UP);
                holder.porcNew.setText(""+formateador.format(acomulado));
            }

        }


        if(formula.getPorc() == 0){
            holder.porcNew.setText("");
            holder.porc.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private float procentaje(Formula formula){

        float porcentaje = 0;
        if(main.selectSpiner != null){
            if(main.selectSpiner.equals("ml")){
                porcentaje = (float)(formula.getPorc()*Double.parseDouble(main.milAndLt))/100;
            }
            if(main.selectSpiner.equals("Lt")){
                porcentaje = (float)
                        ((formula.getPorc()*Double.parseDouble(main.milAndLt))/100) * 1000;
            }
        }else{
            porcentaje = (float)(formula.getPorc()*Double.parseDouble(main.milAndLt))/100;
        }
        return porcentaje;


    }

    private double porcentajeNoSpinner(Formula formula){

        double porcentaje = 0;

        if(main.selectSpiner != null){

            if(main.milAndLt != null && !main.milAndLt.equals("")) {

                if (main.selectSpiner.equals("ml")) {
                    porcentaje = (formula.getPorc() * Double.parseDouble(main.milAndLt)) / 100;
                }
                if (main.selectSpiner.equals("Lt")) {
                    porcentaje = ((formula.getPorc() * Double.parseDouble(main.milAndLt)) / 100) * 1000;
                }
            }else{

                if (main.selectSpiner.equals("ml")) {
                    porcentaje = formula.getPorc();
                }
                if (main.selectSpiner.equals("Lt")) {
                    porcentaje = formula.getPorc() * 1000;
                }
            }
        }else{
            porcentaje = formula.getPorc();
        }

        return  porcentaje;
    }







}