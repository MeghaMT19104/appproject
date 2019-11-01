package com.example.appproject;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;



import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
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
    int blockcount,flag=0;
    String[] options=new String[10];

    final Context context = this;
    private DatabaseReference mDatabase;
    private FirebaseDatabase data;
    private ValueEventListener vl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference data1 = mDatabase.child("users");
        data1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int j = 0;
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {

                    String k = locationSnapshot.getKey();
                    if (!k.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        options[j] = k;
                        j++;
                    }


                    String[] op = new String[j];
                    for (int i = 0; i < j; i++) {
                        op[i] = options[i];
                    }
                    sp = findViewById(R.id.blockspinner);

                    ArrayAdapter<String> spinnerD = new ArrayAdapter<String>(context, R.layout.spinner1, op);
                    spinnerD.setDropDownViewResource(R.layout.spinner1);
                    sp.setAdapter(spinnerD);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        setContentView(R.layout.activity_editprofile);
        findViewById(R.id.b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                block(view);
            }
        });
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
            case R.id.nav_request:
                Intent i3=new Intent(this,MainActivity.class);
                startActivity(i3);
                return true;
            default:
                return true;
        }
    }
    protected boolean check(final String  z, final String u){
        if(u.equals(z))
        {
            return true;
        }
        else{
            return false;
        }





    }
    protected void edit(View v){

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

    protected void rem(DatabaseReference d,ValueEventListener kl){
        d.removeEventListener(kl);
    }
    protected void block(View V){
        final Object[] v = new Object[1];
        String b = new String();
        final String[] kl=new String[1];
        sp = findViewById(R.id.blockspinner);
        final String value = sp.getSelectedItem().toString();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference d2=mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("BlockList");
        flag=0;



        final DatabaseReference data1 = mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        vl = data1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() < 3) {
                    kl[0] = "User" + 1;
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("BlockCount").setValue(1);
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("BlockList").child(kl[0]).setValue(value);
                    rem(data1, vl);
                    return;

                } else {
                    for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {

                        String k = locationSnapshot.getKey();
                        if (k.equals("BlockCount")) {
                            blockcount = Integer.parseInt(locationSnapshot.getValue().toString());

                        }
                        if (k.equals("BlockList")) {
                            flag = 0;
                            for (DataSnapshot gh : locationSnapshot.getChildren()) {
                                String df = gh.getValue().toString();
                                boolean t = check(df, value);
                                if (t == true) {


                                    rem(data1, vl);
                                    Toast.makeText(context, "Already blocked!! Have Fun!!! ", Toast.LENGTH_SHORT).show();
                                    return;
                                } else {
                                    flag = 1;
                                }

                            }

                            if (flag == 1) {
                                Log.d("galti", locationSnapshot.getValue().toString());
                                DatabaseReference d = locationSnapshot.getRef();
                                int h = blockcount + 1;
                                kl[0] = "User" + h;
                                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("BlockCount").setValue(h);

                                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("BlockList").child(kl[0]).setValue(value);
                                rem(data1, vl);
                                return;
                            }

                        }

                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}