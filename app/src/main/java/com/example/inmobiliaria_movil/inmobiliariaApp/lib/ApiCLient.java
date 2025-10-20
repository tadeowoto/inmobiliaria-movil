package com.example.inmobiliaria_movil.inmobiliariaApp.lib;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.inmobiliaria_movil.inmobiliariaApp.model.Contrato;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Inmueble;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Pago;
import com.example.inmobiliaria_movil.inmobiliariaApp.model.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiCLient {

    private static final String BASE_URL = "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/";

    public static inmobiliariaService getService(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(inmobiliariaService.class);
    }

    public interface inmobiliariaService {


        @FormUrlEncoded
        @POST("api/Propietarios/login")
        Call<String> login(@Field("Usuario") String email, @Field("Clave") String password);


        @GET("api/Propietarios")
        Call<Propietario> obtenerPropietario(@Header("Authorization") String bearerToken);


        @PUT("api/Propietarios/actualizar")
        Call<Propietario> actualizarPropietario(
                @Header("Authorization") String token,
                @Body Propietario propietario
        );

        @FormUrlEncoded
        @PUT("/api/propietarios/changePassword")
        //esto o hacer un modelo para la res de la API
        Call<JsonObject> cambiarPassword(@Header("Authorization") String token, @Field("currentPassword") String contraseniaActual, @Field("newPassword") String nuevaContrasenia);


        @GET("api/inmuebles")
        Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);

        @GET("/api/Inmuebles/GetContratoVigente")
        Call<List<Inmueble>> obtenerInmueblesConContratosVigentes(@Header("Authorization") String token);

        @GET("/api/contratos/inmueble/{idInmueble}")
        Call<Contrato> obtenerContratoPorInmueble(@Header("Authorization") String token, @Path("idInmueble") int idInmueble);


        @GET("/api/pagos/contrato/{idContrato}")
        Call<List<Pago>> obtenerPagosPorContrato(@Header("Authorization") String token, @Path("idContrato") int idContrato);


    }
}
