package com.example.tfmtest;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import com.example.tfmtest.model.Reporte;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /*
    * Verifica cual reporte tiene la fecha reciente
    * */
    @Test
    public void verificarFechaReciente() throws ParseException {
        String sDate1="31/02/2022";
        Date fechaFebrero=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        String sDate2="25/06/2022";
        Date fechaJunio=new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);

        Reporte reporte = new Reporte();
        reporte.setNombre("Reporte 1");
        reporte.setIdReporte("1");
        reporte.setFecha(fechaFebrero);

        Reporte reporteReciente = new Reporte();
        reporteReciente.setNombre("Reporte 3");
        reporteReciente.setIdReporte("3");
        reporteReciente.setFecha(fechaJunio);

        assertTrue(reporte.compareTo(reporteReciente) == 1);

    }
}