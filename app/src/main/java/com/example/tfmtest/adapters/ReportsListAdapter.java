package com.example.tfmtest.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfmtest.R;
import com.example.tfmtest.model.Address;
import com.example.tfmtest.model.Reporte;
import com.example.tfmtest.presenter.CreateEditTemplate;
import com.example.tfmtest.presenter.TabActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class ReportsListAdapter extends FirestoreRecyclerAdapter<Reporte,ReportsListAdapter.ReportViewHolder>{

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
   Activity activity;


    public ReportsListAdapter(@NonNull FirestoreRecyclerOptions<Reporte> options, Activity activity) {
        super(options);
        this.activity = activity;

    }

    @Override
    protected void onBindViewHolder(@NonNull ReportViewHolder holder, int position, @NonNull Reporte report) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id = documentSnapshot.getId();
        Gson gson = new Gson();
        Address address = gson.fromJson(report.getUbicacion(), (Type) Address.class);
        String direccion = address.getProvincia() + " , " + address.getCanton() + " , " + address.getDistrito();
        holder.nombrehueco.setText(direccion);
        holder.severidad.setText(report.getSeveridad());
        holder.fecha.setText(report.getFecha().toString());

        if(report.getEstado() == true){
            holder.estado.setText("Reparado");
        }else
        {
            holder.estado.setText("No Reparado");
        }


        //Evento que permite que con un mantener presionado el itemm, se elimine el reporte
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.setOnLongClickListener(this);
                deleteReporte(id);
                return false;
            }

        });


    //Evento que permite que con un click en el itemm, se desplegue la pantalla 2 : details DH
        holder.linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, CreateEditTemplate.class);
                intent.putExtra("reporte",report);
                activity.startActivity(intent);

            }

        });
    }

    private void deleteReporte(String id) {
     db.collection("Reportes").document(id).delete().addOnSuccessListener
             (new OnSuccessListener<Void>() {
                 @Override
                 public void onSuccess(Void unused) {
                     Toast.makeText(
                             activity,
                             "Delete Successful",
                             Toast.LENGTH_LONG).show();
                 }
             }).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
             Toast.makeText(
                     activity.getApplicationContext(),
                     "Error al Eliminar",
                     Toast.LENGTH_LONG).show();
         }
     });
    }


    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report_dh, parent, false);

        return new ReportViewHolder(v);
    }

        @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }



    public class ReportViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        public TextView nombrehueco, fecha, severidad, estado;


        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.layoutGeneral);
            nombrehueco = itemView.findViewById(R.id.nombree);
            fecha = itemView.findViewById(R.id.fecha);
            severidad = itemView.findViewById(R.id.severidad);
            estado = itemView.findViewById(R.id.estado);




        }
    }


}

