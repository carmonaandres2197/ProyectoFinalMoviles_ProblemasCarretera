package com.example.tfmtest.presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfmtest.R;
import com.example.tfmtest.adapters.ReportsListAdapter;
import com.example.tfmtest.model.ProvinciasCantonesDistritos;
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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;


public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;
    Button signOutBtn;
    Button search_view;
    Button search_view2;
    List<Reporte> reportes;
    RecyclerView recyclerView;
    ReportsListAdapter itemAdapter;
    Reporte reporte = new Reporte();
    FirebaseFirestore firebaseFirestore;
    Button dateButton;
    DatePickerDialog datePickerDialog;

    Spinner spSeveridad;
    ArrayAdapter<String> adSeveridad;
    String[] severidadlist= {"None","Alta", "Media", "Baja"};

    public FirebaseFirestore getFirebaseFirestore() {
        return firebaseFirestore;
    }


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

            }
        });

        FloatingActionButton fab_search = findViewById(R.id.fab_search);
        fab_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { /// aca se agrega la activity a  donde se agregar los dmbes de la parte de christian
                Intent intent = new Intent(SecondActivity.this, TabActivity.class);
                startActivityForResult(intent, SEARCH_DERRUMBE_ACTIVITY_REQUEST_CODE);
            }
        });


        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Query query = firebaseFirestore.collection("Reportes").orderBy("fecha").orderBy("severidad");
        //

        FirestoreRecyclerOptions<Reporte> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Reporte>().setQuery(query, Reporte.class).build();

        itemAdapter = new ReportsListAdapter(firestoreRecyclerOptions, this);
        itemAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(itemAdapter);
        //metodo para acceder al details DH por medio de un click en un reporte de la lista
        getIntent().getSerializableExtra("Details DH");

        //spinners de search view y evento de buscar por severidad

        spSeveridad=(Spinner)findViewById(R.id.spinnerSeveridad);
        spSeveridad.setOnItemSelectedListener(this);

        adSeveridad= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, severidadlist);
        spSeveridad.setAdapter(adSeveridad);

        search_view = findViewById(R.id.searchbutton);
        search_view2 = findViewById(R.id.searchbutton2);
        initDatePicker();
        dateButton = findViewById(R.id.spinner_fecha);
        dateButton.setText(obtenerFechaActual());
        // metodo para buscar por severidad
        search_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    search_view(spSeveridad.getSelectedItem().toString());
                }
            });
        // metodos para buscar por fecha
        search_view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  search_view_by_fecha(obtenerFechaActual());
                }
            });

    }

    private String obtenerFechaActual() {
        Calendar calendar = GregorianCalendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        mes = mes + 1;

        int año = calendar.get(Calendar.YEAR);



        return makeDateString(dia,mes,año);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int año, int mes, int dia) {
                mes = mes + 1;
                String fecha = makeDateString(dia, mes, año);
                dateButton.setText(fecha);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int año = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        int formato = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this,formato, dateSetListener,año,mes,dia);
    }

    private String makeDateString(int año, int mes, int dia) {
        return  año + " " +  getMonthFormat(mes) + " " +dia;

    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";

    }


    private String getDiaSemanaFormat(int diaSemana) {
        if(diaSemana == 1)
            return "MON";
        if(diaSemana == 2)
            return "TUE";
        if(diaSemana == 3)
            return "WED";
        if(diaSemana == 4)
            return "THUR";
        if(diaSemana == 5)
            return "FRI";
        if(diaSemana == 6)
            return "SAT";
        if(diaSemana == 7)
            return "SUN";

        return "MON";

    }

    private void search_view(String s){

        Query query = firebaseFirestore.collection("Reportes");
        FirestoreRecyclerOptions<Reporte> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Reporte>().setQuery(query.orderBy("severidad").startAt(s).endAt(s+"~"),Reporte.class).build();
        itemAdapter = new ReportsListAdapter(firestoreRecyclerOptions,this);
        itemAdapter.startListening();
        recyclerView.setAdapter(itemAdapter);
    }

    private void search_view_by_fecha(String s){

        Query query = firebaseFirestore.collection("Reportes");
        FirestoreRecyclerOptions<Reporte> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Reporte>().setQuery(query,Reporte.class).build();
        itemAdapter = new ReportsListAdapter(firestoreRecyclerOptions,this);
        itemAdapter.startListening();
        recyclerView.setAdapter(itemAdapter);
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


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spinnerSeveridad:
//                    Toast.makeText(this,"pos:"+p,Toast.LENGTH_LONG).show();
                break;

            case R.id.spinner_tipoReporte:
//                    Toast.makeText(this,"pos:"+p,Toast.LENGTH_LONG).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + adapterView.getId());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void openDatePicker(View view) {
        datePickerDialog.show();

    }
}