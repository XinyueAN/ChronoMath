package com.example.museum.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class LoginActivity extends AppCompatActivity {

    private ImageView imgBack;
    private EditText etMail;
    private EditText etPassword;
    private RadioButton rb;
    private Button btnLogin;
    private TextView tvRegister;
    private TextView tvForget;
    private UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        back();
        register();
        login();
        forgot();
    }


    // 定义一个私有方法login，用于处理用户登录逻辑
    private void login() {
        // 为btnLogin按钮设置点击事件监听器
        btnLogin.setOnClickListener(v -> {
            // 从etMail（可能是一个EditText控件）中获取文本内容，并转换为字符串，赋值给mail变量
            String mail = etMail.getText().toString();
            // 从etPassword（可能是一个EditText控件）中获取文本内容，并转换为字符串，赋值给password变量
            String password = etPassword.getText().toString();
            // 判断mail或password是否为空
            if (mail.isEmpty() || password.isEmpty()) {
                // 如果其中之一为空，则显示一个Toast提示信息“请确保所有内容不为空！”
                Toast.makeText(this, "Please make sure that all the content is not empty!", Toast.LENGTH_SHORT).show();
                // 提前返回，不再执行后续代码
                return;
            }
            // 检查RadioButton（rb）是否被选中
            boolean isRbChecked = rb.isChecked();

            // 判断RadioButton是否未被选中
            if (!isRbChecked) {
                // 如果未被选中，则显示一个Toast提示信息“请勾选同意注册协议！”
                Toast.makeText(this, "Please check the box to agree to the registration agreement!", Toast.LENGTH_SHORT).show();
                // 提前返回，不再执行后续代码
                return;
            }
            // 调用userHelper的validateUser方法验证用户的邮箱和密码是否正确，结果赋值给b变量
            boolean b = userHelper.validateUser(mail, password);
            // 判断用户验证是否成功
            if (b) {
                // 如果验证成功，则显示一个Toast提示信息“登录成功！”
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                // 获取SharedPreferences对象，名称为"User"，模式为私有模式
                SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                // 获取SharedPreferences.Editor对象，用于编辑SharedPreferences数据
                SharedPreferences.Editor editor = sharedPreferences.edit();
                // 将邮箱和密码存储到SharedPreferences中
                editor.putString("mail", mail);
                editor.putString("password", password);
                // 提交修改
                editor.apply();
                // 结束当前Activity
                finish();
            } else {
                // 如果验证失败，则显示一个Toast提示信息“登录失败，请重试！”
                Toast.makeText(this, "Login failed. Please try again!", Toast.LENGTH_SHORT).show();
                // 清空etPassword和etMail的文本内容
                etPassword.setText("");
                etMail.setText("");
            }
        });
    }

    // 定义一个私有方法register，用于处理注册页面的跳转
    private void register() {
        // 为tvRegister（可能是一个TextView控件）设置点击事件监听器
        tvRegister.setOnClickListener(v -> {
            // 创建一个Intent，用于从当前Activity跳转到RegisterActivity
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            // 启动RegisterActivity
            startActivity(intent);
        });
    }

    // 定义一个私有方法back，用于处理返回操作
    private void back() {
        // 为imgBack（可能是一个ImageView控件）设置点击事件监听器
        imgBack.setOnClickListener(v -> {
            // 结束当前Activity
            finish();
        });
    }

    // 定义一个私有方法forgot，用于处理忘记密码页面的跳转
    private void forgot() {
        // 为tvForget（可能是一个TextView控件）设置点击事件监听器
        tvForget.setOnClickListener(v -> {
            // 创建一个Intent，用于从当前Activity跳转到ForgotPasswordActivity
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            // 启动ForgotPasswordActivity
            startActivity(intent);
        });
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        etMail = (EditText) findViewById(R.id.et_mail);
        etPassword = (EditText) findViewById(R.id.et_password);
        rb = (RadioButton) findViewById(R.id.rb);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvForget = (TextView) findViewById(R.id.tv_forget);
        userHelper = new UserHelper(this);
    }
}