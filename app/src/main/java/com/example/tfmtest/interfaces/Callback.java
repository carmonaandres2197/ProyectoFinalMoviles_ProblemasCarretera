package com.example.tfmtest.interfaces;

import com.example.tfmtest.model.Reporte;
import java.util.List;

public interface Callback<T> {
    public void onSucces(List<Reporte> result);
    public void onFailed(Exception e);
}
