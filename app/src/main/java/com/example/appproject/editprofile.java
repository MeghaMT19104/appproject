package com.example.appproject;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class editprofile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText Desc, age, ed, name;
    TextView Desc1, age1, ed1, name1,gender1,session1,status1;
    boolean chooseimage;
    int blockcount,flag=0;
    final Context context = this;

    Spinner sp1,sp2,sp3,sp;
    TextView t;
    ImageView Editpic;

    String[] options=new String[10];
    private static final int CHOOSE_IMAGE = 101;
    String Uri;
    private ValueEventListener vl;
    private DatabaseReference mDatabase,mDatabase1;
    private FirebaseDatabase data;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mFirebaseAuth;
    private Uri mImageUri;
    private StorageTask mUploadTask;
    private StorageReference mStorageRef;
    FirebaseUser user;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        // LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        firebaseDatabase=FirebaseDatabase.getInstance();
        mDatabase1=firebaseDatabase.getReference("profiles");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference data1 = mDatabase.child("users");
        mFirebaseAuth=FirebaseAuth.getInstance();
        user=mFirebaseAuth.getCurrentUser();
        //firebaseDatabase=FirebaseDatabase.getInstance();
        // mDatabase=firebaseDatabase.getReference("profiles");
        DatabaseReference da = FirebaseDatabase.getInstance().getReference("profiles");
        Editpic=(ImageView)findViewById(R.id.Edit_pic);
        Desc = (EditText) findViewById(R.id.descriptionedit);
        age = (EditText) findViewById(R.id.ageedit);

        name = (EditText) findViewById(R.id.yournameedit);
        Desc1 = (TextView) findViewById(R.id.descriptiontext);
        age1 = (TextView) findViewById(R.id.agetext);
        name1 = (TextView) findViewById(R.id.yournametext);
        status1 = (TextView) findViewById(R.id.statustext);
        gender1 = (TextView) findViewById(R.id.gendertext);
        session1 = (TextView) findViewById(R.id.sessiontext);
        mStorageRef = FirebaseStorage.getInstance().getReference("Uploads");


        //Query query=mDatabase.orderByChild("email").equalTo(user.getEmail());
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {

                //get data
                String Name=""+ds.child(user.getUid()).child("Name").getValue();
                System.out.println("checking name"+Name);
                String desc=""+ds.child(user.getUid()).child("Description").getValue();
                String session=""+ds.child(user.getUid()).child("Session").getValue();
                String status=""+ds.child(user.getUid()).child("Status").getValue();
                String gender=""+ds.child(user.getUid()).child("Gender").getValue();
                //Uri=""+ds.child(user.getUid()).child("imageUrl").getValue();
                String Age=""+ds.child(user.getUid()).child("Age").getValue();
                Desc.setText(desc);
                age.setText(Age);
                name.setText(Name);
           /*     status.setSelection(options.indexOf("status");
                  session.setText(session);
                   gender.setText(session); */
                Desc1.setText(desc);
                age1.setText(Age);
                name1.setText(Name);
                status1.setText(status);
                session1.setText(session);
                gender1.setText(gender);
                //sp1.setSelection(options.indexOf("status"));
                /*   try
                       { //if image is there
                          Picasso.get().load(Uri).into(Editpic);
                          System.out.println("your image is stored here" +Uri);
                       }
                   catch(Exception e)
                   { //if not there set an defalut image
                       Picasso.get().load(R.drawable.abc).into(Editpic);
                   }*/

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;
//        data1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                int j = 0;
//                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
//
//                    String k = locationSnapshot.getKey();
//                    if (!k.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
//                        options[j] = k;
//                        j++;
//                    }
//
//
//                    String[] op = new String[j];
//                    for (int i = 0; i < j; i++) {
//                        op[i] = options[i];
//                    }
//                    sp = findViewById(R.id.blockspinner);
//
//                    ArrayAdapter<String> spinnerD = new ArrayAdapter<String>(context, R.layout.spinner1, op);
//                    spinnerD.setDropDownViewResource(R.layout.spinner1);
//                    sp.setAdapter(spinnerD);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        findViewById(R.id.b).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                block(view);
//            }
//        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        sp1 = findViewById(R.id.statusspinner);
        String[] status = new String[]{
                "Student",
                "Faculty"
        };
        ArrayAdapter<String> spinnerA = new ArrayAdapter<String>(this, R.layout.spinner1, status);
        spinnerA.setDropDownViewResource(R.layout.spinner1);
        sp1.setAdapter(spinnerA);
        sp2 = findViewById(R.id.sesionspinner);
        String[] session = new String[]{
                "BTECH-First Year",
                "BTECH-Second Year",
                "BTECH-Third Year",
                "BTECH-Fourth Year",
                "MTECH-First Year",
                "MTECH-Second Year",
                "N/A"
        };
        ArrayAdapter<String> spinnerB = new ArrayAdapter<String>(this, R.layout.spinner1, session);
        spinnerB.setDropDownViewResource(R.layout.spinner1);
        sp2.setAdapter(spinnerB);
        sp3 = findViewById(R.id.genderspinner);
        String[] gender = new String[]{
                "Male",
                "Female",
                "Others"
        };
        ArrayAdapter<String> spinnerC = new ArrayAdapter<String>(this, R.layout.spinner1, gender);
        spinnerC.setDropDownViewResource(R.layout.spinner1);
        sp3.setAdapter(spinnerC);
//        sp = findViewById(R.id.blockspinner);
//        Log.d("galti",sp.toString());

        //   ArrayAdapter<String> spinnerD = new ArrayAdapter<String>(this, R.layout.spinner1, options);
//        spinnerD.setDropDownViewResource(R.layout.spinner1);
//        sp.setAdapter(spinnerD);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference demoRef = rootRef.child("users");
        findViewById(R.id.pen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit(view);
                //  givedialogbox();
            }
        });
        findViewById(R.id.butonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
                /* if(mUploadTask !=null)
                {
                if(mUploadTask!=null)
                {
                }
                else
                {
                uploadfile();
                }
                 */
            }
        });
        Editpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();
                chooseimage=true;
                //submit onclicklistener //upload function
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_IMAGE  && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            //Picasso.with
            //Picasso.get().load(mImageUri).into(Editpic);
            //Picasso.with(this).load(mImageUri).into(Editpic);
            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),mImageUri);
                Editpic.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void uploadFile() {
        if(mImageUri !=null)
        {
            final StorageReference fileReference=mStorageRef.child("ProfileImages"+System.currentTimeMillis()
                    +".jpg");
            mUploadTask=fileReference.putFile(mImageUri);
            Task<Uri> urlTask=mUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                    } else {
                        // Handle failures
                        // ...
                    }
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(editprofile.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE);
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
    protected boolean check(final String  z, final String u){
        if(u.equals(z))
        {
            return true;
        }
        else{
            return false;
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

        switch (id) {
            case R.id.nav_edit:
                Intent i = new Intent(this, editprofile.class);
                startActivity(i);
                return true;

            case R.id.nav_about:
                Intent i1 = new Intent(this, About.class);
                startActivity(i1);
                return true;

            case R.id.nav_logout:
                Intent i2 = new Intent(this, LoginActivity.class);
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

    public void submit() {
        String Session, Description, Age, Status, Gender, Name;
        String Uid,Image;
        //uploadFile();
        //  Image=null;
        /*if(chooseimage)
        {
            uploadFile();
        } */
        Name = name.getText().toString();
        sp = findViewById(R.id.sesionspinner);
        Session = sp.getSelectedItem().toString();
        // sp=findViewById(R.id.sesionspinner);
        Description = Desc.getText().toString();
        //sp=findViewById(R.id.sesionspinner);
        Age = age.getText().toString();
        sp = findViewById(R.id.statusspinner);
        Status = sp.getSelectedItem().toString();
        sp = findViewById(R.id.genderspinner);
        Gender = sp.getSelectedItem().toString();
        if(Name.isEmpty())
        {
            name.setError("Your name");
            name.requestFocus();
            return;
        }
        if (Description.isEmpty()) {
            Desc.setError("Tell people something about yourself");
            Desc.requestFocus();
            return;
        }
        if (Age.isEmpty()) {
            age.setError("Age is left blank");
            age.requestFocus();
            return;
        }
        try {
            // checking valid integer using parseInt() method
            Integer.parseInt(Age);
            if (Integer.parseInt(Age) <= 15) {
                age.setError("your age must be >15");
                age.requestFocus();
                return;
            }
            if (Integer.parseInt(Age) >= 100) {
                age.setError("you can't be that old");
                age.requestFocus();
                return;
            }
        }
        catch (NumberFormatException e)
        {
            age.setError("Age should be integer");
            age.requestFocus();
            return;
        }

        Profiledetails profile = new Profiledetails(Name, Description, Session, Status, Gender, Age);
        Log.d("profile", "profile details");
        FirebaseDatabase.getInstance().getReference("profiles").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(profile)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            finish();

                            Toast.makeText(editprofile.this, "Profile  details saved", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(editprofile.this, editprofile
                                    .class));
                        } else {
                            //failure
                            Toast.makeText(editprofile.this, "Profile details not saved", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(editprofile.this, editprofile
                                    .class));
                        }
                    }
                });
    }




    protected void edit(View view) {
        Log.d("dekho", "bhiya button to dab gya");
        /* Editpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();
                //submit onclicklistener //upload function
            }
        }); */
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
      /* findViewById(R.id.butonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
*/
    }
    protected void rem(DatabaseReference d,ValueEventListener kl){
        d.removeEventListener(kl);
    }

    public void send(View view) {
        Intent inte = new Intent(this, editprofile.class);
        startActivity(inte);
    }


