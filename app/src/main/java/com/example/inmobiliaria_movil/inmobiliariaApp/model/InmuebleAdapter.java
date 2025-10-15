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
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolderInmuebles> {

    private List<Inmueble> lista;
    private Context context;
    private LayoutInflater li;

    private OnInmuebleClickListener listener;

    public InmuebleAdapter(List<Inmueble> lista, Context context, LayoutInflater li, OnInmuebleClickListener listener) {
        this.lista = lista;
        this.context = context;
        this.li = li;
        this.listener = listener;
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
        holder.btnVer.setOnClickListener(v -> {
            if ( listener != null) {
                listener.onInmuebleClick(ia);
            }
        });

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
        MaterialButton btnVer;
        public ViewHolderInmuebles(@NonNull View itemView) {
            super(itemView);

            direccion = itemView.findViewById(R.id.tvDireccion);
            valor = itemView.findViewById(R.id.tvValor);
            foto = itemView.findViewById(R.id.imgInmueble);
            btnVer = itemView.findViewById(R.id.btnVerDetalle);
        }
    }

    public interface OnInmuebleClickListener {
        void onInmuebleClick(Inmueble inmueble);
    }
}



