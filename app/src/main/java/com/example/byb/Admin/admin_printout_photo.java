package com.example.byb.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.byb.R;
import com.example.byb.ViewHolder.ProductViewHolder;
import com.example.byb.model.Stationary_product;
import com.example.byb.model.printout_photo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class admin_printout_photo extends AppCompatActivity {
    private DatabaseReference ProductRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private String Category="user_printout_photo";
    private String type = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notebooks);
        recyclerView=findViewById(R.id.recycler_item);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ProductRef= FirebaseDatabase.getInstance().getReference().child(Category);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<printout_photo> options=
                new FirebaseRecyclerOptions.Builder<printout_photo>()
                        .setQuery(ProductRef,printout_photo.class)
                        .build();
        ProductRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseRecyclerAdapter<printout_photo, ProductViewHolder> adapter=
                        new FirebaseRecyclerAdapter<printout_photo, ProductViewHolder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull @NotNull ProductViewHolder holder, int i, @NonNull @NotNull printout_photo printout_photo) {
                                holder.productname.setText(printout_photo.getUsn());
                                holder.productdescription.setText(printout_photo.getDescription());
                                holder.productprice.setText("Copies = " + printout_photo.getNumberofcopies() );
                                Picasso.get().load(printout_photo.getImage()).into(holder.productimage);
                            }



                            @NotNull
                            @Override
                            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_product_item_layout,parent,false);
                                ProductViewHolder holder=new ProductViewHolder(view);
                                return holder;
                            }
                        };

                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });



    }
}