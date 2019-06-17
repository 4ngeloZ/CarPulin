package com.example.carpulin.Activities;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.carpulin.DB.AdminSQLiteOpenHelper;
import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CrearViajeActivity extends AppCompatActivity {

    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String DOS_PUNTOS = ":";

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
