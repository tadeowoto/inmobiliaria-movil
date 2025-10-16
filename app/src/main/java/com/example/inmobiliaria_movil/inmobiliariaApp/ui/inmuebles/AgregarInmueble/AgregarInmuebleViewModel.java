package com.example.inmobiliaria_movil.inmobiliariaApp.ui.inmuebles.AgregarInmueble;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AgregarInmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<String> mErrorDireccion = new MutableLiveData<>();
    private MutableLiveData<String> mErrorAmbientes = new MutableLiveData<>();
    private MutableLiveData<String> mErrorUso = new MutableLiveData<>();
    private MutableLiveData<String> mErrorTipo = new MutableLiveData<>();
    private MutableLiveData<String> mErrorSuperficie = new MutableLiveData<>();
    private MutableLiveData<String> mErrorValor = new MutableLiveData<>();
    private MutableLiveData<Boolean> estaCargando = new MutableLiveData<>();



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
    public LiveData<Boolean> getEstaCargando() {
        return estaCargando;
    }









    public boolean validarCampos(String direccion, int ambientes, String uso, String tipo, Double superficie, Double valor){

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

        return valido;
    }


    public void AgregarInmueble(String direccion, String ambientes, String uso, String tipo, String superficie, String valor){
        Integer amb = Integer.parseInt(ambientes);
        Double sup = Double.parseDouble(superficie);
        Double val = Double.parseDouble(valor);

        boolean valido = validarCampos(direccion, amb, uso, tipo, sup, val);

        if(valido){
            //llamar a la api de agregar inmueble
        }



    }
}