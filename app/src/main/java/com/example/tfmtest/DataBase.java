package com.example.tfmtest;

import androidx.annotation.NonNull;

import com.example.tfmtest.interfaces.Callback;
import com.example.tfmtest.model.Reporte;
import com.example.tfmtest.interfaces.RealtimeDataListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

    public void listenForUpdates(List<Reporte> list, RealtimeDataListener<Reporte> listener) {
        CollectionReference reportesReference = db.collection("Reportes");
        for (Reporte reporte : list) {
            reportesReference.document(reporte.getIdReporte()).addSnapshotListener((value, error) -> {
                if (error != null) {
                    listener.onError(error);
                }
                if (value != null && value.exists()) {
                    listener.onDataChange(value.toObject(Reporte.class));
                }
            });
        }
    }


}


