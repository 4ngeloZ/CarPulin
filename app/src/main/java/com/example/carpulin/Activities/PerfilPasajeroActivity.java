package com.example.carpulin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Pasajero;
import com.example.carpulin.R;

public class PerfilPasajeroActivity extends AppCompatActivity {
    private Pasajero pasajero;
    private ImageView foto;

    private TextView username;
    private TextView nombre;
    private TextView rut;
    private TextView sexo;
    private TextView correo;
    private TextView telefono;
    private TextView preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_pasajero);
        pasajero = (Pasajero)getIntent().getSerializableExtra("pasajero_entidad");

        username = (TextView)findViewById(R.id.PerfilPasajeroActivity_username);
        nombre = (TextView)findViewById(R.id.PerfilPasajeroActivity_nombre);
        rut = (TextView)findViewById(R.id.PerfilPasajeroActivity_rut);
        sexo = (TextView)findViewById(R.id.PerfilPasajeroActivity_sexo);
        correo = (TextView)findViewById(R.id.PerfilPasajeroActivity_correo);
        telefono = (TextView)findViewById(R.id.PerfilPasajeroActivity_telefono);
        preferencias = (TextView)findViewById(R.id.PerfilPasajeroActivity_preferencias);

        username.setText("Username: " + pasajero.getUsername());
        nombre.setText("Nombre: " + pasajero.getNombre());
        rut.setText("RUN: " + pasajero.getRut());
        sexo.setText("Sexo: " + pasajero.getSexo());
        correo.setText("Correo: " + pasajero.getCorreo());
        telefono.setText("Telefono: " + pasajero.getTelefono());
        if(pasajero.getPreferencias() != null) preferencias.setText("Preferencias: " + pasajero.getPreferencias());

    }

    public void modificarPerfilPasajero(View view){
        Intent ModificarPerfilPasajeroActivity = new Intent(this, ModificarPerfilPasajeroActivity.class);
        ModificarPerfilPasajeroActivity.putExtra("pasajero_entidad", pasajero);
        startActivity(ModificarPerfilPasajeroActivity);
    }

    public void modificarContrPasajero(View view){
        Intent ModificarPerfilPasajeroActivity = new Intent(this, ModificarPerfilPasajeroActivity.class);
        ModificarPerfilPasajeroActivity.putExtra("pasajero_entidad", pasajero);
        startActivity(ModificarPerfilPasajeroActivity);
    }
}
