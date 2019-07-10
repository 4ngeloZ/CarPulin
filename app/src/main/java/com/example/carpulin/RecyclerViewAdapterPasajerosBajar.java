package com.example.carpulin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapterPasajerosBajar extends RecyclerView.Adapter<RecyclerViewAdapterPasajerosBajar.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView pasajero;
        private TextView viaje;
        private TextView reserva;
        private TextView horario;
        private TextView plazas;
        private TextView monto;
        private ImageView foto;

        public ViewHolder(View view){
            super(view);
            pasajero = (TextView)view.findViewById(R.id.Reserva_pasajero);
            reserva = (TextView)view.findViewById(R.id.Reserva_reserva);
            plazas = (TextView)view.findViewById(R.id.Reserva_plazas);
            foto = (ImageView)view.findViewById(R.id.Reserva_foto);
            monto = (TextView)view.findViewById(R.id.Reserva_monto);
        }
    }

    public List<ReservaModelo> listaReservas;

    public RecyclerViewAdapterPasajerosBajar(List<ReservaModelo> listaReservas){
        this.listaReservas = listaReservas;
    }


    @Override
    public RecyclerViewAdapterPasajerosBajar.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View i_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pasajerosbajar, null, false);
        RecyclerViewAdapterPasajerosBajar.ViewHolder viewHolder = new RecyclerViewAdapterPasajerosBajar.ViewHolder(i_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterPasajerosBajar.ViewHolder holder, int position) {
        holder.pasajero.setText("Pasajero: " + listaReservas.get(position).getPasajero());
        holder.reserva.setText("Origen: " + listaReservas.get(position).getOrigenReserva() + " | Destino: " + listaReservas.get(position).getDestinoReserva());
        holder.plazas.setText("Plazas: " + listaReservas.get(position).getPlazas());
        holder.monto.setText("Valor total: " + listaReservas.get(position).getValortotal());
        holder.foto.setImageResource(listaReservas.get(position).getFoto());
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
