package com.example.museum.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museum.Bean.MathematiclanBean;
import com.example.museum.R;

import java.util.List;

public class MathematiclansAdapter extends RecyclerView.Adapter<MathematiclansAdapter.Fy1ViewHolder> {

    private List<MathematiclanBean> list;

    public void setList(List<MathematiclanBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fy1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Fy1ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_math, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Fy1ViewHolder holder, int position) {
        if (list != null) {
            MathematiclanBean mathematiclanBean = list.get(position);

            holder.imageView.setImageResource(mathematiclanBean.getImg());
            holder.name.setText(mathematiclanBean.getName());
            holder.period.setText(mathematiclanBean.getPeriod());
            holder.tags.setText(mathematiclanBean.getTags());


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
        TextView name;
        TextView period;
        TextView tags;

        public Fy1ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            period = itemView.findViewById(R.id.period);
            tags = itemView.findViewById(R.id.tags);
        }
    }
}
