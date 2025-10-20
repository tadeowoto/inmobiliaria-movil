package com.example.inmobiliaria_movil.inmobiliariaApp.ui.inquilinos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_movil.inmobiliariaApp.lib.ApiCLient;
import com.example.inmobiliaria_movil.inmobiliariaApp.lib.Services;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Inmueble;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinosViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> lista = new MutableLiveData<>();

    public InquilinosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getLista(){
        return lista;
    }






    public void cargarInmueblesContratados(){

        String token = Services.leerToken(getApplication());

        ApiCLient.inmobiliariaService service = ApiCLient.getService();
        Call<List<Inmueble>> call = service.obtenerInmueblesConContratosVigentes("Bearer " + token);

        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                lista.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {

            }
        });


    }
}