package com.example.tfmtest.interfaces;

public interface RealtimeDataListener<T> {
    void onDataChange(T updateData);
    void onError(Exception exception);
}
