package com.example.museum.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.museum.R;

public class MapActivity extends AppCompatActivity {

    private ImageView img;
    private LinearLayout linearLayout4;
    private ImageView imgBack;
    private TextView textView33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initView();
        back();
        show();
    }

    // 定义一个私有方法back，用于处理返回操作
    private void back() {
        // 为imgBack（可能是一个ImageView控件）设置点击事件监听器
        imgBack.setOnClickListener(v -> {
            // 调用finish()方法结束当前Activity
            finish();
        });
    }

    // 定义一个私有方法show，用于显示图片
    private void show() {
        // 使用Glide库加载图片
        Glide.with(this)
                // 设置图片的加载地址，这里是一个网络图片URL
                .load("https://img.dpm.org.cn/Public/static/themes/image/xf/map.jpg")
                // 将加载的图片设置到img（可能是一个ImageView控件）上
                .into(img);
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        linearLayout4 = (LinearLayout) findViewById(R.id.linearLayout4);
        imgBack = (ImageView) findViewById(R.id.img_back);
        textView33 = (TextView) findViewById(R.id.textView33);
    }
}