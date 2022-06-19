package com.example.tfmtest.model;

import java.util.Date;

public class Reporte implements Comparable<Reporte> {
    String idReporte;
    String nombre;
    Date fecha;
    String severidad;
    Boolean estado;
    String ubicacion;
    String nombreUsuarioCrea;
    String idUsuario;
    String latitud;
    String longitud;
    String imagen;
    String video;
    String pendienteAtender;

    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String URLimagen) {
        this.imagen = URLimagen;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String URLVideo) {
        this.video = URLVideo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombreUsuarioCrea() {
        return nombreUsuarioCrea;
    }

    public void setNombreUsuarioCrea(String nombreUsuarioCrea) {
        this.nombreUsuarioCrea = nombreUsuarioCrea;
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

    public String getPendienteAtender() {
        return pendienteAtender;
    }

    public void setPendienteAtender(String pendienteAtender) {
        this.pendienteAtender = pendienteAtender;
    }

    @Override
    public int compareTo(Reporte o) {
        if(o.getFecha().before(getFecha())){
            return -1;
        }

        return 1;
    }
}
