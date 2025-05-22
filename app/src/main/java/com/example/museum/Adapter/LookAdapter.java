package com.example.museum.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.museum.R;

import java.util.List;

public class LookAdapter extends RecyclerView.Adapter<LookAdapter.PagerViewHolder> {

    private Context context;
    private List<PageItem> pageItems;

    public LookAdapter(Context context, List<PageItem> pageItems) {
        this.context = context;
        this.pageItems = pageItems;
    }

    @NonNull
    @Override
    public PagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 加载每一页的布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_look, parent, false); // 确保这里是 item_page_content
        return new PagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagerViewHolder holder, int position) {
        PageItem item = pageItems.get(position);

        // 设置内容图片 (iv_page_image)
        int radius = (int) (15 * context.getResources().getDisplayMetrics().density); // 15dp 的圆角
        RequestOptions requestOptions = RequestOptions.bitmapTransform(new RoundedCorners(radius));
        Glide.with(context)
                .load(item.getContentImageResId())
                .apply(requestOptions)
                .into(holder.ivPageImage);

        // 设置叠加图片 (iv_overlay_image)
        Glide.with(context)
                .load(item.getOverlayImageResId())
                .into(holder.ivOverlayImage);

        // 设置文本标题 (tv_page_title)
        holder.tvPageTitle.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return pageItems.size();
    }

    public static class PagerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPageImage;
        ImageView ivOverlayImage;
        TextView tvPageTitle;

        public PagerViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPageImage = itemView.findViewById(R.id.iv_page_image);
            ivOverlayImage = itemView.findViewById(R.id.iv_overlay_image); // 初始化叠加图片 ImageView
            tvPageTitle = itemView.findViewById(R.id.tv_page_title);
        }
    }

    // 简单的数据模型，包含主图片、叠加图片和标题文本
    public static class PageItem {
        private String title;
        private int contentImageResId;
        private int overlayImageResId;

        public PageItem(String title, int contentImageResId, int overlayImageResId) {
            this.title = title;
            this.contentImageResId = contentImageResId;
            this.overlayImageResId = overlayImageResId; // **添加了这一行！**
        }

        public String getTitle() {
            return title;
        }

        public int getContentImageResId() {
            return contentImageResId;
        }

        public int getOverlayImageResId() {
            return overlayImageResId;
        }
    }
}