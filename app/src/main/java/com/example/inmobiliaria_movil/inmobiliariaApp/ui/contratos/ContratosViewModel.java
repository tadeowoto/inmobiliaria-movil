package com.example.inmobiliaria_movil.inmobiliariaApp.ui.contratos;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_movil.inmobiliariaApp.lib.ApiCLient;
import com.example.inmobiliaria_movil.inmobiliariaApp.lib.Services;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Inmueble;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> inmuebles = new MutableLiveData<>();

    public ContratosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getInmuebles() {
        return inmuebles;
    }



    public void cargarContratos(){
        String token = Services.leerToken(getApplication());

        ApiCLient.inmobiliariaService service = ApiCLient.getService();
        Call<List<Inmueble>> call = service.obtenerInmueblesConContratosVigentes("Bearer " + token);

        call.enqueue( new Callback<List<Inmueble>>(){
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {

                if(response.isSuccessful()){
                    inmuebles.postValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText( getApplication(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




}