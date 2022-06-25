package com.example.tfmtest.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfmtest.R;
import com.example.tfmtest.adapters.ReportsListAdapter;
import com.example.tfmtest.model.Reporte;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

import java.util.List;


public class SecondActivity extends AppCompatActivity{

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;
    Button signOutBtn;

    //private ListReportsFragment listReportsFragment;
    List<Reporte> reportes;
    RecyclerView recyclerView;
    ReportsListAdapter itemAdapter;
    //DataBase base;
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
        recyclerView = findViewById(R.id.recyclerview);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }


       /*databaseReference = FirebaseDatabase.getInstance().getReference("reportes");
          recyclerView = findViewById(R.id.recyclerview);
          reportes = new ArrayList<>();
            // nueva  version
        itemAdapter = new ReportsListAdapter(this, reportes);
        //base = new DataBase();
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter.setResults(reportes);*/


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new ReportsListAdapter(this));

          //databaseReference.getDatabase();
         //   cargarDatos();


//          databaseReference.addValueEventListener(new ValueEventListener() {
//              @Override
//              public void onDataChange(@NonNull DataSnapshot snapshot) {
//                  for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                      Reporte reporte = dataSnapshot.getValue(Reporte.class);
//                      reportes.add(reporte);
//                  }
//                  itemAdapter.notifyDataSetChanged();
//
//              }
//
//              @Override
//              public void onCancelled(@NonNull DatabaseError error) {
//
//              }
//          });




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
                navigateEditCreateEvent(view);
            }
        });


    }

    public void navigateEditCreateEvent(View view) {
        Intent intent = new Intent(SecondActivity.this, CreateEditTemplate.class);
        startActivity(intent);
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

//        public void cargarDatos() {
//      //  Loading.showLoading(getActivity(), "Cargando datos");
//        base.obtenerReportes(new Callback<List<Reporte>>() {
//            @Override
//            public void onSucces(List<Reporte> result) {
//                Collections.sort(result);
//                reportes = result;
//                itemAdapter.setResults(result);
//                Loading.hideLoading();
//            }
//
//            @Override
//            public void onFailed(Exception e) {
//                Loading.hideLoading();
//            }
//        });
//
//        addRealtimeDabaseListener();
//    }
//
//    private void addRealtimeDabaseListener() {
//        base.listenForUpdatesPendientes(itemAdapter.getReportes(), new RealtimeDataListener<List<Reporte>>() {
//            @Override
//            public void onDataChange(List<Reporte> updateData) {
//                itemAdapter.setResults(updateData);
//            }
//
//            @Override
//            public void onError(Exception exception) {
//                Toast.makeText(getBaseContext(), "Ocurrio un error",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//    }






}