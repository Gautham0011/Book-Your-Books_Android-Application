package com.example.byb.Admin;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.byb.MainActivity;
import com.example.byb.R;
import com.example.byb.home_activity;

public class AdminAddActivity extends AppCompatActivity {
    public Button add_item;
    private Button login,admin_check_new_order,maintain_btn,photo,documentlink,announcement,messagewithuser_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add);

        add_item = (Button) findViewById(R.id.addbtn);
        login = (Button) findViewById(R.id.loginbtnaddact);
        admin_check_new_order= (Button) findViewById(R.id.admin_check_new_order);
        maintain_btn=(Button) findViewById(R.id.maintain_btn);
        photo=(Button)findViewById(R.id.printout_photo);
        announcement=(Button)findViewById(R.id.addannouncement);
        documentlink=(Button)findViewById(R.id.printout_doc);
        messagewithuser_btn=findViewById(R.id.messagewithuser_btn);



        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddActivity.this, admin_announcements.class);
                startActivity(intent);
            }
        });

        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddActivity.this, AdminCategoryActivity.class);
                startActivity(intent);
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddActivity.this, admin_printout_photo.class);
                startActivity(intent);
            }
        });

        documentlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddActivity.this, admin_printout_document.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        admin_check_new_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddActivity.this, AdminNewOrdersActivity.class);
                startActivity(intent);
            }
        });
        maintain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddActivity.this, home_activity.class);
                intent.putExtra("admins","admins");
                startActivity(intent);
            }
        });
        messagewithuser_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddActivity.this, ChatHomeAdmin.class);
                startActivity(intent);
            }
        });




    }
}
