package com.example.carpulin.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpulin.DB.AdminSQLiteOpenHelper;
import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.R;

public class ModDatosConductorActivity extends AppCompatActivity {
    private EditText nombre,run,telefono,mail, PresAd;
    private RadioButton rb1,rb2;
    private Button b1;
    private TextView usuario;
    private Conductor conductor;
    private String contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_datos_conductor);
        conductor =(Conductor)getIntent().getSerializableExtra("conductor_entidad");
        nombre = (EditText)findViewById(R.id.editTextNombre);
        run =(EditText)findViewById(R.id.editTextRUN);
        telefono =(EditText)findViewById(R.id.telefono);
        mail =(EditText)findViewById(R.id.editTextMail);
        PresAd =(EditText)findViewById(R.id.editTextPresAd);
        usuario = (TextView)findViewById(R.id.textViewUsuario);
       contraseña = conductor.getPassword();

       nombre.setText(conductor.getNombre());
       run.setText(conductor.getRut());
       telefono.setText(conductor.getTelefono());
       mail.setText(conductor.getCorreo());
       usuario.setText(conductor.getUsername());
       PresAd.setText(conductor.getPreferences());


    }

    public void Actualizar (View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String str_nombre = nombre.getText().toString();
        String str_run = run.getText().toString();
        String str_telefono = telefono.getText().toString();
        String str_mail = mail.getText().toString();
        String str_preferences = PresAd.getText().toString();


            if((!str_nombre.isEmpty() && !str_run.isEmpty() && !str_telefono.isEmpty() && !str_mail.isEmpty()  && !str_preferences.isEmpty() )){
                DBQueries.ModConductor(str_nombre, str_run, str_telefono, str_mail, conductor.getUsername(), str_preferences, this);

                conductor.setNombre(str_nombre);
                conductor.setRut(str_run);
                conductor.setTelefono(str_telefono);
                conductor.setCorreo(str_mail);
                conductor.setPreferences(str_preferences);

                Toast.makeText(this, "Modificacion exitosa", Toast.LENGTH_SHORT).show();
                Intent ConductorActivity = new Intent(this, ConductorActivity.class);
                ConductorActivity.putExtra("conductor_entidad",conductor);
                startActivity(ConductorActivity);
            }
                else {
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        }

}
