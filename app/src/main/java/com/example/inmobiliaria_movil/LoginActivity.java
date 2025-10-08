package com.example.inmobiliaria_movil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

        vm.getLoginExitoso().observe(this, loginExitoso -> {
            //inicializar intent para llevar al menu
            Toast.makeText(this, "Login correcto", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        });



    }
}