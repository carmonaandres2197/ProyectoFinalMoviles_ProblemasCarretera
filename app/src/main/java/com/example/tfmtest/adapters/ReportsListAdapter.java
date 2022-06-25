package com.example.tfmtest.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.tfmtest.R;
import com.example.tfmtest.model.Reporte;

import java.util.Date;
import java.util.List;

public class ReportsListAdapter extends RecyclerView.Adapter<ReportsListAdapter.ReportViewHolder> {
    Context  context;
    //private List<Reporte> mReports; // Cached copy of words

    String[] nombres = {"Cobano, Pun, 100 mts sur cancha", "Mts Oca, SJ, Frente a Iglesia", "Cahuita, Lim, 50 mts este del parque"};
    Date[] fechas = {new Date(), new Date(), new Date()};
    String[] severidad = {"Baja", "Media", "Alta"};
    Boolean[] estado = {true, false, true};

  //  private ListReportsFragment listReportsFragment;


    public ReportsListAdapter(Context context) {
        //this.mReports = reportes;
        this.context = context;
        notifyDataSetChanged();

    }
//    public void setListReportsFragment(ListReportsFragment listReportsFragment) {
//        this.listReportsFragment = listReportsFragment;
//    }

    /*public List<Reporte> getReportes() {
        return mReports;
    }*/

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_report_dh, parent, false);

        return new ReportViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsListAdapter.ReportViewHolder holder, int position) {
        //Reporte report = mReports.get(position);
        holder.lblNombre.setText(nombres[position]);
        holder.severidad.setText(severidad[position]);
       // holder.fecha.setText ((CharSequence) report.getFecha());


    //    listReportsFragment.cargarDatos();

        //notifyDataSetChanged();

      }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() { return nombres.length;  }

   /* public void setResults(List<Reporte> reports) {
        this.mReports = reports;
        notifyDataSetChanged();
    }*/


    class ReportViewHolder extends RecyclerView.ViewHolder {

        public TextView lblNombre;
       // public TextView nombreHueco;
       // public TextView fecha;
        public TextView severidad;
        //   public TextView estado;


        private ReportViewHolder(View itemView) {
            super(itemView);
            lblNombre = itemView.findViewById(R.id.nombree);
            //nombreHueco = itemView.findViewById(R.id.nombrehueco);
         //   fecha = itemView.findViewById(R.id.fecha);
            severidad = itemView.findViewById(R.id.severidad);
             //estado = itemView.findViewById(R.id.estadohueco);

        }
    }
}

