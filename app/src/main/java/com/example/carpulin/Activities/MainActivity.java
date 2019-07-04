package com.example.carpulin.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.Entidades.Pasajero;
import com.example.carpulin.R;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity{

    private SharedPreferences sharedPreferences;
    private EditText username;
    private EditText password;
    private RadioButton conductor;
    private RadioButton pasajero;
    private ImageView logo;
    private boolean autoLogin=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        logo = (ImageView)findViewById(R.id.MainActivity_logo);
        username = (EditText)findViewById(R.id.MainActivity_username);
        password = (EditText)findViewById(R.id.MainActivity_password);
        conductor = (RadioButton)findViewById(R.id.MainActivity_conductor_check);
        pasajero = (RadioButton)findViewById(R.id.MainActivity_pasajero_check);

        RecuperarDatos();
        if(autoLogin)AutoLogin();
    }

    public void Ingresar(View view) {
        Login(false);
    }

    private void AutoLogin(){
        Login(true);
    }

    private void Login(boolean comingback){
        GuardarDatos();
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();

        if (!str_username.isEmpty() && !str_password.isEmpty()) {
            if (conductor.isChecked()) {
                if(DBQueries.LoginConductor(str_username,str_password,this, comingback)){
                    Intent ConductorActivity = new Intent(this, ConductorActivity.class);
                    Conductor conductor = DBQueries.getConductor(str_username, this);
                    ConductorActivity.putExtra("conductor_entidad", conductor);
                    startActivity(ConductorActivity);
                    this.finish();
                }

            } else if (pasajero.isChecked()) {
                if (DBQueries.LoginPasajero(str_username, str_password, this, comingback)) {
                    Intent PasajeroActivity = new Intent(this, PasajeroActivity.class);
                    Pasajero pasajero = DBQueries.getPasajero(str_username, this);
                    PasajeroActivity.putExtra("pasajero_entidad", pasajero);
                    startActivity(PasajeroActivity);
                    this.finish();
                }
            }

            else Toast.makeText(this, "Seleccione una casilla que falte", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Ingrese Usuario y/o Contraseña", Toast.LENGTH_SHORT).show();
    }

    public void Registrar(View view){
        Intent RegistroActivity = new Intent(this, com.example.carpulin.Activities.RegistroActivity.class);
        startActivity(RegistroActivity);
    }

    private void RecuperarDatos(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        conductor.setChecked(sharedPreferences.getBoolean("Conductor", false));
        pasajero.setChecked(sharedPreferences.getBoolean("Pasajero", false));
        username.setText(sharedPreferences.getString("Username", ""));
        password.setText(sharedPreferences.getString("Password", ""));
        autoLogin=sharedPreferences.getBoolean("AutoLogin", false);

    }

    private void GuardarDatos(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Conductor", conductor.isChecked());
        editor.putBoolean("Pasajero", pasajero.isChecked());
        editor.putString("Username", username.getText().toString());
        editor.putString("Password", password.getText().toString());
        editor.putBoolean("AutoLogin", true);
        editor.apply();
    }

}