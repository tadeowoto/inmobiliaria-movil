package com.example.inmobiliaria_movil.inmobiliariaApp.ui.perfil;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {

    private PerfilViewModel vm;
    private FragmentPerfilBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getNombre().observe(getViewLifecycleOwner(), nombre -> {
            binding.etNombre.setText(nombre);
        });
        vm.getApellido().observe(getViewLifecycleOwner(), apellido -> {
            binding.etApellido.setText(apellido);
        });
        vm.getDni().observe(getViewLifecycleOwner(), dni -> {
            binding.etDni.setText(dni);
        });
        vm.mEmail.observe(getViewLifecycleOwner(), email -> {
            binding.etMail.setText(email);
        });
        vm.getPassword().observe(getViewLifecycleOwner(), password -> {
            binding.etPassword.setText(password);
        });
        vm.getTelefono().observe(getViewLifecycleOwner(), telefono -> {
            binding.etTelefono.setText(telefono);
        });

        Log.d("salida", "onCreateView: llamando al llenar formulario");
        vm.llenarFormulario();

        binding.btnEditarGuardar.setOnClickListener(v -> {
            //todos los campos deberian estar vacios y ahora activarlos
            binding.btnEditarGuardar.setText("Guardar");
            binding.etNombre.setEnabled(true);
            binding.etApellido.setEnabled(true);
            binding.etDni.setEnabled(true);
            binding.etMail.setEnabled(true);
            binding.etPassword.setEnabled(true);
            binding.etTelefono.setEnabled(true);
        });

        return root;


    }



}