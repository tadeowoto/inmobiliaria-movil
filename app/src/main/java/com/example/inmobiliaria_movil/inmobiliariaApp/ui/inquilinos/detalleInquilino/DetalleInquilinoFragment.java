package com.example.inmobiliaria_movil.inmobiliariaApp.ui.inquilinos.detalleInquilino;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.databinding.FragmentDetalleInquilinoBinding;

public class DetalleInquilinoFragment extends Fragment {

    private DetalleInquilinoViewModel vm;
    private FragmentDetalleInquilinoBinding binding;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleInquilinoViewModel.class);

        binding = FragmentDetalleInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        int idInmueble = getArguments().getInt("idInmueble");
        vm.cargarInquilino(idInmueble);

        vm.getInquilino().observe(getViewLifecycleOwner(), inquilino -> {

            binding.tvApellido.setText("Apellido: " + inquilino.getApellido());
            binding.tvNombre.setText("Nombre: " +inquilino.getNombre());
            binding.tvDni.setText("DNI:  " +inquilino.getDni());
            binding.tvEmail.setText("Email: " +inquilino.getEmail());
            binding.tvTelefono.setText("Telefono: " +inquilino.getTelefono());


        });

        return root;

    }



}