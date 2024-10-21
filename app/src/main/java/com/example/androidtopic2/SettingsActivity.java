package com.example.androidtopic2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchHideShowAddress, switchHideShowUsedElectric, switchPlayMusic;
    private Button buttonSaveSettings;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "settings_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Khởi tạo các thành phần trong giao diện
        switchHideShowAddress = findViewById(R.id.switchHideShowAddress);
        switchHideShowUsedElectric = findViewById(R.id.switchHideShowUsedElectric);
        switchPlayMusic = findViewById(R.id.switchPlayMusic);
        buttonSaveSettings = findViewById(R.id.buttonSaveSettings);

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Đọc trạng thái từ SharedPreferences và đặt lại trạng thái của các Switch
        loadSettings();

        // Sự kiện click cho nút lưu cài đặt
        buttonSaveSettings.setOnClickListener(v -> {
            saveSettings();
            Toast.makeText(SettingsActivity.this, "Cài đặt đã được lưu!", Toast.LENGTH_SHORT).show();
        });
    }

    // Hàm lưu cài đặt vào SharedPreferences
    private void saveSettings() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("hideShowAddress", switchHideShowAddress.isChecked());
        editor.putBoolean("hideShowUsedElectric", switchHideShowUsedElectric.isChecked());
        editor.putBoolean("playMusic", switchPlayMusic.isChecked());
        editor.apply();
    }

    // Hàm tải cài đặt từ SharedPreferences
    private void loadSettings() {
        boolean hideShowAddress = sharedPreferences.getBoolean("hideShowAddress", false);
        boolean hideShowUsedElectric = sharedPreferences.getBoolean("hideShowUsedElectric", false);
        boolean playMusic = sharedPreferences.getBoolean("playMusic", false);

        switchHideShowAddress.setChecked(hideShowAddress);
        switchHideShowUsedElectric.setChecked(hideShowUsedElectric);
        switchPlayMusic.setChecked(playMusic);
    }
}
