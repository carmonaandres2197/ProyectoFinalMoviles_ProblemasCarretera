package com.example.tfmtest.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.tfmtest.JsonProvincias.GpsTracker;
import com.example.tfmtest.R;
import com.example.tfmtest.database.DataBase;
import com.example.tfmtest.interfaces.Callback;
import com.example.tfmtest.model.Address;
import com.example.tfmtest.model.ProvinciasCantonesDistritos;
import com.example.tfmtest.model.Reporte;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CreateEditTemplate  extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button btnTakePhoto;
    Button btnTakeVideo;
    ImageView imageView;
    ImageButton save;
    VideoView videoView;
    Bitmap bitmapImage;
    Bitmap bitmapVideo;
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

    Reporte reporte = new Reporte();
    Address adress = new Address();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_template);
        btnTakePhoto = findViewById(R.id.button);
        imageView = findViewById(R.id.verImagen);
        videoView = findViewById((R.id.verVideo));
        save = findViewById((R.id.save_report));
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



        Reporte reportRecibido = (Reporte) getIntent().getSerializableExtra("reporte");

        if(reportRecibido==null){
            //Inizializar datos

            reporte.setIdReporte(UUID.randomUUID().toString());
            reporte.setEstado(false);
            reporte.setNombreUsuarioCrea("Usuario Invitado Default");
            reporte.setPendienteAtender("pendienteAtender");
            reporte.setFecha(new Date());
        } else {
       TextView title = findViewById(R.id.TitleReporte);
       title.setTextSize(16);
       title.setText(reportRecibido.getNombre());
       tvLatitude.setText(reportRecibido.getLatitud());
       tvLongitude.setText(reportRecibido.getLongitud());
       sptipoReporte.setSelection( Arrays.asList(sevelist).indexOf(reportRecibido.getTipoReporte()));
       spSeveridad.setSelection(Arrays.asList(sevelist).indexOf(reportRecibido.getSeveridad()));

            Gson gson = new Gson();
            Address address2 = gson.fromJson(reportRecibido.getUbicacion(), (Type) Address.class);
            String provincia[] = {address2.getProvincia()};
            spProvincia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, provincia));

            String canton[] = {address2.getCanton()};
            spCanton.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, canton));

            String distrito[] = {address2.getCanton()};
            spDistrito.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, distrito));

            byte[] decodedString = Base64.decode(reportRecibido.getImagen(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);

            File file = createTempFile(reporte.getVideo(), ".mp4",CreateEditTemplate.this );
            if (file != null) {
              videoView.setVideoPath(file.getAbsolutePath());
            }


        }


        // save reporte

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // creating a variable for our

                DataBase db = new DataBase();

                String Provincia = spProvincia.getSelectedItem().toString();
                String Canton = spCanton.getSelectedItem().toString();
                String distrito = spDistrito.getSelectedItem().toString();
                String tipoReporte = sptipoReporte.getSelectedItem().toString();


                reporte.setNombre(tipoReporte + " - "+ Provincia + " - "+ Canton+ " - "+ distrito);
                reporte.setFecha(new Date());



                try {
                   // db.createReporte(reporte);
                    db.agregarRegistro(reporte,reporte.getIdReporte(), new Callback<Void>(){
                        @Override
                        public void onSucces(Void result) {
                            Log.i("Firestore", "Registro exitoso");
                        }
                        @Override            public void onFailed(Exception e) {
                            Log.i("Firestore", "Ocurrio un error " + e.getMessage());
                        }
                    });

                    Toast.makeText(CreateEditTemplate.this, "Reporte Guardado", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(CreateEditTemplate.this, "algo salio mal", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public String encodeBase64(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
      return Base64.encodeToString(byteFormat, Base64.NO_WRAP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            bitmapImage =bitmap;
            reporte.setImagen(encodeBase64(bitmapImage));
        }
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            File codingVideoPath = new File(getRealPathFromURI(videoUri));
            videoView.setVideoURI(videoUri);
            reporte.setVideo(getStringFile(codingVideoPath));
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public String getStringFile(File f) {
        InputStream inputStream = null;
        String encodedFile = "", lastVal;
        try {
            inputStream = new FileInputStream(f.getAbsolutePath());

            byte[] buffer = new byte[10240]; //specify the size to allow
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Base64OutputStream output64 = new Base64OutputStream(output, Base64.DEFAULT);

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output64.write(buffer, 0, bytesRead);
            }
            output64.close();
            encodedFile = output.toString();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastVal = encodedFile;
        return lastVal;
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
            reporte.setLatitud(String.valueOf(latitude));
            reporte.setLongitud(String.valueOf(longitude));
          tvLatitude.setText(String.valueOf(latitude));
            tvLongitude.setText(String.valueOf(longitude));
        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    public  void playVideo (View view){
        videoView.start();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Gson gson = new Gson();

        switch (parent.getId()) {
            case R.id.spinner_provincia:
            {
                String[] list = pcdlist.scantones().get(position + 1);
                adCanton = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
                spCanton.setAdapter(adCanton);
                adCanton.notifyDataSetChanged();


                //Guardando direccion   como Json
               adress.setCanton(spCanton.getSelectedItem().toString());
               adress.setProvincia(spProvincia.getSelectedItem().toString());
                String jsonDireccion = gson.toJson(adress).toString();
                reporte.setUbicacion(jsonDireccion);


            }
            break;
            case R.id.spinner_canton:
            {
                int iPospv = spProvincia.getSelectedItemPosition();
                int iPosCan = spCanton.getSelectedItemPosition();
                String c= spCanton.getSelectedItem().toString();
                HashMap<Integer, String[]> distritos = pcdlist.scantonesDist().get(iPospv +1);
                String[] list =distritos.get(iPosCan + 1);
                adDistrito = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
                spDistrito.setAdapter(adDistrito);
                adress.setCanton(c);
                String jsonDireccion = gson.toJson(adress).toString();
                reporte.setUbicacion(jsonDireccion);
                adDistrito.notifyDataSetChanged();
            }
            break;
            case R.id.spinner_distrito:
                String d= spDistrito.getSelectedItem().toString();
                adress.setDistrito(d);
                String jsonDireccion = gson.toJson(adress).toString();
                reporte.setUbicacion(jsonDireccion);

            case R.id.spinner_severidad:
                reporte.setSeveridad(spSeveridad.getSelectedItem().toString());
                break;

            case R.id.spinner_tipoReporte:
                   reporte.setTipoReporte(sptipoReporte.getSelectedItem().toString());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + parent.getId());
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
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}