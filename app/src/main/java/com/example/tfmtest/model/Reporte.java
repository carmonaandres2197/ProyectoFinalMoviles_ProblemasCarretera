package com.example.tfmtest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Reporte implements Comparable<Reporte>, Serializable {
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
    String tipoReporte;


    public Reporte(String nombre, Date fecha, String severidad, Boolean estado) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.severidad = severidad;
        this.estado = estado;
    }

    public Reporte() {
        super();
    }

    public Reporte(String idReporte, String nombre, Date fecha, String severidad, Boolean estado, String ubicacion, String nombreUsuarioCrea, String idUsuario, String latitud, String longitud, String imagen, String video, String pendienteAtender) {
        this.idReporte = idReporte;
        this.nombre = nombre;
        this.fecha = fecha;
        this.severidad = severidad;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.nombreUsuarioCrea = nombreUsuarioCrea;
        this.idUsuario = idUsuario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.imagen = imagen;
        this.video = video;
        this.pendienteAtender = pendienteAtender;
        this.tipoReporte = "";
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

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


