package com.example.byb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class user_sem_category extends AppCompatActivity {
    private CardView ccycle, pcycle, sem3, sem4,sem5, sem6, sem7, sem8;
    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sem_category);
        ccycle=findViewById(R.id.user_c_cycle);
        pcycle=findViewById(R.id.user_p_cycle);
        sem3=findViewById(R.id.user_sem_3);
        sem4=findViewById(R.id.user_sem_4);
        sem5=findViewById(R.id.user_sem_5);
        sem6=findViewById(R.id.user_sem_6);
        sem7=findViewById(R.id.user_sem_7);
        sem8=findViewById(R.id.user_sem_8);
        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null)
        {
            type = getIntent().getExtras().get("admins").toString();
        }

        if (!type.equals("admins")) {
            ccycle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(user_sem_category.this, user_c_cycle_textbook.class);
                    startActivity(intent1);
                }
            });

            pcycle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_p_cycle_textbook.class);
                    startActivity(intent);
                }
            });

            sem3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_sem_3_texrtbook.class);
                    startActivity(intent);
                }
            });

            sem4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_sem_4_textbook.class);
                    startActivity(intent);
                }
            });

            sem5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_sem_5_textbook.class);
                    startActivity(intent);
                }
            });
            sem6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_sem_6_textbook.class);
                    startActivity(intent);
                }
            });sem7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_sem_7_textbook.class);
                    startActivity(intent);
                }
            });sem8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_sem_8_textbook.class);
                    startActivity(intent);
                }
            });
        }


        else{     ccycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(user_sem_category.this, user_c_cycle_textbook_maintain.class);
                intent.putExtra("admins","admins");
                startActivity(intent1);
            }
        });

            pcycle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_p_cycle_textbook.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });

            sem3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_sem_3_texrtbook.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });

            sem4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_sem_4_textbook.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });

            sem5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_sem_5_textbook.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });
            sem6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_sem_6_textbook.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });sem7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_sem_7_textbook.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });sem8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(user_sem_category.this, user_sem_8_textbook.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });

        }

    }

}