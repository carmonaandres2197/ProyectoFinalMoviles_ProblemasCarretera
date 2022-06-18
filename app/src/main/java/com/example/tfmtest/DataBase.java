package com.example.tfmtest;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tfmtest.interfaces.Callback;
import com.example.tfmtest.model.Reporte;
import com.example.tfmtest.interfaces.RealtimeDataListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import classes.User;


public class DataBase {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    /**
    * Agrega un nuevo reporte a la BD
    * @param data objeto reporte
    * @param id id que se va a guardar el registro
    * @param callback para manipular el resultado en la actividad que este ejecutando
    * */
    public void agregarRegistro(Reporte data, String id, Callback<Void> callback) {
        db.collection("Reportes").document(id).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                callback.onSucces(null);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailed(e);
            }
        });
    }

    /**
     * Actualiza un reporte
     * @param reporte objeto reporte
     * @param callback para manipular el resultado en la actividad que este ejecutando
     * */
    public void  actualizarReporte(Reporte reporte, Callback<Reporte> callback){
        db.collection("Reportes").document(reporte.getIdReporte())
               // .update("estado",false)
                .set(reporte)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSucces(reporte);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailed(e);
                    }
                });
    }

    /**
     * Actualiza un reporte
     * @param reporte objeto reporte
     * @param callback para manipular el resultado en la actividad que este ejecutando
     * */
    public void obtenerReportes(Callback<List<Reporte>> callback) {
        db.collection("Reportes")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Reporte> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                callback.onSucces(task.getResult().toObjects(Reporte.class));
                                break;
                            }
                        } else {
                            callback.onFailed(task.getException());
                        }
                    }
                });
    }

    /**
     * Obtiene un reporte en estado pendiente o en proceso
     * @param callback para actualizar los datos al item-adapter
     * Nota: falta mejorarlo
     * */
    public void obtenerReportesPendientes(Callback<List<Reporte>> callback) {
        db.collection("Reportes").whereEqualTo("pendienteAtender","Pendiente").
                whereEqualTo("pendienteAtender","Progreso")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Reporte> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                callback.onSucces(task.getResult().toObjects(Reporte.class));
                                break;
                            }
                        } else {
                            callback.onFailed(task.getException());
                        }
                    }
                });
    }

    /**
     * Metodo para escuchar las actualizaciones a la BD
     * @param reportes Lista de reportes
     * @param listener para actualizar los datos al item-adapter
     * */
    public void listenForUpdates(List<Reporte> reportes, RealtimeDataListener<List<Reporte>> listener) {
         db.collection("Reportes").addSnapshotListener((value, e) -> {
             if (e != null) {
                 listener.onError(e);
                 return;
             }
             List<Reporte> newList = value.toObjects(Reporte.class);
             listener.onDataChange(newList);
         });
    }

}


