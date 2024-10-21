package com.example.androidtopic2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CustomerDAO {

    private SQLiteDatabase db;

    public CustomerDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Insert new customer
    public long addCustomer(String name, String yyyymm, String address, double usedNumElectric, int elecUserTypeId) {
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("YYYYMM", yyyymm);
        values.put("ADDRESS", address);
        values.put("USED_NUM_ELECTRIC", usedNumElectric);
        values.put("ELEC_USER_TYPE_ID", elecUserTypeId);

        return db.insert("customer", null, values);
    }

    // Get customer by ID
    public Customer getCustomerById(int id) {
        Cursor cursor = db.query("customer", null, "ID=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Kiểm tra chỉ số cột "ID"
            int customerIdIndex = cursor.getColumnIndex("ID");
            int customerId = (customerIdIndex != -1) ? cursor.getInt(customerIdIndex) : -1; // Giá trị mặc định nếu không tìm thấy

            // Kiểm tra chỉ số cột "NAME"
            int nameIndex = cursor.getColumnIndex("NAME");
            String name = (nameIndex != -1) ? cursor.getString(nameIndex) : "Không có tên";

            // Kiểm tra chỉ số cột "YYYYMM"
            int yyyymmIndex = cursor.getColumnIndex("YYYYMM");
            String yyyymm = (yyyymmIndex != -1) ? cursor.getString(yyyymmIndex) : "Không có dữ liệu tháng";

            // Kiểm tra chỉ số cột "ADDRESS"
            int addressIndex = cursor.getColumnIndex("ADDRESS");
            String address = (addressIndex != -1) ? cursor.getString(addressIndex) : "Không có địa chỉ";

            // Kiểm tra chỉ số cột "USED_NUM_ELECTRIC"
            int usedNumElectricIndex = cursor.getColumnIndex("USED_NUM_ELECTRIC");
            double usedNumElectric = (usedNumElectricIndex != -1) ? cursor.getDouble(usedNumElectricIndex) : 0.0;

            cursor.close(); // Đóng cursor
            return new Customer(customerId, name, yyyymm, address, usedNumElectric);
        }

        if (cursor != null) {
            cursor.close(); // Đóng cursor nếu nó không null
        }

        return null; // Trả về null nếu không tìm thấy khách hàng
    }
    // Phương thức cập nhật thông tin khách hàng
    public boolean updateCustomer(Customer customer) {
        ContentValues values = new ContentValues();
        values.put("NAME", customer.getName());
        values.put("YYYYMM", customer.getYyyymm());
        values.put("ADDRESS", customer.getAddress());
        values.put("USED_NUM_ELECTRIC", customer.getUsedElectric());

        // Thực hiện cập nhật trong cơ sở dữ liệu
        int rowsAffected = db.update("customer", values, "ID=?", new String[]{String.valueOf(customer.getId())});
        return rowsAffected > 0; // Trả về true nếu có ít nhất 1 hàng bị ảnh hưởng
    }
    // Lấy tất cả khách hàng
// Sửa đổi phương thức getAllCustomers
    public Cursor getAllCustomers() {
        String query = "SELECT ID AS _id, NAME, ADDRESS, USED_NUM_ELECTRIC FROM customer";
        return db.rawQuery(query, null); // Sử dụng biến db đã được khởi tạo
    }






    // Delete customer data
    public int deleteCustomer(int id) {
        return db.delete("customer", "ID=?", new String[]{String.valueOf(id)});
    }
    // Đóng cơ sở dữ liệu khi hoàn tất
    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
