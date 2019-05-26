package com.example.carpulin.Activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.carpulin.DB.AdminSQLiteOpenHelper;
import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.R;

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

    public void Registrar(View view){
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

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if(!str_nombre.isEmpty() && !str_apellidoPaterno.isEmpty()
                && !str_apellidoMaterno.isEmpty() && !str_username.isEmpty()
                && !str_password1.isEmpty() && !str_password2.isEmpty()
                && !str_correo.isEmpty() && !str_telefono.isEmpty()
                && !str_rut1.isEmpty() && !str_rut2.isEmpty()){
            if((hombre.isChecked() || mujer.isChecked()) && (conductor.isChecked() || pasajero.isChecked())){
                if(str_password1.compareTo(str_password2)==0){
                    ContentValues values = new ContentValues();
                    if(conductor.isChecked()) {
                        values.put("username", str_username);
                        values.put("nombre", str_nombre + " " + str_apellidoPaterno + " " + str_apellidoMaterno);
                        values.put("password", str_password1);
                        values.put("correo", str_correo);
                        values.put("telefono", str_telefono);
                        values.put("rut", str_rut1 + "-" + rut2);
                        if(hombre.isChecked())values.put("sexo", "Masculino");
                        else values.put("sexo", "Femenino");

                        if(!DBQueries.isConductorRegistrado(str_username, this)){
                            db.insert("conductor", null, values);
                            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                            this.finish();
                        }
                        else Toast.makeText(this, "Nombre de usuario no disponible", Toast.LENGTH_SHORT).show();
                        db.close();
                    }
                    else{
                        values.put("username", str_username);
                        values.put("nombre", str_nombre + " " + str_apellidoPaterno + "" + str_apellidoMaterno);
                        values.put("password", str_password1);
                        values.put("correo", str_correo);
                        values.put("telefono", str_telefono);
                        values.put("rut", str_rut1 + "-" + rut2);
                        if(hombre.isChecked())values.put("sexo", "Masculino");
                        else values.put("sexo", "Femenino");

                        if(!DBQueries.isPasajeroRegistrado(str_username, this)){
                            db.insert("pasajero", null, values);
                            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                            this.finish();
                        }
                        else Toast.makeText(this, "Nombre de usuario no disponible", Toast.LENGTH_SHORT).show();
                        db.close();
                    }
                }
                else Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(this, "Seleccione las casillas", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Hay campos sin rellenar", Toast.LENGTH_SHORT).show();

    }
}
