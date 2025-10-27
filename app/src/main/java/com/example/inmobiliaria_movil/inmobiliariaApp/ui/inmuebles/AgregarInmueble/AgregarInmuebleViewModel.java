package com.example.inmobiliaria_movil.inmobiliariaApp.ui.inmuebles.AgregarInmueble;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_movil.inmobiliariaApp.lib.ApiCLient;
import com.example.inmobiliaria_movil.inmobiliariaApp.lib.Services;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Inmueble;

public class AgregarInmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<String> mErrorDireccion = new MutableLiveData<>();
    private MutableLiveData<String> mErrorAmbientes = new MutableLiveData<>();
    private MutableLiveData<String> mErrorUso = new MutableLiveData<>();
    private MutableLiveData<String> mErrorTipo = new MutableLiveData<>();
    private MutableLiveData<String> mErrorSuperficie = new MutableLiveData<>();
    private MutableLiveData<String> mErrorValor = new MutableLiveData<>();
    private MutableLiveData<Boolean> estaCargando = new MutableLiveData<>();
    private MutableLiveData<String> mErrorLatitud = new MutableLiveData<>();
    private MutableLiveData<String> mErrorLongitud = new MutableLiveData<>();





    public AgregarInmuebleViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getErrorDireccion() {
        return mErrorDireccion;
    }
    public LiveData<String> getErrorAmbientes() {
        return mErrorAmbientes;
    }
    public LiveData<String> getErrorUso() {
        return mErrorUso;
    }
    public LiveData<String> getErrorTipo() {
        return mErrorTipo;
    }
    public LiveData<String> getErrorSuperficie() {
        return mErrorSuperficie;
    }
    public LiveData<String> getErrorValor() {
        return mErrorValor;
    }
    public LiveData<String> getErrorLatitud() {
        return mErrorLatitud;
    }
    public LiveData<String> getErrorLongitud() {
        return mErrorLongitud;
    }
    public LiveData<Boolean> getEstaCargando() {
        return estaCargando;
    }









    public boolean validarCampos(String direccion, int ambientes, String uso, String tipo, int superficie, Double valor, Double lat, Double lon){

        boolean valido = true;


        if(direccion.isEmpty()){
            mErrorDireccion.postValue("La dirección es requerida");
            valido = false;
        }
        if(ambientes == 0){
            mErrorAmbientes.postValue("Los ambientes son requeridos");
            valido = false;
        } else if (ambientes < 0){
            mErrorAmbientes.postValue("Debe ser mayor a 0");
            valido = false;
        }
        if(uso.isEmpty()){
            mErrorUso.postValue("El uso del inmueble es requerido");
            valido = false;
        }
        if(tipo.isEmpty()){
            mErrorTipo.postValue("El tipo de inmueble es requerido");
            valido = false;
        }
        if(superficie == 0){
            mErrorSuperficie.postValue("La superficie es requerida");
            valido = false;
        }
        if(valor == 0){
            mErrorValor.postValue("El valor del inmueble es requerido");
            valido = false;
        } else if (valor < 0){
            mErrorValor.postValue("Debe ser mayor a 0");
            valido = false;
        }
        if(lat == 0){
            mErrorLatitud.postValue("La latitud es requerida");
            valido = false;
        }
        if(lon == 0){
            mErrorLongitud.postValue("La longitud es requerida");
            valido = false;
        }


        return valido;
    }


    public void AgregarInmueble(String direccion, String ambientes, String uso, String tipo, String superficie, String valor, String lat, String lon){
        Integer amb = Integer.parseInt(ambientes);
        Integer sup = Integer.parseInt(superficie);
        Double val = Double.parseDouble(valor);
        Double latitud = Double.parseDouble(lat);
        Double longitud = Double.parseDouble(lon);


        boolean valido = validarCampos(direccion, amb, uso, tipo, sup, val, latitud, longitud);

        if(valido){
            String token = Services.leerToken(getApplication());
            ApiCLient.inmobiliariaService service = ApiCLient.getService();

        }



    }
}