package com.example.tfmtest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfmtest.adapters.ItemAdapter;
import com.example.tfmtest.model.Reporte;

import java.util.ArrayList;
import java.util.Date;

public class ListPendientesFragment extends Fragment {

    private ArrayList<Reporte> reportes;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ItemAdapter itemAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_pendientes_activity,
                container, false);

        recyclerView = view.findViewById(R.id.list_pendientes);
        reportes = new ArrayList<>();
        createDummyObjects();
        itemAdapter = new ItemAdapter(reportes);

        recyclerView.setAdapter(itemAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        itemAdapter.setResults(reportes);

        return view;
    }

    public void createDummyObjects(){
        Date date2 = new Date();

        Reporte reporte = new Reporte();
        reporte.setNombre("Prueba 1");
        Date date = new Date();
        reporte.setFecha(date);
        reporte.setIdUsuario("Gabriel");
        reporte.setUbicacion("Liberia");

        Reporte reporte2 = new Reporte();
        reporte2.setNombre("Prueba 2");
        reporte2.setFecha(date2);
        reporte2.setEstado(false);
        reporte2.setIdUsuario("Roy");
        reporte2.setUbicacion("Villa Bonita");

        Reporte reporte3 = new Reporte();
        reporte3.setNombre("Prueba 3");
        reporte3.setFecha(date2);
        reporte3.setEstado(true);
        reporte3.setIdUsuario("Juan");
        reporte3.setUbicacion("Sabanilla");

        reportes.add(reporte);
        reportes.add(reporte2);
        reportes.add(reporte3);
    }
}
