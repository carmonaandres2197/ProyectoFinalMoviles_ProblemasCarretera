package com.example.tfmtest.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfmtest.R;
import com.example.tfmtest.model.Reporte;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ReportsListAdapter extends FirestoreRecyclerAdapter<Reporte,ReportsListAdapter.ReportViewHolder>{

    public ReportsListAdapter(@NonNull FirestoreRecyclerOptions<Reporte> options) {
        super(options);
    }




    @Override
    protected void onBindViewHolder(@NonNull ReportViewHolder holder, int position, @NonNull Reporte report) {

        holder.nombreHueco.setText(report.getNombre());
        holder.severidad.setText(report.getSeveridad());
        holder.fecha.setText(report.getFecha().toString());
        holder.estado.setText(report.getEstado().toString());

        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.setOnLongClickListener(this);
                Toast.makeText(v.getContext(),"Click on D-H",Toast.LENGTH_LONG).show();
              //  nombres.remove(current);
                holder.linearLayout.removeViewInLayout(v);

                notifyDataSetChanged();
                Toast.makeText(
                        v.getContext(),
                        "D-H Delete Successful",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }


    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report_dh, parent, false);

        return new ReportViewHolder(v);
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        public TextView nombreHueco, fecha, severidad, estado;


        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.layoutGeneral);
            nombreHueco = itemView.findViewById(R.id.nombree);
            fecha = itemView.findViewById(R.id.fecha);
            severidad = itemView.findViewById(R.id.severidad);
            estado = itemView.findViewById(R.id.estado);



        }
    }


// RecyclerView.Adapter<ReportsListAdapter.ReportViewHolder>
//    Context  context;
//    private List<Reporte> mReports; // Cached copy of words
//
//    String[] nombres = {"Cobano, Pun, 100 mts sur cancha", "Mts Oca, SJ, Frente a Iglesia", "Cahuita, Lim, 50 mts este del parque", "Caldera, SJ, ruta nacional 707","Puerto Viejo, Lim, 150 mts este de la principal","Turrialba, Cartago, 100 mts sur de la Ruta 10"};
//    Date[] fechas = {new Date(), new Date(), new Date(), new Date(), new Date(), new Date()};
//    String[] severidad = {"Baja", "Media", "Alta", "Alta", "Alta", "Baja"};
//    Boolean[] estado = {true, false, true, false, true, false};
//
//    public ReportsListAdapter(Context context) {
//        //this.mReports = reportes;
//        this.context = context;
//        notifyDataSetChanged();
//
//    }
//
//
//    @NonNull
//    @Override
//    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View item = LayoutInflater.from(context).inflate(R.layout.item_report_dh, parent, false);
//
//        return new ReportViewHolder(item);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ReportsListAdapter.ReportViewHolder holder, int position) {
//        //Reporte report = mReports.get(position);
//        holder.nombreHueco.setText(nombres[position]);
//        holder.severidad.setText(severidad[position]);
//
//        holder.fecha.setText(fechas.toString());
//        holder.estado.setText(estado.toString());
//
//        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                v.setOnLongClickListener(this);
//                Toast.makeText(v.getContext(),"Click on D-H",Toast.LENGTH_LONG).show();
//              //  nombres.remove(current);
//                holder.linearLayout.removeViewInLayout(v);
//
//                notifyDataSetChanged();
//                Toast.makeText(
//                        v.getContext(),
//                        "D-H Delete Successful",
//                        Toast.LENGTH_LONG).show();
//                return false;
//            }
//        });
//
//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//
//
//
//      }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//
//    @Override
//    public int getItemCount() { return nombres.length;  }
//
//
//    class ReportViewHolder extends RecyclerView.ViewHolder {
//
//        public LinearLayout linearLayout;
//        public TextView nombreHueco;
//        public TextView fecha;
//        public TextView severidad;
//        public TextView estado;
//
//
//        private ReportViewHolder(View itemView) {
//            super(itemView);
//            linearLayout = itemView.findViewById(R.id.layoutGeneral);
//            nombreHueco = itemView.findViewById(R.id.nombree);
//            fecha = itemView.findViewById(R.id.fecha);
//            severidad = itemView.findViewById(R.id.severidad);
//            estado = itemView.findViewById(R.id.estado);
//
//        }
//    }
}

