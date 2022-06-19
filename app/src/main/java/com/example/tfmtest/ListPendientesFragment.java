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

import com.example.tfmtest.adapters.ItemAdapter;
import com.example.tfmtest.interfaces.AdapterListener;
import com.example.tfmtest.interfaces.Callback;
import com.example.tfmtest.model.Reporte;
import com.example.tfmtest.utils.Loading;
import com.example.tfmtest.interfaces.RealtimeDataListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListPendientesFragment extends Fragment implements AdapterListener {

    private List<Reporte> reportes;
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

        itemAdapter = new ItemAdapter(reportes,this);
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

    @Override
    public void openMap(Reporte reporte) {
        try {
            if (reporte.getLatitud() != null && reporte.getLongitud() != null
                    && !reporte.getLatitud().equals("") && !reporte.getLongitud().equals("")) {
                Uri gmmIntentUri = Uri.parse("geo:" + reporte.getLatitud() + "," + reporte.getLongitud());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            } else {
                Toast.makeText(getActivity(),"El reporte no tiene las coordenadas",Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(),"Ocurrio un error",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void openVideo(Reporte reporte) {
        Intent intent = new Intent(getActivity(), ImagenVideoActivity.class);
        intent.putExtra("video", reporte.getVideo());
        intent.putExtra("isVideo", true);
    }

    @Override
    public void openImage(Reporte reporte) {
        Intent intent = new Intent(getActivity(), ImagenVideoActivity.class);
        intent.putExtra("imagen", reporte.getImagen());
        intent.putExtra("isImagen", true);
    }
}
