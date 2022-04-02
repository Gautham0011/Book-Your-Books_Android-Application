package com.example.byb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import com.example.byb.Prevalent.Prevalent;
import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {
    private CircleImageView settings_profile_image;
    private TextView settings_close_btn,settings_update_btn,settings_profile_image_change_btn;
    private EditText settings_fullname,settings_userbranch,settings_usermobileno,settings_useremail,settings_usersec,settings_userusn;
    private Uri imageuri;
    private Button securityQuestionBtn;
    private String myUrl=" ";
    private StorageReference storageProfilePictureRef;
    private String checker="";
    private ProgressDialog loadingBar;
    private StorageTask uploadtask;
    private String type = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        storageProfilePictureRef=FirebaseStorage.getInstance().getReference().child("Profile Pictures");
        loadingBar = new ProgressDialog(this);

        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null)
        {
            type = getIntent().getExtras().get("member").toString();
        }

        settings_profile_image=findViewById(R.id.settings_profile_image);
        settings_profile_image_change_btn=findViewById((R.id.settings_profile_image_change_btn));
        settings_close_btn=findViewById(R.id.settings_close_btn);
        settings_update_btn=findViewById(R.id.settings_update_btn);
        settings_fullname=findViewById(R.id.settings_fullname);
        settings_userbranch=findViewById(R.id.settings_userbranch);
        settings_usermobileno=findViewById(R.id.settings_usermobileno);
        settings_fullname=findViewById(R.id.settings_fullname);
        settings_useremail=findViewById(R.id.settings_useremail);
        settings_userusn=findViewById((R.id.userusn));
        settings_usersec=findViewById(R.id.settings_sec);
        securityQuestionBtn=findViewById(R.id.security_question_button);
        Log.d("Success","worked");


        userinfodisplay(settings_profile_image,settings_fullname,settings_userbranch,settings_usermobileno,settings_useremail);

        settings_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        securityQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ResetPasswordActivity.class);
                intent.putExtra("check", "settings" );
                startActivity(intent);
            }
        });

        settings_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checker.equals("clicked")){
                    userinfosaved();
                }
                else{
                  updateonlyuserinfo();
                }
            }
        });

        settings_profile_image_change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker="clicked";

                CropImage.activity(imageuri)
                        .setAspectRatio(1,1)
                        .start(SettingsActivity.this);
            }
        });
    }

    private void updateonlyuserinfo() {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("users");
        HashMap<String,Object>userMap=new HashMap<>();
        userMap.put("branch",settings_userbranch.getText().toString());
        userMap.put("email",settings_useremail.getText().toString());
        userMap.put("mobileno",settings_usermobileno.getText().toString());
        userMap.put("name",settings_fullname.getText().toString());
        //userMap.put("usn",settings_userusn.getText().toString());
        userMap.put("sec",settings_usersec.getText().toString());
        ref.child(Prevalent.currentonlineusers.getUsn()).updateChildren(userMap);

        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
        Toast.makeText(SettingsActivity.this,"Profile info update successfully",Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE&&resultCode==RESULT_OK&&data!=null){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            imageuri=result.getUri();
            settings_profile_image.setImageURI(imageuri);

        }
        else{
            Toast.makeText(SettingsActivity.this, "Error,try again", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SettingsActivity.this,SettingsActivity.class));
            finish();
        }
    }

    private void userinfosaved() {
        if(TextUtils.isEmpty(settings_fullname.getText().toString())){
            Toast.makeText(this, "Name is mandatory", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(settings_userbranch.getText().toString())){
            Toast.makeText(this, "Branch is mandatory", Toast.LENGTH_SHORT).show();
        }

        //else if(TextUtils.isEmpty(settings_usermobileno.getText().toString())){
          //  Toast.makeText(this, "Mobileno is mandatory", Toast.LENGTH_SHORT).show();
        //}
        else if(TextUtils.isEmpty(settings_useremail.getText().toString())){
            Toast.makeText(this, "Email is mandatory", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(settings_usersec.getText().toString())){
            Toast.makeText(this, "Section is mandatory", Toast.LENGTH_SHORT).show();
        }
        else if(checker.equals("clicked")){
            uploadimage();
        }
    }

    private void uploadimage() {
        loadingBar.setTitle("Update Profile");
        loadingBar.setMessage("Please wait, while we are checking the account information");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        if(imageuri!=null)
        {
            final StorageReference fileref=storageProfilePictureRef
                    .child(Prevalent.currentonlineusers.getUsn()+".jpg");
            uploadtask=fileref.putFile(imageuri);

            uploadtask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception
                {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileref.getDownloadUrl();

                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadurl=task.getResult();
                        myUrl=downloadurl.toString();

                        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("users");
                        HashMap<String,Object>userMap=new HashMap<>();
                        userMap.put("branch",settings_userbranch.getText().toString());
                        userMap.put("email",settings_useremail.getText().toString());
                        userMap.put("mobileno",settings_usermobileno.getText().toString());
                        userMap.put("name",settings_fullname.getText().toString());
                       // userMap.put("usn",settings_userusn.getText().toString());
                        userMap.put("sec",settings_usersec.getText().toString());
                        userMap.put("image",myUrl);
                        ref.child(Prevalent.currentonlineusers.getUsn()).updateChildren(userMap);

                        loadingBar.dismiss();
                        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
                        Toast.makeText(SettingsActivity.this,"Profile info update successfully",Toast.LENGTH_SHORT).show();
                        finish();

                    }else{loadingBar.dismiss();
                    Toast.makeText(SettingsActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(SettingsActivity.this,"Image is not selected",Toast.LENGTH_SHORT).show();
        }

    }



    private void userinfodisplay(CircleImageView settings_profile_image, EditText settings_fullname, EditText settings_userbranch, EditText settings_usermobileno, EditText settings_useremail) {

        DatabaseReference Userref= FirebaseDatabase.getInstance().getReference().child("users").child(Prevalent.currentonlineusers.getUsn());
        Userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    if(datasnapshot.child("image").exists()){
                        String image=datasnapshot.child("image").getValue().toString();
                        String name=datasnapshot.child("name").getValue().toString();
                        String branch=datasnapshot.child("branch").getValue().toString();
                        //String usn=datasnapshot.child("usn").getValue().toString();
                        String mobileno=datasnapshot.child("mobileno").getValue().toString();
                        String email=datasnapshot.child("email").getValue().toString();
                        String sec=datasnapshot.child("sec").getValue().toString();

                        Picasso.get().load(image).into(settings_profile_image);
                        settings_fullname.setText(name);
                        settings_userbranch.setText(branch);
                      //  settings_userusn.setText(usn);
                        settings_usermobileno.setText(mobileno);
                        settings_useremail.setText(email);
                        settings_usersec.setText(sec);


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}