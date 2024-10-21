package com.example.androidtopic2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateCustomerActivity extends AppCompatActivity {

    private EditText editTextCustomerName, editTextCustomerYYYYMM, editTextCustomerAddress, editTextCustomerUsedElectric;
    private Button buttonSaveCustomer;
    private CustomerDAO customerDAO;
    private int customerId;  // ID của khách hàng được truyền từ Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);

        // Khởi tạo các thành phần trong layout
        editTextCustomerName = findViewById(R.id.editTextCustomerName);
        editTextCustomerYYYYMM = findViewById(R.id.editTextCustomerYYYYMM);
        editTextCustomerAddress = findViewById(R.id.editTextCustomerAddress);
        editTextCustomerUsedElectric = findViewById(R.id.editTextCustomerUsedElectric);
        buttonSaveCustomer = findViewById(R.id.buttonSaveCustomer);

        // Khởi tạo DAO để làm việc với cơ sở dữ liệu
        customerDAO = new CustomerDAO(this);

        // Lấy ID của khách hàng từ Intent
        customerId = getIntent().getIntExtra("CUSTOMER_ID", -1);
        if (customerId != -1) {
            loadCustomerData(customerId);  // Tải thông tin khách hàng lên giao diện
        }

        // Lưu thông tin khách hàng khi nhấn nút "Lưu"
        buttonSaveCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCustomer();
            }
        });
    }

    // Hàm tải dữ liệu khách hàng và hiển thị lên giao diện
    private void loadCustomerData(int customerId) {
        Customer customer = customerDAO.getCustomerById(customerId);
        if (customer != null) {
            editTextCustomerName.setText(customer.getName());
            editTextCustomerYYYYMM.setText(customer.getYyyymm());
            editTextCustomerAddress.setText(customer.getAddress());
            editTextCustomerUsedElectric.setText(String.valueOf(customer.getUsedElectric()));
        }
    }

    // Hàm cập nhật thông tin khách hàng
    private void updateCustomer() {
        String name = editTextCustomerName.getText().toString().trim();
        String yyyymm = editTextCustomerYYYYMM.getText().toString().trim();
        String address = editTextCustomerAddress.getText().toString().trim();
        int usedElectric;

        // Kiểm tra xem số điện đã sử dụng có hợp lệ không
        try {
            usedElectric = Integer.parseInt(editTextCustomerUsedElectric.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Số điện đã sử dụng không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cập nhật thông tin khách hàng vào cơ sở dữ liệu
        Customer updatedCustomer = new Customer(customerId, name, yyyymm, address, usedElectric);
        if (customerDAO.updateCustomer(updatedCustomer)) {
            Toast.makeText(this, "Cập nhật thông tin khách hàng thành công", Toast.LENGTH_SHORT).show();
            finish();  // Đóng activity
        } else {
            Toast.makeText(this, "Cập nhật thông tin khách hàng thất bại", Toast.LENGTH_SHORT).show();
        }

    }
}
