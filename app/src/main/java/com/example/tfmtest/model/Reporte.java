package com.example.tfmtest.model;

import java.util.ArrayList;
import java.util.Date;

public class Reporte implements Comparable<Reporte> {
    String nombre;
    Date fecha;
    String severidad;
    Boolean estado;
    String ubicacion;
    String ubicacionGoogleMaps;
    String nombreusuarioCrea;
    String idUsuario;
    String latitud;
    String longitud;
    String URLimagen;
    String URLVideo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSeveridad() {
        return severidad;
    }

    public void setSeveridad(String severidad) {
        this.severidad = severidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getURLimagen() {
        return URLimagen;
    }

    public void setURLimagen(String URLimagen) {
        this.URLimagen = URLimagen;
    }

    public String getURLVideo() {
        return URLVideo;
    }

    public void setURLVideo(String URLVideo) {
        this.URLVideo = URLVideo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUbicacionGoogleMaps() {
        return ubicacionGoogleMaps;
    }

    public void setUbicacionGoogleMaps(String ubicacionGoogleMaps) {
        this.ubicacionGoogleMaps = ubicacionGoogleMaps;
    }

    public String getNombreusuarioCrea() {
        return nombreusuarioCrea;
    }

    public void setNombreusuarioCrea(String nombreusuarioCrea) {
        this.nombreusuarioCrea = nombreusuarioCrea;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @Override
    public int compareTo(Reporte o) {
        if(o.getFecha().before(getFecha())){
            return -1;
        }

        return 1;
    }
}
