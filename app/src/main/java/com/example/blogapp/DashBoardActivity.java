package com.example.blogapp;

import static com.example.blogapp.MyApplicationClass.s;
import static com.example.blogapp.MyApplicationClass.sharedPreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class DashBoardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView homeButton, favoriteButton, blogButton, profileButton, toolbar, headerDashImage;
    FragmentManager fm = getSupportFragmentManager();
    TextView navTxtHome, navTxtPrivacyPolicy, headerTxtName, navTxtLogOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Log.e("KKK", "onCreateView of DashBoardActivity is called ");

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);
        headerDashImage = findViewById(R.id.headerDashImage);
        homeButton = findViewById(R.id.homeButton);
        favoriteButton = findViewById(R.id.favoriteButton);
        blogButton = findViewById(R.id.blogButton);
        profileButton = findViewById(R.id.profileButton);
        navTxtHome = findViewById(R.id.navTxtHome);
        navTxtPrivacyPolicy = findViewById(R.id.navTxtPrivacyPolicy);
        toolbar = findViewById(R.id.toolBar);
        headerTxtName = findViewById(R.id.headerTxtName);
        navTxtLogOut = findViewById(R.id.navTxtLogOut);


        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        sharedPreferences = getSharedPreferences("K", Context.MODE_PRIVATE);
        String p = sharedPreferences.getString("profImage", null);
        headerTxtName.setText(sharedPreferences.getString("name", null));

        navTxtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("K", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.clear();
                myEdit.apply();
                Intent intent = new Intent(DashBoardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        if (p != null) {
            File file = new File(p);
            Uri imageUri = Uri.fromFile(file);
            Glide.with(this).load(imageUri).into(headerDashImage);
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, HomeFragment.getFragment());
        ft.commit();
        homeButton.setImageResource(R.drawable.ic_home_filled);
        favoriteButton.setImageResource(R.drawable.ic_favorite);
        blogButton.setImageResource(R.drawable.ic_blog);
        profileButton.setImageResource(R.drawable.ic_profile);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("AAA", "onClick: homeButton");
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, HomeFragment.getFragment());
                ft.commit();
                homeButton.setImageResource(R.drawable.ic_home_filled);
                favoriteButton.setImageResource(R.drawable.ic_favorite);
                blogButton.setImageResource(R.drawable.ic_blog);
                profileButton.setImageResource(R.drawable.ic_profile);
            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("AAA", "onClick: favoriteButton");
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, new FavoriteFragment());
                ft.commit();
                homeButton.setImageResource(R.drawable.ic_home);
                favoriteButton.setImageResource(R.drawable.ic_favorite_filled);
                blogButton.setImageResource(R.drawable.ic_blog);
                profileButton.setImageResource(R.drawable.ic_profile);
            }
        });
        blogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("AAA", "onClick: favoriteButton");
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, BlogFragment.getFragment());
                ft.commit();
                homeButton.setImageResource(R.drawable.ic_home);
                favoriteButton.setImageResource(R.drawable.ic_favorite);
                blogButton.setImageResource(R.drawable.ic_blog_filled);
                profileButton.setImageResource(R.drawable.ic_profile);
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("AAA", "onClick: favoriteButton");
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, ProfileFragment.getFragment());
                ft.commit();
                homeButton.setImageResource(R.drawable.ic_home);
                favoriteButton.setImageResource(R.drawable.ic_favorite);
                blogButton.setImageResource(R.drawable.ic_blog);
                profileButton.setImageResource(R.drawable.ic_profile_filled);
            }
        });

        navTxtHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, HomeFragment.getFragment());
                ft.commit();
                homeButton.setImageResource(R.drawable.ic_home_filled);
                favoriteButton.setImageResource(R.drawable.ic_favorite);
                blogButton.setImageResource(R.drawable.ic_blog);
                profileButton.setImageResource(R.drawable.ic_profile);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        for (int i = 0; i < s.size(); i++) {
            Log.e("TAG", "path is " + s.get(i).getPath());
            Log.e("TAG", "getTitle is " + s.get(i).getTitle());
            Log.e("TAG", "getContent is " + s.get(i).getContent());
            Log.e("TAG", "getContent is " + s.get(i).getContent());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, HomeFragment.getFragment());
        ft.commit();
        homeButton.setImageResource(R.drawable.ic_home_filled);
        favoriteButton.setImageResource(R.drawable.ic_favorite);
        blogButton.setImageResource(R.drawable.ic_blog);
        profileButton.setImageResource(R.drawable.ic_profile);
        sharedPreferences = getSharedPreferences("K", Context.MODE_PRIVATE);
        String p = sharedPreferences.getString("profImage", null);
        Log.e("LLL", "onResume: is called of DashBoard and path is   " + p);
        if (p != null) {
            File file = new File(p);
            Uri imageUri = Uri.fromFile(file);
            Glide.with(this).load(imageUri).into(headerDashImage);
        }
    }
}