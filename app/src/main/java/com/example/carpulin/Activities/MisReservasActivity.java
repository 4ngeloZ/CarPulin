package com.example.carpulin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Pasajero;
import com.example.carpulin.Entidades.Reserva;
import com.example.carpulin.R;
import com.example.carpulin.RecyclerViewAdapterMisReservas;

import java.util.List;

import static com.example.carpulin.DB.DBQueries.getMisReservas;

public class MisReservasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMisReservas;
    private RecyclerViewAdapterMisReservas adaptadorMisReservas;
    private Pasajero pasajero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_reservas);
        pasajero = (Pasajero)getIntent().getSerializableExtra("pasajero_entidad");

        recyclerViewMisReservas = (RecyclerView)findViewById(R.id.RecyclerMisReservas);
        recyclerViewMisReservas.setLayoutManager(new LinearLayoutManager(this));

        adaptadorMisReservas = new RecyclerViewAdapterMisReservas(getMisReservas(pasajero.getUsername()), this);
        recyclerViewMisReservas.setAdapter(adaptadorMisReservas);
    }

    public List<Reserva> getMisReservas(String username){
        List<Reserva> viajes = DBQueries.getMisReservas(username, this);
        return viajes;
    }
}
