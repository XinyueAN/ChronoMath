package com.example.museum.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.museum.Bean.CollectBean;
import com.example.museum.Helper.CollectHelper;
import com.example.museum.R;

public class UnitDetailsActivity extends AppCompatActivity {

    private ImageView img;
    private ImageView imgBack;
    private TextView tvName;
    private TextView tvEra;
    private TextView tvCategory;
    private TextView tvRegion;
    private TextView tvDescription;
    private LinearLayout llCollect;
    private ImageView imgCollect;
    private TextView tvCollect;
    private String imgPath;
    private String era;
    private String name;
    private String category;
    private String region;
    private String description;
    private CollectHelper collectHelper;
    private String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_details);
        initView();
        back();
        show();
        collect();
    }

    // 定义一个名为 collect 的方法
    private void collect() {
        // 设置 llCollect 的点击事件监听器
        llCollect.setOnClickListener(v -> {
            // 判断 mail 是否为空，若为空则提示用户先进行登录
            if (mail.isEmpty()) {
                Toast.makeText(this, "Please log in first!！", Toast.LENGTH_SHORT).show();
            } else {
                // 获取 tvCollect 的文本内容
                String s = tvCollect.getText().toString();
                // 判断当前文本内容是否为 "已收藏"
                if (s.equals("已收藏")) {
                    // 如果已经收藏，则执行删除操作
                    boolean b = collectHelper.deleteData(mail, name);
                    // 根据删除操作的结果显示不同的提示信息
                    if (b) {
                        // 删除成功，显示“已取消收藏”提示，更新 UI
                        Toast.makeText(this, "已取消收藏！", Toast.LENGTH_SHORT).show();
                        tvCollect.setText("收藏");
                        imgCollect.setImageResource(R.drawable.collect);
                    } else {
                        // 删除失败，显示“取消收藏失败”提示
                        Toast.makeText(this, "取消收藏失败！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 如果当前文本内容不是“已收藏”，则执行收藏操作
                    boolean collect = collectHelper.insertData(mail, "建筑", "已收藏", imgPath, name, "", era, category, region, description);
                    // 根据收藏操作的结果显示不同的提示信息
                    if (collect) {
                        // 收藏成功，显示“收藏成功”提示，更新 UI
                        Toast.makeText(this, "收藏成功！", Toast.LENGTH_SHORT).show();
                        tvCollect.setText("已收藏");
                        imgCollect.setImageResource(R.drawable.collectd);
                    } else {
                        // 收藏失败，显示“收藏失败”提示
                        Toast.makeText(this, "收藏失败！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // 定义一个名为 back 的方法
    private void back() {
        // 设置 imgBack 的点击事件监听器
        imgBack.setOnClickListener(v -> {
            // 调用 finish() 方法结束当前活动
            finish();
        });
    }

    // 定义一个名为 show 的方法
    private void show() {
        // 从 collectHelper 获取收藏信息
        CollectBean collectBean = collectHelper.getCollect(mail, name);
        // 判断 collectBean 是否不为空
        if (collectBean != null) {
            // 如果 collectBean 不为空，检查其收藏状态
            if (collectBean.getCollect().equals("已收藏")) {
                // 如果已收藏，更新 UI 显示“已收藏”状态
                tvCollect.setText("已收藏");
                imgCollect.setImageResource(R.drawable.collectd);
            } else {
                // 如果未收藏，更新 UI 显示“收藏”状态
                tvCollect.setText("收藏");
                imgCollect.setImageResource(R.drawable.collect);
            }
        } else {
            // 如果 collectBean 为空，默认显示“收藏”状态
            tvCollect.setText("收藏");
            imgCollect.setImageResource(R.drawable.collect);
        }

        // 使用 Glide 库加载图片，并将其显示在 img 控件中
        Glide.with(this)
                .load(imgPath)
                .into(img);
        // 设置文本控件的内容
        tvName.setText(name);
        tvEra.setText(era);
        tvCategory.setText(category);
        tvRegion.setText(region);
        tvDescription.setText(description);
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        imgBack = (ImageView) findViewById(R.id.img_back);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvEra = (TextView) findViewById(R.id.tv_era);
        tvCategory = (TextView) findViewById(R.id.tv_category);
        tvRegion = (TextView) findViewById(R.id.tv_region);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        llCollect = (LinearLayout) findViewById(R.id.ll_collect);
        imgCollect = (ImageView) findViewById(R.id.img_collect);
        tvCollect = (TextView) findViewById(R.id.tv_collect);
        // 获取传递的数据
        imgPath = getIntent().getStringExtra("img");
        name = getIntent().getStringExtra("name");
        era = getIntent().getStringExtra("era");
        category = getIntent().getStringExtra("category");
        region = getIntent().getStringExtra("region");
        description = getIntent().getStringExtra("description");

        collectHelper = new CollectHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        mail = sharedPreferences.getString("mail", "");

    }
}