package com.example.museum.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.museum.R;

public class NoticeActivity extends AppCompatActivity {

    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        initView();
        back();
    }

    private void back() {
        imgBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
    }
}