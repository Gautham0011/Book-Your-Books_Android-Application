package com.example.byb.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.byb.R;

public class AdminTextbookCategory extends AppCompatActivity {
    private CardView ccycle, pcycle, sem3, sem4,sem5, sem6, sem7, sem8;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_textbook_category);
        ccycle=findViewById(R.id.admin_c_cycle);
        pcycle=findViewById(R.id.admin_p_cycle);
        sem3=findViewById(R.id.admin_sem_3);
        sem4=findViewById(R.id.admin_sem_4);
        sem5=findViewById(R.id.admin_sem_5);
        sem6=findViewById(R.id.admin_sem_6);
        sem7=findViewById(R.id.admin_sem_7);
        sem8=findViewById(R.id.admin_sem_8);
        login=findViewById(R.id.admin_login_add_new_textbook);

        ccycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTextbookCategory.this, AdminAddNewTextbookActivity.class);
                intent.putExtra("category", "CCycle");
                startActivity(intent);
            }
        });

        pcycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTextbookCategory.this, AdminAddNewTextbookActivity.class);
                intent.putExtra("category", "PCycle");
                startActivity(intent);
            }
        });

        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTextbookCategory.this, AdminAddNewTextbookActivity.class);
                intent.putExtra("category", "3Sem");
                startActivity(intent);
            }
        });

        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTextbookCategory.this, AdminAddNewTextbookActivity.class);
                intent.putExtra("category", "4Sem");
                startActivity(intent);
            }
        });

        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTextbookCategory.this, AdminAddNewTextbookActivity.class);
                intent.putExtra("category", "5Sem");
                startActivity(intent);
            }
        });

        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTextbookCategory.this, AdminAddNewTextbookActivity.class);
                intent.putExtra("category", "6Sem");
                startActivity(intent);
            }
        });

        sem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTextbookCategory.this, AdminAddNewTextbookActivity.class);
                intent.putExtra("category", "7Sem");
                startActivity(intent);
            }
        });

        sem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTextbookCategory.this, AdminAddNewTextbookActivity.class);
                intent.putExtra("category", "8Sem");
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTextbookCategory.this, AdminAddActivity.class);
                startActivity(intent);
            }
        });

    }
}