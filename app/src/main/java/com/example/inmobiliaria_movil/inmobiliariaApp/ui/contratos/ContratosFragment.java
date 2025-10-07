package com.example.inmobiliaria_movil.inmobiliariaApp.ui.contratos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.databinding.FragmentContratosBinding;

public class ContratosFragment extends Fragment {

    private ContratosViewModel vm;
    private FragmentContratosBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratosViewModel.class);

        binding = FragmentContratosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

}