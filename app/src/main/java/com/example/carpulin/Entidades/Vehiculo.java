package com.example.carpulin.Entidades;

import java.io.Serializable;

public class Vehiculo implements Serializable {
    private String marca;
    private String modelo;
    private String patente;
    private int asientos;
    private int año;
    private String Username;

    public  Vehiculo (String Username, String marca, String modelo, String patente, int asientos, int año){
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


}



