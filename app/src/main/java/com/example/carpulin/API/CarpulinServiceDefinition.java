package com.example.carpulin.API;

import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.PUT;
import com.example.carpulin.model.*;

public interface CarpulinServiceDefinition {
    /*@GET("conductores/{conductor}/viajes")
    Call<List<Viaje>> getViajesDeConductor(@Path("conductor") String conductor);

    @GET("viajes/{viajeid}/paradas")
    Call<List<Parada>> getParadasDeViaje(@Path("viajeid")int idViaje);

    @GET("viajes/{viajeid}")
    Call<Viaje> getDatosViaje(@Path("viajeid")int idViaje);

    @GET("conductores/{conductor}/viajes/{viajeid}/reservas")
    Call<List<Reserva>> getReservasRecibidas(@Path("conductor")String conductor, @Path("viajeid") int idViaje);

    @PUT("conductores/{conductor}/viajes/{viajeid}/reservas/{idreserva}")
    Call<ResponseBody> setEstadoReserva(@Path("conductor") String conductor, @Path("viajeid") int idViaje, @Path("idreserva") int idReserva, @Body Reserva reserva);
    @GET("viajes")
    Call<List<Viaje>> getViajes(@Query("origen") String origen, @Query("destino") String destino,
                                @Query("fechaminima") String fechamin, @Query("fechamaxima") String fechamax,
                                @Query("asientos") int asientos, @Query("maletas") int maletas);
    @POST("viajes/{viajeid}/pasajeros")
    Call<ResponseBody> postReserva(@Body Reserva res, @Path("viajeid") int viajeid);

    @POST("conductores/{conductor}/viajes")
    Call<Viaje> postViaje(@Body Viaje vi, @Path("conductor") String conductor);

    @POST("conductores/{conductor}/viajes/{viajeid}/paradas")
    Call<ResponseBody> setParadas(@Path("conductor") String conductor, @Path("viajeid") int viajeid, @Body List<Parada> paradas);

    @GET("pasajeros/{pasajero}/reservas")
    Call<List<Reserva>> getReservaPasajero(@Path("pasajero") String pasajero);

    @DELETE("pasajeros/{pasajero}/reservas/{reservaID}")
    Call<ResponseBody> deleteReserva(@Path("pasajero") String pasajero, @Path("reservaID") int reservaid);

    @DELETE("viajes/{viajeid}")
    Call<ResponseBody> deleteViaje(@Path("viajeid") int viajeid);
    */
    @POST("login")
    Call<LoginResponse> login(@Body UserData data);

    /*@GET("viajes/{viajeid}/precio")
    Call<Precio> getPrecio(@Path("viajeid") int viajeid, @Query("origen")String origen, @Query("destino") String destino);
    */
}

