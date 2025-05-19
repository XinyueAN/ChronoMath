package com.example.museum.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.museum.Bean.UserBean;

public class UserHelper extends SQLiteOpenHelper {

    // 数据库版本
    private static final int DATABASE_VERSION = 1;
    // 数据库名称
    private static final String DATABASE_NAME = "UserHelper.db";
    // 表名
    private static final String TABLE_USERS = "users";

    // 创建表的SQL语句
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "user_mail TEXT,"
            + "username TEXT,"
            + "userpassword TEXT" + ")";

    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 在这里可以处理数据库的升级操作
    }

    // 添加用户的方法
    public boolean addUser(String userMail, String username, String userpassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_mail", userMail);
        values.put("username", username);
        values.put("userpassword", userpassword);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        // 如果插入的行ID大于0，则表示添加成功
        return result > 0;
    }

    //判断用户是否存在
        public boolean validateUser(String userMail, String userpassword) {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_USERS + " WHERE user_mail = ? AND userpassword = ?";
            Cursor cursor = db.rawQuery(query, new String[]{userMail, userpassword});

            boolean isValid = cursor.getCount() > 0;
            cursor.close();
            db.close();

            return isValid;
    }

    //根据mail查询所有信息
    public UserBean getUserInfoByEmail(String userMail) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE user_mail = ?";
        Cursor cursor = db.rawQuery(query, new String[]{userMail});

        UserBean user = null;
        if (cursor != null && cursor.moveToFirst()) {
            // 获取各列数据
            String email = cursor.getString(cursor.getColumnIndex("user_mail"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("userpassword"));

            // 创建 User 对象
            user = new UserBean(email, username, password);
        }

        // 关闭 Cursor 和数据库
        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return user;
    }


    //查询邮箱和密码是否正确
    public boolean isUserValid(String userMail, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE user_mail = ? AND userpassword = ?";
        Cursor cursor = db.rawQuery(query, new String[]{userMail, password});

        // 判断是否有匹配的记录
        boolean isValid = cursor != null && cursor.getCount() > 0;

        // 关闭 Cursor 和数据库
        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return isValid; // 如果找到匹配的记录，返回 true；否则返回 false
    }

    //根据邮箱修改密码
    public boolean updateUserPasswordByEmail(String userMail, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_USERS + " SET userpassword = ? WHERE user_mail = ?";
        ContentValues values = new ContentValues();
        values.put("userpassword", newPassword); // 注意：这里应该使用安全的密码存储方法，比如哈希加盐

        // 执行更新操作
        int affectedRows = db.update(TABLE_USERS, values, "user_mail = ?", new String[]{userMail});

        // 判断是否有记录被更新
        boolean isUpdated = affectedRows > 0;

        // 关闭数据库
        db.close();

        return isUpdated; // 如果更新成功，返回 true；否则返回 false
    }


    //查询邮箱和用户名是否正确
    public boolean isUserValidByEmailAndUsername(String userMail, String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE user_mail = ? AND username = ?";
        Cursor cursor = db.rawQuery(query, new String[]{userMail, username});

        // 判断是否有匹配的记录
        boolean isValid = cursor != null && cursor.getCount() > 0;

        // 关闭 Cursor 和数据库
        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return isValid; // 如果找到匹配的记录，返回 true；否则返回 false
    }

    //根据邮箱修改用户名
    public boolean updateUsernameByEmail(String userMail, String newUsername) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_USERS + " SET username = ? WHERE user_mail = ?";
        ContentValues values = new ContentValues();
        values.put("username", newUsername);

        // 执行更新操作
        int affectedRows = db.update(TABLE_USERS, values, "user_mail = ?", new String[]{userMail});

        // 判断是否有记录被更新
        boolean isUpdated = affectedRows > 0;

        // 关闭数据库
        db.close();

        return isUpdated; // 如果更新成功，返回 true；否则返回 false
    }


    // 判断用户邮箱是否存在
    public boolean isEmailExists(String userMail) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE user_mail = ?";
        Cursor cursor = db.rawQuery(query, new String[]{userMail});

        boolean emailExists = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return emailExists;
    }
}