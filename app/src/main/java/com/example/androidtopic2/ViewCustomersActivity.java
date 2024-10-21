package com.example.androidtopic2;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class ViewCustomersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);

        // Khai báo ListView và CustomerDAO
        ListView listViewCustomers = findViewById(R.id.listViewCustomers);
        CustomerDAO customerDAO = new CustomerDAO(this);

        // Lấy dữ liệu khách hàng từ cơ sở dữ liệu
        Cursor cursor = customerDAO.getAllCustomers();

        // Thiết lập SimpleCursorAdapter với các cột tương ứng
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.customer_item,
                cursor,
                new String[]{"NAME", "ADDRESS", "USED_NUM_ELECTRIC"}, // Các cột từ CSDL
                new int[]{R.id.textViewName, R.id.textViewAddress, R.id.textViewUsedElectric}, // Các view tương ứng trong layout
                0
        );
        listViewCustomers.setAdapter(adapter);
    }
}
