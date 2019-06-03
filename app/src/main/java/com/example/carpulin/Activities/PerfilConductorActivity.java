package com.example.carpulin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.carpulin.R;

public class PerfilConductorActivity extends AppCompatActivity {

    private EditText et1,et2,et3,et4,et5;
    private RadioButton rb1,rb2,rb3;
    private Button b1, b2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_conductor);

        et1 = (EditText)findViewById(R.id.editTextNombre);
        et2 =(EditText)findViewById(R.id.editTextApellido);
        et3 =(EditText)findViewById(R.id.editTextRUN);
        et4 =(EditText)findViewById(R.id.Telefono);
        et5 =(EditText)findViewById(R.id.editTextMail);

    }
}
