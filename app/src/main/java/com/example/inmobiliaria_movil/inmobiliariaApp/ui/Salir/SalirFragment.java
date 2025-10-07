package com.example.inmobiliaria_movil.inmobiliariaApp.ui.Salir;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.databinding.FragmentSalirBinding;

public class SalirFragment extends Fragment {

    private FragmentSalirBinding binding;
    private SalirViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {SalirViewModel slideshowViewModel = new ViewModelProvider(this).get(SalirViewModel.class);

        binding = FragmentSalirBinding.inflate(inflater, container, false);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(SalirViewModel.class);
        View root = binding.getRoot();

        mostrarDialogoSalir();


        return root;
    }

    private void mostrarDialogoSalir() {
        new AlertDialog.Builder(getContext())
                .setTitle("Salir de la aplicación")
                .setMessage("¿Estás seguro de que deseas salir?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    requireActivity().finishAffinity();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    requireActivity().onBackPressed();
                })
                .show();
    }

}