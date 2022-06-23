package com.example.tfmtest.presenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfmtest.adapters.ReportsListAdapter;

import com.example.tfmtest.R;
import com.example.tfmtest.database.DataBase;
import com.example.tfmtest.interfaces.Callback;
import com.example.tfmtest.interfaces.RealtimeDataListener;
import com.example.tfmtest.model.Reporte;
import com.example.tfmtest.model.Reportes;
import com.example.tfmtest.utils.Loading;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SecondActivity extends AppCompatActivity{

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;
    Button signOutBtn;

    //private ListReportsFragment listReportsFragment;
    List<Reporte> reportess;
    RecyclerView recyclerView;
    ReportsListAdapter itemAdapter;
    DataBase dataBase = new DataBase();
    DatabaseReference databaseReference;



    //FirebaseFirestore dataBase;


    public static final int NEW_DERRUMBE_ACTIVITY_REQUEST_CODE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        signOutBtn = findViewById(R.id.signout_btn);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }


        Reporte reporte = new Reporte("nombre", "baja");
        databaseReference = FirebaseDatabase.getInstance().getReference("reporte");
        recyclerView = findViewById(R.id.recyclerview);
        reportess = new ArrayList<>();
        itemAdapter = new ReportsListAdapter(this, reportess);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter.setResults(reportess);
        dataBase.createReporte(reporte);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    //databaseRefernce.child("reporte").child(reporte.getNombre()).setValue(reporte);
                    //databaseRefernce.child("reporte").child(reporte.getSeveridad()).setValue(reporte);
                      Reportes reportes = dataSnapshot.getValue(Reportes.class);
                     // reportes.add(reporte);
                      System.out.println("imprimiendo reportes!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                      System.out.println(reporte);
                      System.out.println(reportes.toString());

                  }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SecondActivity.this,"Error:" + error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//
//                      Reporte reporte = dataSnapshot.getValue(Reporte.class);
//                     // reportes.add(reporte);
//                      System.out.println("imprimiendo reportes!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                      System.out.println(reporte);
//                      //System.out.println(reporte.toString());
//
//                  }
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

            signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { /// aca se agrega la activity a  donde se agregar los dmbes de la parte de christian
                Intent intent = new Intent(SecondActivity.this, TabActivity.class);
                startActivityForResult(intent, NEW_DERRUMBE_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
            }
        });
    }



}