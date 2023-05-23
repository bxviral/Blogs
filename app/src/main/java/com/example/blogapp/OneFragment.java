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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class OneFragment extends Fragment {

    static OneFragment oneFragment;

    public static OneFragment getFragment() {

        if (oneFragment == null) {
            oneFragment = new OneFragment();
        }
        return oneFragment;
    }

    RecyclerView recyclerViewOneFrag;
    RecyclerViewOneFragAdapter recyclerViewOneFragAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        Log.e("EEE", "onCreateView: is called   ");

        recyclerViewOneFrag = view.findViewById(R.id.rvFavList);
        recyclerViewOneFrag.setHasFixedSize(true);
        recyclerViewOneFrag.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewOneFragAdapter = new RecyclerViewOneFragAdapter(requireActivity(), new ArrayList<>());
        recyclerViewOneFrag.setAdapter(recyclerViewOneFragAdapter);
        for (int i = 0; i < s.size(); i++) {
            Log.e("SSS", "getPath: " + s.get(i).getPath());
            Log.e("SSS", "getTitle: " + s.get(i).getTitle());
            Log.e("SSS", "getContent: " + s.get(i).getContent());
            Log.e("SSS", "getRealPos: " + s.get(i).getRealPos());
            Log.e("SSS", "isSelected: " + s.get(i).isSelected());
        }
        for (int i = 0; i < s.size(); i++) {
            if (s.get(i).isSelected()) {
                recyclerViewOneFragAdapter.addItem(s.get(i));
            }
        }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("EEE", "onAttach: is called   ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("EEE", "onResume: is called   ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("EEE", "onStart: is called   ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("EEE", "onPause: is called   ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("EEE", "onStop: is called   ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("EEE", "onDestroy: is called   ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("EEE", "onDestroyView: is called   ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("EEE", "onDetach: is called   ");
    }
}