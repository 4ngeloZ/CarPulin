package com.example.carpulin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapterViajes extends RecyclerView.Adapter<RecyclerViewAdapterViajes.ViewHolder> {

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

        public ViewHolder(View view){
            super(view);
            conductor = (TextView)view.findViewById(R.id.Viaje_conductor);
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

    public List<ViajeModelo> listaViajes;

    public RecyclerViewAdapterViajes(List<ViajeModelo> listaViajes){
        this.listaViajes = listaViajes;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View i_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viaje, null, false);
        ViewHolder viewHolder = new ViewHolder(i_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.conductor.setText("ConductorResponse: " + listaViajes.get(position).getConductor());
        holder.recorrido.setText("Origen: " + listaViajes.get(position).getOrigen() + " | Destino: " + listaViajes.get(position).getDestino());
        holder.horario.setText("Fecha: " + listaViajes.get(position).getFecha() + " | Hora: " + listaViajes.get(position).getHora());
        holder.plazas.setText("Plazas: " + listaViajes.get(position).getPlazas() + " | Valor por plazas: " + calcularPrecio(position, listaViajes.get(position).getTipoviaje()));
        holder.foto.setImageResource(listaViajes.get(position).getFoto());
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

    private int calcularPrecio(int position, int tipoviaje){
        int precio=0;
        if(tipoviaje==0)precio=listaViajes.get(position).getValortotal();
        else if(tipoviaje==1)precio=listaViajes.get(position).getValor1()+listaViajes.get(position).getValor2()+listaViajes.get(position).getValor3()+listaViajes.get(position).getValor4();
        else if(tipoviaje==2)precio=listaViajes.get(position).getValor1()+listaViajes.get(position).getValor2()+listaViajes.get(position).getValor3();
        else if(tipoviaje==3)precio=listaViajes.get(position).getValor1()+listaViajes.get(position).getValor2();
        else if(tipoviaje==4)precio=listaViajes.get(position).getValor1();
        else if(tipoviaje==5)precio=listaViajes.get(position).getValortotal()-listaViajes.get(position).getValor1();
        else if(tipoviaje==6)precio=listaViajes.get(position).getValor2()+listaViajes.get(position).getValor3()+listaViajes.get(position).getValor4();
        else if(tipoviaje==7)precio=listaViajes.get(position).getValor2()+listaViajes.get(position).getValor3();
        else if(tipoviaje==8)precio=listaViajes.get(position).getValor2();
        else if(tipoviaje==9)precio=listaViajes.get(position).getValortotal()-listaViajes.get(position).getValor1()-listaViajes.get(position).getValor2();
        else if(tipoviaje==10)precio=listaViajes.get(position).getValor3()+listaViajes.get(position).getValor4();
        else if(tipoviaje==11)precio=listaViajes.get(position).getValor3();
        else if(tipoviaje==12)precio=listaViajes.get(position).getValortotal()-listaViajes.get(position).getValor1()-listaViajes.get(position).getValor2()-listaViajes.get(position).getValor3();
        else if(tipoviaje==13)precio=listaViajes.get(position).getValor4();
        else if(tipoviaje==14)precio=listaViajes.get(position).getValortotal()-listaViajes.get(position).getValor1()-listaViajes.get(position).getValor2()-listaViajes.get(position).getValor3()-listaViajes.get(position).getValor4();
        return precio;
    }

    public String getId(View view){
        int position = (int)view.getTag();
        return listaViajes.get(position).getId();
    }

}











