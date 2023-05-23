package com.example.blogapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewAdapter1 extends RecyclerView.Adapter<RecyclerViewAdapter1.ViewHolder> {

    Context context;
    ArrayList<Data> data;



    public RecyclerViewAdapter1(Context context, ArrayList<Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_horizantal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter1.ViewHolder holder, int position) {
        Data r = data.get(position);
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
                //((DashBoardActivity) context).finish();
            }
        });
    }

    public void addItem(Data r) {
        Log.e("MMM", "addItem: is called ");

        data.add(r);
        int index = data.indexOf(r);
        Log.e("MMM", "value of index is and path is " + index + "   " + r.getPath());
        notifyItemInserted(index);

    }

    @Override
    public int getItemCount() {
        Log.e("MMM", "data.size() = " + data.size());
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgHorizontal);
        }
    }
}
