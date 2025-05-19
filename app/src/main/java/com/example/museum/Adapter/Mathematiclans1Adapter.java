package com.example.museum.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museum.Bean.MathematiclanBean;
import com.example.museum.R;

import java.util.List;

public class Mathematiclans1Adapter extends RecyclerView.Adapter<Mathematiclans1Adapter.Fy1ViewHolder> {

    private List<MathematiclanBean> list;

    public void setList(List<MathematiclanBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fy1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Fy1ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_math1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Fy1ViewHolder holder, int position) {
        if (list != null) {
            MathematiclanBean mathematiclanBean = list.get(position);

            holder.imageView.setImageResource(mathematiclanBean.getImg());
            holder.name.setText(mathematiclanBean.getName());
            holder.des.setText(mathematiclanBean.getDes());
            //holder.tags.setText(mathematiclanBean.getTags());
            String tags = mathematiclanBean.getTags();
            String[] split = tags.split("、");
            holder.tags_layout.removeAllViews();
            for (int i = 0; i < split.length; i++) {
                View inflate = LayoutInflater.from(holder.tags_layout.getContext()).inflate(R.layout.item_tags, holder.tags_layout, false);
                TextView textView = inflate.findViewById(R.id.tags);
                textView.setText(split[i]);
                holder.tags_layout.addView(inflate);
            }



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
        TextView des;
        TextView tags;
        LinearLayout tags_layout;

        public Fy1ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            des = itemView.findViewById(R.id.des);

            //  tags = itemView.findViewById(R.id.tags);
            tags_layout = itemView.findViewById(R.id.tags_layout);
        }
    }
}
