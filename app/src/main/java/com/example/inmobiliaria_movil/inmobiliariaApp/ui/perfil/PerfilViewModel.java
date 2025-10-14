package com.example.inmobiliaria_movil.inmobiliariaApp.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_movil.databinding.FragmentPerfilBinding;
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
    MutableLiveData<Boolean> mHabilitarCampos = new MutableLiveData<>();

    Propietario propietarioActual;
    MutableLiveData<String> mTextoBoton = new MutableLiveData<>();

    MutableLiveData<Boolean> mGuardarPropietario = new MutableLiveData<>();

    MutableLiveData<String> mErrorNombre = new MutableLiveData<>();
    MutableLiveData<String> mErrorApellido = new MutableLiveData<>();
    MutableLiveData<String> mErrorDni = new MutableLiveData<>();
    MutableLiveData<String> mErrorTelefono = new MutableLiveData<>();
    MutableLiveData<String> mErrorEmail = new MutableLiveData<>();




    private Context context;


    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
    }

    public LiveData<String> getNombre() {
        return mNombre;
    }

    public LiveData<Boolean> getHabilitarCampos() {
        return mHabilitarCampos;
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

    public LiveData<String> getTextoBoton() {
        return mTextoBoton;
    }

    public LiveData<Boolean> getGuardarPropietario() {
        return mGuardarPropietario;
    }
    public LiveData<String> getEmail() {
        return mEmail;
    }

    public LiveData<String> getErrorNombre() {
        return mErrorNombre;
    }

    public LiveData<String> getErrorApellido() {
        return mErrorApellido;
    }

    public LiveData<String> getErrorDni() {
        return mErrorDni;
    }

    public LiveData<String> getErrorTelefono() {
        return mErrorTelefono;
    }

    public LiveData<String> getErrorEmail() {
        return mErrorEmail;
    }








    public void procesarBoton(String textoBoton){
        if (textoBoton.equals("Editar")){
            mHabilitarCampos.setValue(true);
            mTextoBoton.setValue("Guardar");
        }else{
            mGuardarPropietario.setValue(true);
            mHabilitarCampos.setValue(false);
            mTextoBoton.setValue("Editar");
        }
    }

    public void guardarPropietario(String nombre, String apellido, String dni, String telefono, String email) {
        //validar formulario

        boolean valido = validarFormulario(nombre, apellido, dni, telefono, email);

        if (valido) {
            if (propietarioActual != null) {
                Propietario actualizado = new Propietario(
                        propietarioActual.getIdPropietario(),
                        nombre,
                        apellido,
                        dni,
                        telefono,
                        email,
                        null
                );
                actualizarPerfil(actualizado);
            }
        }else{
            mHabilitarCampos.setValue(true);
        }
    }

    public boolean validarFormulario(String nombre, String apellido, String dni, String telefono, String email) {

        boolean valido = true;

        if (nombre == null || nombre.trim().isEmpty()) {
            mErrorNombre.setValue("Ingrese un nombre");
            valido = false;
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            mErrorApellido.setValue("Ingrese un apellido");
            valido = false;
        }
        if (dni == null || dni.trim().isEmpty()) {
            mErrorDni.setValue("Ingrese un DNI");
            valido = false;
        }else if (!dni.matches("\\d+")) {
            mErrorDni.setValue("El DNI debe ser un número");
            valido = false;
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            mErrorTelefono.setValue("Ingrese un teléfono");
            valido = false;
        }
        if (email == null || email.trim().isEmpty()) {
            mErrorEmail.setValue("Ingrese un email");
            valido = false;
        }

        return valido;
    }





    public void llenarFormulario() {
        String token = Services.leerToken(context);

        ApiCLient.inmobiliariaService service = ApiCLient.getService();
        Call<Propietario> call = service.obtenerPropietario("Bearer " + token);
        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()){
                    propietarioActual = response.body();
                    mNombre.postValue(propietarioActual.getNombre());
                    mApellido.postValue(propietarioActual.getApellido());
                    mDni.postValue(propietarioActual.getDni());
                    mTelefono.postValue(propietarioActual.getTelefono());
                    mEmail.postValue(propietarioActual.getEmail());
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