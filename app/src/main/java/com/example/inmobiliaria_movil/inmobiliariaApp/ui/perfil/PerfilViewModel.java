package com.example.inmobiliaria_movil.inmobiliariaApp.ui.perfil;

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
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Propietario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    MutableLiveData<String> mNombre = new MutableLiveData<>();
    MutableLiveData<String> mApellido = new MutableLiveData<>();
    MutableLiveData<String> mDni = new MutableLiveData<>();
    MutableLiveData<String> mTelefono = new MutableLiveData<>();
    MutableLiveData<String> mEmail = new MutableLiveData<>();
    MutableLiveData<String> mPassword = new MutableLiveData<>();
    MutableLiveData<Boolean> modoEdicion = new MutableLiveData<>();
    private Context context;


    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
    }

    public LiveData<String> getNombre() {
        return mNombre;
    }



    public LiveData<String> getApellido() {
        return mApellido;
    }

    public LiveData<String> getDni() {
        return mDni;
    }

    public LiveData<String> getTelefono() {
        return mTelefono;
    }

    public LiveData<String> getEmail() {
        return mEmail;
    }

    public LiveData<String> getPassword() {
        return mPassword;
    }

    public LiveData<Boolean> getModoEdicion() {
        return modoEdicion;
    }




    public void llenarFormulario() {
        String token = Services.leerToken(context);

        ApiCLient.inmobiliariaService service = ApiCLient.getService();
        Call<Propietario> call = service.obtenerPropietario("Bearer " + token);
        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()){
                    Propietario propietario = response.body();
                    mNombre.postValue(propietario.getNombre());
                    mApellido.postValue(propietario.getApellido());
                    mDni.postValue(propietario.getDni());
                    mTelefono.postValue(propietario.getTelefono());
                    mEmail.postValue(propietario.getEmail());
                    mPassword.postValue(propietario.getClave());
                }else{
                    Log.d("salida", "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Error llenando el formulario: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onBotonEditarGuardarClick(Propietario propietarioActual) {
        Boolean editando = modoEdicion.getValue();
        if (editando == null) editando = false;

        if (editando) {
            // Guardar cambios
            actualizarPerfil(propietarioActual);
            modoEdicion.setValue(false);
        } else {
            // Activar modo edición
            modoEdicion.setValue(true);
        }
    }

    public void actualizarPerfil(Propietario propietario) {
        String token = Services.leerToken(context);

        ApiCLient.inmobiliariaService service = ApiCLient.getService();
        Call<Propietario> call = service.actualizarPropietario("Bearer " + token, propietario);

        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    Propietario actualizado = response.body();
                    Toast.makeText(context, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error al actualizar (" + response.code() + ")", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.e("salida", "Fallo la llamada: " + t.getMessage());
                Toast.makeText(context, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}