package com.example.museum.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.museum.Activity.FigureActivity;
import com.example.museum.R;

public class LookFragment extends Fragment {


    private ImageView imageView6;
    private TextView textView19;
    private ScrollView scrollView3;
    private LinearLayout llLook01;
    private LinearLayout llLook02;
    private LinearLayout llLook03;
    private LinearLayout llLook04;
    private LinearLayout llLook05;
    private TextView textView20;
    private ImageView img06;
    private TextView textView24;
    private TextView textView21;
    private ImageView img07;
    private TextView textView25;
    private TextView textView22;
    private ImageView img08;
    private TextView textView26;
    private TextView textView23;
    private ImageView img09;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_look, container, false);
        initView(v);
        img();
        look();
        return v;
    }

    private void look() {
        llLook01.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FigureActivity.class);
            startActivity(intent);
        });

    }

    private void img() {
        int radius = (int) (15 * getResources().getDisplayMetrics().density);
        Glide.with(this)
                .load(R.drawable.look_06) // 直接使用资源 ID
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius)))
                .into(img06);
        Glide.with(this)
                .load(R.drawable.look_09) // 直接使用资源 ID
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius)))
                .into(img09);
    }

    private void initView(View v) {
        llLook01 = (LinearLayout) v.findViewById(R.id.ll_look01);
        llLook02 = (LinearLayout) v.findViewById(R.id.ll_look02);
        llLook03 = (LinearLayout) v.findViewById(R.id.ll_look03);
        llLook04 = (LinearLayout) v.findViewById(R.id.ll_look04);
        llLook05 = (LinearLayout) v.findViewById(R.id.ll_look05);
        img06 = (ImageView) v.findViewById(R.id.img_06);
        img07 = (ImageView) v.findViewById(R.id.img_07);
        img08 = (ImageView) v.findViewById(R.id.img_08);
        img09 = (ImageView) v.findViewById(R.id.img_09);
    }
}