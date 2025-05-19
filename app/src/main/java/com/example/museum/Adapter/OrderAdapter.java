package com.example.museum.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.museum.Bean.OrderBean;
import com.example.museum.Helper.BuyHelper;
import com.example.museum.R;

import java.util.List;

public class OrderAdapter extends BaseAdapter {

    private Context context;
    private List<OrderBean> orders;
    private LayoutInflater inflater;
    private BuyHelper buyHelper;

    public OrderAdapter(Context context, List<OrderBean> orders) {
        this.context = context;
        this.orders = orders;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_order, parent, false);
            holder = new ViewHolder();
            holder.visitTime = convertView.findViewById(R.id.tv_visit_time);
            holder.paymentStatus = convertView.findViewById(R.id.tv_paymentStatus);
            holder.totalPrice = convertView.findViewById(R.id.tv_totalPrice);
            holder.orderTime = convertView.findViewById(R.id.tv_order_time);
            holder.ll1 = convertView.findViewById(R.id.ll_1);
            holder.tvPrice1 = convertView.findViewById(R.id.tv_price_1);
            holder.ll2 = convertView.findViewById(R.id.ll_2);
            holder.tvPrice2 = convertView.findViewById(R.id.tv_price_2);
            holder.ll3 = convertView.findViewById(R.id.ll_3);
            holder.tvPrice3 = convertView.findViewById(R.id.tv_price_3);
            holder.ll4 = convertView.findViewById(R.id.ll_4);
            holder.tvPrice4 = convertView.findViewById(R.id.tv_price_4);
            holder.tvTicket1 = convertView.findViewById(R.id.tv_1);
            holder.tvTicket2 = convertView.findViewById(R.id.tv_2);
            holder.tvTicket3 = convertView.findViewById(R.id.tv_3);
            holder.tvTicket4 = convertView.findViewById(R.id.tv_4);
            holder.btnPay = convertView.findViewById(R.id.btn_pay);

            buyHelper = new BuyHelper(context);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        OrderBean order = orders.get(position);

        holder.visitTime.setText("Visiting Date: " + order.getVisitDate() + " " + order.getVisitTime());
        holder.paymentStatus.setText(order.getPaymentStatus());
        holder.orderTime.setText("Ordering Time: " + order.getOrderTime());

        // 初始化总金额
        double totalMoney = 0.0;


        // 设置票1
        String price1 = order.getTotalPrice1();
        holder.tvTicket1.setText(order.getTicketType1() + " x" + order.getTicketCount1());
        holder.tvPrice1.setText("$" + price1);
        totalMoney += parsePrice(price1); // 使用 parsePrice 转换并累加总金额

        // 设置票2
        if (order.getTicketType2() == null || order.getTicketType2().isEmpty()) {
            holder.ll2.setVisibility(View.GONE);
        } else {
            holder.ll2.setVisibility(View.VISIBLE);
            String price2 = order.getTotalPrice2();
            holder.tvTicket2.setText(order.getTicketType2() + " x" + order.getTicketCount2());
            holder.tvPrice2.setText("$" + price2);
            totalMoney += parsePrice(price2); // 使用 parsePrice 转换并累加总金额
        }

        // 设置票3
        if (order.getTicketType3() == null || order.getTicketType3().isEmpty()) {
            holder.ll3.setVisibility(View.GONE);
        } else {
            holder.ll3.setVisibility(View.VISIBLE);
            String price3 = order.getTotalPrice3();
            holder.tvTicket3.setText(order.getTicketType3() + " x" + order.getTicketCount3());
            holder.tvPrice3.setText("$" + price3);
            totalMoney += parsePrice(price3); // 使用 parsePrice 转换并累加总金额
        }

        // 设置票4
        if (order.getTicketType4() == null || order.getTicketType4().isEmpty()) {
            holder.ll4.setVisibility(View.GONE);
        } else {
            holder.ll4.setVisibility(View.VISIBLE);
            String price4 = order.getTotalPrice4();
            holder.tvTicket4.setText(order.getTicketType4() + " x" + order.getTicketCount4());
            holder.tvPrice4.setText("$" + price4);
            totalMoney += parsePrice(price4); // 使用 parsePrice 转换并累加总金额
        }

        // 将总金额显示到相应的视图中
        holder.totalPrice.setText("In total $" + totalMoney);

        if (order.getPaymentStatus().equals("To be paid")) {
            holder.btnPay.setVisibility(View.VISIBLE);
        } else {
            holder.btnPay.setVisibility(View.GONE);
        }

        holder.btnPay.setOnClickListener(v -> {
            // 获取自定义对话框的布局
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.dialog_pay, null);

            // 创建对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(dialogView);

            // 获取对话框中的视图组件
            TextView tvMoney = dialogView.findViewById(R.id.tv_money);
            Button btnPay = dialogView.findViewById(R.id.btn_pay);
            Button btnQuit = dialogView.findViewById(R.id.btn_quit);

            // 设置对话框的总计金额
            // 获取总金额的字符串
            String s = holder.totalPrice.getText().toString();
            // 去除“合计”两个字
            String amount = s.replace("In total", "");

            // 设置对话框中的金额
            tvMoney.setText(amount);

            // 创建对话框实例
            AlertDialog alertDialog = builder.create();

            // 显示对话框
            alertDialog.show();

            // 设置按钮的点击事件
            btnPay.setOnClickListener(v1 -> {
                buyHelper.updatePaymentStatus(order.getOrderId(), "Paid");
                // 关闭对话框
                alertDialog.dismiss();
                orders.remove(order);
                notifyDataSetChanged();
                Toast.makeText(context, "Payment completed. Please refresh the current page for inquiry!", Toast.LENGTH_SHORT).show();

            });
            btnQuit.setOnClickListener(v1 -> {
                // 关闭对话框
                alertDialog.dismiss();
            });
        });

        // 设置长按点击事件监听器
        convertView.setOnLongClickListener(v -> {
            // 创建一个 AlertDialog.Builder 对象
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Deletion Confirmation");
            builder.setMessage("Are you sure you want to delete this order?");

            // 设置确认按钮
            builder.setPositiveButton("Confirm", (dialog, which) -> {
                // 在这里处理用户点击确认后的操作
                buyHelper.deleteOrderData(order.getOrderId()); // 删除数据库中的订单
                orders.remove(order); // 从列表中移除订单
                notifyDataSetChanged(); // 更新适配器以刷新视图
                Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
            });

            // 设置取消按钮
            builder.setNegativeButton("Cancel", (dialog, which) -> {
                dialog.dismiss(); // 关闭对话框
            });

            // 创建并显示对话框
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            return true; // 返回 true 表示事件已处理，避免触发其他点击事件
        });


        return convertView;
    }

    // 辅助方法：将价格从 String 转换为 double
    private double parsePrice(String price) {
        try {
            // 移除人民币符号，并将价格转换为 double
            return Double.parseDouble(price.replace("$", ""));
        } catch (NumberFormatException e) {
            // 处理无效价格情况，默认为 0.0
            return 0.0;
        }
    }

    static class ViewHolder {
        TextView visitTime;
        TextView paymentStatus;
        TextView totalPrice;
        TextView orderTime;
        LinearLayout ll1;
        TextView tvPrice1;
        LinearLayout ll2;
        TextView tvPrice2;
        LinearLayout ll3;
        TextView tvPrice3;
        LinearLayout ll4;
        TextView tvPrice4;
        TextView tvTicket1;
        TextView tvTicket2;
        TextView tvTicket3;
        TextView tvTicket4;
        Button btnPay;
    }
}