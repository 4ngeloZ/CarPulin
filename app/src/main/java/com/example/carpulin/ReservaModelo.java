package com.example.carpulin;

public class ReservaModelo {
    private String id;
    private String idviaje;
    private String pasajero;
    private String origenViaje;
    private String destinoViaje;
    private String origenReserva;
    private String destinoReserva;
    private String fecha;
    private String hora;
    private int plazas;
    private int foto;
    //private int tipoviaje;
    private int valortotal;

    public ReservaModelo(String id, String idviaje, String pasajero, String origenViaje, String destinoViaje, String origenReserva, String destinoReserva, String fecha, String hora, int plazas, int foto, int valortotal) {
        this.id=id;
        this.idviaje=idviaje;
        this.pasajero = pasajero;
        this.origenViaje = origenViaje;
        this.destinoViaje = destinoViaje;
        this.origenReserva = origenReserva;
        this.destinoReserva = destinoReserva;
        this.fecha = fecha;
        this.hora = hora;
        this.plazas = plazas;
        this.foto = foto;
        this.valortotal = valortotal;
    }

    public String getId(){
        return id;
    }

    public String getIdViaje() {
        return idviaje;
    }

    public String getPasajero() {
        return pasajero;
    }

    public String getOrigenViaje() {
        return origenViaje;
    }

    public String getDestinoViaje() {
        return destinoViaje;
    }

    public String getOrigenReserva() {
        return origenReserva;
    }

    public String getDestinoReserva() {
        return destinoReserva;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public int getPlazas() {
        return plazas;
    }

    public int getFoto() {
        return foto;
    }

    public int getValortotal() {
        return valortotal;
    }
}
