package com.example.tourbooking;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tourbooking.dal.SQLiteHelper;
import com.example.tourbooking.model.Category;
import com.example.tourbooking.model.Tour;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddTourAdmin extends AppCompatActivity {

    private EditText tvTourName, tvDes, tvAddress, tvTimeTour, tvStart, tvEnd, tvGuideName, tvGuidePhone, tvPrice;
    private Spinner spinnerCategory;
    private Button btAdd, btSelectImage;
    private ImageView ivTourImage;
    private String selectedImageName = "default_image";
    private SQLiteHelper db;
    private List<Category> categoryList;
    private List<String> categoryNames;

    private final int[] drawableImages = {
            R.drawable.babe,
            R.drawable.baidinh,
            R.drawable.cattien,
            R.drawable.hoian,
            R.drawable.vungtau,
            R.drawable.samson,
    };
    private final String[] drawableImageNames = {
            "babe",
            "baidinh",
            "cattien",
            "hoian",
            "vungtau",
            "samson"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tour_admin);

        tvTourName = findViewById(R.id.tvTourName);
        tvDes = findViewById(R.id.tvDes);
        tvAddress = findViewById(R.id.tvAddress);
        tvTimeTour = findViewById(R.id.tvTimeTour);
        tvStart = findViewById(R.id.tvStart);
        tvEnd = findViewById(R.id.tvEnd);
        tvGuideName = findViewById(R.id.tvGuideName);
        tvGuidePhone = findViewById(R.id.tvGuidePhone);
        tvPrice = findViewById(R.id.tvPrice);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btAdd = findViewById(R.id.btAdd);
        ivTourImage = findViewById(R.id.ivTourImage);
        btSelectImage = findViewById(R.id.btSelectImage);

        db = new SQLiteHelper(this);

        categoryList = db.getAllCategories();
        categoryNames = new ArrayList<>();
        for (Category category : categoryList) {
            categoryNames.add(category.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        btSelectImage.setOnClickListener(v -> showImagePickerDialog());

        btAdd.setOnClickListener(v -> addTour());
    }

    private void showImagePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select an Image");

        builder.setItems(drawableImageNames, (dialog, which) -> {
            selectedImageName = drawableImageNames[which];
            ivTourImage.setImageResource(drawableImages[which]);
        });

        builder.show();
    }

    private void addTour() {
        int selectedPosition = spinnerCategory.getSelectedItemPosition();
        int categoryId = categoryList.get(selectedPosition).getId();

        String tourName = tvTourName.getText().toString().trim();
        String description = tvDes.getText().toString().trim();
        String address = tvAddress.getText().toString().trim();
        String location = tvAddress.getText().toString().trim();
        String timeTour = tvTimeTour.getText().toString().trim();
        String start = tvStart.getText().toString().trim();
        String end = tvEnd.getText().toString().trim();
        String guideName = tvGuideName.getText().toString().trim();
        String guidePhone = tvGuidePhone.getText().toString().trim();
        String priceStr = tvPrice.getText().toString().trim();

        if (tourName.isEmpty() || description.isEmpty() || address.isEmpty() || location.isEmpty() ||
                timeTour.isEmpty() || start.isEmpty() || end.isEmpty() || guideName.isEmpty() ||
                guidePhone.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "All fields must be filled out", Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate;
        try {
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            currentDate = new Date();

            if (startDate.before(currentDate) || endDate.before(currentDate)) {
                Toast.makeText(this, "Start and end dates must be in the future", Toast.LENGTH_SHORT).show();
                return;
            }
            if (startDate.after(endDate)) {
                Toast.makeText(this, "Start date must be before end date", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Invalid date format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!guidePhone.matches("\\d+")) {
            Toast.makeText(this, "Guide phone must be a numeric value", Toast.LENGTH_SHORT).show();
            return;
        }

        float price;
        try {
            price = Float.parseFloat(priceStr);
            if (price <= 0) {
                Toast.makeText(this, "Price must be greater than 0", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show();
            return;
        }

        Tour tour = new Tour();
        tour.setTour_name(tourName);
        tour.setDescription(description);
        tour.setAddress(address);
        tour.setTime_tour(timeTour);
        tour.setStart_date(start);
        tour.setEnd_date(end);
        tour.setGuide_name(guideName);
        tour.setGuide_phone(guidePhone);
        tour.setPrice(price);
        tour.setCategory_id(categoryId);
        tour.setLocation(location);
        tour.setCreate_at(sdf.format(currentDate));
        tour.setUpdate_at(sdf.format(currentDate));
        tour.setDelete_at(sdf.format(currentDate));
        tour.setGuide_image("guideImage");
        tour.setImage(selectedImageName);

        long result = db.addTour(tour);
        if (result != -1) {
            Toast.makeText(this, "Tour added successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error adding tour", Toast.LENGTH_SHORT).show();
        }
    }
}



