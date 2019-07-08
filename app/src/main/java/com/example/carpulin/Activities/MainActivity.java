package com.example.carpulin.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.carpulin.API.CarpulinService;
import com.example.carpulin.Entidades.*;
import com.example.carpulin.R;
import com.example.carpulin.model.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private SharedPreferences sharedPreferences;
    private EditText username;
    private EditText password;
    private RadioButton conductor;
    private RadioButton pasajero;
    private ImageView logo;
    private boolean autoLogin=false;
    private Conductor conduc;
    private Pasajero pasaj;

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
                UserData data = new UserData();
                data.setUsername(str_username);
                data.setPassword(str_password);
                data.setEsConductor(true);
                Log.i("Conductor checked","Conductor checkeado");
                getCond(str_username);
                sendLogin(data);
            } else if (pasajero.isChecked()) {
                UserData data = new UserData();
                data.setUsername(str_username);
                data.setPassword(str_password);
                data.setEsConductor(false);
                Log.i("Pasajero checked","Pasajero checkeado");
                getPas(str_username);
                sendLogin(data);
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
        conductor.setChecked(sharedPreferences.getBoolean("ConductorResponse", false));
        pasajero.setChecked(sharedPreferences.getBoolean("PasajeroResponse", false));
        username.setText(sharedPreferences.getString("Username", ""));
        password.setText(sharedPreferences.getString("Password", ""));
        autoLogin=sharedPreferences.getBoolean("AutoLogin", false);

    }

    private void GuardarDatos(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("ConductorResponse", conductor.isChecked());
        editor.putBoolean("PasajeroResponse", pasajero.isChecked());
        editor.putString("Username", username.getText().toString());
        editor.putString("Password", password.getText().toString());
        editor.putBoolean("AutoLogin", true);
        editor.apply();
    }

    private void sendLogin(final UserData data){
        Call<LoginResponse> call = CarpulinService.getService(this).login(data);
        final Context ctx = this;
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.code() == 200 && response.body().getValido()){
                    if(data.esConductor()) {
                        Intent intent = new Intent(ctx, ConductorActivity.class);
                        intent.putExtra("conductor_entidad",conduc);
                        ctx.startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(ctx, PasajeroActivity.class);
                        intent.putExtra("pasajero_entidad",pasaj);
                        ctx.startActivity(intent);
                    }
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "Sus credenciales son incorrectas o aún no se ha registrado :(";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Log.d("LOGIN", "FALLO EL LOGIN " + response.body().getValido());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Hubo un error en la autenticación, probablemente no" +
                        " esté conectado a internet";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Log.d("LOGIN", "CRASHEO EL LOGIN");
            }
        });
    }

    private void getCond(String username){
        Call<ConductorResponse> call = CarpulinService.getService(this).getConductor(username);
        call.enqueue(new Callback<ConductorResponse>() {
            @Override
            public void onResponse(Call<ConductorResponse> call, Response<ConductorResponse> response) {
                conduc = new Conductor(response.body().getUsername(),
                        response.body().getNombre() + " " + response.body().getApellido(),
                        "password",
                        response.body().getMail(),
                        response.body().getTelefono(),
                        response.body().getRut(),
                        "HM",
                        response.body().getDescripcion());
                Log.i("Conductor", "getCond exitoso");
            }

            @Override
            public void onFailure(Call<ConductorResponse> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Hubo un error en la autenticación, probablemente no" +
                        " esté conectado a internet";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Log.d("LOGIN", "CRASHEO EL CONDUCTOR");
            }
        });
    }

    private void getPas(String username){
        Call<PasajeroResponse> call = CarpulinService.getService(this).getPasajero(username);
        call.enqueue(new Callback<PasajeroResponse>() {
            @Override
            public void onResponse(Call<PasajeroResponse> call, Response<PasajeroResponse> response) {
                //Log.i("Pasajero", "getPass exitoso" + response.body().getUsername());
                if(response.code() == 200 && response.body() != null) {
                    pasaj = new Pasajero(response.body().getUsername(),
                            response.body().getNombre() + " " + response.body().getApellido(),
                            "password",
                            response.body().getMail(),
                            response.body().getTelefono(),
                            response.body().getRut(),
                            "HM",
                            response.body().getDescripcion());
                    Log.i("Pasajero", "getPass exitoso");
                }else if(response.code() != 200){
                    Log.i("Salio mal", "code no es 200");
                }else{
                    Log.i("Salio mal", "reponse null");
                }
            }

            @Override
            public void onFailure(Call<PasajeroResponse> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Hubo un error en la autenticación, probablemente no" +
                        " esté conectado a internet";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Log.d("LOGIN", "CRASHEO EL CONDUCTOR");
            }
        });
    }

}
