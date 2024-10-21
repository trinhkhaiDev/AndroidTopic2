package com.example.androidtopic2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PriceDAO {

    private SQLiteDatabase db;

    public PriceDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Cập nhật giá điện
    public int updateElectricityPrice(double increaseAmount) {
        // Tăng giá điện cho tất cả các loại người dùng điện
        ContentValues values = new ContentValues();
        values.put("UNIT_PRICE", "UNIT_PRICE + " + increaseAmount);

        // Cập nhật giá điện cho tất cả các loại người dùng
        return db.update("electric_user_type", values, null, null);
    }
}
