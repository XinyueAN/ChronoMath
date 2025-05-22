package com.example.museum.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log; // Add Log import for debugging
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.museum.Bean.CollectBean; // Assuming CollectBean exists
import com.example.museum.Helper.CollectHelper; // Assuming CollectHelper exists
import com.example.museum.R;

public class FigureDetailsActivity extends AppCompatActivity {

    // View components
    private ImageView imgBack;
    private TextView tvTitleName; // Changed from tvName to avoid confusion with the detail name
    private ImageView imgMainFigure; // Changed from img to clarify it's the main image
    private TextView tvDetailAuthor; // Maps to tv_num in XML
    private TextView tvDetailPeriod; // Maps to tv_category in XML
    private TextView tvDetailDescription; // Maps to tv_era in XML

    private LinearLayout llCollect;
    private ImageView imgCollect;
    private TextView tvCollect;

    // Data fields to hold values received from Intent
    private String figureName;
    private String figurePrice; // If you plan to display price on detail page
    private String figureImagePath; // This will be imageName or imageUrl
    private String figureAuthor;
    private String figureDescription;
    private String figurePeriod;

    // Collection related
    private CollectHelper collectHelper;
    private String userMail; // Renamed from mail for clarity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure_details);

        initView(); // Initializes views and retrieves intent data
        displayFigureDetails(); // New method to display data
        setupCollectButton(); // Renamed from collect() for clarity
        setupBackButton(); // Renamed from back() for clarity
    }

    private void initView() {
        // Initialize views
        imgBack = findViewById(R.id.img_back);
        tvTitleName = findViewById(R.id.tv_name); // This is the TextView in the top bar

        imgMainFigure = findViewById(R.id.img); // The main image in the scroll view
        tvDetailAuthor = findViewById(R.id.tv_num); // This is the Author TextView in the XML
        tvDetailPeriod = findViewById(R.id.tv_category); // This is the Period TextView in the XML
        tvDetailDescription = findViewById(R.id.tv_era); // This is the Abstract TextView in the XML

        llCollect = findViewById(R.id.ll_collect);
        imgCollect = findViewById(R.id.img_collect);
        tvCollect = findViewById(R.id.tv_collect);

        // Retrieve data from the Intent
        // Ensure these keys match what you put in FigureAdapter's Intent.putExtra()
        figureName = getIntent().getStringExtra("name");
        figurePrice = getIntent().getStringExtra("price"); // Retrieve price
        figureImagePath = getIntent().getStringExtra("img"); // Retrieve image name/URL
        figureAuthor = getIntent().getStringExtra("author"); // Retrieve author
        figureDescription = getIntent().getStringExtra("description"); // Retrieve description
        figurePeriod = getIntent().getStringExtra("period"); // Retrieve period

        // Initialize CollectHelper and get user email
        collectHelper = new CollectHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        userMail = sharedPreferences.getString("mail", "");

        // Set the title bar name as soon as it's available
        if (tvTitleName != null) {
            tvTitleName.setText(figureName);
        }
    }

    private void displayFigureDetails() {
        // Display image
        if (imgMainFigure != null && figureImagePath != null && !figureImagePath.isEmpty()) {
            // Check if figureImagePath is a URL or a drawable resource name
            if (figureImagePath.startsWith("http://") || figureImagePath.startsWith("https://")) {
                // It's a URL
                Glide.with(this).load(figureImagePath).into(imgMainFigure);
            } else {
                // Assume it's a drawable resource name
                int imageResId = getResources().getIdentifier(figureImagePath, "drawable", getPackageName());
                if (imageResId != 0) {
                    Glide.with(this).load(imageResId).into(imgMainFigure);
                } else {
                    Log.e("FigureDetailsActivity", "图片资源未找到: " + figureImagePath);
                    // imgMainFigure.setImageResource(R.drawable.placeholder_error); // Fallback placeholder
                }
            }
        } else {
            Log.e("FigureDetailsActivity", "图片路径为空或ImageView为空.");
            // imgMainFigure.setImageResource(R.drawable.default_placeholder); // Fallback placeholder
        }

        // Display author, period, description
        if (tvDetailAuthor != null) {
            tvDetailAuthor.setText(figureAuthor);
        }
        if (tvDetailPeriod != null) {
            tvDetailPeriod.setText(figurePeriod);
        }
        if (tvDetailDescription != null) {
            tvDetailDescription.setText(figureDescription);
        }

        // Initialize collect button state (same as your original show() logic)
        CollectBean collectBean = collectHelper.getCollect(userMail, figureName);
        if (collectBean != null && collectBean.getCollect() != null && collectBean.getCollect().equals("已收藏")) {
            tvCollect.setText("Have Collected");
            imgCollect.setImageResource(R.drawable.collectd);
        } else {
            tvCollect.setText("Collect");
            imgCollect.setImageResource(R.drawable.collect);
        }
    }

    private void setupBackButton() {
        imgBack.setOnClickListener(v -> finish());
    }

    private void setupCollectButton() {
        llCollect.setOnClickListener(v -> {
            if (userMail.isEmpty()) {
                Toast.makeText(this, "请先进行登录！", Toast.LENGTH_SHORT).show();
            } else {
                String currentCollectStatus = tvCollect.getText().toString();
                if (currentCollectStatus.equals("已收藏")) {
                    boolean success = collectHelper.deleteData(userMail, figureName);
                    if (success) {
                        Toast.makeText(this, "已取消收藏！", Toast.LENGTH_SHORT).show();
                        tvCollect.setText("收藏");
                        imgCollect.setImageResource(R.drawable.collect);
                    } else {
                        Toast.makeText(this, "取消收藏失败！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    boolean success = collectHelper.insertData(
                            userMail,
                            "文物",
                            "Have Collected!",            // collect status
                            figureImagePath,
                            figureName,
                            figureAuthor,
                            figurePeriod,
                            figureDescription,
                            figurePrice,
                            " ");

                    if (success) {
                        Toast.makeText(this, "Collection Successful!", Toast.LENGTH_SHORT).show();
                        tvCollect.setText("Have Collected");
                        imgCollect.setImageResource(R.drawable.collectd);
                    } else {
                        Toast.makeText(this, "Collection Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}