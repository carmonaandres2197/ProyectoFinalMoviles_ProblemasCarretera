package com.example.tfmtest.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tfmtest.interfaces.AdapterListener;
import com.example.tfmtest.model.Reporte;
import com.example.tfmtest.presenter.ImagenVideoActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class ReporteFragment extends Fragment implements AdapterListener {




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
