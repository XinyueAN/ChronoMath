package com.example.museum.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.museum.Helper.UserHelper;
import com.example.museum.MainActivity;
import com.example.museum.R;

public class ModifyActivity extends AppCompatActivity {

    private ImageView imgBack;
    private EditText etMail;
    private TextView tvOld;
    private EditText etOld;
    private TextView tvNew;
    private EditText etNew;
    private Button btnModify;
    private String mail;
    private UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        initView();
        show();
        back();
    }

    private void back() {
        imgBack.setOnClickListener(v -> {
            finish();
        });
    }

    // 显示方法，根据不同的状态设置视图和按钮点击事件
    private void show() {
        // 从SharedPreferences中获取用户的邮件地址
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        mail = sharedPreferences.getString("mail", "");
        // 将获取到的邮件地址设置到etMail（一个EditText控件）中
        etMail.setText(mail);

        // 获取Intent传递过来的“state”参数
        String state = getIntent().getStringExtra("state");

        // 根据“state”的值设置不同的视图和点击事件
        if (state.equals("1")) {
            setupPasswordUpdate(); // 如果state为"1"，调用设置密码更新的函数
        } else if (state.equals("2")) {
            setupUsernameUpdate(); // 如果state为"2"，调用设置用户名更新的函数
        }
    }

    // 设置密码更新的相关视图和点击事件
    private void setupPasswordUpdate() {
        // 设置原密码和新密码的提示文本
        tvOld.setText("Original Password");
        etOld.setHint("Please enter the original password");
        tvNew.setText("New Password");
        etNew.setHint("Please enter a new password (6-16 characters)");

        // 设置修改按钮的点击事件
        btnModify.setOnClickListener(v -> {
            String oldPassword = etOld.getText().toString();
            String newPassword = etNew.getText().toString();

            // 检查新密码的长度是否在6到16个字符之间
            if (newPassword.length() < 6 || newPassword.length() > 16) {
                Toast.makeText(this, "The password length should be 6 to 16 characters！", Toast.LENGTH_SHORT).show();
                return;
            }

            // 验证原密码是否正确
            if (userHelper.isUserValid(mail, oldPassword)) {
                // 更新密码
                if (userHelper.updateUserPasswordByEmail(mail, newPassword)) {
                    showToast("Update successful");
                    navigateToMainActivity("3"); // 密码更新成功后，跳转到主界面
                } else {
                    showToast("Update failed");
                }
            } else {
                showToast("The original password is incorrect！");
            }
        });
    }

    // 设置用户名更新的相关视图和点击事件
    private void setupUsernameUpdate() {
        // 设置原用户名和新用户名的提示文本
        tvOld.setText("Original user name");
        etOld.setHint("Please enter the original username");
        tvNew.setText("New username");
        etNew.setHint("Please enter a new username");

        // 设置修改按钮的点击事件
        btnModify.setOnClickListener(v -> {
            String oldUsername = etOld.getText().toString();
            String newUsername = etNew.getText().toString();

            // 验证原用户名是否正确
            if (userHelper.isUserValidByEmailAndUsername(mail, oldUsername)) {
                // 更新用户名
                if (userHelper.updateUsernameByEmail(mail, newUsername)) {
                    showToast("Update successful");
                    etOld.setText(""); // 清空输入框
                    etNew.setText("");
                } else {
                    showToast("Update failed");
                }
            } else {
                showToast("The original username is incorrect！");
            }
        });
    }

    // 显示Toast消息的方法
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // 跳转到主界面的方法
    private void navigateToMainActivity(String state) {
        Intent intent = new Intent(ModifyActivity.this, MainActivity.class);
        intent.putExtra("state", state); // 传递状态参数
        startActivity(intent); // 启动主界面Activity
        finish(); // 结束当前Activity
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        etMail = (EditText) findViewById(R.id.et_mail);
        tvOld = (TextView) findViewById(R.id.tv_old);
        etOld = (EditText) findViewById(R.id.et_old);
        tvNew = (TextView) findViewById(R.id.tv_new);
        etNew = (EditText) findViewById(R.id.et_new);
        btnModify = (Button) findViewById(R.id.btn_modify);
        userHelper = new UserHelper(this);
    }
}