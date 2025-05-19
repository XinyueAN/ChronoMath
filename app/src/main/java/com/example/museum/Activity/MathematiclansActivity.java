package com.example.museum.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museum.Adapter.LearnAdapter;
import com.example.museum.Adapter.Mathematiclans1Adapter;
import com.example.museum.Adapter.MathematiclansAdapter;
import com.example.museum.Bean.MathematiclanBean;
import com.example.museum.Helper.UserHelper;
import com.example.museum.R;

import java.util.ArrayList;

public class MathematiclansActivity extends AppCompatActivity {

    private ImageView imgBack;
    private MathematiclansAdapter mathematiclansAdapter;
    private Mathematiclans1Adapter mathematiclans1Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematiclans);
        initView();

    }



    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MathematiclansActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mathematiclansAdapter = new MathematiclansAdapter();
        recyclerView.setAdapter(mathematiclansAdapter);
        ArrayList<MathematiclanBean> mathematiclanBeans = new ArrayList<>();

        mathematiclanBeans.add(new MathematiclanBean(R.mipmap.img_p1, "刘徽", "魏晋时期", "数学家",""));
        mathematiclanBeans.add(new MathematiclanBean(R.mipmap.img_p2, "祖冲之", "南北朝时期", "数学家",""));
        mathematiclanBeans.add(new MathematiclanBean(R.mipmap.img_p3, "张衡", "东汉时期", "科学家、文学家",""));
        mathematiclanBeans.add(new MathematiclanBean(R.mipmap.img_p4, "华罗庚", "中国科学院", "院士、数学家",""));
        mathematiclanBeans.add(new MathematiclanBean(R.mipmap.img_p5, "陈景润", "中国科学院", "院士、数学家",""));
        mathematiclansAdapter.setList(mathematiclanBeans);


        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(MathematiclansActivity.this));
        mathematiclans1Adapter = new Mathematiclans1Adapter();
        recyclerView1.setAdapter(mathematiclans1Adapter);
        ArrayList<MathematiclanBean> mathematiclanBeans1 = new ArrayList<>();
        mathematiclanBeans1.add(new MathematiclanBean(R.mipmap.img_m1, "Johann Carl Friedrich Gauß", "", "Physicist、Astronomer","In 1796, Gauss proved that a ruler could be formed into a regular heptagon. In 1807, Gauss became professor at ..."));
        mathematiclanBeans1.add(new MathematiclanBean(R.mipmap.img_m2, "Isaac Newton", "", "Mathematician、Philosopher","He made pioneering contributions to optics and co-discovered calculus with German mathematicians..."));
        mathematiclanBeans1.add(new MathematiclanBean(R.mipmap.img_m3, "Archimedes", "", "Mathematician、Physicist","Archimedes laid the foundation of modern calculus and analysis by applying the concept of..."));
        mathematiclanBeans1.add(new MathematiclanBean(R.mipmap.img_m4, "Henri Poincare", "", "Philosopher of Science、Celestial Mechanist","He is known for his conjecture of the three-body problem and for concepts related to the development..."));
        mathematiclans1Adapter.setList(mathematiclanBeans1);




    }
}