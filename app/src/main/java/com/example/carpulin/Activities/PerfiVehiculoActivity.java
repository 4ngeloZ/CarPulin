package com.example.carpulin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.carpulin.R;

public class PerfiVehiculoActivity extends AppCompatActivity {
    private EditText et1, et2, et3, et4, et5;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfi_vehiculo);

        et1 = (EditText)findViewById(R.id.editTextModelo);
        et2 = (EditText)findViewById(R.id.editTextMarca);
        et3 = (EditText)findViewById(R.id.editTextPatente);
        et4 = (EditText)findViewById(R.id.editTextAsientos);
        et5 = (EditText)findViewById(R.id.editTextAño);


    }
}
