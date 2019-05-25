package com.example.carpulin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Pasajero;
import com.example.carpulin.Entidades.Viaje;
import com.example.carpulin.R;
import com.example.carpulin.ViajeModelo;

import java.util.ArrayList;
import java.util.List;

public class BuscarViajeActivity extends AppCompatActivity {
    private EditText origen;
    private EditText destino;
    private EditText fecha;
    private EditText plazas;
    private Pasajero pasajero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_viaje);

        origen = (EditText)findViewById(R.id.BuscarViajeActivity_origen);
        destino = (EditText)findViewById(R.id.BuscarViajeActivity_destino);
        fecha = (EditText)findViewById(R.id.BuscarViajeActivity_fecha);
        plazas = (EditText)findViewById(R.id.BuscarViajeActivity_plazas);

        pasajero = (Pasajero)getIntent().getSerializableExtra("pasajero_entidad");
    }

    public void Buscar(View view){

        String str_origen = origen.getText().toString();
        String str_destino = destino.getText().toString();
        String str_fecha = fecha.getText().toString();

        List<ViajeModelo> viajes = DBQueries.getViajes(str_origen, str_destino, str_fecha, this);

        if(!str_origen.isEmpty() && !str_destino.isEmpty()) {
            if (!viajes.isEmpty()) {
                Intent ViajesEncontradosActivity = new Intent(this, ViajesEncontradosActivity.class);
                ViajesEncontradosActivity.putExtra("origen_busqueda", str_origen);
                ViajesEncontradosActivity.putExtra("destino_busqueda", str_destino);
                ViajesEncontradosActivity.putExtra("fecha_busqueda", str_fecha);
                startActivity(ViajesEncontradosActivity);
            }
            else Toast.makeText(this, "No hay viajes que satisfagan su búsqueda", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Ingrese origen y/o destino", Toast.LENGTH_SHORT).show();
    }

}
