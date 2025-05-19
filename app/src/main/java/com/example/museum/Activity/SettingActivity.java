package com.example.museum.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.museum.R;

public class SettingActivity extends AppCompatActivity {

    private ImageView imgBack;
    private LinearLayout llPassword;
    private LinearLayout llUsername;
    private LinearLayout llQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        quit();
        back();
        ll();
    }

    private void ll() {
        // 设置密码修改项的点击事件监听器
        llPassword.setOnClickListener(v -> {
            // 创建一个 Intent 用于启动 ModifyActivity
            Intent intent = new Intent(SettingActivity.this, ModifyActivity.class);
            // 添加额外数据到 Intent 中，用于标识修改类型为密码
            intent.putExtra("state", "1");
            // 启动 ModifyActivity
            startActivity(intent);
        });

        // 设置用户名修改项的点击事件监听器
        llUsername.setOnClickListener(v -> {
            // 创建一个 Intent 用于启动 ModifyActivity
            Intent intent = new Intent(SettingActivity.this, ModifyActivity.class);
            // 添加额外数据到 Intent 中，用于标识修改类型为用户名
            intent.putExtra("state", "2");
            // 启动 ModifyActivity
            startActivity(intent);
        });
    }

    private void back() {
        // 设置返回按钮的点击事件监听器
        imgBack.setOnClickListener(v -> {
            // 关闭当前活动
            finish();
        });
    }

    private void quit() {
        // 设置退出按钮的点击事件监听器
        llQuit.setOnClickListener(v -> {
            // 获取名为 "User" 的 SharedPreferences 实例
            SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
            // 获取 SharedPreferences 编辑器
            SharedPreferences.Editor editor = sharedPreferences.edit();
            // 清空 SharedPreferences 中的所有数据
            editor.clear();
            // 提交更改
            editor.apply();
            // 关闭当前活动
            finish();
            // 显示退出成功的提示消息
            Toast.makeText(this, "Exit successful!", Toast.LENGTH_SHORT).show();
        });
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        llPassword = (LinearLayout) findViewById(R.id.ll_password);
        llUsername = (LinearLayout) findViewById(R.id.ll_username);
        llQuit = (LinearLayout) findViewById(R.id.ll_quit);
    }
}