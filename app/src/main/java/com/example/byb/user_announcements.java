package com.example.byb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.byb.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class user_announcements extends AppCompatActivity {
private TextView user_announcement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_announcements);
        user_announcement=findViewById(R.id.user_announcement);

        announcementdisplay(user_announcement);
    }
    private void announcementdisplay(TextView user_announcement){
        DatabaseReference Userref= FirebaseDatabase.getInstance().getReference().child("announncement");
        Userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                        String description=datasnapshot.child("description").getValue().toString();

                        user_announcement.setText(description);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}