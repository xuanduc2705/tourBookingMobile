package com.example.tourbooking.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.tourbooking.model.Category;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TOUR.db";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE Category (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "image TEXT NOT NULL, " +
                "name TEXT NOT NULL, " +
                "create_at TEXT NOT NULL, " +
                "update_at TEXT NOT NULL, " +
                "delete_at TEXT NOT NULL)";
        db.execSQL(createTable);

        String insertData = "INSERT INTO Category (image, name, create_at, update_at, delete_at) VALUES " +
                "('image1.png', 'Category 1', '2024-01-01', '2024-01-02', '2024-01-03')," +
                "('image2.png', 'Category 2', '2024-02-01', '2024-02-02', '2024-02-03')," +
                "('image3.png', 'Category 3', '2024-03-01', '2024-03-02', '2024-03-03');";
        db.execSQL(insertData);

        Log.d("SQLiteHelper", "Database created and sample data inserted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addCategories(List<Category> categories) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (Category category : categories) {
                ContentValues values = new ContentValues();
                values.put("image", category.getImage());
                values.put("name", category.getName());
                values.put("create_at", category.getCreate_at());
                values.put("update_at", category.getUpdate_at());
                values.put("delete_at", category.getDelete_at());
                db.insert("Category", null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    public void clearCategories() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Category");
    }
    public List<Category> getAllBookings() {
        List<Category> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM Category" +  " ORDER BY create_at DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(0));
                category.setImage(cursor.getString(1));
                category.setName(cursor.getString(2));
                category.setCreate_at(cursor.getString(3));
                category.setUpdate_at(cursor.getString(4));
                category.setDelete_at(cursor.getString(5));
                list.add(category);
            } while (cursor.moveToNext());
        } else {
            Log.d("SQLiteHelper", "No data found in Category table");
        }
        cursor.close();
        return list;
    }

}
