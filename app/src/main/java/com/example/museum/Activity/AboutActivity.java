package com.example.museum.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.museum.Helper.UserHelper;
import com.example.museum.R;

public class AboutActivity extends AppCompatActivity {

    private ImageView imgBack;
    private EditText etMail;
    private EditText etName;
    private EditText etPassword;
    private RadioButton rb;
    private Button btnRegister;
    private TextView tvHavelogin;
    private TextView tvLogin;
    private UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();

    }



    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(v -> finish());
        findViewById(R.id.btn_register).setOnClickListener(v -> finish());
    }
}