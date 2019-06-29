package com.example.carpulin.Activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpulin.DB.AdminSQLiteOpenHelper;
import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Pasajero;
import com.example.carpulin.Entidades.Viaje;
import com.example.carpulin.R;

public class InformacionViajeActivity extends AppCompatActivity {

    private String idViaje;
    private ImageView foto;
    private Viaje viaje;
    private int tipoViaje;
    private Pasajero pasajero;

    private TextView nombreConductor;
    private TextView vehiculo;
    private TextView calificacion;
    private TextView origen1;
    private TextView origen2;
    private TextView origen3;
    private TextView origen4;
    private TextView origen5;
    private TextView destino1;
    private TextView destino2;
    private TextView destino3;
    private TextView destino4;
    private TextView destino5;
    private TextView precio1;
    private TextView precio2;
    private TextView precio3;
    private TextView precio4;
    private TextView precio5;
    private TextView fecha1;
    private TextView fecha2;
    private TextView fecha3;
    private TextView fecha4;
    private TextView fecha5;
    private TextView hora1;
    private TextView hora2;
    private TextView hora3;
    private TextView hora4;
    private TextView hora5;
    private Spinner plazas1;
    private Spinner plazas2;
    private Spinner plazas3;
    private Spinner plazas4;
    private Spinner plazas5;
    private int reserva1;
    private int reserva2;
    private int reserva3;
    private int reserva4;
    private int reserva5;
    private TextView valorReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_viaje);

        idViaje=getIntent().getStringExtra("idViaje");
        pasajero = (Pasajero)getIntent().getSerializableExtra("pasajero_entidad");

        foto = (ImageView)findViewById(R.id.InformacionViajeActivity_foto);
        foto.setImageResource(R.drawable.user);
        viaje = DBQueries.getfullViaje(idViaje,this);
        tipoViaje=getTipoViaje();

        nombreConductor = (TextView)findViewById(R.id.InformacionViajeActivity_Nombre);
        vehiculo = (TextView)findViewById(R.id.InformacionViajeActivity_Vehiculo);
        calificacion = (TextView)findViewById(R.id.InformacionViajeActivity_Calificacion);
        origen1 = (TextView)findViewById(R.id.InformacionViajeActivity_Origen1);
        origen2 = (TextView)findViewById(R.id.InformacionViajeActivity_Origen2);
        origen3 = (TextView)findViewById(R.id.InformacionViajeActivity_Origen3);
        origen4 = (TextView)findViewById(R.id.InformacionViajeActivity_Origen4);
        origen5 = (TextView)findViewById(R.id.InformacionViajeActivity_Origen5);
        destino1 = (TextView)findViewById(R.id.InformacionViajeActivity_Destino1);
        destino2 = (TextView)findViewById(R.id.InformacionViajeActivity_Destino2);
        destino3 = (TextView)findViewById(R.id.InformacionViajeActivity_Destino3);
        destino4 = (TextView)findViewById(R.id.InformacionViajeActivity_Destino4);
        destino5 = (TextView)findViewById(R.id.InformacionViajeActivity_Destino5);
        precio1 = (TextView)findViewById(R.id.InformacionViajeActivity_Precio1);
        precio2 = (TextView)findViewById(R.id.InformacionViajeActivity_Precio2);
        precio3 = (TextView)findViewById(R.id.InformacionViajeActivity_Precio3);
        precio4 = (TextView)findViewById(R.id.InformacionViajeActivity_Precio4);
        precio5 = (TextView)findViewById(R.id.InformacionViajeActivity_Precio5);
        fecha1 = (TextView)findViewById(R.id.InformacionViajeActivity_Fecha1);
        fecha2 = (TextView)findViewById(R.id.InformacionViajeActivity_Fecha2);
        fecha3 = (TextView)findViewById(R.id.InformacionViajeActivity_Fecha3);
        fecha4 = (TextView)findViewById(R.id.InformacionViajeActivity_Fecha4);
        fecha5 = (TextView)findViewById(R.id.InformacionViajeActivity_Fecha5);
        hora1 = (TextView)findViewById(R.id.InformacionViajeActivity_Hora1);
        hora2 = (TextView)findViewById(R.id.InformacionViajeActivity_Hora2);
        hora3 = (TextView)findViewById(R.id.InformacionViajeActivity_Hora3);
        hora4 = (TextView)findViewById(R.id.InformacionViajeActivity_Hora4);
        hora5 = (TextView)findViewById(R.id.InformacionViajeActivity_Hora5);
        plazas1 = (Spinner)findViewById(R.id.InformacionViajeActivity_Plazas1);
        plazas2 = (Spinner)findViewById(R.id.InformacionViajeActivity_Plazas2);
        plazas3 = (Spinner)findViewById(R.id.InformacionViajeActivity_Plazas3);
        plazas4 = (Spinner)findViewById(R.id.InformacionViajeActivity_Plazas4);
        plazas5 = (Spinner)findViewById(R.id.InformacionViajeActivity_Plazas5);
        valorReserva = (TextView)findViewById(R.id.InformacionViajeActivity_ValorReserva);

        nombreConductor.setText(viaje.getConductor());

        setCantidadTramos();
        setDatosTramos();

        final Handler h = new Handler();
        final int delay = 100; //milliseconds

        h.postDelayed(new Runnable(){
            public void run(){
                ActualizacionValorReserva();
                h.postDelayed(this, delay);
            }
        }, delay);
    }

    private int getTipoViaje(){
        if(viaje.getParada1().isEmpty())return 0; //viaje sin paradas
        else if(viaje.getParada2().isEmpty())return 1; //1 parada
        else if(viaje.getParada3().isEmpty())return 2; //2 paradas
        else if(viaje.getParada4().isEmpty())return 3; //3 paradas
        else return 4; //4 paradas
    }

    private void setCantidadTramos(){
        if(tipoViaje==0){
            origen2.setVisibility(View.INVISIBLE);
            destino2.setVisibility(View.INVISIBLE);
            precio2.setVisibility(View.INVISIBLE);
            fecha2.setVisibility(View.INVISIBLE);
            hora2.setVisibility(View.INVISIBLE);
            plazas2.setVisibility(View.INVISIBLE);
            origen3.setVisibility(View.INVISIBLE);
            destino3.setVisibility(View.INVISIBLE);
            precio3.setVisibility(View.INVISIBLE);
            fecha3.setVisibility(View.INVISIBLE);
            hora3.setVisibility(View.INVISIBLE);
            plazas3.setVisibility(View.INVISIBLE);
            origen4.setVisibility(View.INVISIBLE);
            destino4.setVisibility(View.INVISIBLE);
            precio4.setVisibility(View.INVISIBLE);
            fecha4.setVisibility(View.INVISIBLE);
            hora4.setVisibility(View.INVISIBLE);
            plazas4.setVisibility(View.INVISIBLE);
            origen5.setVisibility(View.INVISIBLE);
            destino5.setVisibility(View.INVISIBLE);
            precio5.setVisibility(View.INVISIBLE);
            fecha5.setVisibility(View.INVISIBLE);
            hora5.setVisibility(View.INVISIBLE);
            plazas5.setVisibility(View.INVISIBLE);
        }
        else if(tipoViaje==1){
            origen3.setVisibility(View.INVISIBLE);
            destino3.setVisibility(View.INVISIBLE);
            precio3.setVisibility(View.INVISIBLE);
            fecha3.setVisibility(View.INVISIBLE);
            hora3.setVisibility(View.INVISIBLE);
            plazas3.setVisibility(View.INVISIBLE);
            origen4.setVisibility(View.INVISIBLE);
            destino4.setVisibility(View.INVISIBLE);
            precio4.setVisibility(View.INVISIBLE);
            fecha4.setVisibility(View.INVISIBLE);
            hora4.setVisibility(View.INVISIBLE);
            plazas4.setVisibility(View.INVISIBLE);
            origen5.setVisibility(View.INVISIBLE);
            destino5.setVisibility(View.INVISIBLE);
            precio5.setVisibility(View.INVISIBLE);
            fecha5.setVisibility(View.INVISIBLE);
            hora5.setVisibility(View.INVISIBLE);
            plazas5.setVisibility(View.INVISIBLE);
        }
        else if(tipoViaje==2){
            origen4.setVisibility(View.INVISIBLE);
            destino4.setVisibility(View.INVISIBLE);
            precio4.setVisibility(View.INVISIBLE);
            fecha4.setVisibility(View.INVISIBLE);
            hora4.setVisibility(View.INVISIBLE);
            plazas4.setVisibility(View.INVISIBLE);
            origen5.setVisibility(View.INVISIBLE);
            destino5.setVisibility(View.INVISIBLE);
            precio5.setVisibility(View.INVISIBLE);
            fecha5.setVisibility(View.INVISIBLE);
            hora5.setVisibility(View.INVISIBLE);
            plazas5.setVisibility(View.INVISIBLE);
        }
        else if(tipoViaje==3){
            origen5.setVisibility(View.INVISIBLE);
            destino5.setVisibility(View.INVISIBLE);
            precio5.setVisibility(View.INVISIBLE);
            fecha5.setVisibility(View.INVISIBLE);
            hora5.setVisibility(View.INVISIBLE);
            plazas5.setVisibility(View.INVISIBLE);
        }
    }

    private void setDatosTramos(){
        if(tipoViaje==0){
            origen1.setText(viaje.getOrigen());
            destino1.setText(viaje.getDestino());
            precio1.setText("$"+Integer.toString(viaje.getValorTotal()));
            fecha1.setText(viaje.getFechaInicio());
            hora1.setText(viaje.getHoraInicio());

            int cantplazas1 = viaje.getPlazas1();
            String[] spinner1 = new String[cantplazas1 + 1];
            for(int i=0; i<=cantplazas1; i++)spinner1[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner1);
            plazas1.setAdapter(adapterSpinner1);

        }
        else if(tipoViaje==1){
            origen1.setText(viaje.getOrigen());
            destino1.setText(viaje.getParada1());
            precio1.setText("$"+Integer.toString(viaje.getValor1()));
            fecha1.setText(viaje.getFechaInicio());
            hora1.setText(viaje.getHoraInicio());
            origen2.setText(viaje.getParada1());
            destino2.setText(viaje.getDestino());
            precio2.setText("$"+Integer.toString(viaje.getValorTotal()-viaje.getValor1()));
            fecha2.setText(viaje.getFecha1());
            hora2.setText(viaje.getHora1());

            int cantplazas1 = viaje.getPlazas1();
            String[] spinner1 = new String[cantplazas1 + 1];
            for(int i=0; i<=cantplazas1; i++)spinner1[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner1);
            plazas1.setAdapter(adapterSpinner1);

            int cantplazas2 = viaje.getPlazas2();
            String[] spinner2 = new String[cantplazas2 + 1];
            for(int i=0; i<=cantplazas2; i++)spinner2[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner2);
            plazas2.setAdapter(adapterSpinner2);
        }
        else if(tipoViaje==2){
            origen1.setText(viaje.getOrigen());
            destino1.setText(viaje.getParada1());
            precio1.setText("$"+Integer.toString(viaje.getValor1()));
            fecha1.setText(viaje.getFechaInicio());
            hora1.setText(viaje.getHoraInicio());
            origen2.setText(viaje.getParada1());
            destino2.setText(viaje.getParada2());
            precio2.setText("$"+Integer.toString(viaje.getValor2()));
            fecha2.setText(viaje.getFecha1());
            hora2.setText(viaje.getHora1());
            origen3.setText(viaje.getParada2());
            destino3.setText(viaje.getDestino());
            precio3.setText("$"+ Integer.toString(viaje.getValorTotal()-viaje.getValor1()-viaje.getValor2()));
            fecha3.setText(viaje.getFecha2());
            hora3.setText(viaje.getHora2());

            int cantplazas1 = viaje.getPlazas1();
            String[] spinner1 = new String[cantplazas1 + 1];
            for(int i=0; i<=cantplazas1; i++)spinner1[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner1);
            plazas1.setAdapter(adapterSpinner1);

            int cantplazas2 = viaje.getPlazas2();
            String[] spinner2 = new String[cantplazas2 + 1];
            for(int i=0; i<=cantplazas2; i++)spinner2[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner2);
            plazas2.setAdapter(adapterSpinner2);

            int cantplazas3 = viaje.getPlazas3();
            String[] spinner3 = new String[cantplazas3 + 1];
            for(int i=0; i<=cantplazas3; i++)spinner3[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner3);
            plazas3.setAdapter(adapterSpinner3);
        }
        else if(tipoViaje==3){
            origen1.setText(viaje.getOrigen());
            destino1.setText(viaje.getParada1());
            precio1.setText("$"+Integer.toString(viaje.getValor1()));
            fecha1.setText(viaje.getFechaInicio());
            hora1.setText(viaje.getHoraInicio());
            origen2.setText(viaje.getParada1());
            destino2.setText(viaje.getParada2());
            precio2.setText("$"+Integer.toString(viaje.getValor2()));
            fecha2.setText(viaje.getFecha1());
            hora2.setText(viaje.getHora1());
            origen3.setText(viaje.getParada2());
            destino3.setText(viaje.getParada3());
            precio3.setText("$"+Integer.toString(viaje.getValor3()));
            fecha3.setText(viaje.getFecha2());
            hora3.setText(viaje.getHora2());
            origen4.setText(viaje.getParada3());
            destino4.setText(viaje.getDestino());
            precio4.setText("$"+Integer.toString(viaje.getValorTotal()-viaje.getValor1()-viaje.getValor2()-viaje.getValor3()));
            fecha4.setText(viaje.getFecha3());
            hora4.setText(viaje.getHora3());

            int cantplazas1 = viaje.getPlazas1();
            String[] spinner1 = new String[cantplazas1 + 1];
            for(int i=0; i<=cantplazas1; i++)spinner1[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner1);
            plazas1.setAdapter(adapterSpinner1);

            int cantplazas2 = viaje.getPlazas2();
            String[] spinner2 = new String[cantplazas2 + 1];
            for(int i=0; i<=cantplazas2; i++)spinner2[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner2);
            plazas2.setAdapter(adapterSpinner2);

            int cantplazas3 = viaje.getPlazas3();
            String[] spinner3 = new String[cantplazas3 + 1];
            for(int i=0; i<=cantplazas3; i++)spinner3[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner3);
            plazas3.setAdapter(adapterSpinner3);

            int cantplazas4 = viaje.getPlazas4();
            String[] spinner4 = new String[cantplazas4 + 1];
            for(int i=0; i<=cantplazas4; i++)spinner4[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner4);
            plazas4.setAdapter(adapterSpinner4);
        }
        else if(tipoViaje==4){
            origen1.setText(viaje.getOrigen());
            destino1.setText(viaje.getParada1());
            precio1.setText("$"+Integer.toString(viaje.getValor1()));
            fecha1.setText(viaje.getFechaInicio());
            hora1.setText(viaje.getHoraInicio());
            origen2.setText(viaje.getParada1());
            destino2.setText(viaje.getParada2());
            precio2.setText("$"+Integer.toString(viaje.getValor2()));
            fecha2.setText(viaje.getFecha1());
            hora2.setText(viaje.getHora1());
            origen3.setText(viaje.getParada2());
            destino3.setText(viaje.getParada3());
            precio3.setText("$"+Integer.toString(viaje.getValor3()));
            fecha3.setText(viaje.getFecha2());
            hora3.setText(viaje.getHora2());
            origen4.setText(viaje.getParada3());
            destino4.setText(viaje.getParada4());
            precio4.setText("$"+Integer.toString(viaje.getValor4()));
            fecha4.setText(viaje.getFecha3());
            hora4.setText(viaje.getHora3());
            origen5.setText(viaje.getParada4());
            destino5.setText(viaje.getDestino());
            precio5.setText("$"+Integer.toString(viaje.getValorTotal()-viaje.getValor1()-viaje.getValor2()-viaje.getValor3()-viaje.getValor4()));
            fecha5.setText(viaje.getFecha4());
            hora5.setText(viaje.getHora4());

            int cantplazas1 = viaje.getPlazas1();
            String[] spinner1 = new String[cantplazas1 + 1];
            for(int i=0; i<=cantplazas1; i++)spinner1[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner1);
            plazas1.setAdapter(adapterSpinner1);

            int cantplazas2 = viaje.getPlazas2();
            String[] spinner2 = new String[cantplazas2 + 1];
            for(int i=0; i<=cantplazas2; i++)spinner2[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner2);
            plazas2.setAdapter(adapterSpinner2);

            int cantplazas3 = viaje.getPlazas3();
            String[] spinner3 = new String[cantplazas3 + 1];
            for(int i=0; i<=cantplazas3; i++)spinner3[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner3);
            plazas3.setAdapter(adapterSpinner3);

            int cantplazas4 = viaje.getPlazas4();
            String[] spinner4 = new String[cantplazas4 + 1];
            for(int i=0; i<=cantplazas4; i++)spinner4[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner4);
            plazas4.setAdapter(adapterSpinner4);

            int cantplazas5 = viaje.getPlazas5();
            String[] spinner5 = new String[cantplazas5 + 1];
            for(int i=0; i<=cantplazas5; i++)spinner5[i]= i +" Plaza(s)";
            ArrayAdapter<String> adapterSpinner5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner5);
            plazas5.setAdapter(adapterSpinner5);

        }
    }

    public void Reservar(View view) {
        if (!(reserva1==0 && reserva2==0 && reserva3==0 && reserva4==0 && reserva5==0)) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues values = new ContentValues();
            String randID = Integer.toString((int) (Math.random() * 999999999));
            while (DBQueries.isReservaIdOcupado(randID, this)) {
                randID = Integer.toString((int) (Math.random() * 999999999));
            }

            String str_origen="";
            String str_destino="";
            int reserva = 0;

            if(reserva1>0){
                str_origen=origen1.getText().toString(); reserva=reserva1;
            }
            else if(reserva2>0){
                str_origen=origen2.getText().toString(); reserva=reserva2;
            }
            else if(reserva3>0){
                str_origen=origen3.getText().toString(); reserva=reserva3;
            }
            else if(reserva4>0){
                str_origen=origen4.getText().toString(); reserva=reserva4;
            }
            else if(reserva5>0){
                str_origen=origen5.getText().toString(); reserva=reserva5;
            }

            if(reserva5>0)str_destino=destino5.getText().toString();
            else if(reserva4>0)str_destino=destino4.getText().toString();
            else if(reserva3>0)str_destino=destino3.getText().toString();
            else if(reserva2>0)str_destino=destino2.getText().toString();
            else if(reserva1>0)str_destino=destino1.getText().toString();

            values.put("id", randID);
            values.put("idviaje", viaje.getId());
            values.put("username", pasajero.getUsername());
            if (tipoViaje == 0) {
                values.put("plazas", reserva);
                values.put("plazas1", reserva1);
                values.put("valor", reserva1 * viaje.getValorTotal());
            } else if (tipoViaje == 1) {
                values.put("plazas", reserva);
                values.put("plazas1", reserva1);
                values.put("plazas2", reserva2);
                values.put("valor", reserva1 * viaje.getValor1() +
                        reserva2 * (viaje.getValorTotal() - viaje.getValor1()));
            } else if (tipoViaje == 2) {
                values.put("plazas", reserva);
                values.put("plazas1", reserva1);
                values.put("plazas2", reserva2);
                values.put("plazas3", reserva3);
                values.put("valor", reserva1 * viaje.getValor1() +
                        reserva2 * viaje.getValor2() +
                        reserva3 * (viaje.getValorTotal() - viaje.getValor1() - viaje.getValor2()));
            } else if (tipoViaje == 3) {
                values.put("plazas", reserva);
                values.put("plazas1", reserva1);
                values.put("plazas2", reserva2);
                values.put("plazas3", reserva3);
                values.put("plazas4", reserva4);
                values.put("valor", reserva1 * viaje.getValor1() +
                        reserva2 * viaje.getValor2() +
                        reserva3 * viaje.getValor3() +
                        reserva3 * (viaje.getValorTotal() - viaje.getValor1() - viaje.getValor2() - viaje.getValor3()));
            } else if (tipoViaje == 4) {
                values.put("plazas", reserva);
                values.put("plazas1", reserva1);
                values.put("plazas2", reserva2);
                values.put("plazas3", reserva3);
                values.put("plazas4", reserva4);
                values.put("plazas5", reserva5);
                values.put("valor", reserva1 * viaje.getValor1() +
                        reserva2 * viaje.getValor2() +
                        reserva3 * viaje.getValor3() +
                        reserva4 * viaje.getValor4() +
                        reserva3 * (viaje.getValorTotal() - viaje.getValor1() - viaje.getValor2() - viaje.getValor3() - viaje.getValor4()));
            }
            values.put("origen", str_origen);
            values.put("destino", str_destino);
            values.put("procesada", 0);
            db.insert("reserva", null, values);
            db.close();
            Toast.makeText(this, "Reserva solicitada con éxito", Toast.LENGTH_SHORT).show();
            this.finish();
        }
        else Toast.makeText(this, "Seleccione plazas para reservar", Toast.LENGTH_SHORT).show();
    }

    private void ActualizacionValorReserva(){
        if(tipoViaje==0){
            String seleccion1 = plazas1.getSelectedItem().toString();
            seleccion1 = seleccion1.substring(0, seleccion1.length()-9); // elimina "Plaza(s)" del string
            reserva1 = Integer.valueOf(seleccion1);
            valorReserva.setText(Integer.toString(reserva1*viaje.getValorTotal()));
        }
        else if(tipoViaje==1){
            String seleccion1 = plazas1.getSelectedItem().toString();
            seleccion1 = seleccion1.substring(0, seleccion1.length()-9);
            reserva1 = Integer.valueOf(seleccion1);
            String seleccion2 = plazas2.getSelectedItem().toString();
            seleccion2 = seleccion2.substring(0, seleccion2.length()-9);
            reserva2 = Integer.valueOf(seleccion2);
            valorReserva.setText(Integer.toString(reserva1*viaje.getValor1() +
                    reserva2* (viaje.getValorTotal()-viaje.getValor1())) );
        }
        else if(tipoViaje==2){
            String seleccion1 = plazas1.getSelectedItem().toString();
            seleccion1 = seleccion1.substring(0, seleccion1.length()-9);
            reserva1 = Integer.valueOf(seleccion1);
            String seleccion2 = plazas2.getSelectedItem().toString();
            seleccion2 = seleccion2.substring(0, seleccion2.length()-9);
            reserva2 = Integer.valueOf(seleccion2);
            String seleccion3 = plazas3.getSelectedItem().toString();
            seleccion3 = seleccion3.substring(0, seleccion3.length()-9);
            reserva3 = Integer.valueOf(seleccion3);
            valorReserva.setText(Integer.toString(reserva1*viaje.getValor1() +
                    reserva2*viaje.getValor2() +
                    reserva3* (viaje.getValorTotal()-viaje.getValor1()-viaje.getValor2())) );
        }
        else if(tipoViaje==3){
            String seleccion1 = plazas1.getSelectedItem().toString();
            seleccion1 = seleccion1.substring(0, seleccion1.length()-9);
            reserva1 = Integer.valueOf(seleccion1);
            String seleccion2 = plazas2.getSelectedItem().toString();
            seleccion2 = seleccion2.substring(0, seleccion2.length()-9);
            reserva2 = Integer.valueOf(seleccion2);
            String seleccion3 = plazas3.getSelectedItem().toString();
            seleccion3 = seleccion3.substring(0, seleccion3.length()-9);
            reserva3 = Integer.valueOf(seleccion3);
            String seleccion4 = plazas4.getSelectedItem().toString();
            seleccion4 = seleccion4.substring(0, seleccion4.length()-9);
            reserva4 = Integer.valueOf(seleccion4);
            valorReserva.setText(Integer.toString(reserva1*viaje.getValor1() +
                    reserva2*viaje.getValor2() +
                    reserva3*viaje.getValor3() +
                    reserva3* (viaje.getValorTotal()-viaje.getValor1()-viaje.getValor2()-viaje.getValor3())) );
        }
        else if(tipoViaje==4){
            String seleccion1 = plazas1.getSelectedItem().toString();
            seleccion1 = seleccion1.substring(0, seleccion1.length()-9);
            reserva1 = Integer.valueOf(seleccion1);
            String seleccion2 = plazas2.getSelectedItem().toString();
            seleccion2 = seleccion2.substring(0, seleccion2.length()-9);
            reserva2 = Integer.valueOf(seleccion2);
            String seleccion3 = plazas3.getSelectedItem().toString();
            seleccion3 = seleccion3.substring(0, seleccion3.length()-9);
            reserva3 = Integer.valueOf(seleccion3);
            String seleccion4 = plazas4.getSelectedItem().toString();
            seleccion4 = seleccion4.substring(0, seleccion4.length()-9);
            reserva4 = Integer.valueOf(seleccion4);
            String seleccion5 = plazas5.getSelectedItem().toString();
            seleccion5 = seleccion5.substring(0, seleccion5.length()-9);
            reserva5 = Integer.valueOf(seleccion5);
            valorReserva.setText(Integer.toString(reserva1*viaje.getValor1() +
                    reserva2*viaje.getValor2() +
                    reserva3*viaje.getValor3() +
                    reserva4*viaje.getValor4() +
                    reserva3* (viaje.getValorTotal()-viaje.getValor1()-viaje.getValor2()-viaje.getValor3()-viaje.getValor4())) );
        }
    }
}
