package com.expose.drive.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vineesh.expose on 03-11-2017.
 */

public class Model implements Serializable {

    @SerializedName("id")
    private  String ID;

    @SerializedName("name")
    private  String NAME;

    @SerializedName("files")
    private List<Model> FILES;


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public List<Model> getFILES() {
        return FILES;
    }

    public void setFILES(List<Model> FILES) {
        this.FILES = FILES;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
