package com.example.tfmtest.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfmtest.ListReportsFragment;
import com.example.tfmtest.R;
import com.example.tfmtest.interfaces.AdapterListener;
import com.example.tfmtest.model.Reporte;

import java.util.Collections;
import java.util.List;

public class ReportsListAdapter extends RecyclerView.Adapter<ReportsListAdapter.ReportViewHolder> {

    private List<Reporte> mReports; // Cached copy of words
    //private final LayoutInflater mInflater;
   // AdapterListener adapterListener;
    private final LayoutInflater mInflater;
    private ListReportsFragment listReportsFragment;

    public void setListReportsFragment(ListReportsFragment listReportsFragment) {
        this.listReportsFragment = listReportsFragment;
    }
    public ReportsListAdapter(Context context, List<Reporte> reportes) {
      //  mInflater = LayoutInflater.from(context);
        this.mReports = reportes;
        mInflater = LayoutInflater.from(context);
       // this.adapterListener = adapterListener;
        notifyDataSetChanged();

    }

    public List<Reporte> getReportes() {
        return mReports;
    }

    @NonNull
    @Override
    public ReportsListAdapter.ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = mInflater.inflate(R.layout.reporte, parent,false);
        return new ReportViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsListAdapter.ReportViewHolder holder, int position) {
        final Reporte report = mReports.get(position);
        holder.lblNombre.setText(mReports.get(position).getNombre());
        //holder.nombreHueco.setText(mReports.get(position).getNombre());
        holder.fecha.setText((CharSequence) mReports.get(position).getFecha());
        holder.severidad.setText(mReports.get(position).getSeveridad());

//        holder.itemView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.setOnClickListener(this);
//                notifyDataSetChanged();
//
//            }
//        });

        listReportsFragment.cargarDatos();
        notifyDataSetChanged();

        holder.linearLayout.setOnClickListener(v -> {
            if (holder.sub_item.getVisibility() == View.VISIBLE) {
                holder.sub_item.setVisibility(View.GONE);
            } else {
                holder.sub_item.setVisibility(View.VISIBLE);
            }
        });
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

        public LinearLayout linearLayout;
        public LinearLayout sub_item;
        public TextView lblNombre;
       // public TextView nombreHueco;
        public TextView fecha;
        public TextView severidad;
        //   public TextView estado;


        private ReportViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.layout_list_reportes);
            sub_item = itemView.findViewById(R.id.sub_itemm);
            lblNombre = itemView.findViewById(R.id.nombree);
            //nombreHueco = itemView.findViewById(R.id.nombrehueco);
            fecha = itemView.findViewById(R.id.fecha);
            severidad = itemView.findViewById(R.id.severidad);
             //estado = itemView.findViewById(R.id.estadohueco);

        }
    }

}



