package com.example.byb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.byb.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class user_print_upload_document_link extends AppCompatActivity {
    private EditText new_user_document_description,Name,new_user_number_of_copies_document;
    private Button update;
    FirebaseFirestore firebaseFirestore;
    DatabaseReference pdfref;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_print_upload_document_link);
        new_user_document_description=(EditText) findViewById((R.id.new_user_document_description));
        Name=(EditText) findViewById((R.id.new_user_document_name));
        new_user_number_of_copies_document=(EditText) findViewById((R.id.new_user_number_of_copies_document));
        update=(Button)findViewById(R.id.update_document_btn);
        storageReference= FirebaseStorage.getInstance().getReference();
        pdfref= FirebaseDatabase.getInstance().getReference().child("user_printout_document");
        firebaseFirestore=FirebaseFirestore.getInstance();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatepdf();
            }
        });


    }

    private void updatepdf() {
        Intent intent=new Intent();
        intent.setType("applicatiion/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF File"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK &&data!=null&&data.getData()!=null){
            uploadpdffile(data.getData());

        }
    }

    private void uploadpdffile(Uri data) {
        String description= new_user_document_description.getText().toString().trim();
        String numberofcopies= new_user_number_of_copies_document.getText().toString().trim();
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("uploading..");
        progressDialog.show();
        StorageReference reference=storageReference.child("print_document/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uri=taskSnapshot.getStorage().getDownloadUrl();
                        while(!uri.isComplete());
                        Uri url=uri.getResult();

                        //Firestore
                        Map<String,Object>map=new HashMap<>();
                        map.put("usn",Prevalent.currentonlineusers.getUsn());
                        map.put("name",Name.getText().toString());
                        map.put("description",description);
                        map.put("url",url.toString());
                        map.put("numberofcopies",numberofcopies);
                        firebaseFirestore.collection("user_printout_document")
                                .add(map)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(user_print_upload_document_link.this,"File Uploaded",Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(user_print_upload_document_link.this, home_activity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull @NotNull UploadTask.TaskSnapshot snapshot) {
                double progress=(100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded:"+(int)progress+"%");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

            }
        });
    }
}
