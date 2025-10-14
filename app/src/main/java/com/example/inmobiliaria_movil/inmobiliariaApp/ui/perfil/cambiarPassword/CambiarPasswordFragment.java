package com.example.inmobiliaria_movil.inmobiliariaApp.ui.perfil.cambiarPassword;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.databinding.FragmentCambiarPasswordBinding;

public class CambiarPasswordFragment extends Fragment {

    private CambiarPasswordViewModel vm;
    private FragmentCambiarPasswordBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(CambiarPasswordViewModel.class);
        binding = FragmentCambiarPasswordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.btnGuardar.setOnClickListener(v -> {
            String contraseniaActual = binding.etContraseniaActual.getText().toString();
            String nuevaContrasenia = binding.etNuevaContrasenia.getText().toString();
            String repeticionNuevaContrasenia = binding.etRepetirContrasenia.getText().toString();

            vm.cambiarPassword(contraseniaActual, nuevaContrasenia, repeticionNuevaContrasenia);

        });

        vm.getErrorContraseniaActual().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorContraseniaActual.setText(error);
            binding.tvErrorContraseniaActual.setVisibility(View.VISIBLE);
        });
        vm.getErrorNuevaContrasenia().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorNuevaContrasenia.setText(error);
            binding.tvErrorNuevaContrasenia.setVisibility(View.VISIBLE);
        });
        vm.getErrorRepetirNuevaContrasenia().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorRepetirContrasenia.setText(error);
            binding.tvErrorRepetirContrasenia.setVisibility(View.VISIBLE);
        });



        return  root;
    }


}