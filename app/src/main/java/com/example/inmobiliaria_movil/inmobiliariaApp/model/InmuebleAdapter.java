package com.example.inmobiliaria_movil.inmobiliariaApp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria_movil.R;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolderInmuebles> {

    private List<Inmueble> lista;
    private Context context;
    private LayoutInflater li;

    public InmuebleAdapter(List<Inmueble> lista, Context context, LayoutInflater li) {
        this.lista = lista;
        this.context = context;
        this.li = li;
    }


    @NonNull
    @Override
    public ViewHolderInmuebles onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.card_inmueble, parent, false);
        return new ViewHolderInmuebles(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInmuebles holder, int position) {
        Inmueble ia = lista.get(position);
        holder.direccion.setText(ia.getDireccion());
        holder.valor.setText(String.valueOf(ia.getValor()));

        String imageUrl = ia.getImagen().replace("\\", "/");
        String fullUrl = "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/" + imageUrl;

        Glide.with(context)
                .load(fullUrl)
                .into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return lista == null ? 0 : lista.size();
    }

    public class ViewHolderInmuebles extends RecyclerView.ViewHolder{
        TextView direccion, valor;
        ImageView foto;
        public ViewHolderInmuebles(@NonNull View itemView) {
            super(itemView);

            direccion = itemView.findViewById(R.id.tvDireccion);
            valor = itemView.findViewById(R.id.tvValor);
            foto = itemView.findViewById(R.id.imgInmueble);
        }
    }
}
