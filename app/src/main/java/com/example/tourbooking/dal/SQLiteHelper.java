package com.example.tourbooking.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

import com.example.tourbooking.model.Booking;
import com.example.tourbooking.model.Category;
import com.example.tourbooking.model.Tour;
import com.example.tourbooking.model.User;

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

        String insertQuery = "INSERT INTO Category (image, name, create_at, update_at, delete_at) VALUES " +
                "('eco', 'ECO', '2024-10-28', '2024-10-28', '')," +
                "('beach', 'Beach', '2024-10-29', '2024-10-29', '')," +
                "( 'mountain', 'Mount', '2024-10-30', '2024-10-30', '')," +
                "('resort', 'Resort', '2024-10-30', '2024-10-30', '')," +
                "( 'tradition', 'Tradition', '2024-10-30', '2024-10-30', '');";

        db.execSQL(insertQuery);
        String createTourTable = "CREATE TABLE Tour (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "category_id INTEGER, " +
                "tour_name TEXT NOT NULL, " +
                "description TEXT, " +
                "address TEXT, " +
                "image TEXT, " +
                "time_tour TEXT, " +
                "start_date TEXT, " +
                "end_date TEXT, " +
                "create_at TEXT, " +
                "update_at TEXT, " +
                "delete_at TEXT, " +
                "guide_name TEXT, " +
                "guide_phone TEXT, " +
                "guide_image TEXT, " +
                "location TEXT, " +
                "price REAL)";
        db.execSQL(createTourTable);
        String insertQuery2 = "INSERT INTO Tour (category_id, tour_name, description, address, image, time_tour, start_date, end_date, create_at, update_at, delete_at, guide_name, guide_phone, guide_image, location, price) VALUES " +
                "(2, 'Nha Trang Trip', 'A trip to the beautiful beaches of Nha Trang', 'Nha Trang City', 'guide_image1', '5 days', '2024-11-01', '2024-11-06', '2024-10-01', '2024-10-15', null, 'Nguyen Van A', '0123456789', 'tour_g', 'Nha Trang', 500)," +
                "(4, 'Đà Lạt Adventure', 'Explore the scenic mountains of Da Lat', 'Da Lat City', 'guide_image2', '3 days', '2024-11-10', '2024-11-13', '2024-10-05', '2024-10-15', null, 'Nguyen Van B', '0987654321', 'tour_g', 'Da Lat', 200)," +
                "(3, 'Sa Pa Eco', 'Experience the eco-friendly tours in Sa Pa', 'Sa Pa', 'guide_image3', '4 days', '2024-11-15', '2024-11-19', '2024-10-07', '2024-10-17', null, 'Nguyen Van C', '0123987654', 'tour_g', 'SaPa', 210)," +
                "(3, 'Hạ Long Bay', 'Cruise through the stunning waters of Ha Long Bay', 'Ha Long Bay', 'guide_image4', '2 days', '2024-11-20', '2024-11-22', '2024-10-09', '2024-10-19', null, 'Nguyen Van D', '0934567890', 'tour_g', 'Quang Ninh', 199)," +
                "(5, 'Hue Heritage', 'A tour through the historical sites of Hue', 'Hue City', 'guide_image5', '3 days', '2024-12-01', '2024-12-03', '2024-10-12', '2024-10-22', null, 'Nguyen Van E', '0123456700', 'tour_g', 'Hue', 350)," +
                "(1, 'Mekong Delta Journey', 'A boat journey along the Mekong Delta', 'Can Tho City', 'guide_image6', '2 days', '2024-12-05', '2024-12-07', '2024-10-14', '2024-10-24', null, 'Nguyen Van F', '0945678901', 'tour_g', 'Can Tho', 1000)," +
                "(3, 'Phong Nha Cave Adventure', 'Explore the famous Phong Nha Cave', 'Phong Nha', 'guide_image7', '1 day', '2024-12-10', '2024-12-10', '2024-10-16', '2024-10-26', null, 'Nguyen Van G', '0912345678', 'tour_g', 'Quang Binh', 120)," +
                "(2, 'Con Dao Island Getaway', 'Relax on the peaceful Con Dao Island', 'Con Dao', 'guide_image8', '3 days', '2024-12-15', '2024-12-17', '2024-10-18', '2024-10-28', null, 'Nguyen Van H', '0932109876', 'tour_g', 'Ba Ria-Vung Tau', 110)," +
                "(1, 'Saigon City Tour', 'Discover the vibrant life in Ho Chi Minh City', 'Ho Chi Minh City', 'guide_image9', '1 day', '2024-12-20', '2024-12-20', '2024-10-20', '2024-10-30', null, 'Nguyen Van I', '0965432109', 'tour_g', 'Ho Chi Minh', 310)," +
                "(4, 'Ba Na Hills Fantasy', 'Explore the fantasy world of Ba Na Hills', 'Ba Na Hills', 'guide_image10', '1 day', '2024-12-25', '2024-12-25', '2024-10-22', '2024-11-01', null, 'Nguyen Van J', '0976543210', 'tour_g', 'Da Nang', 2590)," +
                "(3, 'Cao Bang Waterfalls', 'Visit the beautiful Ban Gioc Waterfall', 'Cao Bang', 'guide_image11', '2 days', '2024-12-28', '2024-12-29', '2024-10-23', '2024-11-03', null, 'Nguyen Van K', '0987654322', 'tour_g', 'Cao Bang', 144)," +
                "(5, 'Hanoi Historical Sites', 'A guided tour around Hanoi historical sites', 'Hanoi', 'guide_image12', '1 day', '2025-01-03', '2025-01-03', '2024-10-25', '2024-11-05', null, 'Nguyen Van L', '0901234567', 'tour_g', 'Hanoi', 275)," +
                "(2, 'Phu Quoc Beach Retreat', 'Relax and unwind on Phu Quoc Island', 'Phu Quoc', 'guide_image13', '4 days', '2025-01-08', '2025-01-11', '2024-10-27', '2024-11-07', null, 'Nguyen Van M', '0934567891', 'tour_g', 'Phu Quoc', 1500)," +
                "(5, 'Mai Chau Village', 'Discover the traditional culture in Mai Chau', 'Mai Chau', 'guide_image14', '2 days', '2025-01-15', '2025-01-16', '2024-10-28', '2024-11-08', null, 'Nguyen Van N', '0912345671', 'tour_g', 'Hoa Binh', 100)," +
                "(3, 'Tam Coc River Cruise', 'Enjoy a boat trip along the Tam Coc river', 'Ninh Binh', 'guide_image15', '1 day', '2025-01-18', '2025-01-18', '2024-10-30', '2024-11-10', null, 'Nguyen Van O', '0923456789', 'tour_g', 'Ninh Binh', 300);";

        db.execSQL(insertQuery2); // Thực thi chèn dữ liệu
        String createUserTable = "CREATE TABLE User (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "status INTEGER NOT NULL, " +
                "name TEXT NOT NULL, " +
                "address TEXT, " +
                "phone TEXT, " +
                "email TEXT, " +
                "dob TEXT, " +
                "image TEXT, " +
                "password TEXT NOT NULL, " + // Thêm trường password
                "isAdmin INTEGER NOT NULL)";
        db.execSQL(createUserTable);
                String insertUserData = "INSERT INTO User (status, name, address, phone, email, dob, image, isAdmin) VALUES " +
                "(1, 'John Doe', '123 Main St', '0123456789', 'john.doe@example.com', '1990-01-01', 'john_image.jpg', 0), " +
                "(1, 'Jane Smith', '456 Elm St', '0987654321', 'jane.smith@example.com', '1992-02-02', 'jane_image.jpg', 1), " +
                "(0, 'Alice Brown', '789 Maple Ave', '0112233445', 'alice.brown@example.com', '1995-03-03', 'alice_image.jpg', 0)";
        db.execSQL(insertUserData);
        String createBookingTable = "CREATE TABLE Booking (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tour_id INTEGER NOT NULL, " +
                "user_id INTEGER NOT NULL, " +
                "numOfPeople INTEGER NOT NULL, " +
                "status INTEGER NOT NULL, " +
                "total REAL NOT NULL, " +
                "bookingDate TEXT NOT NULL, " +
                "FOREIGN KEY(tour_id) REFERENCES Tour(id), " +
                "FOREIGN KEY(user_id) REFERENCES User(id))";
        db.execSQL(createBookingTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXISTS Category");
//        db.execSQL("DROP TABLE IF EXISTS Tour");
//
//        onCreate(db);
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

    public void addSampleTours() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM Tour", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        Cursor cursor1 = db.rawQuery("SELECT COUNT(*) FROM Category", null);
        cursor1.moveToFirst();
        int count1 = cursor1.getInt(0);
        cursor1.close();

        if (count1 == 0) {
            String insertQuery = "INSERT INTO Category (image, name, create_at, update_at, delete_at) VALUES " +
                    "('eco', 'ECO', '2024-10-28', '2024-10-28', '')," +
                    "('beach', 'Beach', '2024-10-29', '2024-10-29', '')," +
                    "( 'mountain', 'Mount', '2024-10-30', '2024-10-30', '')," +
                    "('resort', 'Resort', '2024-10-30', '2024-10-30', '')," +
                    "( 'tradition', 'Tradition', '2024-10-30', '2024-10-30', '');";

            db.execSQL(insertQuery);
        }
        if (count == 0) {
            String insertQuery = "INSERT INTO Tour (category_id, tour_name, description, address, image, time_tour, start_date, end_date, create_at, update_at, delete_at, guide_name, guide_phone, guide_image, location, price) VALUES " +
                    "(2, 'Nha Trang Trip', 'A trip to the beautiful beaches of Nha Trang', 'Nha Trang City', 'guide_image1', '5 days', '2024-11-01', '2024-11-06', '2024-10-01', '2024-10-15', null, 'Nguyen Van A', '0123456789', 'tour_g', 'Nha Trang', 500)," +
                    "(4, 'Đà Lạt Adventure', 'Explore the scenic mountains of Da Lat', 'Da Lat City', 'guide_image2', '3 days', '2024-11-10', '2024-11-13', '2024-10-05', '2024-10-15', null, 'Nguyen Van B', '0987654321', 'tour_g', 'Da Lat', 200)," +
                    "(3, 'Sa Pa Eco', 'Experience the eco-friendly tours in Sa Pa', 'Sa Pa', 'guide_image3', '4 days', '2024-11-15', '2024-11-19', '2024-10-07', '2024-10-17', null, 'Nguyen Van C', '0123987654', 'tour_g', 'SaPa', 210)," +
                    "(3, 'Hạ Long Bay', 'Cruise through the stunning waters of Ha Long Bay', 'Ha Long Bay', 'guide_image4', '2 days', '2024-11-20', '2024-11-22', '2024-10-09', '2024-10-19', null, 'Nguyen Van D', '0934567890', 'tour_g', 'Quang Ninh', 199)," +
                    "(5, 'Hue Heritage', 'A tour through the historical sites of Hue', 'Hue City', 'guide_image5', '3 days', '2024-12-01', '2024-12-03', '2024-10-12', '2024-10-22', null, 'Nguyen Van E', '0123456700', 'tour_g', 'Hue', 350)," +
                    "(1, 'Mekong Delta Journey', 'A boat journey along the Mekong Delta', 'Can Tho City', 'guide_image6', '2 days', '2024-12-05', '2024-12-07', '2024-10-14', '2024-10-24', null, 'Nguyen Van F', '0945678901', 'tour_g', 'Can Tho', 1000)," +
                    "(3, 'Phong Nha Cave Adventure', 'Explore the famous Phong Nha Cave', 'Phong Nha', 'guide_image7', '1 day', '2024-12-10', '2024-12-10', '2024-10-16', '2024-10-26', null, 'Nguyen Van G', '0912345678', 'tour_g', 'Quang Binh', 120)," +
                    "(2, 'Con Dao Island Getaway', 'Relax on the peaceful Con Dao Island', 'Con Dao', 'guide_image8', '3 days', '2024-12-15', '2024-12-17', '2024-10-18', '2024-10-28', null, 'Nguyen Van H', '0932109876', 'tour_g', 'Ba Ria-Vung Tau', 110)," +
                    "(1, 'Saigon City Tour', 'Discover the vibrant life in Ho Chi Minh City', 'Ho Chi Minh City', 'guide_image9', '1 day', '2024-12-20', '2024-12-20', '2024-10-20', '2024-10-30', null, 'Nguyen Van I', '0965432109', 'tour_g', 'Ho Chi Minh', 310)," +
                    "(4, 'Ba Na Hills Fantasy', 'Explore the fantasy world of Ba Na Hills', 'Ba Na Hills', 'guide_image10', '1 day', '2024-12-25', '2024-12-25', '2024-10-22', '2024-11-01', null, 'Nguyen Van J', '0976543210', 'tour_g', 'Da Nang', 2590)," +
                    "(3, 'Cao Bang Waterfalls', 'Visit the beautiful Ban Gioc Waterfall', 'Cao Bang', 'guide_image11', '2 days', '2024-12-28', '2024-12-29', '2024-10-23', '2024-11-03', null, 'Nguyen Van K', '0987654322', 'tour_g', 'Cao Bang', 144)," +
                    "(5, 'Hanoi Historical Sites', 'A guided tour around Hanoi historical sites', 'Hanoi', 'guide_image12', '1 day', '2025-01-03', '2025-01-03', '2024-10-25', '2024-11-05', null, 'Nguyen Van L', '0901234567', 'tour_g', 'Hanoi', 275)," +
                    "(2, 'Phu Quoc Beach Retreat', 'Relax and unwind on Phu Quoc Island', 'Phu Quoc', 'guide_image13', '4 days', '2025-01-08', '2025-01-11', '2024-10-27', '2024-11-07', null, 'Nguyen Van M', '0934567891', 'tour_g', 'Phu Quoc', 1500)," +
                    "(5, 'Mai Chau Village', 'Discover the traditional culture in Mai Chau', 'Mai Chau', 'guide_image14', '2 days', '2025-01-15', '2025-01-16', '2024-10-28', '2024-11-08', null, 'Nguyen Van N', '0912345671', 'tour_g', 'Hoa Binh', 100)," +
                    "(3, 'Tam Coc River Cruise', 'Enjoy a boat trip along the Tam Coc river', 'Ninh Binh', 'guide_image15', '1 day', '2025-01-18', '2025-01-18', '2024-10-30', '2024-11-10', null, 'Nguyen Van O', '0923456789', 'tour_g', 'Ninh Binh', 300);";

            db.execSQL(insertQuery);
        }
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
    public List<Category> getAllCategories() {
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
    public List<Tour> getListTour() {
        List<Tour> tourList = new ArrayList<>();
        String selectQuery = "SELECT * FROM Tour ORDER BY create_at DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Tour tour = new Tour();
                tour.setId(cursor.getInt(0));
                tour.setCategory_id(cursor.getInt(1));
                tour.setTour_name(cursor.getString(2));
                tour.setDescription(cursor.getString(3));
                tour.setAddress(cursor.getString(4));
                tour.setImage(cursor.getString(5));
                tour.setTime_tour(cursor.getString(6));
                tour.setStart_date(cursor.getString(7));
                tour.setEnd_date(cursor.getString(8));
                tour.setCreate_at(cursor.getString(9));
                tour.setUpdate_at(cursor.getString(10));
                tour.setDelete_at(cursor.getString(11));
                tour.setGuide_name(cursor.getString(12));
                tour.setGuide_phone(cursor.getString(13));
                tour.setGuide_image(cursor.getString(14));
                tour.setLocation(cursor.getString(15));
                tour.setPrice(cursor.getFloat(16));
                tourList.add(tour);
            } while (cursor.moveToNext());
        } else {
            Log.d("SQLiteHelper", "No data found in Tour table");
        }

        cursor.close();
        return tourList;
    }
    public List<Tour> getListTourShow() {
        List<Tour> tourList = new ArrayList<>();
        String selectQuery = "SELECT * FROM Tour WHERE delete_at IS NULL ORDER BY create_at DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Tour tour = new Tour();
                tour.setId(cursor.getInt(0));
                tour.setCategory_id(cursor.getInt(1));
                tour.setTour_name(cursor.getString(2));
                tour.setDescription(cursor.getString(3));
                tour.setAddress(cursor.getString(4));
                tour.setImage(cursor.getString(5));
                tour.setTime_tour(cursor.getString(6));
                tour.setStart_date(cursor.getString(7));
                tour.setEnd_date(cursor.getString(8));
                tour.setCreate_at(cursor.getString(9));
                tour.setUpdate_at(cursor.getString(10));
                tour.setDelete_at(cursor.getString(11));
                tour.setGuide_name(cursor.getString(12));
                tour.setGuide_phone(cursor.getString(13));
                tour.setGuide_image(cursor.getString(14));
                tour.setLocation(cursor.getString(15));
                tour.setPrice(cursor.getFloat(16));
                tourList.add(tour);
            } while (cursor.moveToNext());
        } else {
            Log.d("SQLiteHelper", "No data found in Tour table");
        }

        cursor.close();
        return tourList;
    }
    public Tour getDetailTourById(int id) {
        Tour tour = null;
        String selectQuery = "SELECT * FROM Tour WHERE id = ?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            tour = new Tour();
            tour.setId(cursor.getInt(0));
            tour.setCategory_id(cursor.getInt(1));
            tour.setTour_name(cursor.getString(2));
            tour.setDescription(cursor.getString(3));
            tour.setAddress(cursor.getString(4));
            tour.setImage(cursor.getString(5));
            tour.setTime_tour(cursor.getString(6));
            tour.setStart_date(cursor.getString(7));
            tour.setEnd_date(cursor.getString(8));
            tour.setCreate_at(cursor.getString(9));
            tour.setUpdate_at(cursor.getString(10));
            tour.setDelete_at(cursor.getString(11));
            tour.setGuide_name(cursor.getString(12));
            tour.setGuide_phone(cursor.getString(13));
            tour.setGuide_image(cursor.getString(14));
            tour.setLocation(cursor.getString(15));
            tour.setPrice(cursor.getFloat(16));
        } else {
            Log.d("SQLiteHelper", "Tour with id " + id + " not found");
        }

        cursor.close();
        return tour;
    }
    public List<Tour> getFilteredTours(String tourName, int categoryId, double minPrice, double maxPrice) {
        List<Tour> tours = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Tour WHERE delete_at IS NULL");
        List<String> queryArgs = new ArrayList<>();

        if (!tourName.isEmpty()) {
            queryBuilder.append(" AND tour_name LIKE ?");
            queryArgs.add("%" + tourName + "%");
        }

        if (categoryId != -1) {
            queryBuilder.append(" AND category_id = ?");
            queryArgs.add(String.valueOf(categoryId));
        }

        if (minPrice > 0) {
            queryBuilder.append(" AND price >= ?");
            queryArgs.add(String.valueOf(minPrice));
        }

        if (maxPrice < Double.MAX_VALUE) {
            queryBuilder.append(" AND price <= ?");
            queryArgs.add(String.valueOf(maxPrice));
        }

        Cursor cursor = db.rawQuery(queryBuilder.toString(), queryArgs.toArray(new String[0]));

        if (cursor.moveToFirst()) {
            do {
                Tour tour = new Tour(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("category_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("tour_name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getString(cursor.getColumnIndexOrThrow("address")),
                        cursor.getString(cursor.getColumnIndexOrThrow("image")),
                        cursor.getString(cursor.getColumnIndexOrThrow("time_tour")),
                        cursor.getString(cursor.getColumnIndexOrThrow("start_date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("end_date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("create_at")),
                        cursor.getString(cursor.getColumnIndexOrThrow("update_at")),
                        cursor.getString(cursor.getColumnIndexOrThrow("delete_at")),
                        cursor.getString(cursor.getColumnIndexOrThrow("guide_name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("guide_phone")),
                        cursor.getString(cursor.getColumnIndexOrThrow("guide_image")),
                        cursor.getString(cursor.getColumnIndexOrThrow("location")),
                        cursor.getFloat(cursor.getColumnIndexOrThrow("price"))
                );
                tours.add(tour);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return tours;
    }


    public Tour getTourById(int id) {
        Tour tour = null;
        String selectQuery = "SELECT * FROM Tour WHERE id = ?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {

            tour = new Tour(
                    cursor.getString(cursor.getColumnIndexOrThrow("address")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("category_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("create_at")),
                    cursor.getString(cursor.getColumnIndexOrThrow("delete_at")),
                    cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    cursor.getString(cursor.getColumnIndexOrThrow("end_date")),
                    cursor.getString(cursor.getColumnIndexOrThrow("guide_image")),
                    cursor.getString(cursor.getColumnIndexOrThrow("guide_name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("guide_phone")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("image")),
                    cursor.getFloat(cursor.getColumnIndexOrThrow("price")),
                    cursor.getString(cursor.getColumnIndexOrThrow("start_date")),
                    cursor.getString(cursor.getColumnIndexOrThrow("time_tour")),
                    cursor.getString(cursor.getColumnIndexOrThrow("tour_name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("update_at"))
            );
        } else {
            Log.d("SQLiteHelper", "No tour found for id: " + id);
        }

        if (cursor != null) {
            cursor.close();
        }

        return tour;
    }
    public List<Booking> getAllBooking(int user_id) {
        List<Booking> bookings = new ArrayList<>();
        String selectQuery = "SELECT * FROM Booking WHERE user_id = ? ORDER BY id DESC";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(user_id)});


        if (cursor.moveToFirst()) {
            do {
                Booking booking = new Booking();
                booking.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                booking.setTour_id(cursor.getInt(cursor.getColumnIndexOrThrow("tour_id")));
                booking.setUser_id(cursor.getInt(cursor.getColumnIndexOrThrow("user_id")));
                booking.setNumOfPeople(cursor.getInt(cursor.getColumnIndexOrThrow("numOfPeople")));
                booking.setStatus(cursor.getInt(cursor.getColumnIndexOrThrow("status")));
                booking.setTotal(cursor.getFloat(cursor.getColumnIndexOrThrow("total")));
                booking.setBookingDate(cursor.getString(cursor.getColumnIndexOrThrow("bookingDate")));


                bookings.add(booking);
            } while (cursor.moveToNext());
        } else {
            Log.d("SQLiteHelper", "No bookings found for user_id: " + user_id);
        }


        cursor.close();
        return bookings;
    }


    public long addBooking(Booking i) {
        ContentValues values = new ContentValues();
        values.put("tour_id", i.getTour_id());
        values.put("user_id", 1);
        values.put("numOfPeople", i.getNumOfPeople());
        values.put("status", i.getStatus());
        values.put("total", i.getTotal());
        values.put("bookingDate", i.getBookingDate());


        SQLiteDatabase sqliteDatabase = getWritableDatabase();
        return sqliteDatabase.insert("Booking", null, values);
    }

    public long addUser(User u){
        ContentValues values = new ContentValues();
        values.put("name", u.getName()); // Thêm tên
        values.put("address", u.getAddress()); // Thêm địa chỉ
        values.put("phone", u.getPhone()); // Thêm số điện thoại
        values.put("email", u.getEmail()); // Thêm email
        values.put("dob", u.getDob()); // Thêm ngày sinh
        values.put("image", u.getImage()); // Thêm hình ảnh
        values.put("status", u.getStatus()); // Thêm trạng thái
        values.put("isAdmin", u.isAdmin() ? 1 : 0); // Thêm quyền admin, lưu dưới dạng số (1 hoặc 0)
        values.put("password", u.getPassword()); // Thêm mật khẩu




        SQLiteDatabase sqliteDatabase = getWritableDatabase();
        return sqliteDatabase.insert("User", null, values);
    }


    public boolean login(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM User WHERE email = ? AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});


        boolean isAuthenticated = false;


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                isAuthenticated = true; // Tìm thấy người dùng
            }
            cursor.close(); // Đóng con trỏ
        }


        return isAuthenticated;
    }


    public User getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;


        String query = "SELECT * FROM User WHERE email = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // Giả sử các trường trong bảng User theo thứ tự id, status, name, email, password, address, phone, dob, image, isAdmin
                int id = cursor.getInt(0);                 // Lấy ID
                int status = cursor.getInt(1);             // Lấy trạng thái
                String name = cursor.getString(2);         // Lấy tên
                String email1 = cursor.getString(3);        // Lấy email
                String password = cursor.getString(4);     // Lấy mật khẩu
                String address = cursor.getString(5);      // Lấy địa chỉ
                String phone = cursor.getString(6);        // Lấy số điện thoại
                String dob = cursor.getString(7);          // Lấy ngày sinh
                String image = cursor.getString(8);        // Lấy hình ảnh
                boolean isAdmin = cursor.getInt(9) > 0;    // Lấy trạng thái quản trị viên


                // Tạo đối tượng User từ dữ liệu lấy được
                user = new User(address,dob,email,id,image,isAdmin,name,phone,password,status);
            }
            cursor.close(); // Đóng con trỏ
        }


        return user; // Trả về đối tượng User hoặc null nếu không tìm thấy
    }


    public User getUserById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;


        String query = "SELECT * FROM User WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // Giả sử các trường trong bảng User theo thứ tự id, status, name, email, password, address, phone, dob, image, isAdmin
                int id1 = cursor.getInt(0);                 // Lấy ID
                int status = cursor.getInt(1);             // Lấy trạng thái
                String name = cursor.getString(2);         // Lấy tên
                String email = cursor.getString(3);        // Lấy email
                String password = cursor.getString(4);     // Lấy mật khẩu
                String address = cursor.getString(5);      // Lấy địa chỉ
                String phone = cursor.getString(6);        // Lấy số điện thoại
                String dob = cursor.getString(7);          // Lấy ngày sinh
                String image = cursor.getString(8);        // Lấy hình ảnh
                boolean isAdmin = cursor.getInt(9) > 0;    // Lấy trạng thái quản trị viên


                // Tạo đối tượng User từ dữ liệu lấy được
                user = new User(address,dob,email,id1,image,isAdmin,name,phone,password,status);
            }
            cursor.close(); // Đóng con trỏ
        }


        return user; // Trả về đối tượng User hoặc null nếu không tìm thấy
    }
