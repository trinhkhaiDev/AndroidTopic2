package com.example.androidtopic2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidtopic2.PriceDAO;  // Import này là cần thiết

public class UpdatePriceActivity extends AppCompatActivity {

    private EditText editTextIncreaseAmount;
    private Button buttonUpdatePrice;
    private PriceDAO priceDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_price);

        // Khởi tạo các thành phần trong layout
        editTextIncreaseAmount = findViewById(R.id.editTextIncreaseAmount);
        buttonUpdatePrice = findViewById(R.id.buttonUpdatePrice);

        // Khởi tạo DAO để làm việc với cơ sở dữ liệu
        priceDAO = new PriceDAO(this);

        // Sự kiện click cho nút Cập nhật giá
        buttonUpdatePrice.setOnClickListener(v -> updatePrice());  // Sử dụng biểu thức lambda để đơn giản hóa
    }

    private void updatePrice() {
        // Lấy giá trị từ trường EditText
        String increaseAmountStr = editTextIncreaseAmount.getText().toString();

        // Kiểm tra xem người dùng đã nhập đủ thông tin chưa
        if (TextUtils.isEmpty(increaseAmountStr)) {
            Toast.makeText(UpdatePriceActivity.this, "Vui lòng nhập số tiền tăng", Toast.LENGTH_SHORT).show();
            return;
        }

        // Chuyển số tiền tăng giá sang kiểu double
        double increaseAmount = Double.parseDouble(increaseAmountStr);

        // Cập nhật giá điện trong cơ sở dữ liệu
        int result = priceDAO.updateElectricityPrice(increaseAmount);

        // Kiểm tra kết quả và hiển thị thông báo
        if (result > 0) {
            Toast.makeText(UpdatePriceActivity.this, "Đã tăng giá điện thành công!", Toast.LENGTH_SHORT).show();
            // Sau khi cập nhật thành công, bạn có thể kết thúc Activity hoặc quay lại màn hình trước
            finish();  // Kết thúc activity, quay lại màn hình trước
        } else {
            Toast.makeText(UpdatePriceActivity.this, "Cập nhật giá điện thất bại!", Toast.LENGTH_SHORT).show();
        }

    }

}