package com.example.carpulin.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PasajeroResponse implements Serializable{
    @SerializedName("nombre")
    private String nombre;

    @SerializedName("apellido")
    private String apellido;

    @SerializedName("username")
    private String username;

    @SerializedName("mail")
    private String mail;

    @SerializedName("telefono")
    private String telefono;

    @SerializedName("rut")
    private String rut;

    @SerializedName("descripcion")
    private String descripcion;

    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getUsername(){
        return username;
    }
    public String getMail(){
        return mail;
    }
    public String getTelefono(){
        return telefono;
    }
    public String getRut(){
        return rut;
    }
    public String getDescripcion(){
        return descripcion;
    }
}
