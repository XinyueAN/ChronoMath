package com.example.museum.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museum.Adapter.CollectAdapter;
import com.example.museum.Adapter.FigureAdapter;
import com.example.museum.Bean.CollectBean;
import com.example.museum.Bean.FigureBean;
import com.example.museum.Helper.CollectHelper;
import com.example.museum.R;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class CollectActivity extends AppCompatActivity {

    private LinearLayout linearLayout4;
    private ImageView imgBack;
    private TextView textView33;
    private RecyclerView rv;
    private TextView tvNoData;
    private String mail;
    private TabLayout tablayout;
    private CollectHelper collectHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initView();
        back();
        setupTabLayout();
        // 默认选择“建筑”标签后，调用 JZData() 来加载数据
        JZData();
    }

    @Override
    public void onResume() {
        super.onResume();
        TabLayout.Tab defaultTab = tablayout.getTabAt(0); // 默认选择第一个 Tab，即“建筑”
        if (defaultTab != null) {
            defaultTab.select();
        }
    }

    private void initView() {
        linearLayout4 = findViewById(R.id.linearLayout4);
        imgBack = findViewById(R.id.img_back);
        textView33 = findViewById(R.id.textView33);
        rv = findViewById(R.id.rv);
        tvNoData = findViewById(R.id.tv_no_data); // 初始化占位视图

        collectHelper = new CollectHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        mail = sharedPreferences.getString("mail", "");
        tablayout = findViewById(R.id.tablayout);
    }

    private void back() {
        imgBack.setOnClickListener(v -> finish());
    }

    private void setupTabLayout() {
        // 添加 Tab
        tablayout.addTab(tablayout.newTab().setText("Mathematicians")); // 添加“建筑”Tab
        tablayout.addTab(tablayout.newTab().setText("Inventions")); // 添加“文物”Tab

        // 设置 Tab 选择监听器
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 获取被选中的 Tab 的文本
                String tabText = tab.getText().toString();
                // 判断选中的 Tab 文本，执行相应方法
                if (tabText.equals("建筑")) {
                    JZData(); // 如果是“建筑”Tab，调用 JZData 方法
                } else {
                    WWData(); // 否则，调用 WWData 方法
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // 不需要处理此回调，暂时留空
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // 不需要处理此回调，暂时留空
            }
        });
    }

    private void JZData() {
        // 获取“建筑”相关的数据
        List<CollectBean> collectBeans = collectHelper.getJZ(mail, "建筑");
        // 创建 CollectAdapter 适配器
        CollectAdapter collectAdapter = new CollectAdapter(this, collectBeans);
        // 更新 UI
        updateUI(collectBeans, collectAdapter, "您还没有收藏！");
    }

    private void WWData() {
        // 获取“文物”相关的数据
        List<FigureBean> figureBeans = collectHelper.getWW(mail, "文物");
        // 创建 FigureAdapter 适配器
        FigureAdapter figureAdapter = new FigureAdapter(this, figureBeans);
        // 更新 UI
        updateUI(figureBeans, figureAdapter, "您还没有收藏！");
    }

    private <T> void updateUI(List<T> dataList, RecyclerView.Adapter<?> adapter, String emptyMessage) {
        // 判断数据列表是否为空
        if (dataList.isEmpty()) {
            // 显示提示消息
            Toast.makeText(this, emptyMessage, Toast.LENGTH_SHORT).show();
            // 隐藏 RecyclerView
            rv.setVisibility(View.GONE);
            // 显示占位视图
            tvNoData.setVisibility(View.VISIBLE);
        } else {
            // 显示 RecyclerView
            rv.setVisibility(View.VISIBLE);
            // 隐藏占位视图
            tvNoData.setVisibility(View.GONE);
            // 设置 RecyclerView 的布局管理器为 GridLayoutManager，横排两个显示
            rv.setLayoutManager(new GridLayoutManager(this, 2));
            // 设置 RecyclerView 的适配器
            rv.setAdapter(adapter);
        }
    }
}
