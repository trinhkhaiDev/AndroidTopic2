package com.example.androidtopic2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddCustomerActivity extends AppCompatActivity {

    private EditText editTextCustomerName, editTextCustomerYYYYMM, editTextCustomerAddress, editTextCustomerUsedElectric;
    private Button buttonSaveCustomer;
        private CustomerDAO customerDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        // Khởi tạo các thành phần trong layout
        editTextCustomerName = findViewById(R.id.editTextCustomerName);
        editTextCustomerYYYYMM = findViewById(R.id.editTextCustomerYYYYMM);
        editTextCustomerAddress = findViewById(R.id.editTextCustomerAddress);
        editTextCustomerUsedElectric = findViewById(R.id.editTextCustomerUsedElectric);
        buttonSaveCustomer = findViewById(R.id.buttonSaveCustomer);

        // Khởi tạo DAO để làm việc với cơ sở dữ liệu
        customerDAO = new CustomerDAO(this);

        // Sự kiện click cho nút Lưu khách hàng
        buttonSaveCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi hàm thêm khách hàng mới
                addNewCustomer();
            }
        });
    }

    private void addNewCustomer() {
        // Lấy dữ liệu từ các trường EditText
        String name = editTextCustomerName.getText().toString();
        String yyyymm = editTextCustomerYYYYMM.getText().toString();
        String address = editTextCustomerAddress.getText().toString();
        String usedElectricStr = editTextCustomerUsedElectric.getText().toString();

        // Kiểm tra xem người dùng đã nhập đủ thông tin chưa

        try {
            double usedElectric = Double.parseDouble(usedElectricStr);
        } catch (NumberFormatException e) {
            Toast.makeText(AddCustomerActivity.this, "Invalid number format for used electricity", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(yyyymm) || TextUtils.isEmpty(address)) {

            Toast.makeText(AddCustomerActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Chuyển số điện đã sử dụng sang kiểu double
        double usedElectric = Double.parseDouble(usedElectricStr);

        // Thêm khách hàng vào cơ sở dữ liệu
        long result = customerDAO.addCustomer(name, yyyymm, address, usedElectric, 1); // Giả sử loại khách hàng là 'Private' (1)

        // Kiểm tra kết quả và hiển thị thông báo
        if (result != -1) {
            Toast.makeText(AddCustomerActivity.this, "Khách hàng đã được thêm thành công!", Toast.LENGTH_SHORT).show();
            // Sau khi thêm thành công, bạn có thể kết thúc Activity hoặc reset form nhập liệu
            finish();  // Kết thúc activity, quay lại màn hình trước
        } else {
            Toast.makeText(AddCustomerActivity.this, "Thêm khách hàng thất bại!", Toast.LENGTH_SHORT).show();
        }
    }
}
