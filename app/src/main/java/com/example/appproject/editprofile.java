package com.example.appproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class editprofile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText ed;
    Spinner sp;
    TextView t;
    String[] options=new String[10];
    Context c=this;

    private DatabaseReference mDatabase;
    private FirebaseDatabase data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference data1 = mDatabase.child("users");
        data1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int j=0;

                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    Object data = locationSnapshot.getValue();
                    String s = null;
                    if (data != null) {
                        s = data.toString();
                    }
                    options[j]=s;
                    j++;
                    Log.d("Value", "Bhiya ji puri string to h ye :"+s);
                    String[] t  =s.split(",");
                    for(int i=0 ;i<t.length;i++){
                        Log.d("Value", "Bhaiya ji after comma sperated"+t[i]);
                        String[] v = t[i].split("=");
                        if(v[0].equalsIgnoreCase("email")){
                            Log.d("Value", "email: " + v[1]);
                        }
                    }
                }
                String[] op= new String[j];
                for (int i=0;i<j;i++){
                    op[i]=options[i];
                }
                sp = findViewById(R.id.blockspinner);

                ArrayAdapter<String> spinnerD = new ArrayAdapter<String>(c,R.layout.spinner1,op);
                spinnerD.setDropDownViewResource(R.layout.spinner1);
                sp.setAdapter(spinnerD);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        setContentView(R.layout.activity_editprofile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        sp = findViewById(R.id.blockspinner);

        ArrayAdapter<String> spinnerD = new ArrayAdapter<String>(this,R.layout.spinner1,options);
//        spinnerD.setDropDownViewResource(R.layout.spinner1);
//        sp.setAdapter(spinnerD);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference demoRef = rootRef.child("users");
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

        switch(id)
        {
            case R.id.nav_edit:
                Intent i = new Intent(this,editprofile.class);
                startActivity(i);
                return true;

            case R.id.nav_about:
                Intent i1=new Intent(this,About.class);
                startActivity(i1);
                return true;

            case R.id.nav_logout:
                Intent i2=new Intent(this,LoginActivity.class);
                startActivity(i2);
                return true;
            default:
                return true;
        }
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

    public void send(View view) {
        Intent inte = new Intent(this,editprofile.class);
        startActivity(inte);
    }


    public void block(View view) {
    }
}
