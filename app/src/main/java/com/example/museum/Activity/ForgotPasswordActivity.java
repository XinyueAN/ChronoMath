package com.example.museum.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.museum.Helper.UserHelper;
import com.example.museum.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ImageView imgBack;
    private EditText etMail;
    private EditText etPassword;
    private Button btnFinish;
    private TextView tvLogin;
    private UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initView();
        back();
        Finish();
    }

    // 定义一个私有方法Finish
    private void Finish() {
        // 为btnFinish按钮设置点击事件监听器
        btnFinish.setOnClickListener(v -> {
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
            // 检查密码长度是否在6到16个字符之间
            if (password.length() < 6 || password.length() > 16) {
                // 如果密码长度不在6到16个字符之间，则显示一个Toast提示信息“密码长度应为6到16个字符！”
                Toast.makeText(this, "The password length should be 6 to 16 characters!", Toast.LENGTH_SHORT).show();
                // 提前返回，不再执行后续代码
                return;
            }
            // 调用userHelper的isEmailExists方法检查邮箱是否存在，结果赋值给emailExists变量
            boolean emailExists = userHelper.isEmailExists(mail);
            // 判断邮箱是否存在
            if (emailExists) {
                // 如果邮箱存在，则调用userHelper的updateUserPasswordByEmail方法尝试修改密码，结果赋值给b变量
                boolean b = userHelper.updateUserPasswordByEmail(mail, password);
                // 判断密码修改是否成功
                if (b) {
                    // 如果成功，则显示一个Toast提示信息“修改成功！”
                    Toast.makeText(this, "Modify successfully!", Toast.LENGTH_SHORT).show();
                    // 结束当前Activity
                    finish();
                } else {
                    // 如果失败，则显示一个Toast提示信息“修改失败！”
                    Toast.makeText(this, "Modify failed!", Toast.LENGTH_SHORT).show();
                    // 清空etPassword和etMail的文本内容
                    etPassword.setText("");
                    etMail.setText("");
                }
            } else {
                // 如果邮箱不存在，则显示一个Toast提示信息“邮箱不存在，请重新输入！”
                Toast.makeText(this, "The email address does not exist. Please re-enter it!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void back() {
        imgBack.setOnClickListener(v -> {
            finish();
        });
        tvLogin.setOnClickListener(v -> {
            finish();
        });
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        etMail = (EditText) findViewById(R.id.et_mail);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnFinish = (Button) findViewById(R.id.btn_finish);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        userHelper = new UserHelper(this);
    }
}