//    protected void block(View V){
//        final Object[] v = new Object[1];
//        String b = new String();
//        final String[] kl=new String[1];
//        sp = findViewById(R.id.blockspinner);
//        final String value = sp.getSelectedItem().toString();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference d2=mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("BlockList");
//        flag=0;
//
//
//
//        final DatabaseReference data1 = mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
//
//        vl = data1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.getChildrenCount() < 3) {
//                    kl[0] = "User" + 1;
//                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("BlockCount").setValue(1);
//                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("BlockList").child(kl[0]).setValue(value);
//                    rem(data1, vl);
//                    return;
//
//                } else {
//                    for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
//
//                        String k = locationSnapshot.getKey();
//                        if (k.equals("BlockCount")) {
//                            blockcount = Integer.parseInt(locationSnapshot.getValue().toString());
//
//                        }
//                        if (k.equals("BlockList")) {
//                            flag = 0;
//                            for (DataSnapshot gh : locationSnapshot.getChildren()) {
//                                String df = gh.getValue().toString();
//                                boolean t = check(df, value);
//                                if (t == true) {
//
//
//                                    rem(data1, vl);
//                                    Toast.makeText(context, "Already blocked!! Have Fun!!! ", Toast.LENGTH_SHORT).show();
//                                    return;
//                                } else {
//                                    flag = 1;
//                                }
//
//                            }
//
//                            if (flag == 1) {
//                                Log.d("galti", locationSnapshot.getValue().toString());
//                                DatabaseReference d = locationSnapshot.getRef();
//                                int h = blockcount + 1;
//                                kl[0] = "User" + h;
//                                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("BlockCount").setValue(h);
//
//                                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("BlockList").child(kl[0]).setValue(value);
//                                rem(data1, vl);
//                                return;
//                            }
//
//                        }
//
//                    }
//
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//    }

}