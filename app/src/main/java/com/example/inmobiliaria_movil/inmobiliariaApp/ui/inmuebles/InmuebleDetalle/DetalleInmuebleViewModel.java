package com.example.inmobiliaria_movil.inmobiliariaApp.ui.inmuebles.InmuebleDetalle;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_movil.inmobiliariaApp.lib.ApiCLient;
import com.example.inmobiliaria_movil.inmobiliariaApp.lib.Services;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Inmueble;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.InmuebleRequest;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel {


    MutableLiveData<Inmueble> mInmueble = new MutableLiveData<>();
    MutableLiveData<String> mensaje = new MutableLiveData<>();


    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getInmueble (){
        return mInmueble;
    }

    public void cargarInmueble(Inmueble inmueble){
        mInmueble.setValue(inmueble);
    }

    public LiveData<String> getMensaje() {
        return mensaje;
    }


    public void cambiarEstado(boolean disponible) {

        InmuebleRequest inmuebleActualizado = new InmuebleRequest(mInmueble.getValue().getIdInmueble(), disponible);
        //PREGUNTAR, tuve que hacer un modelo con el tipo de objeto que recibe la request porque GSON no me esta detectando que los otros campos son nulos.

        String token = Services.leerToken(getApplication());
        ApiCLient.inmobiliariaService service = ApiCLient.getService();
        Call<Inmueble> call = service.actualizarInmueble("Bearer " + token, inmuebleActualizado);

        call.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()){
                    mensaje.postValue("Estado actualizado");
                }else{
                    mensaje.postValue("Error al actualizar estado " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                mensaje.postValue("Error al contactar al servidor");
            }
        });
    }



}