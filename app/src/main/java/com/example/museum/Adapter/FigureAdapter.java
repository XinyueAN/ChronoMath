package com.example.museum.Adapter;

import android.content.Context;
import android.content.Intent; // 导入 Intent 类
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log; // 添加 Logcat 导入，方便调试

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.museum.Activity.FigureDetailsActivity; // 导入 FigureDetailsActivity
import com.example.museum.Bean.FigureBean;
import com.example.museum.R; // 确保 R 文件导入正确

import java.util.List;

public class FigureAdapter extends RecyclerView.Adapter<FigureAdapter.FigureViewHolder> {

    private Context context;
    private List<FigureBean> figureList;

    public FigureAdapter(Context context, List<FigureBean> figureList) {
        this.context = context;
        this.figureList = figureList;
    }

    @NonNull
    @Override
    public FigureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_figure2, parent, false);
        return new FigureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FigureViewHolder holder, int position) {
        FigureBean unit = figureList.get(position);

        // 设置名称 (对应 item_figure2.xml 中的 tv_title)
        if (holder.tvTitle != null) { // 安全检查
            holder.tvTitle.setText(unit.getName());
        } else {
            Log.e("FigureAdapter", "tvTitle is null at position: " + position + ". Check R.id.tv_title in item_figure2.xml");
        }

        // 设置价格 (对应 item_figure2.xml 中的 tv_price)
        if (holder.tvPrice != null) { // 安全检查
            holder.tvPrice.setText(unit.getPrice());
        } else {
            Log.e("FigureAdapter", "tvPrice is null at position: " + position + ". Check R.id.tv_price in item_figure2.xml");
        }

        // 加载图片 (对应 item_figure2.xml 中的 imageView)
        if (holder.imageView != null && unit.getImgName() != null) {
            // 通过图片文件名获取其对应的资源ID
            int imageResId = context.getResources().getIdentifier(
                    unit.getImgName(), "drawable", context.getPackageName());

            if (imageResId != 0) { // 确保找到了资源ID
                Glide.with(context)
                        .load(imageResId) // 使用资源ID加载图片
                        .into(holder.imageView);
            } else {
                // 如果找不到图片资源，可以打印日志或设置一个默认的占位图
                Log.e("FigureAdapter", "图片资源未找到: " + unit.getImgName());
                // holder.imageView.setImageResource(R.drawable.default_placeholder); // 可选：设置错误占位图
            }
        } else {
            Log.e("FigureAdapter", "imageView 为空或图片名称为空，position: " + position);
        }

        // ---------- 添加点击事件部分代码 START ----------
        holder.itemView.setOnClickListener(v -> {
            // 创建一个 Intent，用于启动 FigureDetailsActivity
            Intent intent = new Intent(context, FigureDetailsActivity.class);

            // 将 FigureBean 中的所有相关数据通过 Intent 传递到 FigureDetailsActivity
            // 确保这些 key ("name", "price", "img", "author", "description", "period")
            // 与 FigureDetailsActivity 中 getIntent().getStringExtra() 使用的 key 一致
            intent.putExtra("name", unit.getName());
            intent.putExtra("price", unit.getPrice());
            intent.putExtra("img", unit.getImgName()); // 传递图片名称 (或 URL)
            // 请确保 FigureBean 中有 getAuthor(), getDescription(), getPeriod() 方法
            intent.putExtra("author", unit.getAuthor());
            intent.putExtra("description", unit.getDescription());
            intent.putExtra("period", unit.getEra());

            // 启动 FigureDetailsActivity
            context.startActivity(intent);
        });
        // ---------- 添加点击事件部分代码 END ----------
    }

    @Override
    public int getItemCount() {
        return figureList.size();
    }

    public void updateData(List<FigureBean> newFigureList) {
        this.figureList = newFigureList;
        notifyDataSetChanged();
    }

    public static class FigureViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        TextView tvPrice;
        ImageView imageViewTitleRight;


        public FigureViewHolder(@NonNull View itemView) {
            super(itemView);
            // 初始化视图，使用 item_figure2.xml 中的 ID
            imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
            imageViewTitleRight = itemView.findViewById(R.id.imageViewTitleRight); // 购物车图标
        }
    }
}