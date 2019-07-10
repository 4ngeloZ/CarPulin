package com.example.carpulin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.Entidades.Viaje;
import com.example.carpulin.R;
import com.example.carpulin.RecyclerViewAdapterMisViajes;

import java.util.ArrayList;
import java.util.List;

public class MisViajesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMisViajes;
    private RecyclerViewAdapterMisViajes adaptadorMisViajes;
    private Conductor conductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_viajes);
        conductor = (Conductor)getIntent().getSerializableExtra("conductor_entidad");

        recyclerViewMisViajes = (RecyclerView)findViewById(R.id.RecyclerMisViajes);
        recyclerViewMisViajes.setLayoutManager(new LinearLayoutManager(this));

        adaptadorMisViajes = new RecyclerViewAdapterMisViajes(getMisViajes(conductor.getUsername()));
        recyclerViewMisViajes.setAdapter(adaptadorMisViajes);
    }

    public List<Viaje> getMisViajes(String username){
        List<Viaje> viajespendientes = new ArrayList<>();
        List<Viaje> viajes = DBQueries.getMisViajes(username, this);
        for(int i=0;i<viajes.size();i++){
            if(!DBQueries.isViajeTerminado(viajes.get(i).getId(),this)) viajespendientes.add(viajes.get(i));
        }
        return viajespendientes;
    }

    public void verInformacionViaje(View view){
        String idViaje=adaptadorMisViajes.getId(view);
        Intent ViajesEncontradosActivity = new Intent(this, InformacionViajeConductorActivity.class);
        ViajesEncontradosActivity.putExtra("idViaje", idViaje);
        ViajesEncontradosActivity.putExtra("conductor_entidad", conductor);
        startActivity(ViajesEncontradosActivity);
    }
}
