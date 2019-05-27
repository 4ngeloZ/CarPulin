package com.example.carpulin.Activities;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carpulin.DB.AdminSQLiteOpenHelper;
import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.R;


public class CrearViajeActivity extends AppCompatActivity {

    private EditText origen;
    private EditText destino;
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
    private EditText parada1;
    private EditText parada2;
    private EditText parada3;
    private EditText parada4;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Places.initialize(getApplicationContext(), apiKey);
        // Create a new Places client instance.
        //PlacesClient placesClient = Places.createClient(this);
        setContentView(R.layout.activity_crear_viaje);
        // Initialize the AutocompleteSupportFragment.

        origen = (EditText)findViewById(R.id.CrearViajeActivity_origen);
        destino = (EditText)findViewById(R.id.CrearViajeActivity_destino);
        fechaInicio = (EditText)findViewById(R.id.CrearViajeActivity_fechaInicio);
        fecha1 = (EditText)findViewById(R.id.CrearViajeActivity_fecha1);
        fecha2 = (EditText)findViewById(R.id.CrearViajeActivity_fecha2);
        fecha3 = (EditText)findViewById(R.id.CrearViajeActivity_fecha3);
        fecha4 = (EditText)findViewById(R.id.CrearViajeActivity_fecha4);
        fechaLlegada = (EditText)findViewById(R.id.CrearViajeActivity_fechaLlegada);
        horaInicio = (EditText)findViewById(R.id.CrearViajeActivity_horaInicio);
        hora1 = (EditText)findViewById(R.id.CrearViajeActivity_hora1);
        hora2 = (EditText)findViewById(R.id.CrearViajeActivity_hora2);
        hora3 = (EditText)findViewById(R.id.CrearViajeActivity_hora3);
        hora4 = (EditText)findViewById(R.id.CrearViajeActivity_hora4);
        horaLlegada = (EditText)findViewById(R.id.CrearViajeActivity_horaLlegada);
        parada1 = (EditText)findViewById(R.id.CrearViajeActivity_parada1);
        parada2 = (EditText)findViewById(R.id.CrearViajeActivity_parada2);
        parada3 = (EditText)findViewById(R.id.CrearViajeActivity_parada3);
        parada4 = (EditText)findViewById(R.id.CrearViajeActivity_parada4);
        valorTotal = (EditText)findViewById(R.id.CrearViajeActivity_valorTotal);
        valor1 = (EditText)findViewById(R.id.CrearViajeActivity_valor1);
        valor2 = (EditText)findViewById(R.id.CrearViajeActivity_valor2);
        valor3 = (EditText)findViewById(R.id.CrearViajeActivity_valor3);
        valor4 = (EditText)findViewById(R.id.CrearViajeActivity_valor4);
        plazas = (EditText)findViewById(R.id.CrearViajeActivity_plazas);
        mas = (Button)findViewById(R.id.CrearViajeActivity_mas);
        menos = (Button)findViewById(R.id.CrearViajeActivity_menos);

        fecha1.setVisibility(View.INVISIBLE);
        fecha2.setVisibility(View.INVISIBLE);
        fecha3.setVisibility(View.INVISIBLE);
        fecha4.setVisibility(View.INVISIBLE);
        hora1.setVisibility(View.INVISIBLE);
        hora2.setVisibility(View.INVISIBLE);
        hora3.setVisibility(View.INVISIBLE);
        hora4.setVisibility(View.INVISIBLE);
        parada1.setVisibility(View.INVISIBLE);
        parada2.setVisibility(View.INVISIBLE);
        parada3.setVisibility(View.INVISIBLE);
        parada4.setVisibility(View.INVISIBLE);
        valor1.setVisibility(View.INVISIBLE);
        valor2.setVisibility(View.INVISIBLE);
        valor3.setVisibility(View.INVISIBLE);
        valor4.setVisibility(View.INVISIBLE);
        menos.setVisibility(View.INVISIBLE);

