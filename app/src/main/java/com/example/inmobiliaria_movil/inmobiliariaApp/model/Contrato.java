package com.example.inmobiliaria_movil.inmobiliariaApp.model;

import java.io.Serializable;
import java.util.Date;

public class Contrato implements Serializable {

    private int idContrato;
    private Date fechaInicio;
    private Date fechaFinalizacion;
    private double montoAlquiler;
    private boolean estado;
    private int idInquilino;
    private int idInmueble;

    private Inquilino inquilino;
    private Inmueble inmueble;

    private boolean tieneContratoVigente;


    public Contrato(int idContrato, Date fechaInicio, Date fechaFinalizacion, double montoAlquiler, boolean estado, int idInquilino, int idInmueble, Inquilino inquilino, Inmueble inmueble, boolean tieneContratoVigente) {
        this.idContrato = idContrato;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.montoAlquiler = montoAlquiler;
        this.estado = estado;
        this.idInquilino = idInquilino;
        this.idInmueble = idInmueble;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
        this.tieneContratoVigente = tieneContratoVigente;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public double getMontoAlquiler() {
        return montoAlquiler;
    }

    public void setMontoAlquiler(double montoAlquiler) {
        this.montoAlquiler = montoAlquiler;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public boolean isTieneContratoVigente() {
        return tieneContratoVigente;
    }

    public void setTieneContratoVigente(boolean tieneContratoVigente) {
        this.tieneContratoVigente = tieneContratoVigente;
    }
}

