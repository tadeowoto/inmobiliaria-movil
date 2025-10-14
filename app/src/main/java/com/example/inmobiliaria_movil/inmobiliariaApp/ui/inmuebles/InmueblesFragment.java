package com.example.inmobiliaria_movil.inmobiliariaApp.ui.inmuebles;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.databinding.FragmentInmueblesBinding;
import com.example.inmobiliaria_movil.databinding.FragmentPerfilBinding;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.InmuebleAdapter;

public class InmueblesFragment extends Fragment {

    private InmueblesViewModel vm;
    private FragmentInmueblesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmueblesViewModel.class);
        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        vm.getListado().observe(getViewLifecycleOwner(), listado -> {
            InmuebleAdapter ia = new InmuebleAdapter(listado, getContext(), getLayoutInflater());
            GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
            binding.lista.setLayoutManager(glm);
            binding.lista.setAdapter(ia);
        });

        vm.cargarInmuebles();

        return root;

    }


}