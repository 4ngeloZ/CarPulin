package com.example.carpulin.Entidades;

import java.io.Serializable;

public class Vehiculo implements Serializable {
    private String marca;
    private String modelo;
    private String patente;
    private int asientos;
    private int año;
    private String Username;

    public  Vehiculo (String Username, String patente, String marca, String modelo,  int asientos, int año){
       this.Username = Username;
        this.marca = marca;
        this.modelo = modelo;
        this.patente = patente;
        this.asientos = asientos;
        this.año = año;
    }

    public String getMarca(){return  marca;}
    public String getModelo(){return modelo;}
    public String getPatente(){return patente;}
    public int getAsientos(){return asientos;}
    public int getAño(){return año;}
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }
    public void setMarca(String marca){this.marca = marca;}
    public void setModelo(String modelo){this.modelo = modelo;}
    public void setPatente(String patente){this.patente = patente;}
    public void setAsientos(int asientos){this.asientos = asientos;}
    public void setAño(int año){this.año = año;}


}



