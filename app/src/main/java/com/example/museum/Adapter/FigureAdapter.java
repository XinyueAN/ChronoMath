package com.example.museum.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.museum.Activity.FigureDetailsActivity;
import com.example.museum.Activity.UnitDetailsActivity;
import com.example.museum.Bean.FigureBean;
import com.example.museum.R;

import java.util.List;

public class FigureAdapter extends RecyclerView.Adapter<FigureAdapter.FigureViewHolder> {
    private List<FigureBean> figureList;
    private Context context;

    public FigureAdapter(Context context, List<FigureBean> figureList) {
        this.context = context;
        this.figureList = figureList;
    }

    @NonNull
    @Override
    public FigureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_figure, parent, false);
        return new FigureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FigureViewHolder holder, int position) {
        FigureBean unit = figureList.get(position);
        holder.tvName.setText(unit.getName());
        // 添加圆角转换器
        Transformation<Bitmap> transformations = new MultiTransformation<>(new CenterCrop(), new RoundedCorners(20));
        // 加载并显示图像
        Glide.with(context)
                .load(unit.getImagePath())
                .fitCenter() // 缩放模式
                .apply(RequestOptions.bitmapTransform(transformations)) // 应用所有转换器
                .into(holder.img);

        // 设置点击事件
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FigureDetailsActivity.class);
            // 将需要的参数传递给目标 Activity
            intent.putExtra("img", unit.getImagePath());
            intent.putExtra("name", unit.getName());
            intent.putExtra("num", unit.getNum());
            intent.putExtra("category", unit.getCategory());
            intent.putExtra("era", unit.getEra());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return figureList.size();
    }

    public void updateData(List<FigureBean> newFigureList) {
        this.figureList = newFigureList;
        notifyDataSetChanged(); // 通知适配器数据已更改
    }

    public static class FigureViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvName;

        public FigureViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
