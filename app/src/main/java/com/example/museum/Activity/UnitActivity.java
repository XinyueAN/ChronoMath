package com.example.museum.Activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museum.Adapter.UnitAdapter;
import com.example.museum.Bean.UnitBean;
import com.example.museum.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UnitActivity extends AppCompatActivity {

    private ImageView imgBack;
    private ImageView imgSearch;
    private EditText etSearch;
    private RecyclerView rv;
    private List<UnitBean> unitList = new ArrayList<>(); // 保存原始数据
    private UnitAdapter adapter; // 添加适配器的成员变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        initView();
        back();
        show();
        search();
    }

    private void search() {
        // 设置搜索图标的点击事件监听器
        imgSearch.setOnClickListener(v -> {
            // 获取搜索框中的文本，去除前后空格
            String search = etSearch.getText().toString().trim();

            // 如果搜索条件为空，显示所有数据
            if (search.isEmpty()) {
                // 更新适配器的数据为原始数据（即显示全部数据）
                adapter.updateData(unitList);
                // 提示用户输入查询名称
                Toast.makeText(this, "Please enter the name you want to query!", Toast.LENGTH_SHORT).show();
            } else {
                // 过滤数据以得到匹配的项
                List<UnitBean> filteredList = filterList(search);

                // 如果过滤后的列表为空，则提示未找到结果
                if (filteredList.isEmpty()) {
                    Toast.makeText(this, "No matching result was found!", Toast.LENGTH_SHORT).show();
                } else {
                    // 更新适配器的数据为过滤后的列表
                    adapter.updateData(filteredList); // 调用适配器的更新方法
                }
            }
        });
    }

    private List<UnitBean> filterList(String query) {
        // 创建一个空的过滤结果列表
        List<UnitBean> filteredList = new ArrayList<>();
        // 遍历原始数据列表
        for (UnitBean unit : unitList) {
            // 如果单位名称中包含搜索的查询字符串（忽略大小写），则将其添加到过滤列表中
            if (unit.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(unit);
            }
        }
        // 返回过滤后的列表
        return filteredList;
    }

    private void back() {
        // 设置返回按钮的点击事件监听器
        imgBack.setOnClickListener(v -> finish()); // 关闭当前活动
    }

    private void show() {
        // 从资产中加载 JSON 数据
        String json = loadJSONFromAsset();
        if (json != null) {
            // 创建 Gson 实例用于解析 JSON
            Gson gson = new Gson();
            // 定义用于解析的类型
            TypeToken<List<UnitBean>> token = new TypeToken<List<UnitBean>>() {
            };
            // 解析 JSON 数据并保存到 unitList
            unitList = gson.fromJson(json, token.getType());

            // 设置 RecyclerView 的布局管理器为 GridLayoutManager，设置列数为 2
            rv.setLayoutManager(new GridLayoutManager(this, 2)); // 2 列布局

            // 初始化适配器并与 RecyclerView 关联
            adapter = new UnitAdapter(this, unitList);
            rv.setAdapter(adapter);
        }
    }

    private String loadJSONFromAsset() {
        String json;
        try {
            // 从 assets 目录中打开 "unit.json" 文件
            InputStream is = getAssets().open("unit.json");
            // 获取文件的大小
            int size = is.available();
            byte[] buffer = new byte[size];
            // 读取文件内容到 buffer
            is.read(buffer);
            is.close();
            // 将 buffer 转换为字符串（UTF-8 编码）
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace(); // 打印异常堆栈信息
            return null; // 返回 null 表示读取失败
        }
        return json; // 返回读取到的 JSON 字符串
    }

    private void initView() {
        imgBack = findViewById(R.id.img_back);
        imgSearch = findViewById(R.id.img_search);
        etSearch = findViewById(R.id.et_search);
        rv = findViewById(R.id.rv);
    }
}
