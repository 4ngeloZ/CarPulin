package com.example.carpulin.Activities;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.Entidades.Vehiculo;
import com.example.carpulin.R;

public class PerfilVehiculoActivity extends AppCompatActivity {
    private TextView marca, modelo, patente, asientos, year;
    private Vehiculo vehiculo;
    private Conductor conductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_vehiculo);
        conductor=(Conductor) getIntent().getSerializableExtra("conductor_entidad");
        vehiculo = DBQueries.getVehiculo(conductor.getUsername(),this);

        marca = (TextView)findViewById(R.id.textViewModelo);
        modelo = (TextView)findViewById(R.id.textViewMarca);
        patente= (TextView)findViewById(R.id.textViewPatente);
        asientos= (TextView)findViewById(R.id.textViewAsientos);
        year = (TextView)findViewById(R.id.textViewAño);

        marca.setText("Toyota");
        modelo.setText("Yaris");
        patente.setText("HK LM 56");
        asientos.setText("4");
        year.setText("2017");

        marca.setText(vehiculo.getMarca());
        modelo.setText(vehiculo.getModelo());
        patente.setText(vehiculo.getPatente());
        asientos.setText(Integer.toString(vehiculo.getAsientos()));
        year.setText(Integer.toString(vehiculo.getAño()));


    }

    public void ModificarVehiculo (View view){
        Intent PerfilVehiculoActivity= new Intent(this, ModVehiculoConductorActivity.class);
        PerfilVehiculoActivity.putExtra("conductor_entidad", conductor);
        startActivity(PerfilVehiculoActivity);

    }
}
