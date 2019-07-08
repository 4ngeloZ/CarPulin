package com.example.carpulin.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ConductorResponse implements Serializable {
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

    @SerializedName("fechaLicencia")
    private String fechaLicencia;

    @SerializedName("claseLicencia")
    private String claseLicencia;

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
    public String getFechaLicencia(){
        return fechaLicencia;
    }
    public String getClaseLicencia(){
        return claseLicencia;
    }
}
