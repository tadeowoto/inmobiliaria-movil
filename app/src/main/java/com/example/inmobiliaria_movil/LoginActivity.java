package com.example.inmobiliaria_movil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliaria_movil.databinding.ActivityLoginBinding;
import com.example.inmobiliaria_movil.inmobiliariaApp.MenuActivity;

public class LoginActivity extends AppCompatActivity {


    private LoginActivityViewModel vm;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLogin.setOnClickListener(v -> {
            String usuario = binding.etUsuario.getText().toString();
            String password = binding.etPassword.getText().toString();
            vm.validarUsuario(usuario, password);
        });

        vm.getErrorNombre().observe(this, error -> {
            binding.tvErrorUsuario.setText(error);
            binding.tvErrorUsuario.setVisibility((View.VISIBLE));
        });

        vm.getErrorPassword().observe(this, error -> {
            binding.tvErrorPassword.setText(error);
            binding.tvErrorPassword.setVisibility(View.VISIBLE);
        });
        vm.getEstaCargando().observe(this, estaCargando -> {
            binding.loader.setVisibility(estaCargando ? View.VISIBLE : View.GONE);
        });


        vm.getLoginExitoso().observe(this, loginExitoso -> {
            //inicializar intent para llevar al menu
            Toast.makeText(this, "Login correcto", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        });

        vm.activarLecturas();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}