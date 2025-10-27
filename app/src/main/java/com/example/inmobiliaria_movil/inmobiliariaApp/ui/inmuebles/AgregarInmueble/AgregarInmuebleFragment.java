package com.example.inmobiliaria_movil.inmobiliariaApp.ui.inmuebles.AgregarInmueble;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.databinding.FragmentAgregarInmuebleBinding;
import com.google.android.material.snackbar.Snackbar;

public class AgregarInmuebleFragment extends Fragment {

    private AgregarInmuebleViewModel vm;
    private FragmentAgregarInmuebleBinding binding;
    private Intent intent;
    private ActivityResultLauncher<Intent> arl;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AgregarInmuebleViewModel.class);
        binding = FragmentAgregarInmuebleBinding.inflate(inflater, container, false);
        abrirGaleria();
        View root = binding.getRoot();

        vm.getErrorDireccion().observe(getViewLifecycleOwner(), error -> {
            binding.tilDireccion.setError(error);
        });
        vm.getErrorAmbientes().observe(getViewLifecycleOwner(), error -> {
            binding.tilAmbientes.setError(error);
        });
        vm.getErrorUso().observe(getViewLifecycleOwner(), error -> {
            binding.tilUso.setError(error);
        });
        vm.getErrorTipo().observe(getViewLifecycleOwner(), error -> {
            binding.tilTipo.setError(error);
        });
        vm.getErrorSuperficie().observe(getViewLifecycleOwner(), error -> {
            binding.tilSuperficie.setError(error);
        });
        vm.getErrorValor().observe(getViewLifecycleOwner(), error -> {
            binding.tilValor.setError(error);
        });
        vm.getErrorLatitud().observe(getViewLifecycleOwner(), error -> {
            binding.tilLatitud.setError(error);
        });
        vm.getErrorLongitud().observe(getViewLifecycleOwner(), error -> {
            binding.tilLongitud.setError(error);
        });

        vm.getImagen().observe(getViewLifecycleOwner(), imagen -> {
            binding.ivNuevaFoto.setImageURI(imagen);
        });

        vm.getError().observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_SHORT).show();
        });
        vm.getValido().observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_SHORT).show();
        });

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

        binding.btnAgregarFoto.setOnClickListener( v ->{
            arl.launch(intent);
        });



        return root;


    }
    private void abrirGaleria() {
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// esto abre la galeria
        arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                vm.recibirFoto(result);

            }
        });
    }
}