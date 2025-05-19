package com.example.museum.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.museum.MainActivity;
import com.example.museum.R;

public class StartActivity extends AppCompatActivity {
    private TimeCount timeCount;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            navigateIfNeeded();
        }
    };
    private Button btnSkip;
    private boolean hasNavigated = false;

    // 进入登陆页面
    private void tomainActive() {
        if (!hasNavigated) {
            hasNavigated = true;
            startActivity(new Intent(this, MainActivity.class));
            // 跳转完成后注销
            finish();
        }
    }

    private void navigateIfNeeded() {
        if (!hasNavigated) {
            tomainActive();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // 初始化视图和按钮
        initView();

        // 设置按钮点击事件
        btnSkip.setOnClickListener(v -> {
            // 取消计时器
            if (timeCount != null) {
                timeCount.cancel();
            }
            // 移除 runnable
            handler.removeCallbacks(runnable);
            // 跳转到 MainActivity
            tomainActive();
        });

        // 延迟三秒执行 runnable
        handler.postDelayed(runnable, 3000);

        // 初始化计时器, 共执行四秒, 一秒执行一次
        timeCount = new TimeCount(4000, 1000);
        timeCount.start();
    }

    private void initView() {
        btnSkip = findViewById(R.id.btn_skip);
    }

    // 计时器
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 在这里可以实现更新UI等操作
        }

        @Override
        public void onFinish() {
            navigateIfNeeded();
        }
    }
}