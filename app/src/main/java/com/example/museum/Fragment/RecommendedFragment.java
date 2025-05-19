package com.example.museum.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Outline;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.museum.Activity.BuyActivity;
import com.example.museum.Adapter.ImageAdapter;
import com.example.museum.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecommendedFragment extends Fragment {

    private View viewBuy;
    private View viewMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recommended, container, false);
        banner(v);
        buy(v);
        return v;
    }

    private void buy(View v) {
        viewBuy = (View) v.findViewById(R.id.view_buy);
        viewBuy.setOnClickListener(v1 -> {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
            String mail = sharedPreferences.getString("mail", "");

            if (!TextUtils.isEmpty(mail)) { // 检查 mail 是否为空
                Intent intent = new Intent(getActivity(), BuyActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "Please log in first!", Toast.LENGTH_SHORT).show(); // 提示用户需要登录
            }
        });

    }

    private void banner(View v) {
        Banner banner = v.findViewById(R.id.banner);
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.home_page_11);
        list.add(R.drawable.home_page_12);
        list.add(R.drawable.home_page_13);
        list.add(R.drawable.home_page_14);
        list.add(R.drawable.home_page_15);
        banner.setImages(list);
        // banner设置圆角
        banner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 0);
            }
        });
        banner.setClipToOutline(true);
        // banner显示
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((Integer) path).into(imageView);
            }
        });
        banner.start();
    }
}