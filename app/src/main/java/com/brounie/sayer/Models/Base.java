package com.brounie.sayer.Models;

/**
 * Created by ajamaica on 30/03/17.
 */

public class Base {

    private int id;
    private String objectId;
    private double precio;
    private String material;
    private String materialName;
    private int idSayer;
    private int peso;
    private String textoMat;


    public Base(){}

    public Base(String objectId,double precio,String material,int idSayer,int peso,String textoMat,
                String materialName){
        super();
        this.objectId = objectId;
        this.precio = precio;
        this.material = material;
        this.idSayer = idSayer;
        this.peso = peso;
        this.textoMat = textoMat;
        this.materialName = materialName;
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

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }

    public int getIdSayer() {
        return idSayer;
    }
    public void setIdSayer(int idSayer) {
        this.idSayer = idSayer;
    }

    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getTextoMat() {
        return textoMat;
    }
    public void setTextoMat(String textoMat) {
        this.textoMat = textoMat;
    }

    public String getMaterialName() {
        return materialName;
    }
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

}
