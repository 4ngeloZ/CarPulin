package com.example.carpulin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapterReservas extends RecyclerView.Adapter<RecyclerViewAdapterReservas.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView pasajero;
        private TextView viaje;
        private TextView reserva;
        private TextView horario;
        private TextView plazas;
        private ImageView foto;
        private Button aceptar;
        private Button rechazar;

        public ViewHolder(View view){
            super(view);
            pasajero = (TextView)view.findViewById(R.id.Reserva_pasajero);
            viaje = (TextView)view.findViewById(R.id.Reserva_viaje);
            reserva = (TextView)view.findViewById(R.id.Reserva_reserva);
            horario = (TextView)view.findViewById(R.id.Reserva_horario);
            plazas = (TextView)view.findViewById(R.id.Reserva_plazas);
            foto = (ImageView)view.findViewById(R.id.Reserva_foto);
            aceptar = (Button)view.findViewById(R.id.Reserva_aceptar);
            rechazar = (Button)view.findViewById(R.id.Reserva_rechazar);
        }
    }

    public List<ReservaModelo> listaReservas;

    public RecyclerViewAdapterReservas(List<ReservaModelo> listaReservas){
        this.listaReservas = listaReservas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View i_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reserva, null, false);
        ViewHolder viewHolder = new ViewHolder(i_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.pasajero.setText("PasajeroResponse: " + listaReservas.get(position).getPasajero());
        holder.viaje.setText("Viaje -> Origen: " + listaReservas.get(position).getOrigenViaje() + " | Destino: " + listaReservas.get(position).getDestinoViaje());
        holder.reserva.setText("Reserva -> Origen: " + listaReservas.get(position).getOrigenReserva() + " | Destino: " + listaReservas.get(position).getDestinoReserva());
        holder.horario.setText("Fecha: " + listaReservas.get(position).getFecha() + " | Hora: " + listaReservas.get(position).getHora());
        holder.plazas.setText("Plazas: " + listaReservas.get(position).getPlazas());
        holder.foto.setImageResource(listaReservas.get(position).getFoto());
        holder.aceptar.setTag(position);
        holder.rechazar.setTag(position);
    }

    @Override
    public int getItemCount() {
        return listaReservas.size();
    }

    public String getIdReserva(View view){
        int position = (int)view.getTag();
        return listaReservas.get(position).getId();
    }

    public String getIdViaje(View view){
        int position = (int)view.getTag();
        return listaReservas.get(position).getIdViaje();
    }

}











