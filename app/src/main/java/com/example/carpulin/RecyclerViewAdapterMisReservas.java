package com.example.carpulin;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Reserva;
import com.example.carpulin.Entidades.Viaje;

import java.util.List;

public class RecyclerViewAdapterMisReservas extends RecyclerView.Adapter<RecyclerViewAdapterMisReservas.ViewHolder>{
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView conductor;
        private TextView origen;
        private TextView destino;
        private TextView recorrido;
        private TextView horario;
        private TextView plazas;
        private ImageView foto;
        private TextView parada1;
        private TextView parada2;
        private TextView parada3;
        private TextView parada4;
        private TextView estado;

        public ViewHolder(View view){
            super(view);
            conductor = (TextView)view.findViewById(R.id.Reserva_conductor);
            recorrido = (TextView)view.findViewById(R.id.Reserva_recorrido_reservado);
            origen = (TextView)view.findViewById(R.id.Reserva_origen);
            destino = (TextView)view.findViewById(R.id.Reserva_destino);
            horario = (TextView)view.findViewById(R.id.Reserva_horario);
            plazas = (TextView)view.findViewById(R.id.Reserva_plazas);
            foto = (ImageView)view.findViewById(R.id.Reserva_foto);
            parada1 = (TextView)view.findViewById(R.id.Reserva_parada1);
            parada2 = (TextView)view.findViewById(R.id.Reserva_parada2);
            parada3 = (TextView)view.findViewById(R.id.Reserva_parada3);
            parada4 = (TextView)view.findViewById(R.id.Reserva_parada4);
            estado = (TextView)view.findViewById(R.id.Reserva_estado);
        }
    }

    public List<Reserva> listaReservas;
    private Context context;
    private Viaje viaje;

    public RecyclerViewAdapterMisReservas(List<Reserva> listaReservas, Context context){
        this.listaReservas = listaReservas;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View i_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_misreservas, null, false);
        ViewHolder viewHolder = new ViewHolder(i_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int p=0;
        viaje = DBQueries.getfullViaje(listaReservas.get(position).getIdviaje(), context);
        holder.conductor.setText("Conductor: " + viaje.getConductor());
        holder.recorrido.setText("Reserva: " + listaReservas.get(position).getOrigen() + " -> " + listaReservas.get(position).getDestino());
        if(listaReservas.get(position).getPlazas1()!=0){
            holder.horario.setText("Fecha: " + viaje.getFechaInicio() + " | Hora: " + viaje.getHoraInicio());
            p=listaReservas.get(position).getPlazas1();
        }
        else if (listaReservas.get(position).getPlazas2()!=0){
            holder.horario.setText("Fecha: " + viaje.getFecha1() + " | Hora: " + viaje.getHora1());
            p=listaReservas.get(position).getPlazas2();
        }
        else if (listaReservas.get(position).getPlazas3()!=0){
            holder.horario.setText("Fecha: " + viaje.getFecha2() + " | Hora: " + viaje.getHora2());
            p=listaReservas.get(position).getPlazas3();
        }
        else if (listaReservas.get(position).getPlazas4()!=0){
            holder.horario.setText("Fecha: " + viaje.getFecha3() + " | Hora: " + viaje.getHora3());
            p=listaReservas.get(position).getPlazas4();
        }
        else if (listaReservas.get(position).getPlazas5()!=0){
            holder.horario.setText("Fecha: " + viaje.getFecha4() + " | Hora: " + viaje.getHora4());
            p=listaReservas.get(position).getPlazas5();
        }
        holder.plazas.setText("Plazas Totales: " + p + " | Valor viaje: " + Integer.toString(listaReservas.get(position).getValorTotal()));
        holder.foto.setImageResource(R.drawable.user);
        holder.origen.setText(viaje.getOrigen() + "->");
        if(!viaje.getParada1().isEmpty())holder.parada1.append(viaje.getParada1() + "->");
        if(!viaje.getParada2().isEmpty())holder.parada2.append(viaje.getParada2() + "->");
        if(!viaje.getParada3().isEmpty())holder.parada3.append(viaje.getParada3() + "->");
        if(!viaje.getParada4().isEmpty())holder.parada4.append(viaje.getParada4() + "->");
        holder.destino.setText(viaje.getDestino());
        holder.estado.setText("Estado: ");
        if(listaReservas.get(position).getProcesada()==0){
            holder.estado.append("En proceso");
        }
        else if(listaReservas.get(position).getProcesada()==1){
            holder.estado.setTextColor(Color.GREEN);
            holder.estado.append("Aceptada");
        }
        else if(listaReservas.get(position).getProcesada()==2){
            holder.estado.setTextColor(Color.RED);
            holder.estado.append("Rechazada");
        }
        holder.foto.setTag(position);
    }

    @Override
    public int getItemCount() {
        return listaReservas.size();
    }

    public String getId(View view){
        int position = (int)view.getTag();
        return listaReservas.get(position).getId();
    }
}
