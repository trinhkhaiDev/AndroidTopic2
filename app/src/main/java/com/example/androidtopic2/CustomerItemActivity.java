package com.example.androidtopic2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class CustomerItemActivity extends AppCompatActivity {

    private TextView textViewCustomerName, textViewCustomerYYYYMM, textViewCustomerAddress, textViewCustomerUsedElectric;
    private Button buttonEditCustomer;
    private CustomerDAO customerDAO;
    private int customerId;  // ID của khách hàng được truyền từ Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_item);

        // Khởi tạo các thành phần trong layout
        textViewCustomerName = findViewById(R.id.textViewCustomerName);
        textViewCustomerYYYYMM = findViewById(R.id.textViewCustomerYYYYMM);
        textViewCustomerAddress = findViewById(R.id.textViewCustomerAddress);
        textViewCustomerUsedElectric = findViewById(R.id.textViewCustomerUsedElectric);
        buttonEditCustomer = findViewById(R.id.buttonEditCustomer);

        // Khởi tạo DAO để làm việc với cơ sở dữ liệu
        customerDAO = new CustomerDAO(this);

        // Lấy ID của khách hàng từ Intent
        customerId = getIntent().getIntExtra("CUSTOMER_ID", -1);
        if (customerId != -1) {
            loadCustomerData(customerId);  // Tải thông tin khách hàng lên giao diện
        }

        // Chuyển sang màn hình chỉnh sửa thông tin khi người dùng nhấn nút "Chỉnh sửa"
        buttonEditCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerItemActivity.this, UpdateCustomerActivity.class);
                intent.putExtra("CUSTOMER_ID", customerId);  // Truyền ID của khách hàng để chỉnh sửa
                startActivity(intent);
            }
        });
    }

    // Hàm tải dữ liệu khách hàng và hiển thị lên giao diện
    private void loadCustomerData(int customerId) {
        Customer customer = customerDAO.getCustomerById(customerId);
        if (customer != null) {
            // Kiểm tra null cho từng trường dữ liệu
            String name = customer.getName() != null ? customer.getName() : "Không có tên";
            String yyyymm = customer.getYyyymm() != null ? customer.getYyyymm() : "Không có dữ liệu tháng";
            String address = customer.getAddress() != null ? customer.getAddress() : "Không có địa chỉ";

            // Xử lý và định dạng giá trị usedElectric
            double usedElectric = customer.getUsedElectric();
            String usedElectricStr = String.format("%.2f", usedElectric);  // Định dạng với 2 chữ số thập phân

            // Hiển thị dữ liệu lên giao diện
            textViewCustomerName.setText("Tên khách hàng: " + name);
            textViewCustomerYYYYMM.setText("Tháng sử dụng: " + yyyymm);
            textViewCustomerAddress.setText("Địa chỉ: " + address);
            textViewCustomerUsedElectric.setText("Số điện đã sử dụng: " + usedElectricStr);
        }
    }

}
