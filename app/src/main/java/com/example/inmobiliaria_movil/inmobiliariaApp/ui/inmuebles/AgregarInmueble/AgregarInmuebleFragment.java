package com.example.inmobiliaria_movil.inmobiliariaApp.ui.inmuebles.AgregarInmueble;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.databinding.FragmentAgregarInmuebleBinding;

public class AgregarInmuebleFragment extends Fragment {

    private AgregarInmuebleViewModel vm;
    private FragmentAgregarInmuebleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AgregarInmuebleViewModel.class);
        binding = FragmentAgregarInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.btnGuardarInmueble.setOnClickListener(v -> {
            String direccion = binding.etDireccion.getText().toString();
            String ambientes = binding.etAmbientes.getText().toString();
            String uso = binding.etUso.getText().toString();
            String tipo = binding.etTipo.getText().toString();
            String superficie = binding.etSuperficie.getText().toString();
            String valor = binding.etValor.getText().toString();
            String lat = binding.etLatitud.getText().toString();
            String lon = binding.etLongitud.getText().toString();

            vm.AgregarInmueble(direccion, ambientes, uso, tipo, superficie, valor, lat, lon );



        });



        return root;


    }



}