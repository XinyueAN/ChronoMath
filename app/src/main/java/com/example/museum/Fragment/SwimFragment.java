package com.example.museum.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.museum.Activity.BuyActivity;
import com.example.museum.Activity.MapActivity;
import com.example.museum.Activity.NoticeActivity;
import com.example.museum.Activity.UnitActivity;
import com.example.museum.R;


public class SwimFragment extends Fragment {

    private ImageView img01;
    private ImageView img02;
    private ImageView img03;
    private LinearLayout llBuy;
    private LinearLayout llNotice;
    private LinearLayout llUnit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_swim, container, false);
        initView(v);
        image();
        buy();
        Notice();
        unit();
        map();
        return v;
    }

    // 定义一个方法用于设置地图点击事件
    private void map() {
        // 为 img01 视图设置点击事件监听器
        img01.setOnClickListener(v -> {
            // 创建一个 Intent 对象，用于启动 MapActivity
            Intent intent = new Intent(getActivity(), MapActivity.class);
            // 启动 MapActivity
            startActivity(intent);
        });
    }

    // 定义一个方法用于设置单位点击事件
    private void unit() {
        // 为 llUnit 视图设置点击事件监听器
        llUnit.setOnClickListener(v -> {
            // 创建一个 Intent 对象，用于启动 UnitActivity
            Intent intent = new Intent(getActivity(), UnitActivity.class);
            // 启动 UnitActivity
            startActivity(intent);
        });
    }

    // 定义一个方法用于设置通知点击事件
    private void Notice() {
        // 为 llNotice 视图设置点击事件监听器
        llNotice.setOnClickListener(v -> {
            // 创建一个 Intent 对象，用于启动 NoticeActivity
            Intent intent = new Intent(getActivity(), NoticeActivity.class);
            // 启动 NoticeActivity
            startActivity(intent);
        });
    }

    // 定义一个方法用于设置购买点击事件
    private void buy() {
        // 为 llBuy 视图设置点击事件监听器
        llBuy.setOnClickListener(v -> {
            // 获取 SharedPreferences 对象，名称为 "User"，模式为私有模式
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
            // 从 SharedPreferences 中获取用户的邮件地址
            String mail = sharedPreferences.getString("mail", "");

            // 检查邮件地址是否为空
            if (!TextUtils.isEmpty(mail)) {
                // 如果邮件地址不为空，创建一个 Intent 对象，用于启动 BuyActivity
                Intent intent = new Intent(getActivity(), BuyActivity.class);
                // 启动 BuyActivity
                startActivity(intent);
            } else {
                // 如果邮件地址为空，显示提示消息“请先登录”
                Toast.makeText(getActivity(), "Please log in first!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 定义一个方法用于设置图片加载及圆角处理
    private void image() {
        // 计算圆角半径，单位为像素
        int radius = (int) (15 * getResources().getDisplayMetrics().density);

        // 使用 Glide 库加载 img01 视图的图片，并应用圆角效果
        Glide.with(this)
                .load(R.drawable.swim_01) // 加载资源 ID 为 R.drawable.swim_01 的图片
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius))) // 应用圆角效果
                .into(img01); // 将图片设置到 img01 视图中

        // 使用 Glide 库加载 img02 视图的图片，并应用圆角效果
        Glide.with(this)
                .load(R.drawable.swim_02) // 加载资源 ID 为 R.drawable.swim_02 的图片
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius))) // 应用圆角效果
                .into(img02); // 将图片设置到 img02 视图中

        // 使用 Glide 库加载 img03 视图的图片，并应用圆角效果
        Glide.with(this)
                .load(R.drawable.swim_03) // 加载资源 ID 为 R.drawable.swim_03 的图片
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius))) // 应用圆角效果
                .into(img03); // 将图片设置到 img03 视图中
    }

    private void initView(View v) {
        img01 = (ImageView) v.findViewById(R.id.img_01);
        img02 = (ImageView) v.findViewById(R.id.img_05);
        img03 = (ImageView) v.findViewById(R.id.img_03);
        llBuy = (LinearLayout) v.findViewById(R.id.ll_buy);
        llNotice = (LinearLayout) v.findViewById(R.id.ll_notice);
        llUnit = (LinearLayout) v.findViewById(R.id.ll_unit);
    }
}