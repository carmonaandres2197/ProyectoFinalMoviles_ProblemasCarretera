package com.example.tfmtest.utils;

import android.app.Activity;
import android.app.ProgressDialog;

public class Loading {
    public static ProgressDialog progressDialog;

    public static void showLoading(Activity activity, String mensaje){
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(mensaje);
        progressDialog.show();
    }

    public static void setMessage(String message){
        progressDialog.setMessage(message);
    }

    public static void hideLoading(){
        if(progressDialog!=null)
            progressDialog.cancel();
    }
}
