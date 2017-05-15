package com.brounie.sayer.Models;

/**
 * Created by ajamaica on 24/03/17.
 */

public class Armadora {

    private int id;
    private String objectId;
    private String name;
    private String nameComplete;

    public Armadora(){}

    public Armadora(String name, String nameComplete,String objectId) {
        super();
        this.name = name;
        this.nameComplete = nameComplete;
        this.objectId = objectId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNameComplete() {
        return nameComplete;
    }
    public void setNameComplete(String nameComplete) {
        this.nameComplete = nameComplete;
    }

    public String getObjectId() {
        return objectId;
    }
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

}
