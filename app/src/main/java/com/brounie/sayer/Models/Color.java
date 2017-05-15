package com.brounie.sayer.Models;

/**
 * Created by ajamaica on 27/03/17.
 */

public class Color {


    private int id;
    private String objectId;
    private int idSayer;
    private String armadora;
    private String armadoraSayer;
    private String colorCode;
    private String colorNode;
    private String colCode;
    private String colorName;
    private String sayer;
    private String stdNbr;
    private int firstYear;
    private int lastYear;
    private String variation;
    private String hexadecimal;


    public Color(){

    }

    public Color(String objectId,int idSayer,String armadora,String colorCode,String colorNode,
        String colCode,String colorName, String sayer, String stdNbr, int firstYear, int lastYear,
                 String variation, String hexadecimal,String armadoraSayer){
        super();

        this.objectId = objectId;
        this.idSayer = idSayer;
        this.armadora = armadora;
        this.colorCode = colorCode;
        this.colorNode = colorNode;
        this.colCode = colCode;
        this.colorName = colorName;
        this.sayer = sayer;
        this.stdNbr = stdNbr;
        this.firstYear = firstYear;
        this.lastYear = lastYear;
        this.variation = variation;
        this.hexadecimal = hexadecimal;
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

    public String getArmadora() {
        return armadora;
    }
    public void setArmadora(String armadora) {
        this.armadora = armadora;
    }

    public String getColorCode() {
        return colorCode;
    }
    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorNode() {
        return colorNode;
    }
    public void setColorNode(String colorNode) {
        this.colorNode = colorNode;
    }

    public String getColCode() {
        return colCode;
    }
    public void setColCode(String colCode) {
        this.colCode = colCode;
    }


    public String getColorName() {
        return colorName;
    }
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }


    public String getSayer() {
        return sayer;
    }
    public void setSayer(String sayer) {
        this.sayer = sayer;
    }


    public String getStdNbr() {
        return stdNbr;
    }
    public void setStdNbr(String stdNbr) {
        this.stdNbr = stdNbr;
    }


    public int getFirstYear() {
        return firstYear;
    }
    public void setFirstYear(int firstYear) {
        this.firstYear = firstYear;
    }

    public int getLastYear() {
        return lastYear;
    }
    public void setLastYear(int lastYear) {
        this.lastYear = lastYear;
    }


    public String getVariation() {
        return variation;
    }
    public void setVariation(String variation) {
        this.variation = variation;
    }


    public String getHexadecimal() {
        return hexadecimal;
    }
    public void setHexadecimal(String hexadecimal) {
        this.hexadecimal = hexadecimal;
    }

    public String getArmadoraSayer() {
        return armadoraSayer;
    }
    public void setArmadoraSayer(String armadoraSayer) {
        this.armadoraSayer = armadoraSayer;
    }


}
