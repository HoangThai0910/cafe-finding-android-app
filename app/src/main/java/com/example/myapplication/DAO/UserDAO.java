package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DataBase.DBHandler;

public class UserDAO {
    private DBHandler dbHandler;

    public UserDAO(Context context) {
        dbHandler = new DBHandler(context);
    }

    // Đăng ký người dùng mới
    public boolean registerUser(String name, String email, String password) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        try {
            // Kiểm tra xem email đã tồn tại chưa
            if (isEmailExists(db, email)) {
                return false; // Email đã tồn tại
            }

            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("email", email);
            values.put("password", password);

            long result = db.insert("users", null, values);
            return result != -1;
        } finally {
            db.close(); // Đảm bảo đóng DB khi hoàn thành
        }
    }

    // Kiểm tra email đã tồn tại chưa (truyền database để tránh mở lại)
    private boolean isEmailExists(SQLiteDatabase db, String email) {
        Cursor cursor = db.rawQuery("SELECT 1 FROM users WHERE email = ?", new String[]{email});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    // Kiểm tra đăng nhập
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT 1 FROM users WHERE email = ? AND password = ?", new String[]{email, password});
            boolean exists = cursor.moveToFirst();
            cursor.close();
            return exists;
        } finally {
            db.close();
        }
    }

    public int getUserIdByEmail(String email) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM users WHERE email = ?", new String[]{email});

        try {
            if (cursor.moveToFirst()) {
                return cursor.getInt(0);
            }
            return -1; // Không tìm thấy
        } finally {
            cursor.close();
            db.close();
        }
    }
}
