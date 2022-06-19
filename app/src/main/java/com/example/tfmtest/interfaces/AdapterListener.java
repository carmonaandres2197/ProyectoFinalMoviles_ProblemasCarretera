package com.example.tfmtest.interfaces;

import com.example.tfmtest.model.Reporte;

public interface AdapterListener {
    void openMap(Reporte reporte);
    void openVideo(Reporte reporte);
    void openImage(Reporte reporte);
}
