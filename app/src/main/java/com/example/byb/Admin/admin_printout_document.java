package com.example.byb.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byb.HomeModel.testadapter;
import com.example.byb.R;
import com.example.byb.ViewHolder.testviewholder;
import com.example.byb.model.down_model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class admin_printout_document extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button update;
    private TextView txtTotalPrice,confirmordermsg;
    private int overAllTotalPrice=0;

    FirebaseFirestore db;
    ArrayList<down_model> downModelArrayList = new ArrayList<>();
    testadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_printout_document);


       setUpRV();
        db= FirebaseFirestore.getInstance();
        dataFromFirebase();

    }

    private void setUpRV() {
        recyclerView= findViewById(R.id.document_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void dataFromFirebase() {
        if(downModelArrayList.size()>0)
            downModelArrayList.clear();


        db.collection("user_printout_document")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot documentSnapshot: task.getResult()) {

                            down_model downModel= new down_model(documentSnapshot.getString("usn"),documentSnapshot.getString("name"),documentSnapshot.getString("description"),
                                    documentSnapshot.getString("url"),documentSnapshot.getString("numberofcopies"));
                            downModelArrayList.add(downModel);

                        }

                        adapter= new testadapter(admin_printout_document.this,downModelArrayList);
                        recyclerView.setAdapter(adapter);
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(admin_printout_document.this, "Error ;-.-;", Toast.LENGTH_SHORT).show();
                    }
                })
        ;
    }


}