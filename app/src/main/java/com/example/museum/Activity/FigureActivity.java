package com.example.museum.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museum.Adapter.FigureAdapter;
import com.example.museum.Bean.FigureBean;
import com.example.museum.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FigureActivity extends AppCompatActivity {

    private LinearLayout linearLayout4;
    private ImageView imgBack;
    private TextView tvName;
    private RecyclerView rv;
    private List<FigureBean> figureList = new ArrayList<>();
    private FigureAdapter adapter;
    private View view15;
    private View view16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure);
        initView();
        back();
        show();
    }

    private void back() {
        imgBack.setOnClickListener(v -> finish());
    }

    // 初始化显示数据的方法 - 保留
    private void show() {
        String json = loadJSONFromAsset();
        if (json != null) {
            Gson gson = new Gson();
            TypeToken<List<FigureBean>> token = new TypeToken<List<FigureBean>>() {
            };
            // 将 JSON 数据解析为 List<FigureBean> 对象，并赋值给 figureList
            figureList = gson.fromJson(json, token.getType()); // 加载并保存原始数据

            // 设置 RecyclerView 的布局管理器为 GridLayoutManager，显示为 2 列布局
            rv.setLayoutManager(new GridLayoutManager(this, 2));

            // 初始化适配器，并将 figureList 传递给适配器 (使用原始数据)
            adapter = new FigureAdapter(this, figureList);
            // 设置 RecyclerView 的适配器
            rv.setAdapter(adapter);
        }
    }

    // 从资产目录中加载 JSON 数据的方法 - 保留
    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("figure.json"); // 从 assets 加载 JSON
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // 初始化视图的方法 - 修改
    private void initView() {
        linearLayout4 = (LinearLayout) findViewById(R.id.linearLayout4);
        imgBack = (ImageView) findViewById(R.id.img_back);
        tvName = (TextView) findViewById(R.id.tv_name);
        rv = (RecyclerView) findViewById(R.id.rv);
        view15 = (View) findViewById(R.id.view15);
        view16 = (View) findViewById(R.id.view16);
    }
}