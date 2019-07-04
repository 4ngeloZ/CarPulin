package com.example.carpulin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carpulin.Entidades.Viaje;

import java.util.List;

public class RecyclerViewAdapterMisViajes extends RecyclerView.Adapter<RecyclerViewAdapterMisViajes.ViewHolder>{
    public static class ViewHolder extends RecyclerView.ViewHolder{
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

        public ViewHolder(View view){
            super(view);
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
        }
    }

    public List<Viaje> listaViajes;

    public RecyclerViewAdapterMisViajes(List<Viaje> listaViajes){
        this.listaViajes = listaViajes;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View i_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_misviajes, null, false);
        ViewHolder viewHolder = new ViewHolder(i_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.recorrido.setText("Origen: " + listaViajes.get(position).getOrigen() + " | Destino: " + listaViajes.get(position).getDestino());
        holder.horario.setText("Fecha: " + listaViajes.get(position).getFechaInicio() + " | Hora: " + listaViajes.get(position).getHoraInicio());
        holder.plazas.setText("Plazas Totales: " + listaViajes.get(position).getPlazas1() + " | Valor viaje: " + Integer.toString(listaViajes.get(position).getValorTotal()));
        holder.foto.setImageResource(R.drawable.user);
        holder.origen.setText(listaViajes.get(position).getOrigen() + "->");
        if(!listaViajes.get(position).getParada1().isEmpty())holder.parada1.setText(listaViajes.get(position).getParada1() + "->");
        if(!listaViajes.get(position).getParada2().isEmpty())holder.parada2.setText(listaViajes.get(position).getParada2() + "->");
        if(!listaViajes.get(position).getParada3().isEmpty())holder.parada3.setText(listaViajes.get(position).getParada3() + "->");
        if(!listaViajes.get(position).getParada4().isEmpty())holder.parada4.setText(listaViajes.get(position).getParada4() + "->");
        holder.destino.setText(listaViajes.get(position).getDestino());
        holder.foto.setTag(position);
    }

    @Override
    public int getItemCount() {
        return listaViajes.size();
    }

    public String getId(View view){
        int position = (int)view.getTag();
        return listaViajes.get(position).getId();
    }
}
