package com.example.byb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import java.util.Calendar;
import android.os.Bundle;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.byb.Prevalent.Prevalent;
import com.example.byb.model.Stationary_product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.Button;
import com.rey.material.widget.FloatingActionButton;

import android.view.View;
import android.widget.ImageView;
import com.rey.material.widget.SnackBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import okhttp3.internal.cache.DiskLruCache;

public class user_product_detail_activity extends AppCompatActivity {

    private Button addToCardBtn;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productPrice, productDescription, productName;
    private String productID = "";
    private String category="",state="Normal";
    private Button addToCardButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_product_detail);

        productID = getIntent().getStringExtra("pid");
        category=getIntent().getStringExtra("category");

       addToCardBtn = (Button) findViewById(R.id.pd_cart_btn_cart);
       numberButton = (ElegantNumberButton) findViewById(R.id.number_btn);
         productImage = (ImageView) findViewById(R.id.product_image_details);
        productPrice = (TextView) findViewById(R.id.product_price_details);
        productDescription = (TextView) findViewById(R.id.product_description_details);
        productName = (TextView) findViewById(R.id.product_name_details);

        getProductDetails(productID);

        addToCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state.equals("Order Placed")||state.equals("Order Shipped")){
                    Toast.makeText(user_product_detail_activity.this,"You can purchase more orders ,once your order is shipped or confirmed",Toast.LENGTH_SHORT).show();
                }
                else{
                    addingToCartList();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        CheckOrderState();
    }

    private void addingToCartList() {

        String saveCurrentDate,saveCurrentTime;

        Calendar CallForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(CallForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(CallForDate.getTime());

       final DatabaseReference cartlistRef = FirebaseDatabase.getInstance().getReference().child("Cart_List");

        final HashMap<String,Object> cartMap= new HashMap<>();
        cartMap.put("pid", productID);
        cartMap.put("pname", productName.getText().toString());
        cartMap.put("price", productPrice.getText().toString());
        cartMap.put("time", saveCurrentTime);
        cartMap.put("category", category);
        cartMap.put("quantity", numberButton.getNumber());
        cartMap.put("discount", "");
        cartMap.put("Date", saveCurrentDate);

        cartlistRef.child("User view").child(Prevalent.currentonlineusers.getUsn()).child("Products").child(productID).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    cartlistRef.child("Admin view").child(Prevalent.currentonlineusers.getUsn()).child("Products").child(productID).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {

                            if (task.isSuccessful())
                            {
                                Toast.makeText(user_product_detail_activity.this, "Added To Cart", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(user_product_detail_activity.this, home_activity.class);
                                startActivity(intent);

                            }

                        }
                    });
                }
            }
        });



    }

    private void getProductDetails(String productID) {
        DatabaseReference productref = FirebaseDatabase.getInstance().getReference().child(category);

        productref.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if (snapshot.exists())
                {
                    Stationary_product products = snapshot.getValue(Stationary_product.class);
                    assert products != null;
                    productName.setText(products.getPname());
                    productPrice.setText(products.getPrice());
                    productDescription.setText(products.getDescription());
                    Picasso.get().load(products.getImage()).into(productImage);


                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }
    private void CheckOrderState()
    {
        DatabaseReference orderref;
        orderref=FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentonlineusers.getUsn()).child("Products");
        orderref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String ShippingState=snapshot.child("state").getValue().toString();

                    if(ShippingState.equals("Shipped")){
                        state="Order Shipped";
                    }
                    else if (ShippingState.equals("Not Shipped")){
                        state="Order Placed";
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}