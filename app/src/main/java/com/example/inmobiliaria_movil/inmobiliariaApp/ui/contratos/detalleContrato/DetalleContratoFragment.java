package com.example.inmobiliaria_movil.inmobiliariaApp.ui.contratos.detalleContrato;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.databinding.FragmentDetalleContratoBinding;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetalleContratoFragment extends Fragment {

    private DetalleContratoViewModel vm;
    private FragmentDetalleContratoBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())
                .create(DetalleContratoViewModel.class);
        binding = FragmentDetalleContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        int idInmueble = getArguments().getInt("idInmueble");
        vm.cargarContrato(idInmueble);

        vm.getContrato().observe(getViewLifecycleOwner(), contrato -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));


            binding.tvDireccionInmueble.setText(contrato.getInmueble().getDireccion());
            binding.tvAmbientesInmueble.setText(String.valueOf(contrato.getInmueble().getAmbientes()));
            binding.tvUsoInmueble.setText(contrato.getInmueble().getUso());
            binding.tvTipoInmueble.setText(contrato.getInmueble().getTipo());
            binding.tvValorInmueble.setText(currencyFormat.format(contrato.getInmueble().getValor()));


            binding.tvNombreInquilino.setText(contrato.getInquilino().getNombre());
            binding.tvDniInquilino.setText(contrato.getInquilino().getDni());
            binding.tvTelefonoInquilino.setText(contrato.getInquilino().getTelefono());
            binding.tvEmailInquilino.setText(contrato.getInquilino().getEmail());


            String fechaInicio = dateFormat.format(contrato.getFechaInicio());
            String fechaFin = dateFormat.format(contrato.getFechaFinalizacion());
            String estado = contrato.isEstado() ? "Activo" : "Inactivo";

            binding.tvFechaInicio.setText(fechaInicio);
            binding.tvFechaFin.setText(fechaFin);
            binding.tvMonto.setText(currencyFormat.format(contrato.getMontoAlquiler()));
            binding.tvEstadoContrato.setText(estado);


            String imageUrl = contrato.getInmueble().getImagen().replace("\\", "/");
            String fullUrl = "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/" + imageUrl;

            Glide.with(getContext())
                    .load(fullUrl)
                    .into(binding.imgInmuebleContrato);

            binding.btnVerPagos.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt("idContrato", contrato.getIdContrato());
                Navigation.findNavController(v).navigate(R.id.action_detalleContratoFragment_to_pagosContratoFragment, bundle);
            });
        });



        return root;
    }
}