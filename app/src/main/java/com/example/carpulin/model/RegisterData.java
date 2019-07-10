package com.example.carpulin.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterData  implements Serializable {
    //region Variables
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("conductor")
    private boolean conductor;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("apellido")
    private String apellido;
    @SerializedName("telefono")
    private String telefono;
    @SerializedName("mail")
    private String mail;
    @SerializedName("rut")
    private String rut;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("sexo")
    private String sexo;


    public void setConductor(boolean conductor){
        this.conductor = conductor;
    }

    public void setPassword(String pass){
        password = pass;
    }

    public void setUsername(String user){
        username = user;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean esConductor(){
        return conductor;
    }
}
