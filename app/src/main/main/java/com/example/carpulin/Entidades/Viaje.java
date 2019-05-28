package com.example.carpulin.Entidades;

public class Viaje {
    private String id;
    private String origen;
    private String destino;
    private String fechaInicio;
    private String horaInicio;
    private String fechaLlegada;
    private String horaLlegada;
    private int valorTotal;
    private String parada1;
    private String parada2;
    private String parada3;
    private String parada4;
    private String fecha1;
    private String fecha2;
    private String fecha3;
    private String fecha4;
    private String hora1;
    private String hora2;
    private String hora3;
    private String hora4;
    private int valor1;
    private int valor2;
    private int valor3;
    private int valor4;
    private int plazas1;
    private int plazas2;
    private int plazas3;
    private int plazas4;
    private int plazas5;
    private String conductor;

    public Viaje(String id, String origen, String destino, String fechaInicio, String horaInicio, int valorTotal, String fechaLlegada,
                 String horaLlegada, String parada1, String parada2, String parada3, String parada4, String fecha1, String fecha2,
                 String fecha3, String fecha4, String hora1, String hora2, String hora3, String hora4, int valor1, int valor2,
                 int valor3, int valor4, int plazas1, int plazas2, int plazas3, int plazas4, int plazas5, String conductor){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.fechaLlegada = fechaLlegada;
        this.horaLlegada = horaLlegada;
        this.valorTotal = valorTotal;
        this.parada1 = parada1;
        this.parada2 = parada2;
        this.parada3 = parada3;
        this.parada4 = parada4;
        this.fecha1 = fecha1;
        this.fecha2 = fecha2;
        this.fecha3 = fecha3;
        this.fecha4 = fecha4;
        this.hora1 = hora1;
        this.hora2 = hora2;
        this.hora3 = hora3;
        this.hora4 = hora4;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.valor3 = valor3;
        this.valor4 = valor4;
        this.plazas1 = plazas1;
        this.plazas2 = plazas2;
        this.plazas3 = plazas3;
        this.plazas4 = plazas4;
        this.plazas5 = plazas5;
        this.conductor = conductor;
    }

    public String getId() {
        return id;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public int getValorTotal() {
        return valorTotal;
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

    public String getFecha1() {
        return fecha1;
    }

    public String getFecha2() {
        return fecha2;
    }

    public String getFecha3() {
        return fecha3;
    }

    public String getFecha4() {
        return fecha4;
    }

    public String getHora1() {
        return hora1;
    }

    public String getHora2() {
        return hora2;
    }

    public String getHora3() {
        return hora3;
    }

    public String getHora4() {
        return hora4;
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

    public int getPlazas5(){
        return plazas5;
    }

    public String getConductor() {
        return conductor;
    }
}
