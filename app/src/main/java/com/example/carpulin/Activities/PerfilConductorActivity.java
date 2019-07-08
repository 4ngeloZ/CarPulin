package com.example.carpulin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.R;

public class PerfilConductorActivity extends AppCompatActivity {

    private TextView nombre;
    private TextView run;
    private TextView telefono;
    private TextView mail;
    private TextView usuario;
    private TextView p;
    private Conductor conductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_conductor);
        conductor =(Conductor)getIntent().getSerializableExtra("conductor_entidad");


        nombre = (TextView)findViewById(R.id.textViewNombre);
        run =(TextView)findViewById(R.id.textViewRun);
        telefono =(TextView)findViewById(R.id.textViewtelefono);
        mail =(TextView)findViewById(R.id.textViewMail);
        usuario = (TextView)findViewById(R.id.textViewUsuario);
        p = (TextView)findViewById(R.id.textViewP);

      //  Toast.makeText(this, conductor.getCorreo(), Toast.LENGTH_SHORT).show();


        usuario.setText("Usuario: "+ conductor.getUsername());
        nombre.setText("Nombre: "+ conductor.getNombre());
        run.setText("Run: " + conductor.getRut());
        telefono.setText("Telefono: "+conductor.getTelefono());
        mail.setText("Mail: "+conductor.getCorreo());
        p.setText("Preferencias: "+conductor.getPreferences());


    }

    public void Modificar (View view) {
        Intent ModDatosConductorActivity = new Intent(this, ModDatosConductorActivity.class);
        ModDatosConductorActivity.putExtra("conductor_entidad", conductor);
        startActivity(ModDatosConductorActivity);
    }
    public void VerPerfilVehiculo(View view){
        Intent PerfilVehiculoActivity = new Intent(this, PerfilVehiculoActivity.class);
        PerfilVehiculoActivity.putExtra("conductor_entidad", conductor);
        startActivity(PerfilVehiculoActivity);

    }
    public void ModificarContraseña(View view){
        Intent ModPasswordConductorActivity = new Intent(this, ModPasswordConductorActivity.class);
        ModPasswordConductorActivity.putExtra("conductor_entidad", conductor);
        startActivity(ModPasswordConductorActivity);
    }
    public void AgregarVehiculo (View view){
        Intent AgregarVehiculoActivity= new Intent(this, AgregarVehiculoActivity.class);
        AgregarVehiculoActivity.putExtra("conductor_entidad", conductor);
        startActivity(AgregarVehiculoActivity);
    }

}
