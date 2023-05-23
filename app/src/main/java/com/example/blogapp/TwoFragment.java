package com.example.blogapp;

import static com.example.blogapp.MyApplicationClass.s;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TwoFragment extends Fragment {

    static TwoFragment twoFragment;

    public static TwoFragment getFragment() {

        if (twoFragment == null) {
            twoFragment = new TwoFragment();
        }
        return twoFragment;
    }

    RecyclerView recyclerViewTwoFrag;
    RecyclerViewTwoFragmentAdapter recyclerViewTwoFragAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        recyclerViewTwoFrag = view.findViewById(R.id.rvUnFavList);
        recyclerViewTwoFrag.setHasFixedSize(true);
        recyclerViewTwoFrag.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewTwoFragAdapter = new RecyclerViewTwoFragmentAdapter(requireActivity(), new ArrayList<>());
        recyclerViewTwoFrag.setAdapter(recyclerViewTwoFragAdapter);
        for (int i = 0; i < s.size(); i++) {
            if (!s.get(i).isSelected()) {
                recyclerViewTwoFragAdapter.addItem(s.get(i));
            }
        }

        return view;
    }
}