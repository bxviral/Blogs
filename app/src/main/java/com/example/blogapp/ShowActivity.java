package com.example.blogapp;

import static com.example.blogapp.MyApplicationClass.s;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.File;

public class ShowActivity extends AppCompatActivity {

    EditText showTitle, showContent;
    ImageView showImageView, showImageStar;
    boolean selected;
    RecyclerViewAdapter1 recyclerViewAdapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Log.e("UUU", "ShowActivity is called ");


        showTitle = findViewById(R.id.showTitle);
        showContent = findViewById(R.id.showContent);
        showImageView = findViewById(R.id.showImageView);
        showImageStar = findViewById(R.id.showImageStar);

        Data d = (Data) getIntent().getSerializableExtra("obj");
//        int realPos = getIntent().getIntExtra("position", 0);
//        Log.e("NNN", "getPath: " + s.get(realPos).getPath());
//        Log.e("NNN", "getTitle: " + s.get(realPos).getTitle());
//        Log.e("NNN", "getContent: " + s.get(realPos).getContent());
//        Log.e("NNN", "isSelected: " + s.get(realPos).isSelected());
//        Log.e("NNN", "realPos: " + s.get(realPos).isSelected());


        Log.e("KKK", "path: " + d.getPath());
        Log.e("KKK", "title: " + d.getTitle());
        Log.e("KKK", "content: " + d.getContent());
        Log.e("KKK", "selected: " + d.isSelected());
        Log.e("KKK", "realPos" + d.getRealPos());


        showTitle.setText(d.getTitle());
        showContent.setText(d.getContent());
        File file = new File(d.getPath());
        Uri imageUri = Uri.fromFile(file);
        Glide.with(this).load(imageUri).into(showImageView);

        if (s.get(d.getRealPos()).isSelected()) {
            showImageStar.setImageResource(R.drawable.star);
        } else {
            showImageStar.setImageResource(R.drawable.unstar);
        }
        showImageStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (d.isSelected()) {
                    showImageStar.setImageResource(R.drawable.unstar);
                    d.setSelected(false);
                    s.get(d.getRealPos()).setSelected(false);
                    Pref.writeListInPref(ShowActivity.this, s);
                } else {
                    showImageStar.setImageResource(R.drawable.star);
                    d.setSelected(true);
                    s.get(d.realPos).setSelected(true);
                    Pref.writeListInPref(ShowActivity.this, s);
                }
            }
        });


//        Log.e("KKK", "path: " + path);
//        Log.e("KKK", "title: " + title);
//        Log.e("KKK", "content: " + content);
//        Log.e("KKK", "selected: " + selected);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent = new Intent(ShowActivity.this, DashBoardActivity.class);
//        startActivity(intent);
//        finish();
    }
}