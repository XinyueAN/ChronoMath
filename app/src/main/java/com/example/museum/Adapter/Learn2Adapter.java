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
import com.example.museum.Bean.LearnBean;
import com.example.museum.R;

import java.util.List;


public class Learn2Adapter extends RecyclerView.Adapter<Learn2Adapter.Fy1ViewHolder> {

    private List<LearnBean> list;

    public void setList(List<LearnBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fy1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Fy1ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learn2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Fy1ViewHolder holder, int position) {
        if (list != null) {
            LearnBean learnBean = list.get(position);
            holder.imageView.setImageResource(learnBean.getLearn_img());
            holder.tv_title.setText(learnBean.getTitle());
            holder.tv_price.setText(learnBean.getContent());

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
        TextView tv_title;
        TextView tv_price;

        public Fy1ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_price = itemView.findViewById(R.id.tv_price);

        }
    }
}
