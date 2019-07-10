package com.example.carpulin.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;


public class Reserva implements Serializable{
    public static final int PENDIENTE = 1, APROBADA = 2, RECHAZADA = 3;

    //region Variables
    @SerializedName("id")
    private int id;

    @SerializedName("pasajero")
    private String pasajero;

    @SerializedName("idViaje")
    private int idViaje;

    @SerializedName("origen")
    private String origen;

    @SerializedName("destino")
    private String destino;

    @SerializedName("asientos")
    private int asientos;

    @SerializedName("maletas")
    private int maletas;

    @SerializedName("estado")
    private int estado;

    @SerializedName("hora")
    private Date hora;

    @SerializedName("precio")
    int precio;

    public int getPrecio(){
        return precio;
    }

    public void setPrecio(int precio){
        this.precio = precio;
    }

    //endregion

    //region Acceors/Mutators
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPasajero() {
        return pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }

    public int getMaletas() {
        return maletas;
    }

    public void setMaletas(int maletas) {
        this.maletas = maletas;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getHora() { return hora; }

    //endregion
}