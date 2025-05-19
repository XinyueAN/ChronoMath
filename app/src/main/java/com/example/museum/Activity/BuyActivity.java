package com.example.museum.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.museum.Adapter.BuyAdapter;
import com.example.museum.Helper.BuyHelper;
import com.example.museum.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class BuyActivity extends AppCompatActivity {

    private ImageView imgBack;
    private TextView selectedDateText;
    private TextView tvTotalCount;
    private BuyAdapter adapter;
    private RadioGroup rb;
    private RadioButton rbForenoon;
    private RadioButton rbAfternoon;
    private ListView lv;
    private TextView tvNum;
    private Button btnReserve;
    private int totalCount;
    private BuyHelper buyHelper;
    private String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        initView();
        back();
        date();
        lv();
        reserve();
    }

    private void reserve() {
        btnReserve.setOnClickListener(v -> {
            String visitDate = selectedDateText.getText().toString();

            if (visitDate.equals("Click to select the visiting date")) {
                Toast.makeText(this, "Please select the visiting date!", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedId = rb.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedId);

            if (selectedRadioButton == null) {
                Toast.makeText(this, "Please select the visiting time!", Toast.LENGTH_SHORT).show();
                return;
            }

            String visitTime = selectedRadioButton.getText().toString();
            String orderId = "D" + new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()) {{
                setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            }}.format(new Date());

            String orderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) {{
                setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            }}.format(new Date());

            boolean hasTickets = false;
            StringBuilder ticketType1 = new StringBuilder();
            StringBuilder ticketCount1 = new StringBuilder();
            StringBuilder totalPrice1 = new StringBuilder();
            StringBuilder ticketType2 = new StringBuilder();
            StringBuilder ticketCount2 = new StringBuilder();
            StringBuilder totalPrice2 = new StringBuilder();
            StringBuilder ticketType3 = new StringBuilder();
            StringBuilder ticketCount3 = new StringBuilder();
            StringBuilder totalPrice3 = new StringBuilder();
            StringBuilder ticketType4 = new StringBuilder();
            StringBuilder ticketCount4 = new StringBuilder();
            StringBuilder totalPrice4 = new StringBuilder();

            int count = 0;

            for (Map<String, String> item : adapter.getData()) {
                String ticketCountStr = item.get("ticketCount");
                int ticketCount = Integer.parseInt(ticketCountStr);

                if (ticketCount > 0) {
                    hasTickets = true;
                    String ticketType = item.get("ticketLabel");
                    String totalPrice = calculateTotalPrice(ticketType, ticketCount);
                    count++;

                    if (count == 1) {
                        ticketType1.append(ticketType);
                        ticketCount1.append(ticketCount);
                        totalPrice1.append(totalPrice);
                    } else if (count == 2) {
                        ticketType2.append(ticketType);
                        ticketCount2.append(ticketCount);
                        totalPrice2.append(totalPrice);
                    } else if (count == 3) {
                        ticketType3.append(ticketType);
                        ticketCount3.append(ticketCount);
                        totalPrice3.append(totalPrice);
                    } else if (count == 4) {
                        ticketType4.append(ticketType);
                        ticketCount4.append(ticketCount);
                        totalPrice4.append(totalPrice);
                    }
                }
            }

            if (hasTickets) {
                buyHelper.insertOrderData(
                        mail,
                        orderId,
                        orderTime,
                        visitDate,
                        visitTime,
                        ticketType1.toString(),
                        ticketCount1.toString(),
                        totalPrice1.toString(),
                        ticketType2.toString(),
                        ticketCount2.toString(),
                        totalPrice2.toString(),
                        ticketType3.toString(),
                        ticketCount3.toString(),
                        totalPrice3.toString(),
                        ticketType4.toString(),
                        ticketCount4.toString(),
                        totalPrice4.toString(),
                        "To be paid"
                );

                Intent intent = new Intent(BuyActivity.this, OrderActivity.class);
                Toast.makeText(this, "The reservation was successful!", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            } else {
                Toast.makeText(this, "Please select the type of ticket you need!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String calculateTotalPrice(String ticketType, int count) {
        switch (ticketType) {
            case "Adult Ticket / $60":
                return String.valueOf(count * 60);
            case "Senior Ticket / $30":
                return String.valueOf(count * 30);
            case "Minor Ticket / $0":
                return "0";
            case "Student Ticket / $20":
                return String.valueOf(count * 20);
            default:
                return "0";
        }
    }

    private void lv() {
        List<Map<String, String>> data = new ArrayList<>();
        String[] labels = {"Adult Ticket / $60", "Senior Ticket / $30", "Minor Ticket / $0", "Student Ticket / $20"};

        for (String label : labels) {
            Map<String, String> map = new HashMap<>();
            map.put("ticketLabel", label);
            map.put("ticketCount", "0");
            data.add(map);
        }

        adapter = new BuyAdapter(this, data) {
            @Override
            public void notifyDataSetChanged() {
                super.notifyDataSetChanged();
                updateTotalCount();
            }
        };
        lv.setAdapter(adapter);
        updateTotalCount();
    }

    private void back() {
        imgBack.setOnClickListener(v -> finish());
    }

    private void date() {
        selectedDateText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone("Asia/Beijing"));
            int currentYear = calendar.get(Calendar.YEAR);
            int currentMonth = calendar.get(Calendar.MONTH);
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        // 创建Calendar实例并设置日期
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year, monthOfYear, dayOfMonth);

                        // 使用SimpleDateFormat格式化日期为英文格式
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
                        String formattedDate = dateFormat.format(selectedCalendar.getTime());

                        // 设置格式化后的日期到TextView
                        selectedDateText.setText(formattedDate);
                    },
                    currentYear, currentMonth, currentDay);

            datePickerDialog.show();
        });
    }

    private void initView() {
        imgBack = findViewById(R.id.img_back);
        selectedDateText = findViewById(R.id.selectedDateText);
        tvTotalCount = findViewById(R.id.tv_num);
        rb = (RadioGroup) findViewById(R.id.rb);
        rbForenoon = (RadioButton) findViewById(R.id.rb_forenoon);
        rbAfternoon = (RadioButton) findViewById(R.id.rb_afternoon);
        lv = (ListView) findViewById(R.id.lv);
        btnReserve = (Button) findViewById(R.id.btn_reserve);
        buyHelper = new BuyHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        mail = sharedPreferences.getString("mail", "");
    }

    private void updateTotalCount() {
        totalCount = adapter.getTotalCount();
        tvTotalCount.setText("" + totalCount);
    }
}