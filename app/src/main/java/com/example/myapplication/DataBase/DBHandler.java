package com.example.myapplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.Model.PostModel;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CafeFinderDB";
    private static final int DATABASE_VERSION = 1;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBHandler", "Creating database...");
        // Table user
        String createUserTable = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT, img TEXT);";

        // Table cafes
        String createCafesTable = "CREATE TABLE cafes (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, lat REAL, lon REAL, address TEXT, wifi_available INTEGER, work_space INTEGER, open_hours TEXT, close_hours TEXT, phone TEXT, min_price REAL, img TEXT, description TEXT, rating INTEGER, total_rating INTEGER);";

        // Table favorite_cafe
        String createFavoriteCafeTable = "CREATE TABLE favorite_cafe (id INTEGER PRIMARY KEY AUTOINCREMENT, id_user INTEGER, id_cafe INTEGER);";

        // Table post
        String createPostTable = "CREATE TABLE post (id INTEGER PRIMARY KEY AUTOINCREMENT, id_user INTEGER, content TEXT);";

        // Table image post

        String createPostImageTable = "CREATE TABLE post_image (id INTEGER PRIMARY KEY AUTOINCREMENT, id_post INTEGER, img TEXT);";

        // Table interact
        String createInteractTable = "CREATE TABLE interact (id INTEGER PRIMARY KEY AUTOINCREMENT, id_post INTEGER, id_user INTEGER, likes INTEGER, comment TEXT);";

        // Table rate
        String createRateTable = "CREATE TABLE rate (id INTEGER PRIMARY KEY AUTOINCREMENT, id_user INTEGER, img TEXT, comment TEXT, star INTEGER, id_cafe INTEGER);";

        // Table search history

        String createSearchHistoryTable = "CREATE TABLE search_history (id INTEGER PRIMARY KEY AUTOINCREMENT, id_user INTEGER, search_query TEXT);";

        db.execSQL(createUserTable);
        db.execSQL(createCafesTable);
        db.execSQL(createFavoriteCafeTable);
        db.execSQL(createPostTable);
        db.execSQL(createPostImageTable);
        db.execSQL(createInteractTable);
        db.execSQL(createRateTable);
        db.execSQL(createSearchHistoryTable);


        insertSampleData(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        // Insert sample users
        db.execSQL("INSERT INTO users (name, email, password, img) VALUES ('chinh', 'chinh31503@gmail.com', 'chinh31503', 'avatar.png');");
        db.execSQL("INSERT INTO users (name, email, password, img) VALUES ('thai', 'hvt@gmail.com', 'hvt', 'avatar.png');");

        // Insert sample cafes
        db.execSQL("INSERT INTO cafes (name, lat, lon, address, wifi_available, work_space, open_hours, close_hours, phone, min_price, img, description, rating, total_rating) VALUES ('Cafe A', 10.123, 106.456, '123 Street', 1, 1, '08:00', '22:00', '0123456789', 2.5, 'cafea.png', 'Nice place', 4, 100);");
        db.execSQL("INSERT INTO cafes (name, lat, lon, address, wifi_available, work_space, open_hours, close_hours, phone, min_price, img, description, rating, total_rating) VALUES ('Cafe B', 10.789, 106.789, '456 Street', 1, 0, '09:00', '23:00', '0987654321', 3.0, 'cafeb.png', 'Cozy atmosphere', 5, 200);");

        // Insert sample posts
        db.execSQL("INSERT INTO post (id_user, content) VALUES (1, 'Xin review các quán cafe gần trường PTIT. Quán nào có không gian để học, giá học sinh sinh viên thì càng tốt');");
        db.execSQL("INSERT INTO post (id_user, content) VALUES (2, 'Recommend cho mọi người quán cafe ABC giá học sinh sinh viên. Địa chỉ quán tại: ...');");
        db.execSQL("INSERT INTO post (id_user, content) VALUES (3, 'Nice ambiance!');");

        // Insert sample interactions
        db.execSQL("INSERT INTO interact (id_post, id_user, likes, comment) VALUES (1, 2, 1, 'I love this!');");
        db.execSQL("INSERT INTO interact (id_post, id_user, likes, comment) VALUES (2, 1, 1, 'Amazing place!');");

        // Insert sample ratings
        db.execSQL("INSERT INTO rate (id_user, img, comment, star, id_cafe) VALUES (1, 'rate1.png', 'Excellent coffee!', 5, 1);");
        db.execSQL("INSERT INTO rate (id_user, img, comment, star, id_cafe) VALUES (2, 'rate2.png', 'Great service!', 4, 2);");

        // Insert sample favorites
        db.execSQL("INSERT INTO favorite_cafe (id_user, id_cafe) VALUES (1, 1);");
        db.execSQL("INSERT INTO favorite_cafe (id_user, id_cafe) VALUES (2, 2);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS cafes");
        db.execSQL("DROP TABLE IF EXISTS favorite_cafe");
        db.execSQL("DROP TABLE IF EXISTS post");
        db.execSQL("DROP TABLE IF EXISTS post_image");
        db.execSQL("DROP TABLE IF EXISTS interact");
        db.execSQL("DROP TABLE IF EXISTS rate");
        db.execSQL("DROP TABLE IF EXISTS search_history");
        onCreate(db);
    }


}
