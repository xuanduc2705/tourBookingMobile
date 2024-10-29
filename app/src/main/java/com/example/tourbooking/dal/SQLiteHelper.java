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
    private static int DATABASE_VERSION = 3;
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
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        String insertUserData = "INSERT INTO User (status, name, address, phone, email, dob, image, isAdmin) VALUES " +
//                "(1, 'John Doe', '123 Main St', '0123456789', 'john.doe@example.com', '1990-01-01', 'john_image.jpg', 0), " +
//                "(1, 'Jane Smith', '456 Elm St', '0987654321', 'jane.smith@example.com', '1992-02-02', 'jane_image.jpg', 1), " +
//                "(0, 'Alice Brown', '789 Maple Ave', '0112233445', 'alice.brown@example.com', '1995-03-03', 'alice_image.jpg', 0)";
//        db.execSQL(insertUserData);
//
//        // Chèn dữ liệu mẫu vào bảng Tour
//        String insertTourData = "INSERT INTO Tour (category_id, tour_name, description, address, image, time_tour, start_date, end_date, create_at, update_at, delete_at, guide_name, guide_phone, guide_image, price) VALUES " +
//                "(1, 'City Tour', 'Explore the main attractions of the city.', 'City Center', 'city_tour.jpg', '2 hours', '2024-06-01', '2024-06-01', '2024-05-01', '2024-05-02', NULL, 'David Lee', '0123456788', 'david_image.jpg', 100.0), " +
//                "(2, 'Beach Adventure', 'A fun day at the beach with activities.', 'Sunny Beach', 'beach_adventure.jpg', '5 hours', '2024-07-01', '2024-07-01', '2024-06-01', '2024-06-02', NULL, 'Emma Wong', '0987654322', 'emma_image.jpg', 150.0), " +
//                "(3, 'Mountain Hike', 'A challenging hike up the mountains.', 'Mountain Base', 'mountain_hike.jpg', '6 hours', '2024-08-01', '2024-08-01', '2024-07-01', '2024-07-02', NULL, 'Mark Green', '0112233446', 'mark_image.jpg', 200.0)";
//        db.execSQL(insertTourData);
//
//        // Chèn dữ liệu mẫu vào bảng Booking
//        String insertBookingData = "INSERT INTO Booking (tour_id, user_id, numOfPeople, status, total, bookingDate) VALUES " +
//                "(1, 1, 2, 1, 200.0, '2024-05-20'), " +
//                "(2, 2, 4, 1, 600.0, '2024-06-15'), " +
//                "(3, 3, 1, 0, 200.0, '2024-07-10')";
//        db.execSQL(insertBookingData);
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
     //   String selectQuery1 = "SELECT * FROM Booking" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
   //     Cursor cursor1 = db.rawQuery(selectQuery1, null);

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
