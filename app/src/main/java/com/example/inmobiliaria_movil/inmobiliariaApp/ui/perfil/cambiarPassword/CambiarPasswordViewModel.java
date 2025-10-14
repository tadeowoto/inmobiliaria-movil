package com.example.inmobiliaria_movil.inmobiliariaApp.ui.perfil.cambiarPassword;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_movil.inmobiliariaApp.lib.ApiCLient;
import com.example.inmobiliaria_movil.inmobiliariaApp.lib.Services;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarPasswordViewModel extends AndroidViewModel {

    private Context context;

    private MutableLiveData<String> mErrorContraseniaActual = new MutableLiveData<>();
    private MutableLiveData<String> mErrorNuevaContrasenia = new MutableLiveData<>();
    private MutableLiveData<String> mErrorRepetirNuevaContrasenia = new MutableLiveData<>();

    private MutableLiveData<Boolean> mEstaCargando = new MutableLiveData<>();


    public CambiarPasswordViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();

    }

    public LiveData<String> getErrorContraseniaActual() {
        return mErrorContraseniaActual;
    }
    public LiveData<String> getErrorNuevaContrasenia() {
        return mErrorNuevaContrasenia;
    }

    public LiveData<String> getErrorRepetirNuevaContrasenia() {
        return mErrorRepetirNuevaContrasenia;
    }

    public LiveData<Boolean> getEstaCargando() {
        return mEstaCargando;
    }



    public boolean validarPasswords(String contraseniaActual, String nuevaContrasenia, String repeticionNuevaContrasenia) {
        boolean valido = true;


        if (contraseniaActual == null || contraseniaActual.trim().isEmpty()) {
            mErrorContraseniaActual.setValue("Ingrese la contraseña actual");
            valido = false;

        }
        if (nuevaContrasenia == null || nuevaContrasenia.trim().isEmpty()) {
            mErrorNuevaContrasenia.setValue("Ingrese una nueva contraseña");
            valido = false;
        }
        if (repeticionNuevaContrasenia == null || repeticionNuevaContrasenia.trim().isEmpty()) {
            mErrorRepetirNuevaContrasenia.setValue("Ingrese nuevamente la nueva contraseña");
            valido = false;
        }

        //validar que las contraseñas sean iguales
        if (nuevaContrasenia.equals(repeticionNuevaContrasenia) == false) {
            mErrorRepetirNuevaContrasenia.setValue("Las contraseñas no coinciden");
            valido = false;
        }

        return valido;


    }


    public void cambiarPassword(String contraseniaActual, String nuevaContrasenia, String repeticionNuevaContrasenia) {
        boolean valido = validarPasswords(contraseniaActual, nuevaContrasenia, repeticionNuevaContrasenia);

        if (valido) {
            mEstaCargando.setValue(true);

            String token = Services.leerToken(context);
            ApiCLient.inmobiliariaService service = ApiCLient.getService();
            Call<JsonObject> call = service.cambiarPassword("Bearer " + token, contraseniaActual, nuevaContrasenia);

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        mEstaCargando.setValue(false);
                        Toast.makeText(context, "Contraseña cambiada correctamente", Toast.LENGTH_SHORT).show();
                    } else if(response.code() == 400){
                        mEstaCargando.setValue(false);
                        mErrorContraseniaActual.setValue("Contraseña actual incorrecta");
                    } else {
                        mEstaCargando.setValue(false);
                        Toast.makeText(context, "Error al cambiar la contraseña: " + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    mEstaCargando.setValue(false);
                    Toast.makeText(context, "Error en el servidor: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }






    }


}