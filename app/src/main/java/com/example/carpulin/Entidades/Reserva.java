package com.example.carpulin.Entidades;

public class Reserva {
    private String id;
    private String idviaje;
    private String username;
    private int plazas1;
    private int plazas2;
    private int plazas3;
    private int plazas4;
    private int plazas5;
    private int valorTotal;
    private int procesada;
    private String origen;
    private String destino;

    public Reserva(String id, String idviaje, String username, int plazas1, int plazas2, int plazas3, int plazas4, int plazas5, int valorTotal, int procesada, String origen, String destino){
        this.id = id;
        this.idviaje = idviaje;
        this.username = username;
        this.plazas1 = plazas1;
        this.plazas2 = plazas2;
        this.plazas3 = plazas3;
        this.plazas4 = plazas4;
        this.plazas5 = plazas5;
        this.valorTotal = valorTotal;
        this.procesada = procesada;
        this.origen = origen;
        this.destino = destino;
    }

    public String getId() {
        return id;
    }

    public String getIdviaje() {
        return idviaje;
    }

    public String getUsername() {
        return username;
    }

    public int getPlazas1() {
        return plazas1;
    }

    public int getPlazas2() {
        return plazas2;
    }

    public int getPlazas3() {
        return plazas3;
    }

    public int getPlazas4() {
        return plazas4;
    }

    public int getPlazas5() {
        return plazas5;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public int getProcesada() {
        return procesada;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }
}