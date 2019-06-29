package com.example.carpulin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.Entidades.Viaje;
import com.example.carpulin.R;
import com.example.carpulin.RecyclerViewAdapterMisViajes;

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
        List<Viaje> viajes = DBQueries.getMisViajes(username, this);
        return viajes;
    }
}
