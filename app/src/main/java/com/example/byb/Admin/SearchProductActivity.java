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
import android.widget.Button;
import com.rey.material.widget.EditText;

import com.example.byb.R;
import com.example.byb.ViewHolder.ProductViewHolder;
import com.example.byb.model.Stationary_product;
import com.example.byb.user_bluebooks;
import com.example.byb.user_product_detail_activity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class SearchProductActivity extends AppCompatActivity {
    private Button SearchBtn;
    private EditText inputText;
    private RecyclerView searchList;
    private String  SearchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        inputText = findViewById(R.id.search_product_name);
        SearchBtn = findViewById(R.id.search_btn);
        searchList = findViewById(R.id.search_list);
        searchList.setLayoutManager(new LinearLayoutManager(SearchProductActivity.this));

        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchInput=inputText.getText().toString().toUpperCase();
                onStart();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("All_Products");
        FirebaseRecyclerOptions<Stationary_product>options=
                new FirebaseRecyclerOptions.Builder<Stationary_product>()
                .setQuery(reference.orderByChild("pname").startAt(SearchInput),Stationary_product.class)
                .build();

        FirebaseRecyclerAdapter<Stationary_product, ProductViewHolder>adapter=
                new FirebaseRecyclerAdapter<Stationary_product, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull @NotNull ProductViewHolder holder, int i, @NonNull @NotNull Stationary_product stationary_product) {
                        holder.productname.setText(stationary_product.getPname());
                        holder.productdescription.setText(stationary_product.getDescription());
                        holder.productprice.setText("Price = " + stationary_product.getPrice() + "Rs");
                        Picasso.get().load(stationary_product.getImage()).into(holder.productimage);
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    Intent intent = new Intent(SearchProductActivity.this, user_product_detail_activity.class);
                                    intent.putExtra("pid",stationary_product.getPid());
                                    intent.putExtra("category",stationary_product.getCategory());
                                    startActivity(intent);

                            }
                        });
                    }

                    @NonNull
                    @NotNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_product_item_layout,parent,false);
                        ProductViewHolder holder=new ProductViewHolder(view);
                        return holder;
                    }
                };
        searchList.setAdapter(adapter);
        adapter.startListening();
    }
}