//    public List<Category> getAllCategories() {
//        List<Category> categories = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT id, name FROM Category", null);
//
//        if (cursor.moveToFirst()) {
//            categories.add(new Category(-1, "All Categories")); // Add an option for "All Categories"
//            do {
//                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
//                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
//                categories.add(new Category(id, name));
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//
//        return categories;
//    }

    public long addTour(Tour i){
        ContentValues values = new ContentValues();
        values.put("category_id", i.getCategory_id());
        values.put("tour_name", i.getTour_name());
        values.put("description", i.getDescription());
        values.put("address", i.getAddress());
        values.put("time_tour", i.getTime_tour());
        values.put("start_date", i.getStart_date());
        values.put("image", i.getImage());
        values.put("end_date", i.getEnd_date());
        values.put("update_at", i.getUpdate_at());
        values.put("delete_at", i.getDelete_at());
        values.put("create_at", i.getCreate_at());
        values.put("guide_phone", i.getGuide_phone());
        values.put("guide_image", i.getGuide_image());
        values.put("guide_name", i.getGuide_name());
        values.put("location", i.getLocation());
        values.put("price", i.getPrice());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("Tour", null, values);

    }

    //updateTour
    public int update(Tour i){
        ContentValues values = new ContentValues();
        values.put("category_id", i.getCategory_id());
        values.put("tour_name", i.getTour_name());
        values.put("description", i.getDescription());
        values.put("address", i.getAddress());
        values.put("time_tour", i.getTime_tour());
        values.put("start_date", i.getStart_date());
        values.put("image", i.getImage());
        values.put("end_date", i.getEnd_date());
        values.put("update_at", i.getUpdate_at());
        values.put("delete_at", i.getDelete_at());
        values.put("create_at", i.getCreate_at());
        values.put("guide_phone", i.getGuide_phone());
        values.put("guide_image", i.getGuide_image());
        values.put("guide_name", i.getGuide_name());
        values.put("location", i.getLocation());
        values.put("price", i.getPrice());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id=?";
        String [] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("Tour",  values, whereClause, whereArgs);
    }
    public List<User> getUsersNonAdmin() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> userList = new ArrayList<>();


        String query = "SELECT * FROM User WHERE isAdmin = 0";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id1 = cursor.getInt(0);                 // Lấy ID
                    int status = cursor.getInt(1);             // Lấy trạng thái
                    String name = cursor.getString(2);         // Lấy tên
                    String address = cursor.getString(3);        // Lấy email
                    String phone = cursor.getString(4);     // Lấy mật khẩu
                    String email = cursor.getString(5);      // Lấy địa chỉ
                    String dob = cursor.getString(6);        // Lấy số điện thoại
                    String image = cursor.getString(7);          // Lấy ngày sinh
                    String password = cursor.getString(8);        // Lấy hình ảnh
                    boolean isAdmin = cursor.getInt(9) > 0;    // Lấy trạng thái quản trị viên

                    // Tạo đối tượng User từ dữ liệu lấy được
                    User user = new User(address, dob, email, id1, image, isAdmin, name, phone, password, status);
                    userList.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close(); // Đóng con trỏ
        }

        return userList; // Trả về danh sách người dùng
    }

}
