package com.example.byb.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.byb.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class admin_announcements extends AppCompatActivity {
   private EditText admin_announcement;
   private Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_announcements);
        admin_announcement=findViewById((R.id.admin_announcement));
        update=(Button)findViewById(R.id.admin_announcement_update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateannouncement();
            }
        });


    }

    private void updateannouncement() {
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("announncement");
        HashMap<String,Object> userMap=new HashMap<>();
        userMap.put("description",admin_announcement.getText().toString());
        ref.updateChildren(userMap);

        Intent intent = new Intent(admin_announcements.this, AdminAddActivity.class);
        Toast.makeText(admin_announcements.this,"Announcement updated Successfully",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}