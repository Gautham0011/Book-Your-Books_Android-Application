package com.example.byb.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.byb.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class AdminMaintainProductsActivity extends AppCompatActivity
{ private Button applyChangesBtn,admin_delete_btn;
    private EditText name,price,description;
    private ImageView imageView;
    private String productID = "";
    private String category="";
    private DatabaseReference productref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain_products);

        productID = getIntent().getStringExtra("pid");
        category=getIntent().getStringExtra("category");

        applyChangesBtn = findViewById(R.id.apply_changes_btn);
        admin_delete_btn= findViewById(R.id.admin_delete_btn);
        name = findViewById(R.id.product_name_maintain);
        price = findViewById(R.id.product_price_maintain);
        description = findViewById(R.id.product_description_maintain);
        imageView = findViewById(R.id.product_image_maintain);
        productref= FirebaseDatabase.getInstance().getReference().child(category).child(productID);



        DisplaySpecificProductInfo();
        applyChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applychanges();
            }
        });

        admin_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteTheseProduct();
            }
        });
    }
    private void applychanges(){
        String Pname=name.getText().toString();
        String Price=price.getText().toString();
        String Pdescription=description.getText().toString();
        if(Pname.equals("")){
            Toast.makeText(AdminMaintainProductsActivity.this,"Write down product name",Toast.LENGTH_SHORT).show();
        }
        else if(Price.equals("")){
            Toast.makeText(AdminMaintainProductsActivity.this,"Write down product Price",Toast.LENGTH_SHORT).show();
        }
        else if(Pdescription.equals("")){
            Toast.makeText(AdminMaintainProductsActivity.this,"Write down product Description",Toast.LENGTH_SHORT).show();
        }
        else{
            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("pid",productID);
            productMap.put("description", Pdescription);
            productMap.put("category", category);
            productMap.put("price", Price);
            productMap.put("pname",Pname);

            productref.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<Void> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(AdminMaintainProductsActivity.this, "Changes applied successfully..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminMaintainProductsActivity.this, AdminAddActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        String message = task.getException().toString();
                        Toast.makeText(AdminMaintainProductsActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void DisplaySpecificProductInfo() {
        productref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String Pname=snapshot.child("pname").getValue().toString();
                    String Price=snapshot.child("price").getValue().toString();
                    String Pdescription=snapshot.child("description").getValue().toString();
                    String Pimage=snapshot.child("image").getValue().toString();

                    name.setText(Pname);
                    price.setText(Price);
                    description.setText(Pdescription);
                    Picasso.get().load(Pimage).into(imageView);

                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }

    private void DeleteTheseProduct(){
        productref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                Intent intent = new Intent(AdminMaintainProductsActivity.this, AdminAddActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(AdminMaintainProductsActivity.this, "The item is deleted successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }


}