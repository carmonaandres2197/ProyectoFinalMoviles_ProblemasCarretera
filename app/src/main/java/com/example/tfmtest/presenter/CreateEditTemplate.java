package com.example.tfmtest.presenter;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.example.tfmtest.JsonProvincias.GpsTracker;
import com.example.tfmtest.R;
import com.example.tfmtest.database.DataBase;
import com.example.tfmtest.interfaces.Callback;
import com.example.tfmtest.model.ProvinciasCantonesDistritos;
import com.example.tfmtest.model.Reporte;
import com.example.tfmtest.utils.Loading;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
public class CreateEditTemplate  extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button btnTakePhoto;
    Button btnTakeVideo;
    ImageView imageView;
    VideoView videoView;
    Bitmap yourBitmap ;
    public static final int RequestPermissionCode = 1;
    static final int REQUEST_VIDEO_CAPTURE = 1;
    //location
    private GpsTracker gpsTracker;
    private TextView tvLatitude,tvLongitude;
    ProvinciasCantonesDistritos pcdlist;
    Spinner spProvincia,spCanton, spDistrito, spSeveridad,sptipoReporte;
    ArrayAdapter<String> adProvincia,adCanton, adDistrito ,adSeveridad, adTipoReporte ;
    String[] sevelist= {"Alta", "Media", "Baja"};
    String[] tipolist= {"Derrumbe", "Hueco"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_template);
        btnTakePhoto = findViewById(R.id.button);
        imageView = findViewById(R.id.verImagen);
        videoView = findViewById((R.id.verVideo));
        EnableRuntimePermission();
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 7);
            }
        });
//image view
        btnTakeVideo = findViewById(R.id.buttonVideo);
        btnTakeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                }
            }
        });
        //spinners
        pcdlist = new ProvinciasCantonesDistritos();
        spProvincia=(Spinner)findViewById(R.id.spinner_provincia);
        spProvincia.setOnItemSelectedListener(this);
        spCanton=(Spinner)findViewById(R.id.spinner_canton);
        spCanton.setOnItemSelectedListener(this);
        spDistrito=(Spinner)findViewById(R.id.spinner_distrito);
        spDistrito.setOnItemSelectedListener(this);
        spSeveridad=(Spinner)findViewById(R.id.spinner_severidad);
        spSeveridad.setOnItemSelectedListener(this);
        sptipoReporte=(Spinner)findViewById(R.id.spinner_tipoReporte);
        sptipoReporte.setOnItemSelectedListener(this);

        //adProvincia,adCanton, adDistrito;
        adSeveridad= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sevelist);
        spSeveridad.setAdapter(adSeveridad);
        adProvincia= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pcdlist.provincias());
        spProvincia.setAdapter(adProvincia);

        //Tipo Reporte
        adTipoReporte = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item, tipolist);
        sptipoReporte.setAdapter(adTipoReporte);

        //location
        tvLatitude = (TextView)findViewById(R.id.latitude);
        tvLongitude = (TextView)findViewById(R.id.longitude);
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            yourBitmap=bitmap;
        }
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            videoView.setVideoURI(videoUri);
        }
    }
    public void EnableRuntimePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(CreateEditTemplate.this,
                Manifest.permission.CAMERA)) {
            Toast.makeText(CreateEditTemplate.this,"CAMERA permission allows us to Access CAMERA app",     Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(CreateEditTemplate.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        super.onRequestPermissionsResult(requestCode, permissions, result);
        switch (requestCode) {
            case RequestPermissionCode:
                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(CreateEditTemplate.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CreateEditTemplate.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    public void getLocation(View view){
        gpsTracker = new GpsTracker(CreateEditTemplate.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            tvLatitude.setText(String.valueOf(latitude));
            tvLongitude.setText(String.valueOf(longitude));
        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    public  void playVideo (View view){
        videoView.start();
    }
    public  void saveReport() {

        // creating a variable for our
        // Firebase Database.
        FirebaseDatabase firebaseDatabase;

        // creating a variable for our Database
        // Reference for Firebase.
        DatabaseReference databaseReference;

        DataBase db = new DataBase();
        Reporte reporte = new Reporte();
        reporte.setIdReporte("23444");
        reporte.setEstado(true);
        reporte.setNombre("Prueba 66666");
        reporte.setFecha(new Date());
        reporte.setLatitud("9.634256");
        reporte.setLongitud("-83.996543");
        reporte.setNombreUsuarioCrea("Christia");
        reporte.setUbicacion("Alajuela, Costa Rica");
        //Registrar
        db.agregarRegistro(reporte,reporte.getIdReporte(), new Callback<Void>(){
            @Override
            public void onSucces(Void result) {
                Log.i("Firestore", "Registro exitoso");
            }
            @Override            public void onFailed(Exception e) {
                Log.i("Firestore", "Ocurrio un error " + e.getMessage());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_provincia:
            {
                String[] list = pcdlist.scantones().get(position + 1);
                adCanton = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
                spCanton.setAdapter(adCanton);
                adCanton.notifyDataSetChanged();
            }
            break;
            case R.id.spinner_canton:
            {
                int iPospv = spProvincia.getSelectedItemPosition();
                int iPosCan = spCanton.getSelectedItemPosition();
                HashMap<Integer, String[]> distritos = pcdlist.scantonesDist().get(iPospv +1);
                String[] list =distritos.get(iPosCan + 1);
                adDistrito = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
                spDistrito.setAdapter(adDistrito);
                adDistrito.notifyDataSetChanged();
            }
            break;
            case R.id.spinner_distrito:
//                    Toast.makeText(this,"pos:"+p,Toast.LENGTH_LONG).show();

            case R.id.spinner_severidad:
//                    Toast.makeText(this,"pos:"+p,Toast.LENGTH_LONG).show();
                break;

            case R.id.spinner_tipoReporte:
//                    Toast.makeText(this,"pos:"+p,Toast.LENGTH_LONG).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + parent.getId());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}