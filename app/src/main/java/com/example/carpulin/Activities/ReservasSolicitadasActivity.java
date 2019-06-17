package com.example.carpulin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.Entidades.Reserva;
import com.example.carpulin.R;
import com.example.carpulin.RecyclerViewAdapterReservas;
import com.example.carpulin.ReservaModelo;

import java.util.List;

public class ReservasSolicitadasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewReserva;
    private RecyclerViewAdapterReservas adaptadorReserva;
    private Conductor conductor;
    private Reserva reserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas_solicitadas);

        recyclerViewReserva = (RecyclerView)findViewById(R.id.RecyclerReserva);
        recyclerViewReserva.setLayoutManager(new LinearLayoutManager(this));

        conductor = (Conductor) getIntent().getSerializableExtra("conductor_entidad");

        adaptadorReserva = new RecyclerViewAdapterReservas(getReservas(conductor.getUsername()));
        recyclerViewReserva.setAdapter(adaptadorReserva);

        if(getReservas(conductor.getUsername()).size()<1)Toast.makeText(this, "No tiene reservas pendientes", Toast.LENGTH_LONG).show();

    }

    public List<ReservaModelo> getReservas(String username){
        List<ReservaModelo> reservas = DBQueries.getReservas(username, this);
        return reservas;
    }

    public void AceptarReserva(View view){
        String idReserva=adaptadorReserva.getIdReserva(view);
        String idViaje=adaptadorReserva.getIdViaje(view);
        //Toast.makeText(this, idReserva, Toast.LENGTH_SHORT).show();
        DBQueries.ReservaAceptada(idReserva, idViaje, this);
        finish();
        startActivity(getIntent());
    }

    public void RechazarReserva(View view){
        String idReserva=adaptadorReserva.getIdReserva(view);
        DBQueries.ReservaRechazada(idReserva, this);
        finish();
        startActivity(getIntent());
    }
}
