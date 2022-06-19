package com.example.tfmtest.interfaces;


public interface Callback<T> {
    void onSucces(T result);
    void onFailed(Exception e);
}
