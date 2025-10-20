package com.example.inmobiliaria_movil.inmobiliariaApp.ui.inquilinos.detalleInquilino;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_movil.inmobiliariaApp.lib.ApiCLient;
import com.example.inmobiliaria_movil.inmobiliariaApp.lib.Services;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Contrato;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Inquilino;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInquilinoViewModel extends AndroidViewModel {

    MutableLiveData<Inquilino> inquilino = new MutableLiveData<>();

    public DetalleInquilinoViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<Inquilino> getInquilino(){
        return inquilino;
    }

    public void cargarInquilino(int idInmueble){
        // tengo que llamar a la api para que me traiga el contrato de ese inmueble y de ahi sacar el inquilino asociado
        String token = Services.leerToken(getApplication());

        ApiCLient.inmobiliariaService service = ApiCLient.getService();
        Call<Contrato> call = service.obtenerContratoPorInmueble("Bearer " + token, idInmueble);

        call.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                //como esto devuelve el contrato, puedo hacer un getInquilino
                inquilino.postValue(response.body().getInquilino());
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {

            }
        });


    }



}