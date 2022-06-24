package com.example.tfmtest.presenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.Transliterator;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.tfmtest.R;
import com.example.tfmtest.model.ProvinciasCantonesDistritos;

import java.util.HashMap;

public class CreateEditTemplate  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btnTakePhoto;
    Button btnTakeVideo;

    ImageView imageView;
    VideoView videoView;
    public static final int RequestPermissionCode = 1;
    static final int REQUEST_VIDEO_CAPTURE = 1;

    ProvinciasCantonesDistritos pcdlist;
    Spinner spProvincia,spCanton, spDistrito, spSeveridad;
    ArrayAdapter<String> adProvincia,adCanton, adDistrito ,adSeveridad;
    String[] sevelist= {"Alta", "Media", "Baja"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_template);

        btnTakePhoto = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        videoView = findViewById((R.id.simpleVideoView));
        EnableRuntimePermission();
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 7);
            }
        });
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

        //adProvincia,adCanton, adDistrito;
        adSeveridad= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sevelist);
        spSeveridad.setAdapter(adSeveridad);
        adProvincia= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pcdlist.provincias());
        spProvincia.setAdapter(adProvincia);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
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
            default:
                throw new IllegalStateException("Unexpected value: " + parent.getId());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}