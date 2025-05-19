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

public class FigureDetailsActivity extends AppCompatActivity {


    private ImageView imgBack;
    private TextView tvName;
    private ImageView img;
    private TextView tvNum;
    private TextView tvCategory;
    private TextView tvEra;
    private LinearLayout llCollect;
    private ImageView imgCollect;
    private TextView tvCollect;
    private String imgPath;
    private String era;
    private String name;
    private String category;
    private String num;
    private CollectHelper collectHelper;
    private String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure_details);
        initView();
        back();
        show();
        collect();
    }

    // 定义一个方法 collect()，用于设置收藏按钮的点击事件
    private void collect() {
        // 设置 llCollect 的点击事件监听器
        llCollect.setOnClickListener(v -> {
            // 检查 mail 是否为空，表示用户是否登录
            if (mail.isEmpty()) {
                // 如果 mail 为空，弹出提示框提醒用户先登录
                Toast.makeText(this, "请先进行登录！", Toast.LENGTH_SHORT).show();
            } else {
                // 获取 tvCollect 上的文本内容
                String s = tvCollect.getText().toString();
                // 如果文本内容是 "已收藏"，则执行取消收藏的操作
                if (s.equals("已收藏")) {
                    // 调用 collectHelper 的 deleteData 方法，删除收藏记录
                    boolean b = collectHelper.deleteData(mail, name);
                    // 如果删除成功，弹出提示框并更新 UI
                    if (b) {
                        Toast.makeText(this, "已取消收藏！", Toast.LENGTH_SHORT).show();
                        // 更新 tvCollect 的文本为 "收藏"
                        tvCollect.setText("收藏");
                        // 更新 imgCollect 的图片资源为收藏的图标
                        imgCollect.setImageResource(R.drawable.collect);
                    } else {
                        // 如果删除失败，弹出提示框
                        Toast.makeText(this, "取消收藏失败！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 如果文本内容是 "收藏"，则执行收藏的操作
                    // 调用 collectHelper 的 insertData 方法，插入收藏记录
                    boolean collect = collectHelper.insertData(mail, "文物", "已收藏", imgPath, name, num, era, category, "", " ");
                    // 如果插入成功，弹出提示框并更新 UI
                    if (collect) {
                        Toast.makeText(this, "收藏成功！", Toast.LENGTH_SHORT).show();
                        // 更新 tvCollect 的文本为 "已收藏"
                        tvCollect.setText("已收藏");
                        // 更新 imgCollect 的图片资源为已收藏的图标
                        imgCollect.setImageResource(R.drawable.collectd);
                    } else {
                        // 如果插入失败，弹出提示框
                        Toast.makeText(this, "收藏失败！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // 定义一个方法 show()，用于展示收藏的状态和相关信息
    private void show() {
        // 从 collectHelper 获取收藏记录
        CollectBean collectBean = collectHelper.getCollect(mail, name);
        // 如果获取到收藏记录
        if (collectBean != null) {
            // 判断收藏记录的状态是否为 "已收藏"
            if (collectBean.getCollect().equals("已收藏")) {
                // 如果是 "已收藏"，更新 UI 显示已收藏状态
                tvCollect.setText("已收藏");
                imgCollect.setImageResource(R.drawable.collectd);
            } else {
                // 如果不是 "已收藏"，更新 UI 显示未收藏状态
                tvCollect.setText("收藏");
                imgCollect.setImageResource(R.drawable.collect);
            }
        } else {
            // 如果没有收藏记录，设置默认的未收藏状态
            tvCollect.setText("收藏");
            imgCollect.setImageResource(R.drawable.collect);
        }

        // 使用 Glide 加载 imgPath 中的图片并设置到 img 组件上
        Glide.with(this)
                .load(imgPath)
                .into(img);
        // 设置 tvName、tvNum、tvCategory 和 tvEra 的文本内容
        tvName.setText(name);
        tvNum.setText(num);
        tvCategory.setText(category);
        tvEra.setText(era);
    }

    private void back() {
        imgBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        tvName = (TextView) findViewById(R.id.tv_name);
        img = (ImageView) findViewById(R.id.img);
        tvNum = (TextView) findViewById(R.id.tv_num);
        tvCategory = (TextView) findViewById(R.id.tv_category);
        tvEra = (TextView) findViewById(R.id.tv_era);
        llCollect = (LinearLayout) findViewById(R.id.ll_collect);
        imgCollect = (ImageView) findViewById(R.id.img_collect);
        tvCollect = (TextView) findViewById(R.id.tv_collect);

        // 获取传递的数据
        imgPath = getIntent().getStringExtra("img");
        name = getIntent().getStringExtra("name");
        num = getIntent().getStringExtra("num");
        category = getIntent().getStringExtra("category");
        era = getIntent().getStringExtra("era");

        collectHelper = new CollectHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        mail = sharedPreferences.getString("mail", "");


    }
}