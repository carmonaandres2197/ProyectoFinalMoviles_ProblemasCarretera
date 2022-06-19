package com.example.tfmtest.interfaces;

import java.util.List;

public interface RealtimeDataListener<T> {
    void onDataChange(T updateData);
    void onError(Exception exception);
}
