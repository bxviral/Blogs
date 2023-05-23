package com.example.blogapp;

import static com.example.blogapp.MyApplicationClass.s;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyTabLayoutAdapter extends FragmentStateAdapter {
    public MyTabLayoutAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.e("PPP", "createFragment is called" );
        if (position == 0) {
            Log.e("PPP", "position == 0" );
            return new OneFragment();
        } else {
            return new TwoFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
