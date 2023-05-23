package com.example.blogapp;

import static com.example.blogapp.MyApplicationClass.sharedPreferences;

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

public class MainActivity extends AppCompatActivity {
    EditText edTextMail, edPass;
    Button logIn;
    TextView txtSignUp;
//    String text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edTextMail = findViewById(R.id.edTextMail);
        edPass = findViewById(R.id.edTextPass);
        logIn = findViewById(R.id.logInButton);
        txtSignUp = findViewById(R.id.txtSignUp);

        sharedPreferences = getSharedPreferences("K", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(edTextMail.getText());
                String password = String.valueOf(edPass.getText());
                myEdit.putString("email", email);
                myEdit.putString("password", password);
                myEdit.apply();
                Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        String text1 = (String) txtSignUp.getText();
        SpannableString spannableString = new SpannableString(text1);
        spannableString.setSpan(new UnderlineSpan(), 0, text1.length(), 0);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        };
        spannableString.setSpan(clickableSpan, 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtSignUp.setText(spannableString);
        txtSignUp.setMovementMethod(LinkMovementMethod.getInstance());
    }
}