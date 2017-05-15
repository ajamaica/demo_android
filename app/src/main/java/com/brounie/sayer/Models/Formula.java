package com.brounie.sayer.Models;

/**
 * Created by ajamaica on 05/04/17.
 */

public class Formula {

    private int id;
    private String objectId;
    private int idSayer;
    private int precioP;
    private String linea;
    private String fondo;
    private double porc;
    private String colorCode;
    private String base;
    private int peso;
    private int costomer;
    private int acomulado;
    private String armadora;
    private String armadoraSayer;

    public Formula(){}

    public Formula(String objectId,int idSayer,int precioP,String linea,String fondo,double porc,
                   String colorCode,String base,int peso,int costomer,int acomulado,String armadora,
                   String armadoraSayer){
        super();
        this.objectId = objectId;
        this.idSayer = idSayer;
        this.precioP = precioP;
        this.linea = linea;
        this.fondo = fondo;
        this.porc = porc;
        this.colorCode = colorCode;
        this.base = base;
        this.peso = peso;
        this.costomer = costomer;
        this.acomulado = acomulado;
        this.armadora = armadora;
        this.armadoraSayer = armadoraSayer;

    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getIdSayer() {
        return idSayer;
    }
    public void setIdSayer(int idSayer) {
        this.idSayer = idSayer;
    }

    public int getPrecioP() {
        return precioP;
    }
    public void setPrecioP(int precioP) {
        this.precioP = precioP;
    }

    public String getLinea() {
        return linea;
    }
    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getFondo() {
        return fondo;
    }
    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public double getPorc() {
        return porc;
    }
    public void setPorc(double porc) {
        this.porc = porc;
    }

    public String getColorCode() {
        return colorCode;
    }
    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getBase() {
        return base;
    }
    public void setBase(String base) {
        this.base = base;
    }

    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getCostomer() {
        return costomer;
    }
    public void setCostomer(int costomer) {
        this.costomer = costomer;
    }

    public int getAcomulado() {
        return acomulado;
    }
    public void setAcomulado(int acomulado) {
        this.acomulado = acomulado;
    }

    public String getArmadora() {
        return armadora;
    }
    public void setArmadora(String armadora) {
        this.armadora = armadora;
    }

    public String getArmadoraSayer() {
        return armadoraSayer;
    }
    public void setArmadoraSayer(String armadoraSayer) {
        this.armadoraSayer = armadoraSayer;
    }



}
