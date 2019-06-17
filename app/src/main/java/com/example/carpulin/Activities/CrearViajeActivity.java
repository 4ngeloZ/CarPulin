package com.example.carpulin.Activities;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.carpulin.DB.AdminSQLiteOpenHelper;
import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.R;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class CrearViajeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String DOS_PUNTOS = ":";

    private String origen;
    private String destino;
    private EditText fechaInicio;
    private EditText fecha1;
    private EditText fecha2;
    private EditText fecha3;
    private EditText fecha4;
    private EditText fechaLlegada;
    private EditText horaInicio;
    private EditText hora1;
    private EditText hora2;
    private EditText hora3;
    private EditText hora4;
    private EditText horaLlegada;
    private AutocompleteSupportFragment autocompleteParada1;
    private AutocompleteSupportFragment autocompleteParada2;
    private AutocompleteSupportFragment autocompleteParada3;
    private AutocompleteSupportFragment autocompleteParada4;
    private LinearLayout lparada1;
    private LinearLayout lparada2;
    private LinearLayout lparada3;
    private LinearLayout lparada4;
    private String parada1;
    private String parada2;
    private String parada3;
    private String parada4;
    private EditText valorTotal;
    private EditText valor1;
    private EditText valor2;
    private EditText valor3;
    private EditText valor4;
    private EditText plazas;
    private Button mas;
    private Button menos;
    private int cantidadparadas=0;
    private Conductor conductor;
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    //Variables para obtener la hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_viaje);

        fechaInicio = (EditText)findViewById(R.id.CrearViajeActivity_fechaInicio);
        fechaInicio.setOnClickListener(this);
        fecha1 = (EditText)findViewById(R.id.CrearViajeActivity_fecha1);
        fecha1.setOnClickListener(this);
        fecha2 = (EditText)findViewById(R.id.CrearViajeActivity_fecha2);
        fecha2.setOnClickListener(this);
        fecha3 = (EditText)findViewById(R.id.CrearViajeActivity_fecha3);
        fecha3.setOnClickListener(this);
        fecha4 = (EditText)findViewById(R.id.CrearViajeActivity_fecha4);
        fecha4.setOnClickListener(this);
        fechaLlegada = (EditText)findViewById(R.id.CrearViajeActivity_fechaLlegada);
        fechaLlegada.setOnClickListener(this);
        horaInicio = (EditText)findViewById(R.id.CrearViajeActivity_horaInicio);
        horaInicio.setOnClickListener(this);
        hora1 = (EditText)findViewById(R.id.CrearViajeActivity_hora1);
        hora1.setOnClickListener(this);
        hora2 = (EditText)findViewById(R.id.CrearViajeActivity_hora2);
        hora2.setOnClickListener(this);
        hora3 = (EditText)findViewById(R.id.CrearViajeActivity_hora3);
        hora3.setOnClickListener(this);
        hora4 = (EditText)findViewById(R.id.CrearViajeActivity_hora4);
        hora4.setOnClickListener(this);
        horaLlegada = (EditText)findViewById(R.id.CrearViajeActivity_horaLlegada);
        horaLlegada.setOnClickListener(this);
        valorTotal = (EditText)findViewById(R.id.CrearViajeActivity_valorTotal);
        valor1 = (EditText)findViewById(R.id.CrearViajeActivity_valor1);
        valor2 = (EditText)findViewById(R.id.CrearViajeActivity_valor2);
        valor3 = (EditText)findViewById(R.id.CrearViajeActivity_valor3);
        valor4 = (EditText)findViewById(R.id.CrearViajeActivity_valor4);
        plazas = (EditText)findViewById(R.id.CrearViajeActivity_plazas);
        mas = (Button)findViewById(R.id.CrearViajeActivity_mas);
        menos = (Button)findViewById(R.id.CrearViajeActivity_menos);
        lparada1 = (LinearLayout) findViewById(R.id.CrearViajeActivity_LayoutParada1);
        lparada2 = (LinearLayout) findViewById(R.id.CrearViajeActivity_LayoutParada2);
        lparada3 = (LinearLayout) findViewById(R.id.CrearViajeActivity_LayoutParada3);
        lparada4 = (LinearLayout) findViewById(R.id.CrearViajeActivity_LayoutParada4);

        lparada1.setVisibility(View.INVISIBLE);
        lparada2.setVisibility(View.INVISIBLE);
        lparada3.setVisibility(View.INVISIBLE);
        lparada4.setVisibility(View.INVISIBLE);
        menos.setVisibility(View.INVISIBLE);

        conductor = (Conductor)getIntent().getSerializableExtra("conductor_entidad");

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
                origen = place.getName();
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
                destino = place.getName();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Destino", "An error occurred: " + status);
            }
        });

        autocompleteParada1 = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.CrearViajeActivity_parada1);

        // Specify the types of place data to return.
        autocompleteParada1.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteParada1.setHint("Parada 1");
        autocompleteParada1.setCountry("CL");

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteParada1.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Parada1", "Place: " + place.getName() + ", " + place.getId());
                parada1 = place.getName();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Parada1", "An error occurred: " + status);
            }
        });
        autocompleteParada2 = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.CrearViajeActivity_parada2);

        // Specify the types of place data to return.
        autocompleteParada2.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteParada2.setHint("Parada 2");
        autocompleteParada2.setCountry("CL");

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteParada2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Parada2", "Place: " + place.getName() + ", " + place.getId());
                parada2 = place.getName();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Parada2", "An error occurred: " + status);
            }
        });
        autocompleteParada3 = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.CrearViajeActivity_parada3);

        // Specify the types of place data to return.
        autocompleteParada3.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteParada3.setHint("Parada 3");
        autocompleteParada3.setCountry("CL");

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteParada3.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Parada3", "Place: " + place.getName() + ", " + place.getId());
                parada3 = place.getName();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Parada3", "An error occurred: " + status);
            }
        });
        autocompleteParada4 = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.CrearViajeActivity_parada4);

        // Specify the types of place data to return.
        autocompleteParada4.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteParada4.setHint("Parada4");
        autocompleteParada4.setCountry("CL");

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteParada4.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Parada4", "Place: " + place.getName() + ", " + place.getId());
                parada4 = place.getName();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Parada4", "An error occurred: " + status);
            }
        });
    }

    public void ModificarParadas(View view){
        if(view==mas){
            if(cantidadparadas==0){
                lparada1.setVisibility(View.VISIBLE);
                cantidadparadas++;
                menos.setVisibility(View.VISIBLE);
            }
            else if(cantidadparadas==1){
                lparada2.setVisibility(View.VISIBLE);
                cantidadparadas++;
            }
            else if(cantidadparadas==2){
                lparada3.setVisibility(View.VISIBLE);
                cantidadparadas++;
            }
            else if(cantidadparadas==3){
                lparada4.setVisibility(View.VISIBLE);
                cantidadparadas++;
                mas.setVisibility(View.INVISIBLE);
            }
        }
        else if(view==menos){
            if(cantidadparadas==1){
                lparada1.setVisibility(View.INVISIBLE);
                cantidadparadas--;
                menos.setVisibility(View.INVISIBLE);
            }
            else if(cantidadparadas==2){
                lparada2.setVisibility(View.INVISIBLE);
                cantidadparadas--;
            }
            else if(cantidadparadas==3){
                lparada3.setVisibility(View.INVISIBLE);
                cantidadparadas--;
            }
            else if(cantidadparadas==4){
                lparada4.setVisibility(View.INVISIBLE);
                cantidadparadas--;
                mas.setVisibility(View.VISIBLE);
            }
        }
    }

    public void Crear(View view){
        String str_origen = origen;
        String str_destino = destino;
        String str_fechaInicio = fechaInicio.getText().toString();
        String str_fecha1 = fecha1.getText().toString();
        String str_fecha2 = fecha2.getText().toString();
        String str_fecha3 = fecha3.getText().toString();
        String str_fecha4 = fecha4.getText().toString();
        String str_fechaLlegada = fechaLlegada.getText().toString();
        String str_horaInicio = horaInicio.getText().toString();
        String str_hora1 = hora1.getText().toString();
        String str_hora2 = hora2.getText().toString();
        String str_hora3 = hora3.getText().toString();
        String str_hora4 = hora4.getText().toString();
        String str_horaLlegada = horaLlegada.getText().toString();
        String str_parada1 = parada1;
        String str_parada2 = parada2;
        String str_parada3 = parada3;
        String str_parada4 = parada4;
        String str_valorTotal = valorTotal.getText().toString();
        String str_valor1 = valor1.getText().toString();
        String str_valor2 = valor2.getText().toString();
        String str_valor3 = valor3.getText().toString();
        String str_valor4 = valor4.getText().toString();
        String str_plazas = plazas.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (!str_origen.isEmpty() && !str_destino.isEmpty() && !str_fechaInicio.isEmpty() && !str_fechaLlegada.isEmpty() && !str_horaInicio.isEmpty() && !str_horaLlegada.isEmpty() && !str_plazas.isEmpty() && !str_valorTotal.isEmpty()){
            boolean fecha_compatible=true;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String date_timei = str_fechaInicio+" "+str_horaInicio;
            String date_time1 = str_fecha1+" "+str_hora1;
            String date_time2 = str_fecha2+" "+str_hora2;
            String date_time3 = str_fecha3+" "+str_hora3;
            String date_time4 = str_fecha4+" "+str_hora4;
            String date_timeL = str_fechaLlegada+" "+str_horaLlegada;
            Date strDatei = null;
            try {
                strDatei = sdf.parse(date_timei);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date strDate1 = null;
            try {
                strDate1 = sdf.parse(date_time1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date strDate2 = null;
            try {
                strDate2 = sdf.parse(date_time2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date strDate3 = null;
            try {
                strDate3 = sdf.parse(date_time3);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date strDate4 = null;
            try {
                strDate4 = sdf.parse(date_time4);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date strDateL = null;
            try {
                strDateL = sdf.parse(date_timeL);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(strDatei!=null){
                if(strDate1!=null){
                    if(strDatei.after(strDate1)) fecha_compatible=false;
                }
                if(strDate2!=null){
                    if(strDatei.after(strDate2)) fecha_compatible=false;
                }
                if(strDate3!=null){
                    if(strDatei.after(strDate3)) fecha_compatible=false;
                }
                if(strDate4!=null){
                    if(strDatei.after(strDate4)) fecha_compatible=false;
                }
                if(strDateL!=null){
                    if(strDatei.after(strDateL)) fecha_compatible=false;
                }
            }
            if(strDate1!=null){
                if(strDate2!=null){
                    if(strDate1.after(strDate2)) fecha_compatible=false;
                }
                if(strDate3!=null){
                    if(strDate1.after(strDate3)) fecha_compatible=false;
                }
                if(strDate4!=null){
                    if(strDate1.after(strDate4)) fecha_compatible=false;
                }
                if(strDateL!=null){
                    if(strDate1.after(strDateL)) fecha_compatible=false;
                }
            }
            if(strDate2!=null){
                if(strDate3!=null){
                    if(strDate2.after(strDate3)) fecha_compatible=false;
                }
                if(strDate4!=null){
                    if(strDate2.after(strDate4)) fecha_compatible=false;
                }
                if(strDateL!=null){
                    if(strDate2.after(strDateL)) fecha_compatible=false;
                }
            }
            if(strDate3!=null){
                if(strDate4!=null){
                    if(strDate3.after(strDate4)) fecha_compatible=false;
                }
                if(strDateL!=null){
                    if(strDate3.after(strDateL)) fecha_compatible=false;
                }
            }
            if(strDate4!=null && strDateL!=null){
                if(strDate4.after(strDateL)) fecha_compatible=false;
            }

            if(new Date().after(strDatei) || strDatei.after(strDateL)) {
                fecha_compatible = false;
            }
            if(fecha_compatible){
                ContentValues values = new ContentValues();
                String randID = Integer.toString((int) (Math.random() * 999999999));
                while (DBQueries.isViajeIdOcupado(randID, this)) {
                    randID = Integer.toString((int) (Math.random() * 999999999));
                }
                values.put("id", randID);
                values.put("origen", str_origen);
                values.put("destino", str_destino);
                values.put("fechainicio", str_fechaInicio);
                values.put("horainicio", str_horaInicio);
                values.put("valortotal", str_valorTotal);
                values.put("fechallegada", str_fechaLlegada);
                values.put("horallegada", str_horaLlegada);
                values.put("parada1", str_parada1);
                values.put("parada2", str_parada2);
                values.put("parada3", str_parada3);
                values.put("parada4", str_parada4);
                values.put("fecha1", str_fecha1);
                values.put("fecha2", str_fecha2);
                values.put("fecha3", str_fecha3);
                values.put("fecha4", str_fecha4);
                values.put("hora1", str_hora1);
                values.put("hora2", str_hora2);
                values.put("hora3", str_hora3);
                values.put("hora4", str_hora4);
                values.put("valor1", str_valor1);
                values.put("valor2", str_valor2);
                values.put("valor3", str_valor3);
                values.put("valor4", str_valor4);
                values.put("plazas1", str_plazas);
                values.put("plazas2", str_plazas);
                values.put("plazas3", str_plazas);
                values.put("plazas4", str_plazas);
                values.put("plazas5", str_plazas);
                values.put("conductor", conductor.getUsername());
                db.insert("viaje", null, values);
                db.close();
                Toast.makeText(this, "Viaje registrado con éxito", Toast.LENGTH_SHORT).show();
                this.finish();
            }
            else Toast.makeText(this, "Fechas incompatibles para el viaje", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Rellene todos los campos principales", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.CrearViajeActivity_fechaInicio:
                obtenerFecha(fechaInicio);
                break;
            case R.id.CrearViajeActivity_fecha1:
                obtenerFecha(fecha1);
                break;
            case R.id.CrearViajeActivity_fecha2:
                obtenerFecha(fecha2);
                break;
            case R.id.CrearViajeActivity_fecha3:
                obtenerFecha(fecha3);
                break;
            case R.id.CrearViajeActivity_fecha4:
                obtenerFecha(fecha4);
                break;
            case R.id.CrearViajeActivity_fechaLlegada:
                obtenerFecha(fechaLlegada);
                break;
            case R.id.CrearViajeActivity_horaInicio:
                obtenerHora(horaInicio);
                break;
            case R.id.CrearViajeActivity_hora1:
                obtenerHora(hora1);
                break;
            case R.id.CrearViajeActivity_hora2:
                obtenerHora(hora2);
                break;
            case R.id.CrearViajeActivity_hora3:
                obtenerHora(hora3);
                break;
            case R.id.CrearViajeActivity_hora4:
                obtenerHora(hora4);
                break;
            case R.id.CrearViajeActivity_horaLlegada:
                obtenerHora(horaLlegada);
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

    private void obtenerHora(final EditText edittext){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                edittext.setText(horaFormateada + DOS_PUNTOS + minutoFormateado);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }
}
