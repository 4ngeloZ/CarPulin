package com.example.carpulin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.Entidades.Reserva;
import com.example.carpulin.Entidades.Viaje;
import com.example.carpulin.R;
import com.example.carpulin.RecyclerViewAdapterPasajerosBajar;
import com.example.carpulin.RecyclerViewAdapterPasajerosSubir;
import com.example.carpulin.ReservaModelo;

import java.util.List;

public class InformacionViajeConductorActivity extends AppCompatActivity {

    private String idViaje;
    private int numparadas;
    private Conductor conductor;
    private Viaje viaje;
    private TextView noreservas;
    private TextView suben;
    private TextView parada1;
    private TextView parada2;
    private TextView parada3;
    private TextView parada4;
    private TextView parada5;
    private TextView parada6;
    private TextView fecha1;
    private TextView fecha2;
    private TextView fecha3;
    private TextView fecha4;
    private TextView fecha5;
    private TextView fecha6;
    private TextView hora1;
    private TextView hora2;
    private TextView hora3;
    private TextView hora4;
    private TextView hora5;
    private TextView hora6;
    private TableRow fila1;
    private TableRow fila2;
    private TableRow fila3;
    private TableRow fila4;
    private TableRow fila5;
    private TableRow fila6;
    private Button iniciar;
    private RecyclerView recyclerViewPasajerosSubir;
    private RecyclerViewAdapterPasajerosSubir adaptadorPasajerosSubir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_viaje_conductor);
        idViaje=getIntent().getStringExtra("idViaje");
        conductor = (Conductor)getIntent().getSerializableExtra("conductor_entidad");
        viaje = DBQueries.getfullViaje(idViaje, this);
        numparadas = getTipoViaje(viaje);

        noreservas = (TextView)findViewById(R.id.InformacionViajeCActivitynoreservas);
        suben = (TextView)findViewById(R.id.textView7);
        noreservas.setVisibility(View.INVISIBLE);

        fila1 = (TableRow) findViewById(R.id.InformacionViajeCActivity_Parada1);
        fila2 = (TableRow)findViewById(R.id.InformacionViajeCActivity_Parada2);
        fila3 = (TableRow)findViewById(R.id.InformacionViajeCActivity_Parada3);
        fila4 = (TableRow)findViewById(R.id.InformacionViajeCActivity_Parada4);
        fila5 = (TableRow)findViewById(R.id.InformacionViajeCActivity_Parada5);
        fila6 = (TableRow)findViewById(R.id.InformacionViajeCActivity_Parada6);

        parada1 = (TextView)findViewById(R.id.InformacionViajeCActivity_Origen1);
        parada2 = (TextView)findViewById(R.id.InformacionViajeCActivity_Origen2);
        parada3 = (TextView)findViewById(R.id.InformacionViajeCActivity_Origen3);
        parada4 = (TextView)findViewById(R.id.InformacionViajeCActivity_Origen4);
        parada5 = (TextView)findViewById(R.id.InformacionViajeCActivity_Origen5);
        parada6 = (TextView)findViewById(R.id.InformacionViajeCActivity_Origen6);

        fecha1 = (TextView)findViewById(R.id.InformacionViajeCActivity_Fecha1);
        fecha2 = (TextView)findViewById(R.id.InformacionViajeCActivity_Fecha2);
        fecha3 = (TextView)findViewById(R.id.InformacionViajeCActivity_Fecha3);
        fecha4 = (TextView)findViewById(R.id.InformacionViajeCActivity_Fecha4);
        fecha5 = (TextView)findViewById(R.id.InformacionViajeCActivity_Fecha5);
        fecha6 = (TextView)findViewById(R.id.InformacionViajeCActivity_Fecha6);

        hora1 = (TextView)findViewById(R.id.InformacionViajeCActivity_Hora1);
        hora2 = (TextView)findViewById(R.id.InformacionViajeCActivity_Hora2);
        hora3 = (TextView)findViewById(R.id.InformacionViajeCActivity_Hora3);
        hora4 = (TextView)findViewById(R.id.InformacionViajeCActivity_Hora4);
        hora5 = (TextView)findViewById(R.id.InformacionViajeCActivity_Hora5);
        hora6 = (TextView)findViewById(R.id.InformacionViajeCActivity_Hora6);

        if(numparadas == 0){
            parada1.setText(viaje.getOrigen());
            fecha1.setText(viaje.getFechaInicio());
            hora1.setText(viaje.getHoraInicio());
            parada2.setText(viaje.getDestino());
            fecha2.setText(viaje.getFechaLlegada());
            hora2.setText(viaje.getHoraLlegada());
            fila3.setVisibility(View.INVISIBLE);
            fila4.setVisibility(View.INVISIBLE);
            fila5.setVisibility(View.INVISIBLE);
            fila6.setVisibility(View.INVISIBLE);
        }
        else if(numparadas == 1){
            parada1.setText(viaje.getOrigen());
            fecha1.setText(viaje.getFechaInicio());
            hora1.setText(viaje.getHoraInicio());
            parada2.setText(viaje.getParada1());
            fecha2.setText(viaje.getFecha1());
            hora2.setText(viaje.getHora1());
            parada3.setText(viaje.getDestino());
            fecha3.setText(viaje.getFechaLlegada());
            hora3.setText(viaje.getHoraLlegada());
            fila4.setVisibility(View.INVISIBLE);
            fila5.setVisibility(View.INVISIBLE);
            fila6.setVisibility(View.INVISIBLE);
        }
        else if(numparadas == 2){
            parada1.setText(viaje.getOrigen());
            fecha1.setText(viaje.getFechaInicio());
            hora1.setText(viaje.getHoraInicio());
            parada2.setText(viaje.getParada1());
            fecha2.setText(viaje.getFecha1());
            hora2.setText(viaje.getHora1());
            parada3.setText(viaje.getParada2());
            fecha3.setText(viaje.getFecha2());
            hora3.setText(viaje.getHora2());
            parada4.setText(viaje.getDestino());
            fecha4.setText(viaje.getFechaLlegada());
            hora4.setText(viaje.getHoraLlegada());
            fila5.setVisibility(View.INVISIBLE);
            fila6.setVisibility(View.INVISIBLE);
        }
        else if(numparadas == 3){
            parada1.setText(viaje.getOrigen());
            fecha1.setText(viaje.getFechaInicio());
            hora1.setText(viaje.getHoraInicio());
            parada2.setText(viaje.getParada1());
            fecha2.setText(viaje.getFecha1());
            hora2.setText(viaje.getHora1());
            parada3.setText(viaje.getParada2());
            fecha3.setText(viaje.getFecha2());
            hora3.setText(viaje.getHora2());
            parada4.setText(viaje.getParada3());
            fecha4.setText(viaje.getFecha3());
            hora4.setText(viaje.getHora3());
            parada5.setText(viaje.getDestino());
            fecha5.setText(viaje.getFechaLlegada());
            hora5.setText(viaje.getHoraLlegada());
            fila6.setVisibility(View.INVISIBLE);
        }
        else{
            parada1.setText(viaje.getOrigen());
            fecha1.setText(viaje.getFechaInicio());
            hora1.setText(viaje.getHoraInicio());
            parada2.setText(viaje.getParada1());
            fecha2.setText(viaje.getFecha1());
            hora2.setText(viaje.getHora1());
            parada3.setText(viaje.getParada2());
            fecha3.setText(viaje.getFecha2());
            hora3.setText(viaje.getHora2());
            parada4.setText(viaje.getParada3());
            fecha4.setText(viaje.getFecha3());
            hora4.setText(viaje.getHora3());
            parada5.setText(viaje.getParada4());
            fecha5.setText(viaje.getFecha4());
            hora5.setText(viaje.getHora4());
            parada6.setText(viaje.getDestino());
            fecha6.setText(viaje.getFechaLlegada());
            hora6.setText(viaje.getHoraLlegada());
        }

        if(DBQueries.getMisReservasViaje(conductor.getUsername(),idViaje,this).isEmpty()) {
            suben.setVisibility(View.INVISIBLE);
            noreservas.setVisibility(View.VISIBLE);
        }

        recyclerViewPasajerosSubir = (RecyclerView)findViewById(R.id.RecyclerReservasViajeSubir);
        recyclerViewPasajerosSubir.setLayoutManager(new LinearLayoutManager(this));

        adaptadorPasajerosSubir = new RecyclerViewAdapterPasajerosSubir(getMisReservas(conductor.getUsername(), idViaje));
        recyclerViewPasajerosSubir.setAdapter(adaptadorPasajerosSubir);

    }

    public List<ReservaModelo> getMisReservas(String username, String idviaje){
        List<ReservaModelo> reservas = DBQueries.getReservasViajeSubir(username, idviaje, viaje.getOrigen(),this);
        return reservas;
    }

    public int getTipoViaje(Viaje viaje){
        if(viaje.getParada1().isEmpty())return 0; //viaje sin paradas
        else if(viaje.getParada2().isEmpty())return 1; //1 parada
        else if(viaje.getParada3().isEmpty())return 2; //2 paradas
        else if(viaje.getParada4().isEmpty())return 3; //3 paradas
        else return 4; //4 paradas
    }

    public void iniciarViaje(View view){
        List<ReservaModelo> reservaslistas = adaptadorPasajerosSubir.getReservasListas();
        for(int i=0; i<reservaslistas.size();i++){
            DBQueries.ReservaTerminada(reservaslistas.get(i).getId(),this);
        }
        List<ReservaModelo> reservasrechazadas = adaptadorPasajerosSubir.getReservasRechazadas();
        for(int i=0; i<reservasrechazadas.size();i++){
            DBQueries.ReservaRechazada(reservasrechazadas.get(i).getId(),this);
        }
        Intent activity = new Intent(this, IniciarViajeActivity.class);
        activity.putExtra("conductor_entidad", conductor);
        activity.putExtra("viaje", idViaje);
        activity.putExtra("num_paradas", numparadas);
        activity.putExtra("siguiente", 1);
        startActivity(activity);
    }
}
