package com.example.tfmtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfmtest.adapters.ReportsListAdapter;
import com.example.tfmtest.interfaces.AdapterListener;
import com.example.tfmtest.interfaces.Callback;
import com.example.tfmtest.model.Reporte;
import com.example.tfmtest.utils.Loading;
import com.example.tfmtest.interfaces.RealtimeDataListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListReportsFragment extends Fragment {

    private List<Reporte> reportes;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReportsListAdapter itemAdapter;
    private DataBase dataBase;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_second,
                container, false);

        recyclerView = view.findViewById(R.id.recycler1);
        reportes = new ArrayList<>();

        itemAdapter = new ReportsListAdapter(getContext(),reportes);
        dataBase = new DataBase();
        recyclerView.setAdapter(itemAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        itemAdapter.setResults(reportes);
        cargarDatos();
        return view;
    }

    public void cargarDatos() {
        Loading.showLoading(getActivity(), "Cargando datos");
        dataBase.obtenerReportesPendientes(new Callback<List<Reporte>>() {
            @Override
            public void onSucces(List<Reporte> result) {
                Collections.sort(result);
                reportes = result;
                itemAdapter.setResults(result);
                Loading.hideLoading();
            }

            @Override
            public void onFailed(Exception exception) {
                Loading.hideLoading();
            }
        });

        addRealtimeDabaseListener();
    }

    private void addRealtimeDabaseListener() {
        dataBase.listenForUpdatesPendientes(itemAdapter.getReportes(), new RealtimeDataListener<List<Reporte>>() {
            @Override
            public void onDataChange(List<Reporte> updateData) {
                itemAdapter.setResults(updateData);
            }

            @Override
            public void onError(Exception exception) {
                Toast.makeText(getActivity(), "Ocurrio un error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
