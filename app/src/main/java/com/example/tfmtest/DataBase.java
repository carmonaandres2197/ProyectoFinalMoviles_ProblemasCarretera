package com.example.tfmtest;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tfmtest.model.Reporte;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void obtenerReportes(){
        db.collection("Reportes")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Reporte> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("firestore", String.valueOf(document.getData()));
                                list = task.getResult().toObjects(Reporte.class);
                                break;

                            }
                            Log.d("firestore", list.toString());
                        } else {
                            Log.d("firestore", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }




}


