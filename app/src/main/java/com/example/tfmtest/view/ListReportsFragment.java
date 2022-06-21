//package com.example.tfmtest.view;
//
//import android.app.Application;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.LiveData;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.tfmtest.R;
//import com.example.tfmtest.adapters.ReportsListAdapter;
//import com.example.tfmtest.database.DataBase;
//import com.example.tfmtest.interfaces.Callback;
//import com.example.tfmtest.model.Reporte;
//import com.example.tfmtest.utils.Loading;
//import com.example.tfmtest.interfaces.RealtimeDataListener;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class ListReportsFragment {
//
//    private List<Reporte> reportes;
//    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;
//    private ReportsListAdapter itemAdapter;
//    private DataBase dataBase;
//    private  LayoutInflater mInflater;
//
//
//    public void onCreateView(ViewGroup parent,Bundle savedInstanceState) {
//        View view = mInflater.inflate(R.layout.activity_second, parent, false);
//
//        recyclerView = view.findViewById(R.id.recyclerview);
//        reportes = new ArrayList<>();
//
//        itemAdapter = new ReportsListAdapter(reportes);
//        dataBase = new DataBase();
//        recyclerView.setAdapter(itemAdapter);
//       // layoutManager = new LinearLayoutManager(itemAdapter.getClass().);
//        recyclerView.setLayoutManager(layoutManager);
//        itemAdapter.setResults(reportes);
//        cargarDatos();
//       // return view;
//    }
//
//
//    List<Reporte> getAllReports() {
//        return reportes;
//    }
//
//    public void cargarDatos() {
//      //  Loading.showLoading(getActivity(), "Cargando datos");
//        dataBase.obtenerReportes(new Callback<List<Reporte>>() {
//            @Override
//            public void onSucces(List<Reporte> result) {
//                Collections.sort(result);
//                reportes = result;
//                itemAdapter.setResults(result);
//                Loading.hideLoading();
//            }
//
//            @Override
//            public void onFailed(Exception exception) {
//                Loading.hideLoading();
//            }
//        });
//
//        addRealtimeDabaseListener();
//    }
//
//    private void addRealtimeDabaseListener() {
//        dataBase.listenForUpdatesPendientes(itemAdapter.getReportes(), new RealtimeDataListener<List<Reporte>>() {
//            @Override
//            public void onDataChange(List<Reporte> updateData) {
//                itemAdapter.setResults(updateData);
//            }
//
//            @Override
//            public void onError(Exception exception) {
////                Toast.makeText(this.notify(), "Ocurrio un error",
////                        Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//
//}
