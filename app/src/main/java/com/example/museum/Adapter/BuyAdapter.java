package com.example.museum.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.museum.R;

import java.util.List;
import java.util.Map;

public class BuyAdapter extends BaseAdapter {
    private final Context context;
    private final List<Map<String, String>> data;

    public BuyAdapter(@NonNull Context context, @NonNull List<Map<String, String>> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_buy, parent, false);
        }

        TextView tvInformation = convertView.findViewById(R.id.tv_information);
        TextView tvNum = convertView.findViewById(R.id.tv_num);
        Button btnMinus = convertView.findViewById(R.id.btn_minus);
        Button btnAdd = convertView.findViewById(R.id.btn_add);

        Map<String, String> itemData = data.get(position);
        String label = itemData.get("ticketLabel");
        String count = itemData.get("ticketCount");

        tvInformation.setText(label);
        tvNum.setText(count);

        // 为减号按钮（btnMinus）设置点击事件监听器
        btnMinus.setOnClickListener(v -> {
            // 从文本视图（tvNum）中获取当前显示的文本，并将其转换为整数
            int currentCount = Integer.parseInt(tvNum.getText().toString());

            // 如果当前计数大于0
            if (currentCount > 0) {
                // 将文本视图（tvNum）中的文本更新为当前计数减1
                tvNum.setText(String.valueOf(currentCount - 1));
                // 更新 itemData 中的 "ticketCount" 为当前计数减1
                itemData.put("ticketCount", String.valueOf(currentCount - 1));
                // 通知数据集已发生变化，更新视图
                notifyDataSetChanged();
            }

            // 如果当前计数等于0（此条件在当前逻辑下其实是多余的，因为上面的条件已经处理了这部分情况）
            if (currentCount == 0) {
                // 显示一个 Toast 提示，告知用户已经达到最小值
                Toast.makeText(context, "It's already the lowest!", Toast.LENGTH_SHORT).show();
            }
        });

// 为加号按钮（btnAdd）设置点击事件监听器
        btnAdd.setOnClickListener(v -> {
            // 从文本视图（tvNum）中获取当前显示的文本，并将其转换为整数
            int currentCount = Integer.parseInt(tvNum.getText().toString());

            // 如果当前计数小于5
            if (currentCount < 5) {
                // 将文本视图（tvNum）中的文本更新为当前计数加1
                tvNum.setText(String.valueOf(currentCount + 1));
                // 更新 itemData 中的 "ticketCount" 为当前计数加1
                itemData.put("ticketCount", String.valueOf(currentCount + 1));
                // 通知数据集已发生变化，更新视图
                notifyDataSetChanged();
            }

            // 如果当前计数等于5（此条件在当前逻辑下也是多余的，因为上面的条件已经处理了这部分情况）
            if (currentCount == 5) {
                // 显示一个 Toast 提示，告知用户每人限购5张
                Toast.makeText(context, "Each person is limited to purchasing 5 tickets!", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }


    public int getTotalCount() {
        int total = 0;
        for (Map<String, String> item : data) {
            total += Integer.parseInt(item.get("ticketCount"));
        }
        return total;
    }

    public List<Map<String, String>> getData() {
        return data;
    }
}
