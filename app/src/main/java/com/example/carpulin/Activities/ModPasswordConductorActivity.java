package com.example.carpulin.Activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carpulin.DB.AdminSQLiteOpenHelper;
import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.R;

public class ModPasswordConductorActivity extends AppCompatActivity {
    private EditText CActual, CNueva;
    private Conductor conductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_password_conductor);
        conductor = (Conductor) getIntent().getSerializableExtra("conductor_entidad");
        CActual = (EditText) findViewById(R.id.editTextCA);
        CNueva = (EditText) findViewById(R.id.editTextCN);


    }


    public void Actualizar(View view) {

        String str_CActual = CActual.getText().toString();
        String str_CNueva = CNueva.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (DBQueries.checkPassword(conductor.getUsername(), str_CActual, this)) {
            if (!str_CActual.isEmpty() && !str_CNueva.isEmpty()){
                    conductor.setPassword(str_CActual);
                    ContentValues registro = new ContentValues();
                    registro.put("contraseña", str_CNueva);
                    Toast.makeText(this, "Cambio de contraseña exitoso", Toast.LENGTH_LONG).show();

            }
        }
    }
}