package com.example.byb.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.byb.R;

public class AdminCategoryActivity extends AppCompatActivity {
    private ConstraintLayout stationary,textbooks,labmanual,forms;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("AdminCategoryActivity","Successes");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        stationary=findViewById(R.id.admin_stationary);
        textbooks=findViewById(R.id.admin_textbooks);
        labmanual=findViewById(R.id.admin_labmanual);
        forms=findViewById(R.id.admin_forms);




        stationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminStationaryActivity.class);
                startActivity(intent);
            }
        });
        textbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminTextbookCategory.class);
                startActivity(intent);
            }
        });
    }
}