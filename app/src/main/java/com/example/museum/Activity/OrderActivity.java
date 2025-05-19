package com.example.museum.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.museum.Adapter.OrderAdapter;
import com.example.museum.Bean.OrderBean;
import com.example.museum.Helper.BuyHelper;
import com.example.museum.MainActivity;
import com.example.museum.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Collections;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private ImageView imgBack;
    private TabLayout tabLayout;
    private ListView lv;


    private BuyHelper dbHelper;
    private String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        back();
        setupTabLayout();
        dbHelper = new BuyHelper(this);
        loadData();
    }

    // back() 方法：设置返回按钮的点击事件
    private void back() {
        // 为 imgBack 图像按钮设置点击事件监听器
        imgBack.setOnClickListener(v -> {
            // 创建一个 Intent，用于启动 MainActivity
            Intent intent = new Intent(OrderActivity.this, MainActivity.class);
            // 向 Intent 中添加一个额外数据 "state"，值为 "3"
            intent.putExtra("state", "3");
            // 启动 MainActivity
            startActivity(intent);
        });
    }

    // loadData() 方法：加载并显示所有订单数据
    private void loadData() {
        // 从数据库中根据邮箱获取所有订单
        List<OrderBean> orders = dbHelper.getOrdersByMail(mail);
        // 将订单列表反转，使得最新的订单显示在列表顶部
        Collections.reverse(orders);
        // 创建 OrderAdapter 实例，并将订单数据传递给适配器
        OrderAdapter adapter = new OrderAdapter(this, orders);
        // 将适配器设置给 ListView 控件
        lv.setAdapter(adapter);
    }

    // initView() 方法：初始化界面上的视图控件
    private void initView() {
        // 通过 findViewById 获取 ImageView 控件 imgBack
        imgBack = (ImageView) findViewById(R.id.img_back);
        // 通过 findViewById 获取 TabLayout 控件 tabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        // 通过 findViewById 获取 ListView 控件 lv
        lv = (ListView) findViewById(R.id.lv);
        // 获取 SharedPreferences 实例，用于读取用户数据
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        // 从 SharedPreferences 中获取用户邮箱地址
        mail = sharedPreferences.getString("mail", "");
    }

    // setupTabLayout() 方法：设置 TabLayout 和选项卡切换逻辑
    private void setupTabLayout() {
        // 向 TabLayout 添加 "全部" 选项卡
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        // 向 TabLayout 添加 "待支付" 选项卡
        tabLayout.addTab(tabLayout.newTab().setText("To be paid"));
        // 向 TabLayout 添加 "已支付" 选项卡
        tabLayout.addTab(tabLayout.newTab().setText("Paid"));

        // 为 TabLayout 添加选项卡选中监听器
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 获取被选中的选项卡的文本
                String tabText = tab.getText().toString();
                // 根据选项卡的文本决定加载哪种数据
                if (tabText.equals("All")) {
                    // 如果选中的是 "全部"，加载所有订单数据
                    loadData();
                } else {
                    // 如果选中的是 "待支付" 或 "已支付"，根据状态获取订单列表
                    List<OrderBean> orders = dbHelper.getOrdersByStatusAndMail(tabText, mail);

                    // 将订单列表反转，使得最新的订单显示在列表顶部
                    Collections.reverse(orders);

                    // 创建 OrderAdapter 实例，并将订单数据传递给适配器
                    OrderAdapter adapter = new OrderAdapter(OrderActivity.this, orders);
                    // 将适配器设置给 ListView 控件
                    lv.setAdapter(adapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // 选项卡未被选中时的操作，可以为空
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // 选项卡被重新选中时的操作，可以为空
            }
        });
    }
}