package com.example.museum.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.museum.R;


import java.util.List;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.Fy1ViewHolder> {

    private List<Integer> list;

    public void setList(List<Integer> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fy1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Fy1ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learn, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Fy1ViewHolder holder, int position) {
        if (list != null) {
            Integer integer = list.get(position);

            holder.imageView.setImageResource(integer);


            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    class Fy1ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public Fy1ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
