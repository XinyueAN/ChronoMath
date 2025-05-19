package com.example.museum.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.museum.Activity.AboutActivity;
import com.example.museum.Activity.BuyActivity;
import com.example.museum.Activity.MapActivity;
import com.example.museum.Activity.MathematiclansActivity;
import com.example.museum.Activity.NoticeActivity;
import com.example.museum.Activity.UnitActivity;
import com.example.museum.Adapter.Learn2Adapter;
import com.example.museum.Adapter.LearnAdapter;
import com.example.museum.Bean.LearnBean;
import com.example.museum.R;

import java.util.ArrayList;
import java.util.List;


public class LearnFragment extends Fragment {


    private LearnAdapter learnAdapter;
    private Learn2Adapter learn2Adapter;

    private LinearLayout llBuy;
    private LinearLayout llNotice;
    private LinearLayout llUnit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learn, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        buy();
        Notice();
        unit();


    }

    private void initView(View view) {
        llBuy = (LinearLayout) view.findViewById(R.id.ll_buy);
        llNotice = (LinearLayout) view.findViewById(R.id.ll_notice);
        llUnit = (LinearLayout) view.findViewById(R.id.ll_unit);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        learnAdapter = new LearnAdapter();
        recyclerView.setAdapter(learnAdapter);
        ArrayList<Integer> imgs = new ArrayList<>();
        imgs.add(R.mipmap.learn);
        imgs.add(R.mipmap.banner2);
        learnAdapter.setList(imgs);


        RecyclerView recyclerView2 = view.findViewById(R.id.recyclerView1);
        recyclerView2.setLayoutManager(new GridLayoutManager(getContext(),2));
        learn2Adapter = new Learn2Adapter();
        recyclerView2.setAdapter(learn2Adapter);
        ArrayList<LearnBean> learnBeans = new ArrayList<>();
        learnBeans.add(new LearnBean(R.mipmap.img_qqb, "七巧板", "The tangram is composed of seven geometric plates."));
        learnBeans.add(new LearnBean(R.mipmap.img_lbs, "鲁班锁", "The Lu Ban lock is composed of several precisely cut wooden blocks."));
        learnBeans.add(new LearnBean(R.mipmap.img_jlh, "九连环", "The baguenaudier is composed of nine metal rings and one metal rod."));
        learnBeans.add(new LearnBean(R.mipmap.img_hrd, "华容道", "Huarong Dao, with its ever-changing and addictive features, is regarded by foreign intelligence experts as one of the \"three Wonders of the intelligence game world\" along with the Rubik's Cube and the Independent Diamond."));
        learn2Adapter.setList(learnBeans);

    }

    // 定义一个方法用于设置购买点击事件
    private void buy() {
        // 为 llBuy 视图设置点击事件监听器
        llBuy.setOnClickListener(v -> {
            // 获取 SharedPreferences 对象，名称为 "User"，模式为私有模式
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
            // 从 SharedPreferences 中获取用户的邮件地址
            String mail = sharedPreferences.getString("mail", "");

            // 检查邮件地址是否为空
            if (!TextUtils.isEmpty(mail)) {
                // 如果邮件地址不为空，创建一个 Intent 对象，用于启动 BuyActivity
                Intent intent = new Intent(getActivity(), BuyActivity.class);
                // 启动 BuyActivity
                startActivity(intent);
            } else {
                // 如果邮件地址为空，显示提示消息“请先登录”
                Toast.makeText(getActivity(), "Please log in first!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // 定义一个方法用于设置单位点击事件
    private void unit() {
        // 为 llUnit 视图设置点击事件监听器
        llUnit.setOnClickListener(v -> {
            // 创建一个 Intent 对象，用于启动 UnitActivity
            //Intent intent = new Intent(getActivity(), UnitActivity.class);
            Intent intent = new Intent(getActivity(), MathematiclansActivity.class);
            // 启动 UnitActivity
            startActivity(intent);
        });
    }

    // 定义一个方法用于设置通知点击事件
    private void Notice() {
        // 为 llNotice 视图设置点击事件监听器
        llNotice.setOnClickListener(v -> {
            // 创建一个 Intent 对象，用于启动 NoticeActivity
           // Intent intent = new Intent(getActivity(), NoticeActivity.class);
            Intent intent = new Intent(getActivity(), AboutActivity.class);
            // 启动 NoticeActivity
            startActivity(intent);
        });
    }

}