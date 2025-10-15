package com.example.inmobiliaria_movil.inmobiliariaApp.ui.inmuebles.InmuebleDetalle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_movil.inmobiliariaApp.model.Inmueble;

public class DetalleInmuebleViewModel extends AndroidViewModel {


    MutableLiveData<Inmueble> mInmueble = new MutableLiveData<>();


    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getInmueble (){
        return mInmueble;
    }

    public void cargarInmueble(Inmueble inmueble){
        mInmueble.setValue(inmueble);
    }
}