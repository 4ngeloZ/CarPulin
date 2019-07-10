package com.example.carpulin.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.carpulin.API.CarpulinService;
import com.example.carpulin.DB.AdminSQLiteOpenHelper;
import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.R;
import com.example.carpulin.model.LoginResponse;
import com.example.carpulin.model.RegisterData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText apellidoPaterno;
    private EditText apellidoMaterno;
    private EditText username;
    private EditText password1;
    private EditText password2;
    private EditText correo;
    private EditText telefono;
    private EditText rut1;
    private EditText rut2;
    private RadioButton conductor;
    private RadioButton pasajero;
    private RadioButton hombre;
    private RadioButton mujer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = (EditText)findViewById(R.id.RegistroActivity_Nombre);
        apellidoPaterno = (EditText)findViewById(R.id.RegistroActivity_ApellidoPaterno);
        apellidoMaterno = (EditText)findViewById(R.id.RegistroActivity_ApellidoMaterno);
        username = (EditText)findViewById(R.id.RegistroActivity_Usuario);
        password1 = (EditText)findViewById(R.id.RegistroActivity_Contraseña1);
        password2 = (EditText)findViewById(R.id.RegistroActivity_Contrasena2);
        correo = (EditText)findViewById(R.id.RegistroActivity_Email);
        telefono = (EditText)findViewById(R.id.RegistroActivity_Telefono);
        rut1 = (EditText)findViewById(R.id.RegistroActivity_Rut1);
        rut2 = (EditText)findViewById(R.id.RegistroActivity_Rut2);
        conductor = (RadioButton)findViewById(R.id.RegistroActivity_Conductor);
        pasajero = (RadioButton)findViewById(R.id.RegistroActivity_Pasajero);
        hombre = (RadioButton)findViewById(R.id.RegistroActivity_SexoM);
        mujer = (RadioButton)findViewById(R.id.RegistroActivity_SexoF);

    }

    public void Registrar(View view) throws InterruptedException {
        String str_nombre = nombre.getText().toString();
        String str_apellidoPaterno = apellidoPaterno.getText().toString();
        String str_apellidoMaterno = apellidoMaterno.getText().toString();
        String str_username = username.getText().toString();
        String str_password1 = password1.getText().toString();
        String str_password2 = password2.getText().toString();
        String str_correo = correo.getText().toString();
        String str_telefono = telefono.getText().toString();
        String str_rut1 = rut1.getText().toString();
        String str_rut2 = rut2.getText().toString();

        if(!str_nombre.isEmpty() && !str_apellidoPaterno.isEmpty()
                && !str_apellidoMaterno.isEmpty() && !str_username.isEmpty()
                && !str_password1.isEmpty() && !str_password2.isEmpty()
                && !str_correo.isEmpty() && !str_telefono.isEmpty()
                && !str_rut1.isEmpty() && !str_rut2.isEmpty()){
            if((hombre.isChecked() || mujer.isChecked()) && (conductor.isChecked() || pasajero.isChecked())){
                if(str_password1.compareTo(str_password2)==0){
                    RegisterData values = new RegisterData();
                    values.setUsername(str_username);
                    values.setNombre(str_nombre);
                    values.setApellido(str_apellidoPaterno + " " + str_apellidoMaterno);
                    values.setPassword(str_password1);
                    values.setMail(str_correo);
                    values.setTelefono(str_telefono);
                    values.setRut(str_rut1 + "-" + str_rut2);
                    if(hombre.isChecked())values.setSexo("Hombre");
                    else values.setSexo("Mujer");
                    if (conductor.isChecked()) values.setConductor(true);
                    else values.setConductor(false);
                    sendRegistro(values);
                    this.finish();
                }
                else Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            }
            else Toast.makeText(this, "Seleccione las casillas", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "Hay campos sin rellenar", Toast.LENGTH_LONG).show();

    }

    private void sendRegistro(final RegisterData data){
        Call<LoginResponse> call = CarpulinService.getService(this).registrar(data);
        final Context ctx = this;
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.code() == 200 && response.body().getValido()){
                    CharSequence text = "Registro exitoso";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(ctx, text, duration);
                    toast.show();
                    Log.d("REGISTRO", "REGISTRO EXISTOSO" + response.body().getValido());

                }
                else{
                    CharSequence text = "Fallo el registro, usuario ya registrado";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(ctx, text, duration);
                    toast.show();
                    Log.d("REGISTRO", "FALLO REGISTRO ");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Hubo un error en el registro, probablemente no tenga internet";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Log.d("REGISTRO", "REGISTRO CRASHED");
            }
        });
    }
}
