package com.example.inmobiliaria_movil.inmobiliariaApp.ui.inmuebles.InmuebleDetalle;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.databinding.FragmentDetalleInmuebleBinding;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Inmueble;
import com.google.android.material.snackbar.Snackbar;

public class DetalleInmuebleFragment extends Fragment {

    private DetalleInmuebleViewModel vm;
    private FragmentDetalleInmuebleBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleInmuebleViewModel.class);
        binding = FragmentDetalleInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Inmueble inmueble = (Inmueble) getArguments().getSerializable("inmueble");

        vm.cargarInmueble(inmueble);

        vm.getInmueble().observe(getViewLifecycleOwner(), inmueble1 -> {

            binding.tvCodigo.setText(String.valueOf(inmueble1.getIdInmueble()));
            binding.tvAmbientes.setText(String.valueOf(inmueble1.getAmbientes()));
            binding.tvDireccion.setText(inmueble1.getDireccion());
            binding.tvPrecio.setText(String.valueOf(inmueble1.getValor()));
            binding.tvTipo.setText(inmueble1.getTipo());
            binding.tvUso.setText(inmueble1.getUso());
            binding.cbDisponible.setChecked(inmueble1.isDisponible());

            String imageUrl = inmueble1.getImagen().replace("\\", "/");
            String fullUrl = "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/" + imageUrl;

            Glide.with(getContext())
                    .load(fullUrl)
                    .into(binding.imgInmuebleDetalle);


            binding.cbDisponible.setOnCheckedChangeListener((buttonView, isChecked) -> {
                Log.d("salida", "Llamada a el cambiar estado " + String.valueOf(isChecked) );
                vm.cambiarEstado(inmueble1);
            });

        });

        vm.getMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            Snackbar.make(binding.getRoot(), mensaje, Snackbar.LENGTH_SHORT).show();
        });

        return root;
    }

}