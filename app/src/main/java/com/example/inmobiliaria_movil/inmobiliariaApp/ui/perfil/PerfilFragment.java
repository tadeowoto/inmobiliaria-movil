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
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Propietario;

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
        vm.getTelefono().observe(getViewLifecycleOwner(), telefono -> {
            binding.etTelefono.setText(telefono);
        });


        //cambia el texto del boton
        vm.getTextoBoton().observe(getViewLifecycleOwner(), texto -> {
            binding.btnEditarGuardar.setText(texto);
        });

        //habilita los campos
        vm.getHabilitarCampos().observe(getViewLifecycleOwner(), habilitar -> {
            binding.etNombre.setEnabled(habilitar);
            binding.etApellido.setEnabled(habilitar);
            binding.etDni.setEnabled(habilitar);
            binding.etMail.setEnabled(habilitar);
            binding.etTelefono.setEnabled(habilitar);
        });


        //guarda los cambios
        vm.getGuardarPropietario().observe(getViewLifecycleOwner(), guardar -> {
                String nombre = binding.etNombre.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String dni = binding.etDni.getText().toString();
                String telefono = binding.etTelefono.getText().toString();
                String email = binding.etMail.getText().toString();
                vm.guardarPropietario(nombre, apellido, dni, telefono, email);
        });

        binding.btnEditarGuardar.setOnClickListener(v -> {
            vm.procesarBoton(binding.btnEditarGuardar.getText().toString());
        });

        vm.getErrorNombre().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorNombre.setText(error);
            binding.tvErrorNombre.setVisibility(View.VISIBLE);
        });
        vm.getErrorApellido().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorApellido.setText(error);
            binding.tvErrorApellido.setVisibility(View.VISIBLE);
        });
        vm.getErrorDni().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorDni.setText(error);
            binding.tvErrorDni.setVisibility(View.VISIBLE);
        });
        vm.getErrorTelefono().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorTelefono.setText(error);
            binding.tvErrorTelefono.setVisibility(View.VISIBLE);
        });
        vm.getErrorEmail().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorMail.setText(error);
            binding.tvErrorMail.setVisibility(View.VISIBLE);
        });








        vm.llenarFormulario();
        return root;

    }
}