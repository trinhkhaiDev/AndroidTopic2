package com.example.androidtopic2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextYYYYMM, editTextAddress, editTextUsedNumElectric;
    private Button buttonAddCustomer, buttonViewCustomers, buttonOpenSettings, buttonOpenUpdatePrice; // Khai báo buttonOpenUpdatePrice
    private CustomerDAO customerDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextYYYYMM = findViewById(R.id.editTextYYYYMM);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextUsedNumElectric = findViewById(R.id.editTextUsedNumElectric);
        buttonAddCustomer = findViewById(R.id.buttonAddCustomer);
        buttonViewCustomers = findViewById(R.id.buttonViewCustomers);
        buttonOpenSettings = findViewById(R.id.buttonOpenSettings);
        buttonOpenUpdatePrice = findViewById(R.id.buttonOpenUpdatePrice); // Khởi tạo buttonOpenUpdatePrice

        customerDAO = new CustomerDAO(this);

        buttonAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String yyyymm = editTextYYYYMM.getText().toString();
                String address = editTextAddress.getText().toString();

                String usedElectricStr = editTextUsedNumElectric.getText().toString();
                double usedNumElectric;
                try {
                    usedNumElectric = Double.parseDouble(usedElectricStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid number format for used electricity", Toast.LENGTH_SHORT).show();
                    return;
                }

                long id = customerDAO.addCustomer(name, yyyymm, address, usedNumElectric, 1); // Assume private user type
                if (id != -1) {
                    Toast.makeText(MainActivity.this, "Customer added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to add customer", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonViewCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewCustomersActivity.class);
                startActivity(intent);
            }
        });

        buttonOpenSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        // Sự kiện khi nhấn nút "Cập nhật giá điện"
        buttonOpenUpdatePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdatePriceActivity.class);
                startActivity(intent);
            }
        });
    }
}
