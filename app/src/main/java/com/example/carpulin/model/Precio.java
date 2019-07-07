package com.example.carpulin.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Precio implements Serializable{
    @SerializedName("precio")
    private int precio;

    public int getPrecio(){
        return precio;
    }

    public void setPrecio(int precio){
        this.precio = precio;
    }
}
