package com.example.tfmtest.presenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tfmtest.adapters.ReportsListAdapter;
import com.example.tfmtest.view.ListReportsFragment;
import com.example.tfmtest.R;
import com.example.tfmtest.model.Reporte;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SecondActivity extends AppCompatActivity{

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;
    Button signOutBtn;

    private ListReportsFragment listReportsFragment;
    private List<Reporte> reportes;


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





            listReportsFragment = new ListReportsFragment();
         //  listReportsFragment.onCreateView(listReportsF ,savedInstanceState);
//          RecyclerView recyclerView = findViewById(R.id.recyclerview);
          ReportsListAdapter adapter = new ReportsListAdapter(reportes);
//          recyclerView.setAdapter(adapter);

          adapter.setListReportsFragment(listReportsFragment);

       // Intent intent = new Intent(SecondActivity.this, ListReportsFragment.class);
       //

    //    getSupportFragmentManager().beginTransaction().replace(R.id.containerr, listReportsFragment).commit();

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