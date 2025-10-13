package com.example.inmobiliaria_movil.inmobiliariaApp.lib;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.inmobiliaria_movil.inmobiliariaApp.model.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    }
}
