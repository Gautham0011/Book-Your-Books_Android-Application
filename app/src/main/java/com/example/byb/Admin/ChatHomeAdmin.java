package com.example.byb.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.byb.HomeModel.AdminmsgAdapter;
import com.example.byb.R;
import com.example.byb.model.users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChatHomeAdmin extends AppCompatActivity {


    FirebaseAuth auth;
    RecyclerView mainUserRecyclerView;
    AdminmsgAdapter adapter;
    FirebaseDatabase database;
    ArrayList<users> adminArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_home);

//        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        adminArrayList = new ArrayList<>();

        DatabaseReference reference=database.getReference().child("users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    users Users=dataSnapshot.getValue(users.class);
                    adminArrayList.add(Users);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        mainUserRecyclerView = findViewById(R.id.recycler_view_chat);
        mainUserRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new AdminmsgAdapter(ChatHomeAdmin.this,adminArrayList);
        mainUserRecyclerView.setAdapter(adapter);

    }

}