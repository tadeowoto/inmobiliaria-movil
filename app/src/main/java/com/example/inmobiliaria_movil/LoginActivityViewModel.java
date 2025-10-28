package com.example.inmobiliaria_movil;

import static android.content.Context.SENSOR_SERVICE;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria_movil.inmobiliariaApp.lib.ApiCLient;
import com.example.inmobiliaria_movil.inmobiliariaApp.lib.Services;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {

    private MutableLiveData<String> mErrorNombre = new MutableLiveData<>();
    private MutableLiveData<String> mErrorPassword = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLoginExitoso = new MutableLiveData<>();

    private MutableLiveData<Boolean> mEstaCargando = new MutableLiveData<>();

    private SensorManager manager;
    private Sensor acelerometro;
    private ManejadorSensores manejador;

    private static final float SENSIBILIDAD = 10;
    private long ultimoUpdate = 0;


    private Context context;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
    }

    public LiveData<String> getErrorNombre() {
        return mErrorNombre;
    }

    public LiveData<String> getErrorPassword() {
        return mErrorPassword;
    }

    public LiveData<Boolean> getEstaCargando() {
        return mEstaCargando;
    }

    public LiveData<Boolean> getLoginExitoso() {
        return mLoginExitoso;
    }


    public void validarUsuario(String usuario, String password) {
        boolean valido = true;


        mErrorNombre.setValue(null);
        mErrorPassword.setValue(null);

        if (!valido) {
            mEstaCargando.setValue(false);
            return;
        }

        if (usuario == null || usuario.trim().isEmpty()) {
            mErrorNombre.setValue("Ingrese un usuario");
            valido = false;
        }

        if (password == null || password.trim().isEmpty()) {
            mErrorPassword.setValue("Ingrese una contraseña");
            valido = false;
        }

        if (valido) {
            mEstaCargando.setValue(true);
            ApiCLient.inmobiliariaService service = ApiCLient.getService();
            Call<String> call = service.login(usuario, password);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        String token = response.body();
                        Services.guardarToken(context, token);
                        mLoginExitoso.postValue(true);
                        mEstaCargando.postValue(false);
                    } else {
                        mErrorPassword.postValue("Usuario o contraseña incorrectos");
                        mEstaCargando.postValue(false);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    mEstaCargando.postValue(false);
                    Toast.makeText(context, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void activarLecturas() {
        manager = (SensorManager) getApplication().getSystemService(SENSOR_SERVICE);
        acelerometro = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manejador = new ManejadorSensores();

        if (acelerometro == null) {
            Toast.makeText(context, "El dispositivo no tiene sensor de aceleración", Toast.LENGTH_SHORT).show();
        } else {
            manager.registerListener(manejador, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void desactivarLecturas() {
        if (acelerometro != null && manejador != null) {
            manager.unregisterListener(manejador);
        }
    }

    class ManejadorSensores implements SensorEventListener {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;

                long currentTime = System.currentTimeMillis();
                if (acceleration > SENSIBILIDAD && (currentTime - SENSIBILIDAD) > 1500) {
                    ultimoUpdate = currentTime;
                    realizarLlamada();
                }
            }
        }


    }

    private void realizarLlamada() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:123456789"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);
    }
}
