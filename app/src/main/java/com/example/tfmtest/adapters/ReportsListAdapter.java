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

import java.util.List;

public class ReportsListAdapter extends RecyclerView.Adapter<ReportsListAdapter.ReportViewHolder> {
    Context  context;
    private List<Reporte> mReports; // Cached copy of words

  //  private ListReportsFragment listReportsFragment;


    public ReportsListAdapter(Context context,List<Reporte> reportes) {
        this.mReports = reportes;
        notifyDataSetChanged();

    }
//    public void setListReportsFragment(ListReportsFragment listReportsFragment) {
//        this.listReportsFragment = listReportsFragment;
//    }

    public List<Reporte> getReportes() {
        return mReports;
    }

    @NonNull
    @Override
    public ReportsListAdapter.ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_report_dh, parent, false);

        return new ReportViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsListAdapter.ReportViewHolder holder, int position) {
        Reporte report = mReports.get(position);
        holder.lblNombre.setText(mReports.get(position).getNombre());
        //holder.nombreHueco.setText(mReports.get(position).getNombre());
       // holder.fecha.setText ((CharSequence) report.getFecha());
        holder.severidad.setText(mReports.get(position).getSeveridad());

    //    listReportsFragment.cargarDatos();

        notifyDataSetChanged();

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
    public int getItemCount() { return mReports.size();  }

    public void setResults(List<Reporte> reports) {
        this.mReports = reports;
        notifyDataSetChanged();
    }


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

