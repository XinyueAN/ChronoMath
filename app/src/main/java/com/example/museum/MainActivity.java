package com.example.museum;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.museum.Fragment.LearnFragment;
import com.example.museum.Fragment.LookFragment;
import com.example.museum.Fragment.MineFragment;
import com.example.museum.Fragment.RecommendedFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fr;
    private LinearLayout llRecommended;
    private ImageView imgRecommende;
    private LinearLayout llSwim;
    private ImageView imgSwim;
    private LinearLayout llLook;
    private ImageView imgLook;
    private LinearLayout llMine;
    private ImageView imgMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Navigation();
        // activity跳转传参—获取参数
        String state = getIntent().getStringExtra("state");
        // 确保 state 不为 null，然后再进行判断
        if (state != null && !state.isEmpty() && state.equals("3")) {
            llMine.callOnClick();
        } else {
            llRecommended.callOnClick();
        }
    }

    // 点击控件进行页面转换
    private void Navigation() {
        llRecommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(0);
                imgRecommende.setImageResource(R.drawable.recommendedd);
                imgSwim.setImageResource(R.drawable.swim);
                imgLook.setImageResource(R.drawable.look);
                imgMine.setImageResource(R.drawable.mine);
            }
        });
        llSwim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(1);
                imgRecommende.setImageResource(R.drawable.recommended);
                imgSwim.setImageResource(R.drawable.swimd);
                imgLook.setImageResource(R.drawable.look);
                imgMine.setImageResource(R.drawable.mine);

            }
        });
        llLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(2);
                imgRecommende.setImageResource(R.drawable.recommended);
                imgSwim.setImageResource(R.drawable.swim);
                imgLook.setImageResource(R.drawable.lookd);
                imgMine.setImageResource(R.drawable.mine);
            }
        });
        llMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(3);
                imgRecommende.setImageResource(R.drawable.recommended);
                imgSwim.setImageResource(R.drawable.swim);
                imgLook.setImageResource(R.drawable.look);
                imgMine.setImageResource(R.drawable.mined);
            }
        });
    }

    //切换fg页面
    private void setFragment(int id) {
        Fragment fragment = null;
        switch (id) {
            case 0:
                fragment = new RecommendedFragment();
                break;
            case 1:
                fragment = new LearnFragment();
                //fragment = new SwimFragment();
                break;
            case 2:
                fragment = new LookFragment();
                break;
            case 3:
                fragment = new MineFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fr, fragment).commit();

    }

    private void initView() {
        fr = (FrameLayout) findViewById(R.id.fr);
        llRecommended = (LinearLayout) findViewById(R.id.ll_Recommended);
        imgRecommende = (ImageView) findViewById(R.id.img_recommende);
        llSwim = (LinearLayout) findViewById(R.id.ll_Swim);
        imgSwim = (ImageView) findViewById(R.id.img_swim);
        llLook = (LinearLayout) findViewById(R.id.ll_Look);
        imgLook = (ImageView) findViewById(R.id.img_look);
        llMine = (LinearLayout) findViewById(R.id.ll_Mine);
        imgMine = (ImageView) findViewById(R.id.img_mine);
    }
}