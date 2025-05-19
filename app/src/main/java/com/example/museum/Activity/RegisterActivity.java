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

public class RegisterActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_register);
        initView();
        back();
        register();
    }

    private void register() {
        // 设置注册按钮的点击事件监听器
        btnRegister.setOnClickListener(v -> {
            // 获取用户输入的邮箱、用户名和密码
            String mail = etMail.getText().toString();
            String name = etName.getText().toString();
            String password = etPassword.getText().toString();

            // 检查邮箱、用户名和密码是否为空
            if (mail.isEmpty() || name.isEmpty() || password.isEmpty()) {
                // 如果有任何字段为空，显示提示消息
                Toast.makeText(this, "Please make sure that all the content is not empty!", Toast.LENGTH_SHORT).show();
                return; // 退出方法
            }

            // 检查密码长度是否在6到16个字符之间
            if (password.length() < 6 || password.length() > 16) {
                // 如果密码长度不符合要求，显示提示消息
                Toast.makeText(this, "The password length should be 6 to 16 characters!", Toast.LENGTH_SHORT).show();
                return; // 退出方法
            }

            // 检查同意注册协议的 RadioButton 是否被选中
            boolean isRbChecked = rb.isChecked();

            if (!isRbChecked) {
                // 如果没有勾选同意注册协议，显示提示消息
                Toast.makeText(this, "Please check the box to agree to the registration agreement!", Toast.LENGTH_SHORT).show();
                return; // 退出方法
            }

            // 调用 userHelper 的 addUser 方法注册用户，并获取返回结果
            boolean b = userHelper.addUser(mail, name, password);
            if (b) {
                // 如果注册成功，显示成功消息并关闭当前活动
                Toast.makeText(this, "Register successfully!", Toast.LENGTH_SHORT).show();
                finish(); // 关闭当前活动
            } else {
                // 如果注册失败，显示失败消息，清空输入框内容
                Toast.makeText(this, "Register failed. Please try again!", Toast.LENGTH_SHORT).show();
                etPassword.setText(""); // 清空密码输入框
                etMail.setText(""); // 清空邮箱输入框
                etName.setText(""); // 清空用户名输入框
            }
        });
    }

    private void back() {
        // 设置返回按钮的点击事件监听器，点击后关闭当前活动
        imgBack.setOnClickListener(v -> finish());

        // 设置“已有账号？”文本的点击事件监听器，点击后关闭当前活动
        tvHavelogin.setOnClickListener(v -> finish());

        // 设置“登录”文本的点击事件监听器，点击后关闭当前活动
        tvLogin.setOnClickListener(v -> finish());
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        etMail = (EditText) findViewById(R.id.et_mail);
        etName = (EditText) findViewById(R.id.et_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        rb = (RadioButton) findViewById(R.id.rb);
        btnRegister = (Button) findViewById(R.id.btn_register);
        tvHavelogin = (TextView) findViewById(R.id.tv_havelogin);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        userHelper = new UserHelper(this);
    }
}