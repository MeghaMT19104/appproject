package com.example.appproject;
import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class poll_list {
    private static poll_list sPollList;
    private List<Polls> mPolls;
    private poll_list() {
        mPolls=new ArrayList<>();
        System.out.println("The poll size is "+mPolls.size());
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("polls");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPolls.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Polls p=dataSnapshot1.getValue(Polls.class);
                    mPolls.add(p);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public static poll_list get(){

        if(sPollList==null){
            sPollList=new poll_list();
        }
        return sPollList;
    }
    public static void makeNull(){
        sPollList=null;
    }
    public List<Polls> getPolls() {
        return mPolls;
    }
}
