package com.example.byb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class user_stationary_category extends AppCompatActivity {
    private CardView notebooks,accessories,sheets,files,bluebooks,record;
    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stationary_category);
        notebooks=findViewById(R.id.user_stationery_notebooks);
        accessories=findViewById(R.id.user_stationery_accesories);
        sheets=findViewById(R.id.user_stationery_a4sheets);
        files=findViewById(R.id.user_stationery_files);
        bluebooks=findViewById(R.id.user_stationery_bluebooks);
        record=findViewById(R.id.user_stationery_record);
        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null)
        {
            type = getIntent().getExtras().get("admins").toString();
        }

        if (!type.equals("admins")) {
            notebooks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(user_stationary_category.this, user__notebooks.class);
                    startActivity(intent1);
                }
            });

            accessories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_stationary_category.this, user_accesssories.class);
                    startActivity(intent);
                }
            });

            sheets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_stationary_category.this, user_sheets.class);
                    startActivity(intent);
                }
            });

            files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_stationary_category.this, user_files.class);
                    startActivity(intent);
                }
            });

            bluebooks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_stationary_category.this, user_bluebooks.class);
                    startActivity(intent);
                }
            });
            record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_stationary_category.this, user_record.class);
                    startActivity(intent);
                }
            });
        }


        else{notebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(user_stationary_category.this, user_notebook_maintain.class);
                intent.putExtra("admins","admins");
                startActivity(intent1);
            }
        });

            accessories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_stationary_category.this, user_accesssories.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });

            sheets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_stationary_category.this, user_sheets.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });

            files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_stationary_category.this, user_files.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });

            bluebooks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_stationary_category.this, user_bluebooks.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });
            record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_stationary_category.this, user_record.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });

        }

    }

}