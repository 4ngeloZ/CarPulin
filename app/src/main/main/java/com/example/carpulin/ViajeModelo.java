package com.example.carpulin;

public class ViajeModelo {
    private String id;
    private String conductor;
    private String origen;
    private String destino;
    private String fecha;
    private String hora;
    private int plazas;
    private int foto;
    private String parada1;
    private String parada2;
    private String parada3;
    private String parada4;
    private int tipoviaje;
    private int valor1;
    private int valor2;
    private int valor3;
    private int valor4;
    private int valortotal;

    public ViajeModelo(String id, String conductor, String origen, String destino, String fecha, String hora, int plazas, int foto, String parada1, String parada2, String parada3, String parada4, int tipoviaje, int valor1, int valor2, int valor3, int valor4, int valortotal) {
        this.id=id;
        this.conductor = conductor;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.plazas = plazas;
        this.foto = foto;
        this.parada1 = parada1;
        this.parada2 = parada2;
        this.parada3 = parada3;
        this.parada4 = parada4;
        this.tipoviaje = tipoviaje;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.valor3 = valor3;
        this.valor4 = valor4;
        this.valortotal = valortotal;
    }

    public String getId(){
        return id;
    }

    public String getConductor() {
        return conductor;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
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

    public String getParada1() {
        return parada1;
    }

    public String getParada2() {
        return parada2;
    }

    public String getParada3() {
        return parada3;
    }

    public String getParada4() {
        return parada4;
    }

    public int getTipoviaje(){
        return tipoviaje;
    }

    public int getValor1() {
        return valor1;
    }

    public int getValor2() {
        return valor2;
    }

    public int getValor3() {
        return valor3;
    }

    public int getValor4() {
        return valor4;
    }

    public int getValortotal() {
        return valortotal;
    }
}
