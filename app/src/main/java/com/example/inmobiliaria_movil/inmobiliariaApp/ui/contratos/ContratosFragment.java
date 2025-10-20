package com.example.inmobiliaria_movil.inmobiliariaApp.ui.contratos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_movil.R;
import com.example.inmobiliaria_movil.databinding.FragmentContratosBinding;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.InmuebleContratoAdapter;

public class ContratosFragment extends Fragment {

    private ContratosViewModel vm;
    private FragmentContratosBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratosViewModel.class);

        binding = FragmentContratosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        vm.getInmuebles().observe(getViewLifecycleOwner(), inmuebles -> {

            InmuebleContratoAdapter ia = new InmuebleContratoAdapter(inmuebles, getContext(), inmueble -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("idInmueble", inmueble.getIdInmueble());
                Navigation.findNavController( getActivity(), R.id.nav_host_fragment_content_menu).navigate(R.id.action_contratosFragment_to_detalleContratoFragment, bundle);
            });

            GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
            binding.lista.setLayoutManager(glm);
            binding.lista.setAdapter(ia);


        });

        return root;
    }

}