package com.example.museum.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2; // 导入 ViewPager2

import com.bumptech.glide.Glide;
import com.example.museum.Activity.FigureActivity;
import com.example.museum.R;
import com.example.museum.Adapter.LookAdapter;

import java.util.ArrayList;
import java.util.List;

public class LookFragment extends Fragment {

    // 声明的成员变量
    private ImageView imageView6;
    private TextView textView19;
    private LinearLayout llLook01;

    private ViewPager2 viewPager2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_look, container, false);
        initView(v);
        img();
        setupViewPager();
        look();
        return v;
    }

    // 设置 look() 方法，用于设置分类按钮的点击事件
    private void look() {
        if (llLook01 != null) {
            llLook01.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), FigureActivity.class);
                startActivity(intent);
            });
        }
    }

    // img() 方法，用于加载顶部图片
    private void img() {
        if (imageView6 != null) {
            Glide.with(this)
                    .load(R.drawable.profile_bg) // 加载顶部背景图
                    .into(imageView6);
        }
    }

    private void initView(View v) {
        imageView6 = v.findViewById(R.id.imageView6);
        textView19 = v.findViewById(R.id.textView19);
        llLook01 = v.findViewById(R.id.ll_look01);
        // 初始化 ViewPager2
        viewPager2 = v.findViewById(R.id.viewPager2);
    }

    // 新增方法：设置 ViewPager2 的数据和适配器
    private void setupViewPager() {
        List<LookAdapter.PageItem> pageItems = new ArrayList<>();

        // 添加第一页数据：Mathematics Works
        pageItems.add(new LookAdapter.PageItem(
                "Mathematics Works",
                R.drawable.work,
                R.drawable.view
        ));

        // 添加第二页数据：Mathematics Inventions
        pageItems.add(new LookAdapter.PageItem(
                "Mathematics Inventions",
                R.drawable.invention,
                R.drawable.view
        ));

        // 添加第三页数据：Mathematicians
        pageItems.add(new LookAdapter.PageItem(
                "Mathematicians",
                R.drawable.mathematician,
                R.drawable.view
        ));

        // 添加第四页数据：Mathematics History
        pageItems.add(new LookAdapter.PageItem(
                "Mathematics History",
                R.drawable.history_1,
                R.drawable.view
        ));

        // 创建适配器实例并设置给 ViewPager2
        LookAdapter adapter = new LookAdapter(requireContext(), pageItems);
        viewPager2.setAdapter(adapter);
    }
}