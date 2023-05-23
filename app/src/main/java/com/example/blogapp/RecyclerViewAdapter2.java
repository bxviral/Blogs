package com.example.blogapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder2> {
    Context context;
    ArrayList<Data> recyclerData;

    public RecyclerViewAdapter2(Context context, ArrayList<Data> recyclerData) {
        this.context = context;
        this.recyclerData = recyclerData;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter2.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gird, parent, false);
        return new ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter2.ViewHolder2 holder, int position) {
        Data r = recyclerData.get(position);
        int index = holder.getAdapterPosition();

        String p = r.getPath();
        File file = new File(p);
        Uri imageUri = Uri.fromFile(file);
        Glide.with(context).load(imageUri).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowActivity.class);
//                intent.putExtra("path", r.getPath());
//                intent.putExtra("title", r.getTitle());
//                intent.putExtra("content", r.getContent());
//                intent.putExtra("selected", false);
                intent.putExtra("index", index);
                intent.putExtra("obj",r);
                context.startActivity(intent);
                ((DashBoardActivity)context).finish();

            }
        });

    }
    public void addItem(Data r){
        recyclerData.add(r);
        int index = recyclerData.indexOf(r);

        notifyItemInserted(index);
    }

    @Override
    public int getItemCount() {
        return recyclerData.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageView imageView;


        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgHorizontal);


        }
    }
}
