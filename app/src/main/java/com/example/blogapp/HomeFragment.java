package com.example.blogapp;

import static com.example.blogapp.MyApplicationClass.s;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    static HomeFragment homeFragment;

    public static HomeFragment getFragment() {

        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

    RecyclerView recyclerView1;
    RecyclerViewAdapter1 recyclerViewAdapter1;
    RecyclerView recyclerView2;
    RecyclerViewAdapter2 recyclerViewAdapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.e("KKK", "onCreate of Comment is called ");

        recyclerView1 = view.findViewById(R.id.recyclerHorizontal);
        recyclerView2 = view.findViewById(R.id.recyclerVertical);

        recyclerView1.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setLayoutManager(new GridLayoutManager(requireActivity(), 2));


        recyclerViewAdapter1 = new RecyclerViewAdapter1(getContext(), new ArrayList<Data>());
        recyclerViewAdapter2 = new RecyclerViewAdapter2(getContext(), new ArrayList<Data>());
        recyclerView1.setAdapter(recyclerViewAdapter1);
        recyclerView2.setAdapter(recyclerViewAdapter2);

        s = Pref.readListFromPref(getContext());
        if (s == null) {
            s = new ArrayList<>();
        }
        for (int i = 0; i < s.size(); i++) {
            if (i <= 1) {
                s.get(i).setRealPos(i);
                recyclerViewAdapter1.addItem(s.get(i));
                //recyclerViewAdapter1.notifyItemInserted(i);
            } else {
                s.get(i).setRealPos(i);
                recyclerViewAdapter2.addItem(s.get(i));
            }
        }
//        for (int i = 0; i < s.size(); i++) {
//            Log.e("FFF", "getPath: " + s.get(i).getPath());
//            Log.e("FFF", "getTitle: " + s.get(i).getTitle());
//            Log.e("FFF", "getContent: " + s.get(i).getContent());
//            Log.e("FFF", "getRealPos: " + s.get(i).getRealPos());
//            Log.e("FFF", "isSelected: " + s.get(i).isSelected());
//        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("KKK", "onResume: is called");
        recyclerViewAdapter1 = new RecyclerViewAdapter1(getContext(), s);//this
        recyclerViewAdapter2 = new RecyclerViewAdapter2(getContext(),s);

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("KKK", "onPause: is called");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("KKK", "onDetach: is called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("KKK", "onDestroy: is called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("KKK", "onStop: is called");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("KKK", "onDestroyView: is called");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("KKK", "onStart: ");
    }
}