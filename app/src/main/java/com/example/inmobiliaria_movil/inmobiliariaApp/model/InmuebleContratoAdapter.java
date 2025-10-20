package com.example.inmobiliaria_movil.inmobiliariaApp.model;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Inmueble;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class InmuebleContratoAdapter extends RecyclerView.Adapter<InmuebleContratoAdapter.ViewHolderInmueblesContrato> {

    private List<Inmueble> lista;
    private Context context;
    private OnInmuebleClickListener listener;

    public InmuebleContratoAdapter(List<Inmueble> lista, Context context, OnInmuebleClickListener listener) {
        this.lista = lista;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderInmueblesContrato onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contrato, parent, false);
        return new ViewHolderInmueblesContrato(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInmueblesContrato holder, int position) {
        Inmueble inmueble = lista.get(position);

        holder.direccion.setText(inmueble.getDireccion());

        String imageUrl = inmueble.getImagen().replace("\\", "/");
        String fullUrl = "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/" + imageUrl;

        Glide.with(context)
                .load(fullUrl)
                .into(holder.foto);

        holder.btnVerContrato.setOnClickListener(v -> {
            if (listener != null) listener.onInmuebleClick(inmueble);
        });
    }

    @Override
    public int getItemCount() {
        return lista == null ? 0 : lista.size();
    }


    public static class ViewHolderInmueblesContrato extends RecyclerView.ViewHolder {
        TextView direccion;
        ImageView foto;
        MaterialButton btnVerContrato;

        @SuppressLint("WrongViewCast")
        public ViewHolderInmueblesContrato(@NonNull View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.tvDireccion);
            foto = itemView.findViewById(R.id.imgInmueble);
            btnVerContrato = itemView.findViewById(R.id.btnVerContrato);
        }
    }

    public interface OnInmuebleClickListener {
        void onInmuebleClick(Inmueble inmueble);
    }
}
