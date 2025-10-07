package com.example.inmobiliaria_movil.inmobiliariaApp.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<MapaActual> mapaActual = new MutableLiveData<>();


    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<MapaActual> getMapaActual() {
        return mapaActual;
    };

    public class MapaActual implements OnMapReadyCallback {

        private LatLng inmobiliaria = new LatLng(-33.6594, -65.1775);

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(inmobiliaria);
            markerOptions.title("Inmobiliaria");

            googleMap.addMarker(markerOptions);

            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(inmobiliaria).zoom(15).bearing(45).tilt(15).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.animateCamera(cameraUpdate);



        }
    }


}