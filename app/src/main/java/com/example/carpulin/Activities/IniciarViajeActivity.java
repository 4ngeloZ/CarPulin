package com.example.carpulin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.Entidades.Viaje;
import com.example.carpulin.R;
import com.example.carpulin.RecyclerViewAdapterMisViajes;
import com.example.carpulin.RecyclerViewAdapterPasajerosBajar;
import com.example.carpulin.RecyclerViewAdapterPasajerosSubir;
import com.example.carpulin.ReservaModelo;

import java.util.List;

public class IniciarViajeActivity extends AppCompatActivity {

    private Conductor conductor;
    private Viaje viaje;
    private String idviaje;
    private String parada;
    private TextView titulo;
    private Button boton;
    private boolean terminar;
    private int numparadas;
    private int siguiente;
    private RecyclerView recyclerViewPasajerosSubir;
    private RecyclerViewAdapterPasajerosSubir adaptadorPasajerosSubir;
    private RecyclerView recyclerViewPasajerosBajar;
    private RecyclerViewAdapterPasajerosBajar adaptadorPasajerosBajar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_viaje);
        terminar = false;
        conductor = (Conductor) getIntent().getSerializableExtra("conductor_entidad");
        idviaje = (String) getIntent().getSerializableExtra("viaje");
        numparadas = getIntent().getIntExtra("num_paradas", 0);
        siguiente = getIntent().getIntExtra("siguiente", 1);
        viaje = DBQueries.getfullViaje(idviaje, this);
        if (siguiente == 1 && numparadas > 0) parada = viaje.getParada1();
        else if (siguiente == 2 && numparadas > 1) parada = viaje.getParada2();
        else if (siguiente == 3 && numparadas > 2) parada = viaje.getParada3();
        else if (siguiente == 4 && numparadas > 3) parada = viaje.getParada4();
        else{
            parada = viaje.getDestino();
            terminar = true;
        }


        titulo = findViewById(R.id.textView_parada);
        boton = findViewById(R.id.button_continuar);
        recyclerViewPasajerosSubir = (RecyclerView)findViewById(R.id.RecyclerReservasViajeSubir);
        recyclerViewPasajerosSubir.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPasajerosBajar = (RecyclerView)findViewById(R.id.RecyclerReservasViajeBajar);
        recyclerViewPasajerosBajar.setLayoutManager(new LinearLayoutManager(this));

        adaptadorPasajerosSubir = new RecyclerViewAdapterPasajerosSubir(DBQueries.getReservasViajeSubir(conductor.getUsername(),idviaje,parada,this));
        recyclerViewPasajerosSubir.setAdapter(adaptadorPasajerosSubir);
        adaptadorPasajerosBajar = new RecyclerViewAdapterPasajerosBajar(DBQueries.getReservasViajeBajar(conductor.getUsername(), idviaje,parada,this));
        recyclerViewPasajerosBajar.setAdapter(adaptadorPasajerosBajar);

        titulo.setText(parada);
        if(terminar) boton.setText("Terminar");
    }

    public void continuar(View view){
        if(terminar){
            DBQueries.terminarViaje(viaje.getId(),this);
            Intent activity = new Intent(this, ConductorActivity.class);
            activity.putExtra("conductor_entidad", conductor);
            startActivity(activity);
            Toast.makeText(this,"Viaje terminado con éxito", Toast.LENGTH_LONG).show();
        }
        else{
            List<ReservaModelo> reservaslistas = adaptadorPasajerosSubir.getReservasListas();
            for(int i=0; i<reservaslistas.size();i++){
                DBQueries.ReservaTerminada(reservaslistas.get(i).getId(),this);
            }
            List<ReservaModelo> reservasrechazadas = adaptadorPasajerosSubir.getReservasRechazadas();
            for(int i=0; i<reservasrechazadas.size();i++){
                DBQueries.ReservaRechazada(reservasrechazadas.get(i).getId(),this);
            }
            Intent activity = new Intent(this, IniciarViajeActivity.class);
            activity.putExtra("conductor_entidad", conductor);
            activity.putExtra("viaje", idviaje);
            activity.putExtra("num_paradas", numparadas);
            if (siguiente == 1) activity.putExtra("siguiente", 2);
            if (siguiente == 2) activity.putExtra("siguiente", 3);
            if (siguiente == 3) activity.putExtra("siguiente", 4);
            if (siguiente == 4) activity.putExtra("siguiente", 5);
            startActivity(activity);
        }
    }
}
