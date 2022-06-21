package com.example.tfmtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
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

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

        itemAdapter = new ItemAdapter(reportes, this);
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
                String geo = reporte.getLatitud() + "," + reporte.getLongitud();
                Uri gmmIntentUri = Uri.parse("geo:" + geo + "?q=" + geo + "(" + reporte.getNombre() + ")");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                getActivity().startActivity(mapIntent);
            } else {
                Toast.makeText(getActivity(), "El reporte no tiene las coordenadas", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Ocurrio un error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void openVideo(Reporte reporte) {
        if (reporte.getVideo() != null && !reporte.getVideo().equals("")) {
            File file = createTempFile(reporte.getVideo(), ".mp4", getActivity());
            if (file != null) {
                Intent intent = new Intent(getActivity(), ImagenVideoActivity.class);
                intent.putExtra("video", file.getAbsolutePath());
                intent.putExtra("isVideo", true);
                getActivity().startActivity(intent);
            }
        } else {
            Toast.makeText(getActivity(), "No tiene video", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void openImage(Reporte reporte) {
        if (reporte.getImagen() != null && !reporte.getImagen().equals("")) {
            File file = createTempFile(reporte.getImagen(), ".jpg", getActivity());
            if (file != null) {
                Intent intent = new Intent(getActivity(), ImagenVideoActivity.class);
                intent.putExtra("imagen", file.getAbsolutePath());
                intent.putExtra("isImagen", true);
                getActivity().startActivity(intent);
            }
        } else {
            Toast.makeText(getActivity(), "No tiene imagen", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Metodo para guardar un string en Base64 a un archivo temporal
     * @param content contenido en Base64
     * @param typeFile tipo de archivo al que se va a guardar ejemplo (.mp4 .jpg)
     * @param activity actividad para acceder al cache
     */
    public File createTempFile(String content, String typeFile, Activity activity) {
        File file = null;
        try {
            file = File.createTempFile("temp" + new Date().getTime(), typeFile, activity.getExternalCacheDir());
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
            fos.write(Base64.decode(content, Base64.NO_WRAP));
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }
}
