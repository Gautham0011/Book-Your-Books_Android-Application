package com.example.byb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.byb.Admin.SearchProductActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import com.example.byb.Prevalent.Prevalent;
import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class home_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private Button btnToggleDark;
    private ConstraintLayout home_stationery,home_textbooks,home_lab_manual,home_forms;
    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home_stationery=findViewById(R.id.home_stationary);
        home_textbooks=findViewById(R.id.home_textbooks);
        home_lab_manual=findViewById(R.id.home_lab_manual);
        home_forms=findViewById(R.id.home_forms);

        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null)
        {
            type = getIntent().getExtras().get("admins").toString();
        }


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.userprofilename);
        CircleImageView profileImageView = headerView.findViewById(R.id.profile_image);
        if (!type.equals("admins")) {
            userNameTextView.setText(Prevalent.currentonlineusers.getName());
            Picasso.get().load(Prevalent.currentonlineusers.getImage()).placeholder(R.drawable.profile).into(profileImageView);
        }
        if(!type.equals("admins")) {
            home_stationery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(home_activity.this, user_stationary_category.class);
                    startActivity(intent);
                }
            });


            home_textbooks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(home_activity.this, user_sem_category.class);
                    startActivity(intent);
                }
            });
        }
        else{
            home_stationery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(home_activity.this, user_stationary_category.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });


            home_textbooks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(home_activity.this, user_sem_category.class);
                    intent.putExtra("admins","admins");
                    startActivity(intent);
                }
            });
        }




        setUpToolbar();

        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case  R.id.nav_cart:Intent intent1 = new Intent(home_activity.this , CartActivity.class);
                        startActivity(intent1);
                        break;
                    case  R.id.nav_search:Intent intent2 = new Intent(home_activity.this , SearchProductActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_orders:Intent intent3 = new Intent(home_activity.this , User_Order_View.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_user_prints:
                        Intent intent4 = new Intent(home_activity.this , user_personal_printout.class);
                        startActivity(intent4);
                        break;
                    case R.id.nav_categories:
                        Intent intent5 = new Intent(home_activity.this , ChatHomeActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.nav_settings:

                    if(type.equals("admins")) {
                        Intent intent6 = new Intent(home_activity.this , SettingsActivity.class);
                        intent.putExtra("member","admins");
                        startActivity(intent6);
                        break;

                    }else {Intent intent7 = new Intent(home_activity.this , SettingsActivity.class);
                        intent.putExtra("member","users");
                        startActivity(intent7);
                        break;

                    }
                    case R.id.nav_logout:
                        Intent intent8 = new Intent(home_activity.this , MainActivity.class);
                        Paper.book().destroy();
                        startActivity(intent8);
                        break;
                    case R.id.nav_annnouncements:
                        Intent intent9 = new Intent(home_activity.this , user_announcements.class);
                        startActivity(intent9);
                        break;

//Paste your privacy policy link

//                    case  R.id.nav_Policy:{
//
//                        Intent browserIntent  = new Intent(Intent.ACTION_VIEW , Uri.parse(""));
//                        startActivity(browserIntent);
//
//                    }
                    //       break;
                    case  R.id.nav_share:{

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody =  "http://play.google.com/store/apps/detail?id=" + getPackageName();
                        String shareSub = "Try now";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));

                    }
                    break;
                }
                return false;
            }
        });
    }

    public void darkbutton(View view) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    }

    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}