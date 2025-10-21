package com.example.inmobiliaria_movil.inmobiliariaApp.model;

import java.io.Serializable;

public class InmuebleRequest implements Serializable {
    private int idInmueble;
    private boolean disponible;

    public InmuebleRequest(int idInmueble, boolean disponible) {
        this.idInmueble = idInmueble;
        this.disponible = disponible;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
