package com.example.inmobiliaria_movil.inmobiliariaApp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliaria_movil.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolderPagos> {

    private List<Pago> lista;
    private Context context;
    private LayoutInflater li;



    public PagoAdapter(List<Pago> lista, Context context, LayoutInflater li) {
        this.lista = lista;
        this.context = context;
        this.li = li;
    }



    @NonNull
    @Override
    public ViewHolderPagos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_pago, parent, false);
        return new ViewHolderPagos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPagos holder, int position) {
        Pago pago = lista.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));

        holder.titulo.setText("Pago #" + pago.getIdPago());
        holder.monto.setText(currencyFormat.format(pago.getMonto()));
        holder.detalle.setText(pago.getDetalle() != null ? pago.getDetalle() : "Sin detalle");

        String fechaFormateada = pago.getFechaPago() != null
                ? dateFormat.format(pago.getFechaPago())
                : "Sin fecha";
        holder.fecha.setText("Fecha: " + fechaFormateada);

        String estadoTexto = pago.isEstado() ? "Pagado" : "Pendiente";
        holder.estado.setText("Estado: " + estadoTexto);
    }

    @Override
    public int getItemCount() {
        return lista == null ? 0 : lista.size();
    }

    public class ViewHolderPagos extends RecyclerView.ViewHolder{

        TextView monto;
        TextView titulo;
        TextView detalle;
        TextView fecha;

        TextView estado;


        public ViewHolderPagos(@NonNull View itemView) {
            super(itemView);

            monto = itemView.findViewById(R.id.tvMontoPago);
            titulo = itemView.findViewById(R.id.tvTituloPago);
            detalle = itemView.findViewById(R.id.tvDetallePago);
            fecha = itemView.findViewById(R.id.tvFechaPago);
            estado = itemView.findViewById(R.id.tvEstadoPago);


        }
    }


}
