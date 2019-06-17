package com.example.carpulin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Pasajero;
import com.example.carpulin.R;
import com.example.carpulin.ViajeModelo;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;



import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

public class BuscarViajeActivity extends AppCompatActivity {
    private EditText fecha;
    private EditText plazas;
    private Pasajero pasajero;
    private String Or;
    private String Dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_viaje);

        fecha = (EditText)findViewById(R.id.BuscarViajeActivity_fecha);
        plazas = (EditText)findViewById(R.id.BuscarViajeActivity_plazas);

        pasajero = (Pasajero)getIntent().getSerializableExtra("pasajero_entidad");

        Places.initialize(getApplicationContext(), "AIzaSyA7MSYdDD3aQarHYYYamIaKnSiyZ4W2aoU");
        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);
         //Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteOrigen = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.frOrigen);

        // Specify the types of place data to return.
        autocompleteOrigen.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteOrigen.setHint("Origen");
        autocompleteOrigen.setCountry("CL");

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteOrigen.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Origen", "Place: " + place.getName() + ", " + place.getId());
                Or = place.getName();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Origen", "An error occurred: " + status);
            }
        });
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteDestino = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.frDest);

        // Specify the types of place data to return.
        autocompleteDestino.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteDestino.setHint("Destino");
        autocompleteDestino.setCountry("CL");
        // Set up a PlaceSelectionListener to handle the response.
        autocompleteDestino.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Destino", "Place: " + place.getName() + ", " + place.getId());
                Dest = place.getName();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Destino", "An error occurred: " + status);
            }
        });
    }

    public void Buscar(View view){

        String str_origen = Or;
        String str_destino = Dest;
        String str_fecha = fecha.getText().toString();

        List<ViajeModelo> viajes = DBQueries.getViajes(str_origen, str_destino, str_fecha, this);

        if(!str_origen.isEmpty() && !str_destino.isEmpty()) {
            if (!viajes.isEmpty()) {
                Intent ViajesEncontradosActivity = new Intent(this, ViajesEncontradosActivity.class);
                ViajesEncontradosActivity.putExtra("origen_busqueda", str_origen);
                ViajesEncontradosActivity.putExtra("destino_busqueda", str_destino);
                ViajesEncontradosActivity.putExtra("fecha_busqueda", str_fecha);
                ViajesEncontradosActivity.putExtra("pasajero_entidad", pasajero);
                startActivity(ViajesEncontradosActivity);
            }
            else Toast.makeText(this, "No hay viajes que satisfagan su búsqueda", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Ingrese origen y/o destino", Toast.LENGTH_SHORT).show();
    }

}
