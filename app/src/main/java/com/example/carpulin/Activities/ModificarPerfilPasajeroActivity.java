package com.example.carpulin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carpulin.DB.AdminSQLiteOpenHelper;
import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Pasajero;
import com.example.carpulin.R;

public class ModificarPerfilPasajeroActivity extends AppCompatActivity {

    private Pasajero pasajero;
    private EditText correo;
    private EditText telefono;
    private EditText preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_perfil_pasajero);
        pasajero = (Pasajero)getIntent().getSerializableExtra("pasajero_entidad");

        correo = (EditText)findViewById(R.id.ModificarPerfilPasajeroActivity_correo);
        telefono = (EditText)findViewById(R.id.ModificarPerfilPasajeroActivity_telefono);
        preferencias = (EditText)findViewById(R.id.ModificarPerfilPasajeroActivity_preferencias);

        correo.setText(pasajero.getCorreo());
        telefono.setText(pasajero.getTelefono());
        if(pasajero.getPreferencias() != null) preferencias.setText(pasajero.getPreferencias());

    }

    public void actualizarPasajero(View view){
        if(pasajero.getCorreo() != correo.getText().toString()) {
            DBQueries.actualizarCorreoPasajero(correo.getText().toString(), pasajero.getUsername(), this);
            pasajero.setCorreo(correo.getText().toString());
        }
        if(pasajero.getTelefono() != telefono.getText().toString()) {
            DBQueries.actualizarTelefonoPasajero(telefono.getText().toString(), pasajero.getUsername(), this);
            pasajero.setTelefono(telefono.getText().toString());
        }
        if(pasajero.getCorreo() != preferencias.getText().toString()) {
            DBQueries.actualizarPreferenciasPasajero(preferencias.getText().toString(), pasajero.getUsername(), this);
            pasajero.setPreferencias(preferencias.getText().toString());
        }
        Toast.makeText(this, "Modificación realizada con exito", Toast.LENGTH_SHORT).show();
        Intent PasajeroActivity = new Intent(this, PasajeroActivity.class);
        PasajeroActivity.putExtra("pasajero_entidad", pasajero);
        startActivity(PasajeroActivity);
    }
}
