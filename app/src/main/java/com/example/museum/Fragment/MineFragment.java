package com.example.museum.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.museum.Activity.CollectActivity;
import com.example.museum.Activity.LoginActivity;
import com.example.museum.Activity.OrderActivity;
import com.example.museum.Activity.SettingActivity;
import com.example.museum.Bean.UserBean;
import com.example.museum.Helper.UserHelper;
import com.example.museum.R;

public class MineFragment extends Fragment {

    private LinearLayout llLogin;
    private ImageView img05;
    private TextView tvLogin;
    private LinearLayout llOrder;
    private LinearLayout llSettings;
    private String mail;
    private UserHelper userHelper;
    private LinearLayout llCollect;

    @Override
    public void onResume() {
        super.onResume();
        show(); // 每次进入页面时调用
        setting();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(v);
        avatar();
        show();
        setting();
        return v;
    }


    // 设置视图的点击事件和登录状态
    private void setting() {
        // 获取 tvLogin 的文本内容并转换为字符串
        String text = tvLogin.getText().toString();

        // 创建一个点击事件监听器，用于显示提示“请先登录！”
        View.OnClickListener loginRequiredListener = v ->
                Toast.makeText(getActivity(), "Please log in first!", Toast.LENGTH_SHORT).show();

        // 如果 tvLogin 的文本为“请点击登录”
        if (text.equals("Please click to log in.")) {
            // 设置 llSettings、llOrder 和 llCollect 的点击事件监听器为 loginRequiredListener
            llSettings.setOnClickListener(loginRequiredListener);
            llOrder.setOnClickListener(loginRequiredListener);
            llCollect.setOnClickListener(loginRequiredListener);
        } else {
            // 如果文本不是“请点击登录”，则设置 llSettings 的点击事件监听器
            llSettings.setOnClickListener(v -> {
                // 启用 llSettings，允许点击
                llSettings.setEnabled(true);
                llSettings.setClickable(true);
                // 启动 SettingActivity 活动
                startActivity(new Intent(getActivity(), SettingActivity.class));
            });

            // 设置 llOrder 的点击事件监听器
            llOrder.setOnClickListener(v ->
                    // 启动 OrderActivity 活动
                    startActivity(new Intent(getActivity(), OrderActivity.class))
            );

            // 设置 llCollect 的点击事件监听器
            llCollect.setOnClickListener(v ->
                    // 启动 CollectActivity 活动
                    startActivity(new Intent(getActivity(), CollectActivity.class))
            );
        }
    }

    // 显示用户信息或登录状态
    private void show() {
        // 从 SharedPreferences 中获取名为 "User" 的共享偏好设置
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        // 从 SharedPreferences 中获取 "mail" 键对应的值，如果不存在则默认为空字符串
        mail = sharedPreferences.getString("mail", "");
        // 根据邮件获取用户信息
        UserBean userInfoByEmail = userHelper.getUserInfoByEmail(mail);

        // 如果用户信息存在
        if (userInfoByEmail != null) {
            // 获取用户名
            String username = userInfoByEmail.getUsername();
            // 更新 tvLogin 的文本为 “Hi, 用户名”
            tvLogin.setText("Hi, " + username);
            // 用户信息存在时，禁用 llLogin 的点击事件
            llLogin.setEnabled(false);
            llLogin.setClickable(false);
        } else {
            // 如果用户信息不存在，更新 tvLogin 的文本为 “请点击登录”
            tvLogin.setText("Please click to log in.");
            // 用户信息不存在时，启用 llLogin 的点击事件
            llLogin.setEnabled(true);
            llLogin.setClickable(true);
            // 设置 llLogin 的点击事件监听器
            llLogin.setOnClickListener(v -> {
                // 创建启动 LoginActivity 活动的意图
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                // 启动 LoginActivity 活动
                startActivity(intent);
            });
        }
    }

    // 设置头像的显示效果
    private void avatar() {
        // 计算圆角半径，单位为像素
        int radius = (int) (50 * getResources().getDisplayMetrics().density);
        // 使用 Glide 加载图片，并应用圆角效果
        Glide.with(this)
                .load(R.drawable.avatar) // 加载资源 ID 为 mine_05 的图片
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius))) // 应用圆角效果
                .into(img05); // 将处理后的图片加载到 img05 视图中
    }

    private void initView(View v) {
        llLogin = (LinearLayout) v.findViewById(R.id.ll_login);
        llCollect = (LinearLayout) v.findViewById(R.id.ll_collect);
        img05 = (ImageView) v.findViewById(R.id.img_05);
        tvLogin = (TextView) v.findViewById(R.id.tv_login);
        llOrder = (LinearLayout) v.findViewById(R.id.ll_order);
        llSettings = (LinearLayout) v.findViewById(R.id.ll_settings);
        userHelper = new UserHelper(getActivity());
    }
}