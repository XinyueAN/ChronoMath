package com.example.museum.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.museum.Bean.CollectBean;
import com.example.museum.Bean.FigureBean;

import java.util.ArrayList;
import java.util.List;

public class CollectHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Collect.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "myCollect";
    private static final String COLUMN_USER_MAIL = "userMail";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_COLLECT = "collect";
    private static final String COLUMN_IMG_PATH = "imgPath";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NUM = "num";
    private static final String COLUMN_ERA = "era";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_REGION = "region";
    private static final String COLUMN_DESCRIPTION = "description";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_USER_MAIL + " TEXT, " +
                    COLUMN_TYPE + " TEXT, " +
                    COLUMN_COLLECT + " TEXT, " +
                    COLUMN_IMG_PATH + " TEXT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_NUM + " TEXT, " +
                    COLUMN_ERA + " TEXT, " +
                    COLUMN_CATEGORY + " TEXT, " +
                    COLUMN_REGION + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT);";

    public CollectHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String userMail,String type, String collect, String imgPath, String name,String num,
                              String era, String category, String region, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_MAIL, userMail);
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_COLLECT, collect);
        values.put(COLUMN_IMG_PATH, imgPath);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_NUM, num);
        values.put(COLUMN_ERA, era);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_REGION, region);
        values.put(COLUMN_DESCRIPTION, description);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        // 返回插入结果：-1 表示插入失败，其他表示成功
        return result != -1;
    }

    public CollectBean getCollect(String userMail, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        CollectBean collectBean = null;

        // 定义查询条件
        String selection = COLUMN_USER_MAIL + " = ? AND " + COLUMN_NAME + " = ?";
        String[] selectionArgs = {userMail, name};

        // 执行查询
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // 从 Cursor 中读取数据
            String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
            String collect = cursor.getString(cursor.getColumnIndex(COLUMN_COLLECT));
            String imgPath = cursor.getString(cursor.getColumnIndex(COLUMN_IMG_PATH));
            String num = cursor.getString(cursor.getColumnIndex(COLUMN_NUM));
            String era = cursor.getString(cursor.getColumnIndex(COLUMN_ERA));
            String category = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY));
            String region = cursor.getString(cursor.getColumnIndex(COLUMN_REGION));
            String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));

            // 创建 CollectBean 实例
            collectBean = new CollectBean(userMail,type, collect, imgPath, name,num, era, category, region, description);

            cursor.close();
        }
        db.close();

        return collectBean;
    }

    public boolean deleteData(String userMail, String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        // 定义删除条件
        String whereClause = COLUMN_USER_MAIL + " = ? AND " + COLUMN_NAME + " = ?";
        String[] whereArgs = {userMail, name};

        // 执行删除操作
        int result = db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();

        // 返回删除结果：1 表示成功，0 表示未找到匹配的记录
        return result > 0;
    }


    public List<CollectBean> getAllCollectsByUserMail(String userMail) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<CollectBean> collectList = new ArrayList<>();

        // 定义查询条件
        String selection = COLUMN_USER_MAIL + " = ?";
        String[] selectionArgs = {userMail};

        // 执行查询
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // 从 Cursor 中读取数据
                String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
                String collect = cursor.getString(cursor.getColumnIndex(COLUMN_COLLECT));
                String imgPath = cursor.getString(cursor.getColumnIndex(COLUMN_IMG_PATH));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String num = cursor.getString(cursor.getColumnIndex(COLUMN_NUM));
                String era = cursor.getString(cursor.getColumnIndex(COLUMN_ERA));
                String category = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY));
                String region = cursor.getString(cursor.getColumnIndex(COLUMN_REGION));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));

                // 创建 CollectBean 实例
                CollectBean collectBean = new CollectBean(userMail,type, collect, imgPath, name, num,era, category, region, description);

                // 将 CollectBean 添加到列表中
                collectList.add(collectBean);
            }
            cursor.close();
        }
        db.close();

        return collectList;
    }


    public List<CollectBean> getJZ(String userMail, String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<CollectBean> collectList = new ArrayList<>();

        // 定义查询条件
        String selection = COLUMN_USER_MAIL + " = ? AND " + COLUMN_TYPE + " = ?";
        String[] selectionArgs = {userMail, type};

        // 执行查询
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // 从 Cursor 中读取数据
                String collect = cursor.getString(cursor.getColumnIndex(COLUMN_COLLECT));
                String imgPath = cursor.getString(cursor.getColumnIndex(COLUMN_IMG_PATH));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String num = cursor.getString(cursor.getColumnIndex(COLUMN_NUM));
                String era = cursor.getString(cursor.getColumnIndex(COLUMN_ERA));
                String category = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY));
                String region = cursor.getString(cursor.getColumnIndex(COLUMN_REGION));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));

                // 创建 CollectBean 实例
                CollectBean collectBean = new CollectBean(userMail, type, collect, imgPath, name, num, era, category, region, description);

                // 将 CollectBean 添加到列表中
                collectList.add(collectBean);
            }
            cursor.close();
        }
        db.close();

        return collectList;
    }


    public List<FigureBean> getWW(String userMail, String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<FigureBean> figureList = new ArrayList<>(); // 列表名改为 figureList 更清晰

        // 定义查询条件
        String selection = COLUMN_USER_MAIL + " = ? AND " + COLUMN_TYPE + " = ?";
        String[] selectionArgs = {userMail, type};

        // 执行查询
        // 注意：这里假设你只需要 FigureBean 中有的字段对应的数据，如果 CollectBean 字段更多，可能会丢失信息
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            // 获取列索引，提高效率并避免getColumnIndex在高版本API中的弃用警告
            int imgPathColumnIndex = cursor.getColumnIndex(COLUMN_IMG_PATH);
            int nameColumnIndex = cursor.getColumnIndex(COLUMN_NAME);
            // int numColumnIndex = cursor.getColumnIndex(COLUMN_NUM); // num 不是 FigureBean 构造函数的参数
            int eraColumnIndex = cursor.getColumnIndex(COLUMN_ERA);
            // int categoryColumnIndex = cursor.getColumnIndex(COLUMN_CATEGORY); // category 不是 FigureBean 构造函数的参数
            // int regionColumnIndex = cursor.getColumnIndex(COLUMN_REGION); // region 不是 FigureBean 构造函数的参数
            int descriptionColumnIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION); // 描述是 FigureBean 构造函数的参数


            while (cursor.moveToNext()) {
                // 从 Cursor 中读取数据，并赋值给对应变量
                // 这些变量需要在循环内部声明，以便每次迭代使用新的数据
                String mainImageResId; // 对应 Bean 中的 mainImageResId (String 类型)
                String name;       // 对应 Bean 中的 name
                String price;      // 对应 Bean 中的 price (数据库中没有，需给默认值)
                String author;     // 对应 Bean 中的 author (数据库中没有，需给默认值)
                String description; // 对应 Bean 中的 description
                String era;        // 对应 Bean 中的 era


                // 从 Cursor 读取数据
                // 检查列索引是否有效，防止列不存在导致异常
                mainImageResId = (imgPathColumnIndex != -1) ? cursor.getString(imgPathColumnIndex) : ""; // 使用 imgPath 作为 mainImageResId (String)
                name = (nameColumnIndex != -1) ? cursor.getString(nameColumnIndex) : "";
                era = (eraColumnIndex != -1) ? cursor.getString(eraColumnIndex) : "";
                description = (descriptionColumnIndex != -1) ? cursor.getString(descriptionColumnIndex) : ""; // **这里之前漏读了，现在加上**


                // 为 FigureBean 构造函数中数据库表里没有的字段提供默认值（空字符串）
                price = "";   // price 不在数据库表中
                author = "";  // author 不在数据库表中


                // 创建 FigureBean 实例
                // 确保参数顺序和类型与 FigureBean 构造函数完全匹配
                FigureBean figureBean = new FigureBean(
                        mainImageResId, // String (来自 imgPath)
                        name,           // String
                        price,          // String (默认值)
                        author,         // String (默认值)
                        description,    // String (来自 description 列)
                        era             // String (来自 era 列)
                );

                // 将 FigureBean 添加到列表中
                figureList.add(figureBean);
            }
            cursor.close();
        }
        db.close();

        return figureList; // 返回 FigureBean 列表
    }



}