package com.example.inmobiliaria_movil.inmobiliariaApp.ui.contratos.pagosContrato;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.databinding.FragmentPagosContratoBinding;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.PagoAdapter;

public class PagosContratoFragment extends Fragment {

    private PagosContratoViewModel vm;
    private FragmentPagosContratoBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PagosContratoViewModel.class);
        binding = FragmentPagosContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        int idContrato = getArguments().getInt("idContrato");
        vm.cargarPagos(idContrato);

        vm.getLista().observe( getViewLifecycleOwner(), lista -> {

            PagoAdapter pa = new PagoAdapter(lista, getContext(), getLayoutInflater());

            GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
            binding.lista.setLayoutManager(glm);
            binding.lista.setAdapter(pa);


        });

        return root;
    }



}