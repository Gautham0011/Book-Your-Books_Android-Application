package com.example.byb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.health.UidHealthStats;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static android.widget.Toast.LENGTH_LONG;

public class RegisterUser extends AppCompatActivity  {
    private Button registerlogin,registeruser;
    private EditText fullname,userbranch, registeredemail,registeredpassword,userusn,userid,usermobileno;
    private ProgressDialog loadingBar;
    private DatabaseReference RootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        registeruser = (Button) findViewById(R.id.registeruser);
        fullname = (EditText) findViewById(R.id.fullname);
        userusn = (EditText) findViewById(R.id.userusn);
        userbranch = (EditText) findViewById(R.id.userbranch);
        usermobileno = (EditText) findViewById(R.id.usermobileno);
        registeredemail = (EditText) findViewById(R.id.registeredemail);
        registeredpassword = (EditText) findViewById(R.id.registeredpassword);
        registerlogin = (Button) findViewById(R.id.registerlogin);
        loadingBar = new ProgressDialog(this);

        registerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        registeruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullname.getText().toString().trim();
                String usn=userusn.getText().toString().trim();
                String branch = userbranch.getText().toString().trim();
                String mobileno = usermobileno.getText().toString().trim();
                String email = registeredemail.getText().toString().trim();
                String password = registeredpassword.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    fullname.setError("Full Name is required");
                    return;
                }
                if (TextUtils.isEmpty(usn)){
                    userusn.setError("USN is Required");
                }
                if (TextUtils.isEmpty(branch)) {
                    userbranch.setError("Branch is required");
                    return;
                }
                if (TextUtils.isEmpty(mobileno)) {
                    usermobileno.setError("Mobile Number is required");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    registeredemail.setError("Email is required");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    registeredemail.setError("Please provide valid email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    registeredpassword.setError("Password is required");
                    return;
                }
                if (password.length() < 8) {
                    registeredpassword.setError("Min password length should be 8 characters");
                    return;
                } else {
                    loadingBar.setTitle("Create Account");
                    loadingBar.setMessage("Please wait, while we are checking the credentials.");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    Validateemail(name,usn ,branch,mobileno, email, password);
                }
                Toast.makeText(RegisterUser.this, "Data VAlidated", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Validateemail(final String name,final String usn, final String branch,final String mobileno, final String email, final String password) {
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("users").child(usn).exists())){
                    HashMap<String,Object>userdataMap=new HashMap<>();
                    userdataMap.put("name",name);
                    userdataMap.put("usn",usn);
                    userdataMap.put("branch",branch);
                    userdataMap.put("mobileno",mobileno);
                    userdataMap.put("email",email);
                    userdataMap.put("password",password);
                    RootRef.child("users").child(usn).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterUser.this, "Congratulations your account has benn created", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent = new Intent(RegisterUser.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterUser.this, "Network erreo please try again later", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(RegisterUser.this, "This " + usn + " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegisterUser.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterUser.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



}
}