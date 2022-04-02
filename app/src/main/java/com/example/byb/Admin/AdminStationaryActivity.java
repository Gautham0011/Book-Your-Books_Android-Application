package com.example.byb.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.byb.R;

public class AdminStationaryActivity extends AppCompatActivity {
    private CardView notebooks,accessories,sheets,files,bluebooks,record;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_stationary);
        notebooks=findViewById(R.id.admin_stationery_notebooks);
        accessories=findViewById(R.id.admin_stationery_accesories);
        sheets=findViewById(R.id.admin_stationery_a4sheets);
        files=findViewById(R.id.admin_stationery_files);
        bluebooks=findViewById(R.id.admin_stationery_bluebooks);
        record=findViewById(R.id.admin_stationery_record);
        login=findViewById(R.id.admin_login_add_new_product);

        notebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminStationaryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "NoteBooks");
                startActivity(intent);
            }
        });

        accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminStationaryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Accessories");
                startActivity(intent);
            }
        });

        sheets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminStationaryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "A4Sheets");
                startActivity(intent);
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminStationaryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Files");
                startActivity(intent);
            }
        });

        bluebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminStationaryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Blue Books");
                startActivity(intent);
            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminStationaryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "BMSIT Record");
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminStationaryActivity.this, AdminAddActivity.class);
                intent.putExtra("category", "Login");
                startActivity(intent);
            }
        });


    }


}