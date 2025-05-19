package com.example.museum.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.museum.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private final List<Integer> imageList;
    private final Context context;

    // 构造函数接收上下文
    public ImageAdapter(Context context, List<Integer> images) {
        this.context = context;
        this.imageList = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 获取屏幕密度
        int radius = (int) (15 * context.getResources().getDisplayMetrics().density);

        // 使用 Glide 加载图片并应用圆角效果
        Glide.with(context)
                .load(imageList.get(position)) // 从 imageList 中获取图片资源 ID
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius))) // 应用圆角效果
                .into(holder.imageView); // 设置图片到 imageView 中
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}