package com.example.museum.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museum.Adapter.FigureAdapter;
import com.example.museum.Bean.FigureBean;
import com.example.museum.Bean.UnitBean;
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
    private List<FigureBean> figureList = new ArrayList<>(); // 保存原始数据
    private FigureAdapter adapter; // 添加适配器的成员变量
    private View view15;
    private ImageView imgSearch;
    private EditText etSearch;
    private View view16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure);
        initView();
        back();
        show();
        search();
    }

    // 定义搜索功能
    private void search() {
        // 设置搜索按钮的点击事件
        imgSearch.setOnClickListener(v -> {
            // 获取用户输入的搜索关键词，并去除前后空白字符
            String search = etSearch.getText().toString().trim();

            // 如果搜索条件为空，显示所有数据
            if (search.isEmpty()) {
                // 更新适配器的数据为原始的 figureList 列表
                adapter.updateData(figureList); // 显示全部数据
                // 弹出提示框，提示用户输入搜索关键词
                Toast.makeText(this, "Please enter the name of the item you want to query!", Toast.LENGTH_SHORT).show();
            } else {
                // 如果搜索条件不为空，进行数据过滤
                List<FigureBean> filteredList = filterList(search);

                // 如果过滤后的列表为空，提示未找到匹配结果
                if (filteredList.isEmpty()) {
                    // 弹出提示框，提示未找到匹配的结果
                    Toast.makeText(this, "No matching result was found!", Toast.LENGTH_SHORT).show();
                } else {
                    // 如果有匹配的结果，更新适配器的数据
                    adapter.updateData(filteredList); // 调用适配器的更新方法
                }
            }
        });
    }

    // 根据搜索关键词过滤列表
    private List<FigureBean> filterList(String query) {
        // 创建一个空的过滤后的列表
        List<FigureBean> filteredList = new ArrayList<>();
        // 遍历原始的 figureList 列表
        for (FigureBean unit : figureList) {
            // 如果列表中的某个项的名称包含搜索关键词（忽略大小写），将其添加到过滤后的列表中
            if (unit.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(unit);
            }
        }
        // 返回过滤后的列表
        return filteredList;
    }

    // 设置返回按钮的点击事件
    private void back() {
        // 设置返回按钮的点击事件，点击时结束当前活动
        imgBack.setOnClickListener(v -> finish());
    }

    // 初始化显示数据的方法
    private void show() {
        // 从资产目录中加载 JSON 数据
        String json = loadJSONFromAsset();
        // 如果 JSON 数据不为空
        if (json != null) {
            // 创建 Gson 对象用于解析 JSON 数据
            Gson gson = new Gson();
            // 定义数据类型
            TypeToken<List<FigureBean>> token = new TypeToken<List<FigureBean>>() {
            };
            // 将 JSON 数据解析为 List<FigureBean> 对象，并赋值给 figureList
            figureList = gson.fromJson(json, token.getType()); // 保存原始数据到 figureList

            // 设置 RecyclerView 的布局管理器为 GridLayoutManager，显示为 2 列布局
            rv.setLayoutManager(new GridLayoutManager(this, 2)); // 2 列布局

            // 初始化适配器，并将 figureList 传递给适配器
            adapter = new FigureAdapter(this, figureList);
            // 设置 RecyclerView 的适配器
            rv.setAdapter(adapter);
        }
    }

    // 从资产目录中加载 JSON 数据的方法
    private String loadJSONFromAsset() {
        String json;
        try {
            // 打开资产目录中的 figure.json 文件
            InputStream is = getAssets().open("figure.json");
            // 获取文件的大小
            int size = is.available();
            // 创建一个字节数组来存储文件内容
            byte[] buffer = new byte[size];
            // 读取文件内容到字节数组中
            is.read(buffer);
            // 关闭文件输入流
            is.close();
            // 将字节数组转换为字符串
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            // 打印异常信息并返回 null
            ex.printStackTrace();
            return null;
        }
        // 返回 JSON 数据
        return json;
    }

    private void initView() {
        linearLayout4 = (LinearLayout) findViewById(R.id.linearLayout4);
        imgBack = (ImageView) findViewById(R.id.img_back);
        tvName = (TextView) findViewById(R.id.tv_name);
        rv = (RecyclerView) findViewById(R.id.rv);
        view15 = (View) findViewById(R.id.view15);
        imgSearch = (ImageView) findViewById(R.id.img_search);
        etSearch = (EditText) findViewById(R.id.et_search);
        view16 = (View) findViewById(R.id.view16);
    }
}