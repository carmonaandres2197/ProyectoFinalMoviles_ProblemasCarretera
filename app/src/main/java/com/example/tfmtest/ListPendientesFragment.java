package com.example.tfmtest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfmtest.adapters.ItemAdapter;
import com.example.tfmtest.interfaces.Callback;
import com.example.tfmtest.model.Reporte;
import com.example.tfmtest.utils.Loading;
import com.example.tfmtest.interfaces.RealtimeDataListener;

import java.util.ArrayList;
import java.util.List;

public class ListPendientesFragment extends Fragment {

    private ArrayList<Reporte> reportes;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ItemAdapter itemAdapter;
    private DataBase dataBase;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_pendientes_activity,
                container, false);

        recyclerView = view.findViewById(R.id.list_pendientes);
        reportes = new ArrayList<>();

        itemAdapter = new ItemAdapter(reportes);
        dataBase = new DataBase();
        recyclerView.setAdapter(itemAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        itemAdapter.setResults(reportes);
        cargarDatos();
        return view;
    }

    public void cargarDatos(){
        Loading.showLoading(getActivity(),"Cargando datos");
        dataBase.obtenerReportes(new Callback<List<Reporte>>() {
            @Override
            public void onSucces(List<Reporte> result) {
                itemAdapter.setResults(result);
                Loading.hideLoading();
            }

            @Override
            public void onFailed(Exception exception) {
              Loading.hideLoading();
            }
        });

        addRealtimeDabaseListener(reportes);
    }

    private void addRealtimeDabaseListener(ArrayList<Reporte> reportes) {
        dataBase.listenForUpdates(reportes, new RealtimeDataListener<Reporte>() {
            @Override
            public void onDataChange(Reporte updateData) {
                int pos = 0;
                for (Reporte item : reportes){
                    if(item.getIdReporte().equals(updateData.getIdReporte())){
                        reportes.set( reportes.indexOf(pos) , updateData);
                        itemAdapter.notifyItemChanged(pos);
                    }
                    pos++;
                }
            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }
}
