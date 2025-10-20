package com.example.inmobiliaria_movil.inmobiliariaApp.ui.contratos.pagosContrato;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_movil.inmobiliariaApp.lib.ApiCLient;
import com.example.inmobiliaria_movil.inmobiliariaApp.lib.Services;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Pago;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosContratoViewModel extends AndroidViewModel {

    private MutableLiveData<List<Pago>> lista = new MutableLiveData<>();

    public PagosContratoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Pago>> getLista() {
        return lista;
    }



    public void cargarPagos(int idContrato) {

        String token = Services.leerToken(getApplication());
        ApiCLient.inmobiliariaService service = ApiCLient.getService();

        Call<List<Pago>> call = service.obtenerPagosPorContrato("Bearer " + token, idContrato);

        call.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                lista.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                Toast.makeText(getApplication(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT);
            }
        });





    }

}