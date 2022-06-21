package com.example.tfmtest.database;

import androidx.annotation.NonNull;

import com.example.tfmtest.interfaces.Callback;
import com.example.tfmtest.interfaces.RealtimeDataListener;
import com.example.tfmtest.model.Reporte;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


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
        List<String> filtros = new ArrayList<>();
        filtros.add("Pendiente");
        filtros.add("Progreso");
        db.collection("Reportes").whereIn("pendienteAtender",filtros)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Reporte> list = new ArrayList<>();
                            if(task.getResult().isEmpty()){
                                callback.onSucces(new ArrayList<>());
                            }
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

    /**
     * Metodo para escuchar las actualizaciones de los reportes pendientes o
     * en progresa
     * @param reportes Lista de reportes
     * @param listener para actualizar los datos al item-adapter
     * */
    public void listenForUpdatesPendientes(List<Reporte> reportes, RealtimeDataListener<List<Reporte>> listener) {
        List<String> filtros = new ArrayList<>();
        filtros.add("Pendiente");
        filtros.add("Progreso");
        db.collection("Reportes").whereIn("pendienteAtender",filtros)
                .addSnapshotListener((value, e) -> {
            if (e != null) {
                listener.onError(e);
                return;
            }
            List<Reporte> newList = value.toObjects(Reporte.class);
            listener.onDataChange(newList);
        });
    }
    /**
     * Actualizar un reporte
     * @param reporte objeto reporte
     * @param callback para manipular el resultado en la actividad que este ejecutando
     * */
    public void actualizarCampoPendienteReporte(Reporte reporte, Callback<Reporte> callback){
        db.collection("Reportes").document(reporte.getIdReporte())
               .update("pendienteAtender","Pendiente")
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
}


