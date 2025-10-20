package com.example.inmobiliaria_movil.inmobiliariaApp.ui.contratos.detalleContrato;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.databinding.FragmentDetalleContratoBinding;

public class DetalleContratoFragment extends Fragment {

    private DetalleContratoViewModel vm;
    private FragmentDetalleContratoBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleContratoViewModel.class);
        binding = FragmentDetalleContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        int idInmueble = getArguments().getInt("idInmueble");
        vm.cargarContrato(idInmueble);

        return root;

    }



}