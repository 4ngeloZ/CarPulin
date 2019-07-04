package com.example.carpulin.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BuscarViajeActivity extends AppCompatActivity{
    private static final String CERO = "0";
    private static final String BARRA = "/";

    private EditText fecha;
    private EditText plazas;
    private TextInputLayout til;
    private Pasajero pasajero;
    private String Or;
    private String Dest;

    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_viaje);

        fecha = (EditText)findViewById(R.id.BuscarViajeActivity_fecha);
        plazas = (EditText)findViewById(R.id.BuscarViajeActivity_plazas);
        til = (TextInputLayout) findViewById(R.id.BuscarViajeActivity_fechainput);

        pasajero = (Pasajero)getIntent().getSerializableExtra("pasajero_entidad");

        Places.initialize(getApplicationContext(), "AIzaSyA7MSYdDD3aQarHYYYamIaKnSiyZ4W2aoU");
        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);
        // Initialize the AutocompleteSupportFragment.
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
        String str_plazas = plazas.getText().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date strDate = null;
        try {
            strDate = sdf.parse(str_fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(!str_origen.isEmpty() && !str_destino.isEmpty() && !str_plazas.isEmpty()) {
            Date currentDate = null;
            try {
                currentDate = sdf.parse(sdf.format(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(strDate != null && !strDate.before(currentDate)) {
                List<ViajeModelo> viajes = DBQueries.getViajes(str_origen, str_destino, str_fecha, str_plazas, this);
                if (!viajes.isEmpty()) {
                    Intent ViajesEncontradosActivity = new Intent(this, ViajesEncontradosActivity.class);
                    ViajesEncontradosActivity.putExtra("origen_busqueda", str_origen);
                    ViajesEncontradosActivity.putExtra("destino_busqueda", str_destino);
                    ViajesEncontradosActivity.putExtra("fecha_busqueda", str_fecha);
                    ViajesEncontradosActivity.putExtra("numero_plazas", str_plazas);
                    ViajesEncontradosActivity.putExtra("pasajero_entidad", pasajero);
                    startActivity(ViajesEncontradosActivity);
                }
                else {
                    til.setError(null);
                }
            }
            else if(strDate == null){
                List<ViajeModelo> viajes = DBQueries.getViajes(str_origen, str_destino, str_fecha, str_plazas, this);
                if (!viajes.isEmpty()) {
                    Intent ViajesEncontradosActivity = new Intent(this, ViajesEncontradosActivity.class);
                    ViajesEncontradosActivity.putExtra("origen_busqueda", str_origen);
                    ViajesEncontradosActivity.putExtra("destino_busqueda", str_destino);
                    ViajesEncontradosActivity.putExtra("fecha_busqueda", str_fecha);
                    ViajesEncontradosActivity.putExtra("numero_plazas", str_plazas);
                    ViajesEncontradosActivity.putExtra("pasajero_entidad", pasajero);
                    startActivity(ViajesEncontradosActivity);
                }
                else {
                    til.setError(null);
                    Toast.makeText(this, "No hay viajes que satisfagan su búsqueda", Toast.LENGTH_SHORT).show();
                }
            }
            else
                til.setError("Ingrese una fecha válida");
                //Toast.makeText(this, "Fecha ingresada no es válida", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Ingrese origen, destino y número de plazas", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BuscarViajeActivity_fecha:
                obtenerFecha(fecha);
                break;
        }
    }

    private void obtenerFecha(final EditText edittext){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                edittext.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }
}
