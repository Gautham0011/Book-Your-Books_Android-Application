package com.example.byb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.byb.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class user_confirm_final_order extends AppCompatActivity {

    private EditText nameEdittext, phoneEditText, addressEditText, cityEditText;
    private Button confirmOrderBtn;

    private String totalamount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_confirm_final_order);

        totalamount = getIntent().getStringExtra("Total Price");
        Toast.makeText(this, "Total Price = Rs. " + totalamount, Toast.LENGTH_SHORT).show();


        confirmOrderBtn = (Button) findViewById(R.id.Confrim_Final_order_button);
        nameEdittext = (EditText) findViewById(R.id.Shipment_Name);
        phoneEditText = (EditText) findViewById(R.id.Shipment_PhoneNumber);
        addressEditText = (EditText) findViewById(R.id.Shipment_Address);
        cityEditText = (EditText) findViewById(R.id.Shipment_City);

        confirmOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();
            }
        });
    }

    private void Check() {
        if (TextUtils.isEmpty(nameEdittext.getText().toString()))
        {
            Toast.makeText(this, "Please Proivde Your Full Name", Toast.LENGTH_SHORT).show();
        }

        else  if (TextUtils.isEmpty(phoneEditText.getText().toString()))
        {
            Toast.makeText(this, "Please Proivde Your Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            Toast.makeText(this, "Please Proivde Your Address", Toast.LENGTH_SHORT).show();
        }
        else  if (TextUtils.isEmpty(cityEditText.getText().toString()))
        {
            Toast.makeText(this, "Please Proivde Your City Name", Toast.LENGTH_SHORT).show();
        }
        else
        {
            confirmOrder();
        }
    }

    private void confirmOrder()
    {
        final String saveCurrentDate,saveCurrentTime;

        Calendar CallForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(CallForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(CallForDate.getTime());


        final DatabaseReference adminorderRef = FirebaseDatabase.getInstance().getReference().child("AdminOrders").child(Prevalent.currentonlineusers.getUsn());

        HashMap<String, Object> adminordersMap = new HashMap<>();
        adminordersMap.put("totalAmount",totalamount);
        adminordersMap.put("name", nameEdittext.getText().toString());
        adminordersMap.put("phone", phoneEditText.getText().toString());
        adminordersMap.put("address", addressEditText.getText().toString());
        adminordersMap.put("city", cityEditText.getText().toString());
        adminordersMap.put("usn", Prevalent.currentonlineusers.getUsn());
        adminordersMap.put("time", saveCurrentTime);
        adminordersMap.put("date", saveCurrentDate);
        adminordersMap.put("state", "Not Shipped");

        adminorderRef.updateChildren(adminordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(user_confirm_final_order.this, " Placed ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        final DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentonlineusers.getUsn());

        HashMap<String, Object> ordersMap = new HashMap<>();
        ordersMap.put("totalAmount",totalamount);
        ordersMap.put("name", nameEdittext.getText().toString());
        ordersMap.put("phone", phoneEditText.getText().toString());
        ordersMap.put("address", addressEditText.getText().toString());
        ordersMap.put("city", cityEditText.getText().toString());
        ordersMap.put("usn", Prevalent.currentonlineusers.getUsn());
        ordersMap.put("time", saveCurrentTime);
        ordersMap.put("date", saveCurrentDate);
        ordersMap.put("state", "Not Shipped");

        orderRef.child("Products").updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    FirebaseDatabase.getInstance().getReference().child("Cart_List").child("User view").child(Prevalent.currentonlineusers.getUsn()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(user_confirm_final_order.this, "Your Final Order Is Been Placed Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(user_confirm_final_order.this, home_activity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();

                            }
                        }
                    });
                }
            }
        });


    }
}