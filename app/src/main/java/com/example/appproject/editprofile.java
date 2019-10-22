package com.example.appproject;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

public class editprofile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText ed;
    Spinner sp;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        sp = findViewById(R.id.statusspinner);
        String[] status = new String[]{
                "Student",
                "Faculty"
        };
        ArrayAdapter<String> spinnerA = new ArrayAdapter<String>(this,R.layout.spinner1,status);
        spinnerA.setDropDownViewResource(R.layout.spinner1);
        sp.setAdapter(spinnerA);
        sp = findViewById(R.id.sesionspinner);
        String[] session = new String[]{
                "BTECH-First Year",
                "BTECH-Second Year",
                "BTECH-Third Year",
                "BTECH-Fourth Year",
                "MTECH-First Year",
                "MTECH-Second Year",
                "N/A"
        };
        ArrayAdapter<String> spinnerB = new ArrayAdapter<String>(this,R.layout.spinner1,session);
        spinnerB.setDropDownViewResource(R.layout.spinner1);
        sp.setAdapter(spinnerB);
        sp = findViewById(R.id.genderspinner);
        String[] gender = new String[]{
                "Male",
                "Female",
                "Others"
        };
        ArrayAdapter<String> spinnerC = new ArrayAdapter<String>(this,R.layout.spinner1,gender);
        spinnerC.setDropDownViewResource(R.layout.spinner1);
        sp.setAdapter(spinnerC);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.editprofile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    protected void edit(View v){
        Log.d("dekho", "bhiya button to dab gya");
        t = findViewById(R.id.yournametext);
        t.setVisibility(View.GONE);
        ed = findViewById(R.id.yournameedit);
        ed.setVisibility(View.VISIBLE);
        t = findViewById(R.id.descriptiontext);
        t.setVisibility(View.GONE);
        ed = findViewById(R.id.descriptionedit);
        ed.setVisibility(View.VISIBLE);
        t = findViewById(R.id.statustext);
        t.setVisibility(View.GONE);
        sp = findViewById(R.id.statusspinner);
        sp.setVisibility(View.VISIBLE);
        t = findViewById(R.id.sessiontext);
        t.setVisibility(View.GONE);
        sp = findViewById(R.id.sesionspinner);
        sp.setVisibility(View.VISIBLE);
        t = findViewById(R.id.gendertext);
        t.setVisibility(View.GONE);
        sp = findViewById(R.id.genderspinner);
        sp.setVisibility(View.VISIBLE);
        t = findViewById(R.id.agetext);
        t.setVisibility(View.GONE);
        ed = findViewById(R.id.ageedit);
        ed.setVisibility(View.VISIBLE);
        Button bt = findViewById(R.id.butonSave);
        bt.setVisibility(View.VISIBLE);

    }
}
