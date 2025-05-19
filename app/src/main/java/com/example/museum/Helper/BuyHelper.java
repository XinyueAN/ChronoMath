package com.example.museum.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.museum.Bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

public class BuyHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "order.db";
    private static final int DATABASE_VERSION = 1;

    public BuyHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE orders (" +
                "userMail TEXT, " +
                "orderId TEXT, " +
                "orderTime TEXT, " +
                "visitDate TEXT, " +
                "visitTime TEXT, " +
                "ticketType1 TEXT, " +
                "ticketCount1 TEXT, " +
                "totalPrice1 TEXT, " +
                "ticketType2 TEXT, " +
                "ticketCount2 TEXT, " +
                "totalPrice2 TEXT, " +
                "ticketType3 TEXT, " +
                "ticketCount3 TEXT, " +
                "totalPrice3 TEXT, " +
                "ticketType4 TEXT, " +
                "ticketCount4 TEXT, " +
                "totalPrice4 TEXT, " +
                "paymentStatus TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(db);
    }

    public void insertOrderData(String userMail, String orderId, String orderTime, String visitDate,
                                String visitTime, String ticketType1, String ticketCount1,
                                String totalPrice1, String ticketType2, String ticketCount2,
                                String totalPrice2, String ticketType3, String ticketCount3,
                                String totalPrice3,String ticketType4, String ticketCount4,
                                String totalPrice4, String paymentStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userMail", userMail);
        values.put("orderId", orderId);
        values.put("orderTime", orderTime);
        values.put("visitDate", visitDate);
        values.put("visitTime", visitTime);
        values.put("ticketType1", ticketType1);
        values.put("ticketCount1", ticketCount1);
        values.put("totalPrice1", totalPrice1);
        values.put("ticketType2", ticketType2);
        values.put("ticketCount2", ticketCount2);
        values.put("totalPrice2", totalPrice2);
        values.put("ticketType3", ticketType3);
        values.put("ticketCount3", ticketCount3);
        values.put("totalPrice3", totalPrice3);
        values.put("ticketType4", ticketType4);
        values.put("ticketCount4", ticketCount4);
        values.put("totalPrice4", totalPrice4);
        values.put("paymentStatus", paymentStatus);

        db.insert("orders", null, values);
        db.close();
    }

    public List<OrderBean> getOrdersByStatusAndMail(String paymentStatus, String userMail) {
        List<OrderBean> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("orders",
                null,  // Query all columns
                "paymentStatus = ? AND userMail = ?",
                new String[]{paymentStatus, userMail},
                null,
                null,
                null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String orderId = cursor.getString(cursor.getColumnIndex("orderId"));
                    String orderTime = cursor.getString(cursor.getColumnIndex("orderTime"));
                    String visitDate = cursor.getString(cursor.getColumnIndex("visitDate"));
                    String visitTime = cursor.getString(cursor.getColumnIndex("visitTime"));
                    String ticketType1 = cursor.getString(cursor.getColumnIndex("ticketType1"));
                    String ticketCount1 = cursor.getString(cursor.getColumnIndex("ticketCount1"));
                    String totalPrice1 = cursor.getString(cursor.getColumnIndex("totalPrice1"));
                    String ticketType2 = cursor.getString(cursor.getColumnIndex("ticketType2"));
                    String ticketCount2 = cursor.getString(cursor.getColumnIndex("ticketCount2"));
                    String totalPrice2 = cursor.getString(cursor.getColumnIndex("totalPrice2"));
                    String ticketType3 = cursor.getString(cursor.getColumnIndex("ticketType3"));
                    String ticketCount3 = cursor.getString(cursor.getColumnIndex("ticketCount3"));
                    String totalPrice3 = cursor.getString(cursor.getColumnIndex("totalPrice3"));
                    String ticketType4 = cursor.getString(cursor.getColumnIndex("ticketType4"));
                    String ticketCount4 = cursor.getString(cursor.getColumnIndex("ticketCount4"));
                    String totalPrice4 = cursor.getString(cursor.getColumnIndex("totalPrice4"));

                    OrderBean order = new OrderBean(userMail, orderId, orderTime, visitDate,
                            visitTime, ticketType1, ticketCount1, totalPrice1,
                            ticketType2, ticketCount2, totalPrice2,
                            ticketType3, ticketCount3, totalPrice3,
                            ticketType4, ticketCount4, totalPrice4, paymentStatus);
                    orderList.add(order);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();
        return orderList;
    }

    public List<OrderBean> getOrdersByMail(String userMail) {
        List<OrderBean> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("orders",
                null,  // Query all columns
                "userMail = ?",  // Selection criteria
                new String[]{userMail},
                null,
                null,
                null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String orderId = cursor.getString(cursor.getColumnIndex("orderId"));
                    String orderTime = cursor.getString(cursor.getColumnIndex("orderTime"));
                    String visitDate = cursor.getString(cursor.getColumnIndex("visitDate"));
                    String visitTime = cursor.getString(cursor.getColumnIndex("visitTime"));
                    String ticketType1 = cursor.getString(cursor.getColumnIndex("ticketType1"));
                    String ticketCount1 = cursor.getString(cursor.getColumnIndex("ticketCount1"));
                    String totalPrice1 = cursor.getString(cursor.getColumnIndex("totalPrice1"));
                    String ticketType2 = cursor.getString(cursor.getColumnIndex("ticketType2"));
                    String ticketCount2 = cursor.getString(cursor.getColumnIndex("ticketCount2"));
                    String totalPrice2 = cursor.getString(cursor.getColumnIndex("totalPrice2"));
                    String ticketType3 = cursor.getString(cursor.getColumnIndex("ticketType3"));
                    String ticketCount3 = cursor.getString(cursor.getColumnIndex("ticketCount3"));
                    String totalPrice3 = cursor.getString(cursor.getColumnIndex("totalPrice3"));
                    String ticketType4 = cursor.getString(cursor.getColumnIndex("ticketType4"));
                    String ticketCount4 = cursor.getString(cursor.getColumnIndex("ticketCount4"));
                    String totalPrice4 = cursor.getString(cursor.getColumnIndex("totalPrice4"));
                    String paymentStatus = cursor.getString(cursor.getColumnIndex("paymentStatus"));

                    OrderBean order = new OrderBean(userMail, orderId, orderTime, visitDate,
                            visitTime, ticketType1, ticketCount1, totalPrice1,
                            ticketType2, ticketCount2, totalPrice2,
                            ticketType3, ticketCount3, totalPrice3,
                            ticketType4, ticketCount4, totalPrice4, paymentStatus);
                    orderList.add(order);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();
        return orderList;
    }

    public boolean updatePaymentStatus(String orderId, String newStatus) {
        // 获取可写的数据库实例
        SQLiteDatabase db = this.getWritableDatabase();

        // 创建 ContentValues 对象，指定要更新的列及其新值
        ContentValues values = new ContentValues();
        values.put("paymentStatus", newStatus);

        // 执行更新操作
        int rowsAffected = db.update(
                "orders",         // 表名
                values,           // 要更新的列和新值
                "orderId = ?",    // 更新条件
                new String[]{orderId}  // 条件参数
        );

        // 关闭数据库
        db.close();

        // 返回更新是否成功
        return rowsAffected > 0;
    }


    public void deleteOrderData(String orderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        // 定义删除条件
        String whereClause = "orderId = ?";
        // 定义删除条件中的参数
        String[] whereArgs = new String[]{orderId};

        // 执行删除操作
        db.delete("orders", whereClause, whereArgs);
        db.close();
    }


}
