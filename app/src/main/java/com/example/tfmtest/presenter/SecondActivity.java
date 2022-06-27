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
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;


public class SecondActivity extends AppCompatActivity{

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;
    Button signOutBtn;

    List<Reporte> reportes;
    RecyclerView recyclerView;
    ReportsListAdapter itemAdapter;

    public FirebaseFirestore getFirebaseFirestore() {
        return firebaseFirestore;
    }

    FirebaseFirestore firebaseFirestore;


    public static final int NEW_DERRUMBE_ACTIVITY_REQUEST_CODE = 1;
    public static final int SEARCH_DERRUMBE_ACTIVITY_REQUEST_CODE = 1;

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
                Intent intent = new Intent(SecondActivity.this, CreateEditTemplate.class);
                startActivityForResult(intent, NEW_DERRUMBE_ACTIVITY_REQUEST_CODE);

//
//                public void navigateEditCreateEvent(View view) {
//                    Intent intent = new Intent(MainActivity.this, .class);
//                    startActivity(intent);
//                }

            }
        });

        FloatingActionButton fab_search = findViewById(R.id.fab_search);
        fab_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { /// aca se agrega la activity a  donde se agregar los dmbes de la parte de christian
                Intent intent = new Intent(SecondActivity.this, SearchReport.class);
                startActivityForResult(intent, SEARCH_DERRUMBE_ACTIVITY_REQUEST_CODE);
            }
        });

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Query query = firebaseFirestore.collection("Reportes");
        //.orderBy("fecha").orderBy("severidad").orderBy("estado")

        FirestoreRecyclerOptions<Reporte> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Reporte>().setQuery(query, Reporte.class).build();

        itemAdapter = new ReportsListAdapter(firestoreRecyclerOptions, this);
        itemAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(itemAdapter);

        getIntent().getSerializableExtra("Details DH");


    }



    @Override

    protected void onStart() {
        super.onStart();
        itemAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        itemAdapter.stopListening();


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