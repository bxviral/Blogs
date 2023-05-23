package com.example.blogapp;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class MyApplicationClass extends Application {
    public static SharedPreferences sharedPreferences;

    public static ArrayList<Data> s = new ArrayList<>();

}
