package com.example.inmobiliaria_movil.inmobiliariaApp.ui.inmuebles.AgregarInmueble;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_movil.inmobiliariaApp.lib.ApiCLient;
import com.example.inmobiliaria_movil.inmobiliariaApp.lib.Services;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Inmueble;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private MutableLiveData<Uri> imagen = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<String> mValido = new MutableLiveData<>();




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
    public LiveData<Uri> getImagen() {
        return imagen;
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
    public LiveData<String> getError() {
        return mError;
    }
    public LiveData<String> getValido() {
        return mValido;
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
        if(imagen == null){
            mError.postValue("La imagen es requerida");
            valido = false;
        }


        return valido;
    }



    public void AgregarInmueble(String direccion, String ambientes, String uso, String tipo, String superficie, String valor, String lat, String lon){

        try {
            Integer amb = Integer.parseInt(ambientes);
            Integer sup = Integer.parseInt(superficie);
            Double val = Double.parseDouble(valor);
            Double latitud = Double.parseDouble(lat);
            Double longitud = Double.parseDouble(lon);
            boolean valido = validarCampos(direccion, amb, uso, tipo, sup, val, latitud, longitud);

            if(valido){

                Inmueble inmueble = new Inmueble(direccion, uso, tipo, amb, sup, latitud, val, true, longitud);
                byte[] imagen = transformarImagen();
                String inmuebleJson = new Gson().toJson(inmueble);
                RequestBody inmuebleBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), inmuebleJson);
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imagen);
                MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", "imagen.jpg", requestFile);

                String token = Services.leerToken(getApplication());
                ApiCLient.inmobiliariaService service = ApiCLient.getService();
                Call<Inmueble> call = service.CargarInmueble("Bearer " + token, imagenPart, inmuebleBody);
                call.enqueue(new Callback<Inmueble>() {
                    @Override
                    public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                        mValido.postValue("Inmueble agregado");
                    }

                    @Override
                    public void onFailure(Call<Inmueble> call, Throwable t) {
                        mError.postValue("Error al agregar el inmueble");
                    }
                });



            }

        }catch (Exception e){
            Toast.makeText(getApplication(), "Error al agregar el inmueble", Toast.LENGTH_SHORT).show();
        }







    }

    private byte[] transformarImagen() {
        try {
            Uri uri = imagen.getValue();
            InputStream inputStream = getApplication().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException er) {
            Toast.makeText(getApplication(), "No ha seleccinado una foto", Toast.LENGTH_SHORT).show();
            return new byte[]{};
        }
    }

    public void recibirFoto(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            Uri uri = data.getData();
            Log.d("salada", uri.toString());
            imagen.setValue(uri);
        }
    }
}