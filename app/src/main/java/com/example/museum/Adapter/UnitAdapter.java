package com.example.museum.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.museum.Activity.UnitDetailsActivity;
import com.example.museum.Bean.UnitBean;
import com.example.museum.R;

import java.util.List;

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.UnitViewHolder> {
    private List<UnitBean> unitList;
    private Context context;

    public UnitAdapter(Context context, List<UnitBean> unitList) {
        this.context = context;
        this.unitList = unitList;
    }

    @NonNull
    @Override
    public UnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_unit, parent, false);
        return new UnitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitViewHolder holder, int position) {
        UnitBean unit = unitList.get(position);
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
            Intent intent = new Intent(context, UnitDetailsActivity.class);
            // 将需要的参数传递给目标 Activity
            intent.putExtra("img", unit.getImagePath());
            intent.putExtra("name", unit.getName());
            intent.putExtra("era", unit.getEra());
            intent.putExtra("category", unit.getCategory());
            intent.putExtra("region", unit.getRegion());
            intent.putExtra("description", unit.getDescription());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return unitList.size();
    }

    public void updateData(List<UnitBean> newUnitList) {
        this.unitList = newUnitList;
        notifyDataSetChanged(); // 通知适配器数据已更改
    }

    public static class UnitViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvName;

        public UnitViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
