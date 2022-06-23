package com.example.tfmtest.model;

import java.util.Map;

public class Reportes {

    public Map<String, Reporte> reportesdh;

    public Reportes(Map<String, Reporte> reportesdh) {
        this.reportesdh = reportesdh;
    }

    public Reportes() {
        super();
    }

    public Map<String, Reporte> getReportesdh() {
        return reportesdh;
    }

    public void setReportesdh(Map<String, Reporte> reportesdh) {
        this.reportesdh = reportesdh;
    }
}
