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


    public void cambiarEstado(Inmueble i) {
        String token = Services.leerToken(getApplication());
        ApiCLient.inmobiliariaService service = ApiCLient.getService();

        Inmueble inmuebleActualizado = new Inmueble();
        inmuebleActualizado.setIdInmueble(i.getIdInmueble());
        inmuebleActualizado.setDireccion(i.getDireccion());
        inmuebleActualizado.setValor(i.getValor());
        inmuebleActualizado.setIdPropietario(i.getIdPropietario());
        inmuebleActualizado.setUso(i.getUso());
        inmuebleActualizado.setTipo(i.getTipo());
        inmuebleActualizado.setAmbientes(i.getAmbientes());
        inmuebleActualizado.setSuperficie((int) i.getSuperficie());
        inmuebleActualizado.setLatitud(i.getLatitud());
        inmuebleActualizado.setLongitud(i.getLongitud());
        inmuebleActualizado.setImagen(i.getImagen());
        inmuebleActualizado.setDisponible(!i.isDisponible());
        inmuebleActualizado.setTieneContratoVigente(i.isTieneContratoVigente());
        Call<Inmueble> call = service.actualizarInmueble("Bearer " + token, inmuebleActualizado);

        call.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    mensaje.postValue("Estado actualizado correctamente");
                } else {
                    try {
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : "sin detalle";
                        Log.e("API_ERROR", "Error al actualizar: " + response.code() + " - " + errorBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mensaje.postValue("Error al actualizar el estado (" + response.code() + ")");
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(getApplication(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}