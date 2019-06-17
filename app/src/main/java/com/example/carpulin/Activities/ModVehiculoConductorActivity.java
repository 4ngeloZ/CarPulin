package com.example.carpulin.Activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carpulin.DB.AdminSQLiteOpenHelper;
import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.Entidades.Vehiculo;
import com.example.carpulin.R;

public class ModVehiculoConductorActivity extends AppCompatActivity {
    private EditText marca, modelo, patente, asientos, año;
    private Button modificar;
    private Conductor conductor;
    private Vehiculo vehiculo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_vehiculo_conductor);
        conductor=(Conductor) getIntent().getSerializableExtra("conductor_entidad");
        vehiculo = DBQueries.getVehiculo(conductor.getUsername(),this);
        marca = (EditText)findViewById(R.id.editTextMarca);
        modelo = (EditText)findViewById(R.id.editTextMarca);
        patente = (EditText)findViewById(R.id.editTextMarca);
        asientos= (EditText)findViewById(R.id.editTextAsientos);
        año = (EditText)findViewById(R.id.editTextAño);

        marca.setText(vehiculo.getMarca());
        modelo.setText(vehiculo.getModelo());
        patente.setText(vehiculo.getPatente());
        asientos.setText(Integer.toString(vehiculo.getAsientos()));
        año.setText(Integer.toString(vehiculo.getAño()));

    }


    public void Actualizar (View view) {

        String str_marca = marca.getText().toString();
        String str_modelo = modelo.getText().toString();
        String str_patente = patente.getText().toString();
        String str_asientos = asientos.getText().toString();
        String str_año = año.getText().toString();




            if(!str_marca.isEmpty() && !str_modelo.isEmpty()
                    && !str_patente.isEmpty() && !str_asientos.isEmpty()
                    && !str_año.isEmpty() ){

           DBQueries.ModVehiculo(conductor.getUsername(), str_marca, str_patente,str_modelo, str_asientos,str_año, this);

                Toast.makeText(this, "Modificacion exitosa", Toast.LENGTH_SHORT).show();
            super.finish();
        }
        else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }



    }