        conductor = (Conductor)getIntent().getSerializableExtra("conductor_entidad");


    }

    public void ModificarParadas(View view){
        if(view==mas){
            if(cantidadparadas==0){
                parada1.setVisibility(View.VISIBLE);
                fecha1.setVisibility(View.VISIBLE);
                hora1.setVisibility(View.VISIBLE);
                valor1.setVisibility(View.VISIBLE);
                cantidadparadas++;
                menos.setVisibility(View.VISIBLE);
            }
            else if(cantidadparadas==1){
                parada2.setVisibility(View.VISIBLE);
                fecha2.setVisibility(View.VISIBLE);
                hora2.setVisibility(View.VISIBLE);
                valor2.setVisibility(View.VISIBLE);
                cantidadparadas++;
            }
            else if(cantidadparadas==2){
                parada3.setVisibility(View.VISIBLE);
                fecha3.setVisibility(View.VISIBLE);
                hora3.setVisibility(View.VISIBLE);
                valor3.setVisibility(View.VISIBLE);
                cantidadparadas++;
            }
            else if(cantidadparadas==3){
                parada4.setVisibility(View.VISIBLE);
                fecha4.setVisibility(View.VISIBLE);
                hora4.setVisibility(View.VISIBLE);
                valor4.setVisibility(View.VISIBLE);
                cantidadparadas++;
                mas.setVisibility(View.INVISIBLE);
            }
        }
        else if(view==menos){
            if(cantidadparadas==1){
                parada1.setVisibility(View.INVISIBLE);
                fecha1.setVisibility(View.INVISIBLE);
                hora1.setVisibility(View.INVISIBLE);
                valor1.setVisibility(View.INVISIBLE);
                cantidadparadas--;
                menos.setVisibility(View.INVISIBLE);
            }
            else if(cantidadparadas==2){
                parada2.setVisibility(View.INVISIBLE);
                fecha2.setVisibility(View.INVISIBLE);
                hora2.setVisibility(View.INVISIBLE);
                valor2.setVisibility(View.INVISIBLE);
                cantidadparadas--;
            }
            else if(cantidadparadas==3){
                parada3.setVisibility(View.INVISIBLE);
                fecha3.setVisibility(View.INVISIBLE);
                hora3.setVisibility(View.INVISIBLE);
                valor3.setVisibility(View.INVISIBLE);
                cantidadparadas--;
            }
            else if(cantidadparadas==4){
                parada4.setVisibility(View.INVISIBLE);
                fecha4.setVisibility(View.INVISIBLE);
                hora4.setVisibility(View.INVISIBLE);
                valor4.setVisibility(View.INVISIBLE);
                cantidadparadas--;
                mas.setVisibility(View.VISIBLE);
            }
        }
    }

    public void Crear(View view){
        String str_origen = origen.getText().toString();
        String str_destino = destino.getText().toString();
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
        String str_parada1 = parada1.getText().toString();
        String str_parada2 = parada2.getText().toString();
        String str_parada3 = parada3.getText().toString();
        String str_parada4 = parada4.getText().toString();
        String str_valorTotal = valorTotal.getText().toString();
        String str_valor1 = valor1.getText().toString();
        String str_valor2 = valor2.getText().toString();
        String str_valor3 = valor3.getText().toString();
        String str_valor4 = valor4.getText().toString();
        String str_plazas = plazas.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (!str_origen.isEmpty() && !str_destino.isEmpty() && !str_fechaInicio.isEmpty() && !str_fechaLlegada.isEmpty() && !str_horaInicio.isEmpty() && !str_horaLlegada.isEmpty() && !str_plazas.isEmpty() && !str_valorTotal.isEmpty()){
            ContentValues values = new ContentValues();
            String randID = Integer.toString((int)(Math.random()*999999999));
            while(DBQueries.isViajeIdOcupado(randID, this)){
                randID = Integer.toString((int)(Math.random()*999999999));
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
        else Toast.makeText(this, "Rellene todos los campos principales", Toast.LENGTH_SHORT).show();
    }
}
