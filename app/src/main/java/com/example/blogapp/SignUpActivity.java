package com.example.blogapp;

import static com.example.blogapp.MyApplicationClass.sharedPreferences;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    EditText edName, edEmail, edPass;
    Button signUp;
    TextView txtlogIn;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edName = findViewById(R.id.edTextName);
        edPass = findViewById(R.id.edTextPass);
        edEmail = findViewById(R.id.edTextMail);
        signUp = findViewById(R.id.SignUpButton);
        txtlogIn = findViewById(R.id.txtLogIn);


        sharedPreferences = getSharedPreferences("K", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        signUp.setOnClickListener(view -> {
            String name = String.valueOf(edName.getText());
            String email = String.valueOf(edEmail.getText());
            String password = String.valueOf(edPass.getText());
            String profPath = null;
            myEdit.putString("name", name);
            myEdit.putString("email", email);
            myEdit.putString("password", password);
            myEdit.putString("profImage", profPath);
            myEdit.apply();
            Intent intent = new Intent(SignUpActivity.this, DashBoardActivity.class);
            startActivity(intent);
            finish();
        });

        String text1 = (String) txtlogIn.getText();
        SpannableString spannableString = new SpannableString(text1);
        spannableString.setSpan(new UnderlineSpan(), 0, text1.length(), 0);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(SignUpActivity.this, DashBoardActivity.class);
                startActivity(intent);
            }
        };
        spannableString.setSpan(clickableSpan, 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtlogIn.setText(spannableString);
        txtlogIn.setMovementMethod(LinkMovementMethod.getInstance());


    }
}