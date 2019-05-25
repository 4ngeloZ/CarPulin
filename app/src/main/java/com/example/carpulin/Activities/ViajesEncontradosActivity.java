package com.example.carpulin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.R;
import com.example.carpulin.RecyclerViewAdapterViajes;
import com.example.carpulin.ViajeModelo;

import java.util.List;

public class ViajesEncontradosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewViaje;
    private RecyclerViewAdapterViajes adaptadorViaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viajes_encontrados);
        Bundle bundle = getIntent().getExtras();

        recyclerViewViaje = (RecyclerView)findViewById(R.id.RecyclerViaje);
        recyclerViewViaje.setLayoutManager(new LinearLayoutManager(this));

        adaptadorViaje = new RecyclerViewAdapterViajes(getViajes(bundle.getString("origen_busqueda"),bundle.getString("destino_busqueda"), bundle.getString("fecha_busqueda")));
        recyclerViewViaje.setAdapter(adaptadorViaje);
    }

    public List<ViajeModelo> getViajes(String origen, String destino, String fecha){
        List<ViajeModelo> viajes = DBQueries.getViajes(origen, destino, fecha, this);
        return viajes;
    }

    public void verInformacionViaje(View view){
        String idViaje=adaptadorViaje.getId(view);
        Intent ViajesEncontradosActivity = new Intent(this, InformacionViajeActivity.class);
        ViajesEncontradosActivity.putExtra("idViaje", idViaje);
        startActivity(ViajesEncontradosActivity);
    }

}
