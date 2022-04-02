package com.example.byb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class user_personal_printout extends AppCompatActivity {
    private Button Photo,Document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_personal_printout);
        Photo = (Button) findViewById(R.id.user_photo_upload);
        Document = (Button)findViewById(R.id.user_document_upload);

        Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_personal_printout.this,user_printout_upload_photo.class);
                startActivity(intent);
            }
        });
        Document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_personal_printout.this,user_print_upload_document_link.class);
                startActivity(intent);
            }
        });
    }
}