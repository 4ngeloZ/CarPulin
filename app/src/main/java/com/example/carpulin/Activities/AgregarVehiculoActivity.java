package com.example.carpulin.Activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpulin.DB.AdminSQLiteOpenHelper;
import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.R;

public class AgregarVehiculoActivity extends AppCompatActivity {

    private EditText marca;
    private EditText modelo;
    private EditText patente;
    private EditText asientos;
    private EditText año;
    private TextView usuario;
    private Conductor conductor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_vehiculo);

        conductor =(Conductor) getIntent().getSerializableExtra("conductor_entidad");


        marca = (EditText) findViewById(R.id.editTextMarca);
        modelo = (EditText) findViewById(R.id.editTextModelo);
        patente = (EditText) findViewById(R.id.editTextPatente);
        asientos = (EditText) findViewById(R.id.editTextAsiento);
        año = (EditText) findViewById(R.id.editTextAño);
        usuario = (TextView) findViewById(R.id.textViewUsername);

    }
    public void AgregarVehiculo(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String str_marca = marca.getText().toString();
        String str_modelo = modelo.getText().toString();
        String str_patente = patente.getText().toString();
        int asiento = Integer.parseInt(asientos.getText().toString());
        int año1 = Integer.parseInt(año.getText().toString());


        if(!str_marca.isEmpty() && !str_modelo.isEmpty() && !str_patente.isEmpty()
                && !Integer.toString(asiento).isEmpty() && !Integer.toString(año1).isEmpty() ){
            DBQueries.insertVehiculo(conductor.getUsername(),
                    str_patente, str_marca, str_modelo, año1, asiento,this);

            Toast.makeText(this, "Modificacion exitosa", Toast.LENGTH_SHORT).show();
            super.finish();
        }
        else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }



    }

}
