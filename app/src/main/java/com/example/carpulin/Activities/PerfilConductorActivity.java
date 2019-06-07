package com.example.carpulin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.carpulin.R;

public class PerfilConductorActivity extends AppCompatActivity {

    private EditText nombre,apellido,run,telefono,mail;
    private RadioButton rb1,rb2,rb3;
    private Button b1, b2;
    private TextView usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_conductor);

        nombre = (EditText)findViewById(R.id.editTextNombre);
        apellido =(EditText)findViewById(R.id.editTextContraseñaA);
        run =(EditText)findViewById(R.id.editTextRUN);
        telefono =(EditText)findViewById(R.id.Telefono);
        mail =(EditText)findViewById(R.id.editTextMail);
        usuario = (TextView)findViewById(R.id.textViewUsuario);

    }

    public void Actualizar (View view) {
        String str_nombre = nombre.getText().toString();

    }
}
