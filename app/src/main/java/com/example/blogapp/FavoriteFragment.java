package com.example.blogapp;

import static com.example.blogapp.MyApplicationClass.s;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class FavoriteFragment extends Fragment {
    static FavoriteFragment favoriteFragment;

    public static FavoriteFragment getFragment() {

        if (favoriteFragment == null) {
            favoriteFragment = new FavoriteFragment();
        }
        return favoriteFragment;
    }

    TabLayout t1;
    ViewPager2 v1;
    MyTabLayoutAdapter myTabLayoutAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        Log.e("AAA", "onCreateView: is called  FAVORITEFRAGMENT  ");

        for (int i = 0; i < s.size(); i++) {
            Log.e("FFF", "getPath: " + s.get(i).getPath());
            Log.e("FFF", "getTitle: " + s.get(i).getTitle());
            Log.e("FFF", "getContent: " + s.get(i).getContent());
            Log.e("FFF", "getRealPos: " + s.get(i).getRealPos());
            Log.e("FFF", "isSelected: " + s.get(i).isSelected());
        }


        t1 = view.findViewById(R.id.tabLayoutMain);
        v1 = view.findViewById(R.id.viewPagerMain);
        myTabLayoutAdapter = new MyTabLayoutAdapter(getActivity());
        v1.setAdapter(myTabLayoutAdapter);

        v1.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Objects.requireNonNull(t1.getTabAt(position)).select();
            }
        });

        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("AAA", "onAttach: is called   ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("AAA", "onResume: is called   ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("AAA", "onStart: is called   ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("AAA", "onPause: is called   ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("AAA", "onStop: is called   ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("AAA", "onDestroy: is called   ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("AAA", "onDestroyView: is called   ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("AAA", "onDetach: is called   ");
    }
}