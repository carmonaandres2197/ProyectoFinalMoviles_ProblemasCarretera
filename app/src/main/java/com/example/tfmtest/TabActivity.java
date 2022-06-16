package com.example.tfmtest;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TabActivity extends AppCompatActivity
        implements NavigationBarView.OnItemSelectedListener {

    ListPendientesFragment listPendientesFragment;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        listPendientesFragment = new ListPendientesFragment();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:

                return true;

            case R.id.pendientes:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, listPendientesFragment).commit();
                return true;

            case R.id.settings:
                return true;
        }
        return false;
    }
}
