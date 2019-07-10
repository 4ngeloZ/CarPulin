package com.example.carpulin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterPasajerosSubir extends RecyclerView.Adapter<RecyclerViewAdapterPasajerosSubir.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView pasajero;
        private TextView viaje;
        private TextView reserva;
        private TextView horario;
        private TextView plazas;
        private ImageView foto;
        private CheckBox check;

        public ViewHolder(View view){
            super(view);
            pasajero = (TextView)view.findViewById(R.id.Reserva_pasajero);
            reserva = (TextView)view.findViewById(R.id.Reserva_reserva);
            plazas = (TextView)view.findViewById(R.id.Reserva_plazas);
            foto = (ImageView)view.findViewById(R.id.Reserva_foto);
            check = (CheckBox)view.findViewById(R.id.Reserva_checkBox);

        }
    }

    public List<ReservaModelo> listaReservas;
    SparseBooleanArray itemStateArray= new SparseBooleanArray();

    public RecyclerViewAdapterPasajerosSubir(List<ReservaModelo> listaReservas){
        this.listaReservas = listaReservas;
    }


    @Override
    public RecyclerViewAdapterPasajerosSubir.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View i_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pasajerossubir, null, false);
        RecyclerViewAdapterPasajerosSubir.ViewHolder viewHolder = new RecyclerViewAdapterPasajerosSubir.ViewHolder(i_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterPasajerosSubir.ViewHolder holder, final int position) {
        holder.pasajero.setText("Pasajero: " + listaReservas.get(position).getPasajero());
        holder.reserva.setText("Origen: " + listaReservas.get(position).getOrigenReserva() + " | Destino: " + listaReservas.get(position).getDestinoReserva());
        holder.plazas.setText("Plazas: " + listaReservas.get(position).getPlazas());
        holder.foto.setImageResource(listaReservas.get(position).getFoto());
        holder.check.setTag(position);

        holder.check.setOnCheckedChangeListener(null);

        holder.check.setChecked(itemStateArray.get(position));

        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set your object's last status
                itemStateArray.append(position,isChecked);
            }
        });
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

    public List<ReservaModelo> getReservasListas(){
        List<ReservaModelo> reservaslistas = new ArrayList<>();
        for(int i = 0; i<itemStateArray.size(); i++) {
            if(itemStateArray.get(i,false)){
                reservaslistas.add(listaReservas.get(i));
            }
        }
        return reservaslistas;
    }

    public List<ReservaModelo> getReservasRechazadas(){
        List<ReservaModelo> reservasrechazadas = new ArrayList<>();
        for(int i = 0; i<itemStateArray.size(); i++) {
            if(!itemStateArray.get(i,false)){
                reservasrechazadas.add(listaReservas.get(i));
            }
        }
        return reservasrechazadas;
    }

}
