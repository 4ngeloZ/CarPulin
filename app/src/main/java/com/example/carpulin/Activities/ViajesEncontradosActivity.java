package com.example.carpulin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Pasajero;
import com.example.carpulin.R;
import com.example.carpulin.RecyclerViewAdapterViajes;
import com.example.carpulin.ViajeModelo;

import java.util.List;

public class ViajesEncontradosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewViaje;
    private RecyclerViewAdapterViajes adaptadorViaje;
    private Pasajero pasajero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viajes_encontrados);
        Bundle bundle = getIntent().getExtras();

        recyclerViewViaje = (RecyclerView)findViewById(R.id.RecyclerViaje);
        recyclerViewViaje.setLayoutManager(new LinearLayoutManager(this));

        adaptadorViaje = new RecyclerViewAdapterViajes(getViajes(bundle.getString("origen_busqueda"),bundle.getString("destino_busqueda"), bundle.getString("fecha_busqueda"), bundle.getString("numero_plazas")));
        recyclerViewViaje.setAdapter(adaptadorViaje);

        pasajero = (Pasajero)getIntent().getSerializableExtra("pasajero_entidad");
    }

    public List<ViajeModelo> getViajes(String origen, String destino, String fecha, String plazas){
        List<ViajeModelo> viajes = DBQueries.getViajes(origen, destino, fecha, plazas,this);
        return viajes;
    }

    public void verInformacionViaje(View view){
        String idViaje=adaptadorViaje.getId(view);
        Intent ViajesEncontradosActivity = new Intent(this, InformacionViajeActivity.class);
        ViajesEncontradosActivity.putExtra("idViaje", idViaje);
        ViajesEncontradosActivity.putExtra("pasajero_entidad", pasajero);
        startActivity(ViajesEncontradosActivity);
    }

}
