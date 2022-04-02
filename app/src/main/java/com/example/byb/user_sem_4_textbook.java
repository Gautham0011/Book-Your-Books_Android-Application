package com.example.byb;

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

import com.example.byb.Admin.AdminMaintainProductsActivity;
import com.example.byb.ViewHolder.ProductViewHolder;
import com.example.byb.model.Stationary_product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class user_sem_4_textbook extends AppCompatActivity {
    private DatabaseReference ProductRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private String Category="4Sem";
    private String type = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notebooks);
        recyclerView=findViewById(R.id.recycler_item);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null)
        {
            type = getIntent().getExtras().get("admins").toString();
        }

        ProductRef= FirebaseDatabase.getInstance().getReference().child(Category);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Stationary_product> options=
                new FirebaseRecyclerOptions.Builder<Stationary_product>()
                        .setQuery(ProductRef,Stationary_product.class)
                        .build();
        ProductRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseRecyclerAdapter<Stationary_product, ProductViewHolder> adapter=
                        new FirebaseRecyclerAdapter<Stationary_product, ProductViewHolder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull  ProductViewHolder holder, int i, @NonNull  Stationary_product stationary_product) {

                                Stationary_product statproduct=snapshot.child(Category).getValue(Stationary_product.class);



                                holder.productname.setText(stationary_product.getPname());
                                holder.productdescription.setText(stationary_product.getDescription());
                                holder.productprice.setText("Price = " + stationary_product.getPrice() + "Rs");
                                Picasso.get().load(stationary_product.getImage()).into(holder.productimage);
                                holder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(!type.equals("admins"))
                                        {
                                            Intent intent = new Intent(user_sem_4_textbook.this,user_product_detail_activity.class);
                                            intent.putExtra("pid",stationary_product.getPid());
                                            intent.putExtra("category","4Sem");
                                            startActivity(intent);
                                        }
                                        else{
                                            Intent intent = new Intent(user_sem_4_textbook.this, AdminMaintainProductsActivity.class);
                                            intent.putExtra("pid",stationary_product.getPid());
                                            intent.putExtra("category","4Sem");
                                            startActivity(intent);
                                        }



                                    }
                                });

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