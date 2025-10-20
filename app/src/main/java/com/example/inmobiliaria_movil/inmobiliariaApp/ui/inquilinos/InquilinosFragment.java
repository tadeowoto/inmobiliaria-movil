package com.example.inmobiliaria_movil.inmobiliariaApp.ui.inquilinos;

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
import com.example.inmobiliaria_movil.databinding.FragmentInquilinosBinding;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.InmuebleContratoAdapter;

public class InquilinosFragment extends Fragment {

    private InquilinosViewModel vm;
    private FragmentInquilinosBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinosViewModel.class);

        binding = FragmentInquilinosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        vm.getLista().observe(getViewLifecycleOwner(), lista -> {

            InmuebleContratoAdapter ia = new InmuebleContratoAdapter(lista, getContext(), inmueble -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("idInmueble", inmueble.getIdInmueble());
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_menu).navigate(R.id.action_inquilinosFragment_to_detalleInquilinoFragment, bundle);
            });

            GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
            binding.lista.setLayoutManager(glm);
            binding.lista.setAdapter(ia);

        });


        vm.cargarInmueblesContratados();

        return root;
    }


}