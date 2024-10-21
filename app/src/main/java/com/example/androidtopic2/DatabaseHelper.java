package com.example.androidtopic2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Tên cơ sở dữ liệu và phiên bản
    private static final String DATABASE_NAME = "ElectricBill.db"; // Tên của cơ sở dữ liệu
    private static final int DATABASE_VERSION = 1; // Phiên bản cơ sở dữ liệu

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Tạo bảng khi cơ sở dữ liệu được tạo lần đầu tiên
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng customer
        String CREATE_CUSTOMER_TABLE = "CREATE TABLE customer (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT NOT NULL," +
                "YYYYMM TEXT NOT NULL," +
                "ADDRESS TEXT," +
                "USED_NUM_ELECTRIC REAL," +
                "ELEC_USER_TYPE_ID INTEGER NOT NULL," +
                "FOREIGN KEY (ELEC_USER_TYPE_ID) REFERENCES electric_user_type(ID))";

        db.execSQL(CREATE_CUSTOMER_TABLE);

        // Tạo bảng electric_user_type
        String CREATE_ELEC_USER_TYPE_TABLE = "CREATE TABLE electric_user_type (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ELEC_USER_TYPE_NAME TEXT NOT NULL," +
                "UNIT_PRICE REAL NOT NULL)";
        db.execSQL(CREATE_ELEC_USER_TYPE_TABLE);

        // Chèn dữ liệu mặc định vào bảng electric_user_type
        db.execSQL("INSERT INTO electric_user_type (ELEC_USER_TYPE_NAME, UNIT_PRICE) VALUES ('Private', 1000)");
        db.execSQL("INSERT INTO electric_user_type (ELEC_USER_TYPE_NAME, UNIT_PRICE) VALUES ('Business', 2000)");
    }

    // Nâng cấp cơ sở dữ liệu (nếu có thay đổi cấu trúc bảng)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS customer");
        db.execSQL("DROP TABLE IF EXISTS electric_user_type");
        // Tạo lại bảng mới
        onCreate(db);
    }
}
