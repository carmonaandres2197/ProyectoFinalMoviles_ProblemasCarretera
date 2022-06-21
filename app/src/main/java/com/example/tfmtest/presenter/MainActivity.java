package com.example.tfmtest.presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tfmtest.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googleBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleBtn = findViewById(R.id.google_btn);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);


        if(acct!=null){
            navigateToSecondActivity();
        }

        //botonParaAbrirTabActivity();
        // cambiar el metodo por singin()
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSecondActivity();
            }
        });


    }

    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
    }

    public void navigateEditCreateEvent(View view) {
        Intent intent = new Intent(MainActivity.this, CreateEditTemplate.class);
        startActivity(intent);
    }

//    /*Metodos para prueba*/
//    public void botonParaAbrirTabActivity(){
//        Button button = findViewById(R.id.loginbtn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//                Intent intent = new Intent(MainActivity.this,TabActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//    /*Metodos para prueba*/
//    public void testInsertUpdateData(){
//        DataBase dataBase = new DataBase();
//
//        Reporte reporte = new Reporte();
//        reporte.setIdReporte(UUID.randomUUID().toString().replace("-","").toLowerCase(Locale.ROOT));
//        reporte.setEstado(true);
//        reporte.setNombre("Prueba 4");
//        reporte.setFecha(new Date());
//        reporte.setLatitud("9.634256");
//        reporte.setLongitud("-83.996543");
//        reporte.setNombreUsuarioCrea("Ricardo");
//        reporte.setUbicacion("Alajuela, Costa Rica");
//
//        //Registrar
//        dataBase.agregarRegistro(reporte,reporte.getIdReporte(), new Callback<Void>(){
//
//            @Override
//            public void onSucces(Void result) {
//                Log.i("Firestore", "Registro exitoso");
//            }
//
//            @Override
//            public void onFailed(Exception e) {
//                Log.i("Firestore", "Ocurrio un error " + e.getMessage());
//            }
//        });
//
//        //Actualizar
////        dataBase.actualizarReporte(reporte, new Callback<Reporte>() {
////            @Override
////            public void onSucces(Reporte result) {
////                Log.i("Firestore", "Registro actualizado");
////            }
////
////            @Override
////            public void onFailed(Exception e) {
////                Log.i("Firestore",  "Ocurrio un error " + e.getMessage());
////            }
////        });
//    }
}