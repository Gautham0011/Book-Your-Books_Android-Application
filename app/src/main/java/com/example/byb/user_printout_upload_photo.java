package com.example.byb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.byb.Prevalent.Prevalent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class user_printout_upload_photo extends AppCompatActivity {
    private String categoryname,description, numberofcopies,saveCurrentDate, saveCurrentTime;
    private EditText  new_user_photo_description,new_user_number_of_copies;
    private Button add_new_photo_btn;
    private ImageView add_new_user_print_image;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private ProgressDialog loadingBar;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_printout_upload_photo);
        add_new_user_print_image=(ImageView) findViewById(R.id.add_new_user_print_image);
        new_user_photo_description=(EditText)findViewById(R.id.new_user_photo_description);
        new_user_number_of_copies=(EditText)findViewById(R.id.new_user_number_of_copies);
        add_new_photo_btn=(Button) findViewById(R.id.add_new_photo_btn);

        categoryname="user_printout_photo";
        ProductImagesRef= FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductRef= FirebaseDatabase.getInstance().getReference().child(categoryname);


        loadingBar = new ProgressDialog(this);


        add_new_user_print_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        add_new_photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });


    }
    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GalleryPick&&resultCode==RESULT_OK&&data!=null){
            ImageUri = data.getData();
            add_new_user_print_image.setImageURI(ImageUri);
        }
    }

    private void ValidateProductData(){
        description=new_user_photo_description.getText().toString();
        numberofcopies =new_user_number_of_copies.getText().toString();

        if (ImageUri == null)
        {
            Toast.makeText(this, "Product image is mandatory...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(description))
        {
            Toast.makeText(this, "Please write product description...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(numberofcopies))
        {
            Toast.makeText(this, "Please write product Price...", Toast.LENGTH_SHORT).show();
        }

        else
        {
            StoreProductInformation();
        }
    }
    public void StoreProductInformation(){
        loadingBar.setTitle("Add New Printout photo");
        loadingBar.setMessage("Dear User, please wait while we are adding the printout photo.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;
        StorageReference filepath=ProductImagesRef.child(ImageUri.getLastPathSegment()+productRandomKey+"jpg");
        final UploadTask uploadtask=filepath.putFile(ImageUri);

        uploadtask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message=e.toString();
                Toast.makeText(user_printout_upload_photo.this,"ERROR"+message,Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(user_printout_upload_photo.this,"Product image uploaded Successfully",Toast.LENGTH_SHORT).show();
                Task<Uri> urlTask=uploadtask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull  Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(user_printout_upload_photo.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                        }

                    }
                });
            }
        });



    }
    public void SaveProductInfoToDatabase(){
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("pid", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("description", description);
        productMap.put("image", downloadImageUrl);
        productMap.put("category",categoryname);
        productMap.put("numberofcopies", numberofcopies);
        productMap.put("usn", Prevalent.currentonlineusers.getUsn());

        ProductRef.child(Prevalent.currentonlineusers.getUsn()).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(user_printout_upload_photo.this, home_activity.class);
                            startActivity(intent);
                            loadingBar.dismiss();
                            Toast.makeText(user_printout_upload_photo.this, "Product is added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {   loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(user_printout_upload_photo.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}