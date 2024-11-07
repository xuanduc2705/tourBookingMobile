package com.example.tourbooking;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

public class UpdateActivity extends AppCompatActivity {

    private EditText tvTourName, tvDes, tvAddress, tvTimeTour, tvStart, tvEnd, tvGuideName, tvGuidePhone, tvPrice;
    private Spinner spinnerCategory;
    private Button btUpdate, btSelectImage;
    private ImageView ivTourImage;
    private SQLiteHelper db;
    private TextView tvStatus;
    private Button btToggleStatus;
    private List<Category> categoryList;
    private List<String> categoryNames;
    private Tour currentTour;
    private String selectedImageName = "default_image";

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
        setContentView(R.layout.activity_update);

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
        ivTourImage = findViewById(R.id.ivTourImage);
        btSelectImage = findViewById(R.id.btSelectImage);
        tvStatus = findViewById(R.id.tvStatus);
        btToggleStatus = findViewById(R.id.btToggleStatus);
        btUpdate = findViewById(R.id.btUpdate);

        db = new SQLiteHelper(this);

        categoryList = db.getAllCategories();
        categoryNames = new ArrayList<>();
        for (Category category : categoryList) {
            categoryNames.add(category.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        currentTour = (Tour) getIntent().getSerializableExtra("tour");
        if (currentTour != null) {
            tvTourName.setText(currentTour.getTour_name());
            tvDes.setText(currentTour.getDescription());
            tvAddress.setText(currentTour.getAddress());
            tvTimeTour.setText(currentTour.getTime_tour());
            tvStart.setText(currentTour.getStart_date());
            tvEnd.setText(currentTour.getEnd_date());
            tvGuideName.setText(currentTour.getGuide_name());
            tvGuidePhone.setText(currentTour.getGuide_phone());
            tvPrice.setText(String.valueOf(currentTour.getPrice()));

            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryList.get(i).getId() == currentTour.getCategory_id()) {
                    spinnerCategory.setSelection(i);
                    break;
                }
            }

            selectedImageName = currentTour.getImage();
            int imageResourceId = getResources().getIdentifier(selectedImageName, "drawable", getPackageName());
            ivTourImage.setImageResource(imageResourceId);
            setStatus();
        }

        btSelectImage.setOnClickListener(v -> showImagePickerDialog());
        btToggleStatus.setOnClickListener(v -> toggleStatus());
        btUpdate.setOnClickListener(v -> updateTour());
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
    private void setStatus() {
        if (currentTour.getDelete_at() == null || currentTour.getDelete_at().isEmpty()) {
            tvStatus.setText("ON SHOW");
            tvStatus.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            tvStatus.setText("ON HIDE");
            tvStatus.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }
    private void toggleStatus() {
        if (currentTour.getDelete_at() == null || currentTour.getDelete_at().isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            currentTour.setDelete_at(sdf.format(new Date()));
        } else {
            currentTour.setDelete_at("");
        }
        setStatus();
    }
    private void updateTour() {
        int selectedPosition = spinnerCategory.getSelectedItemPosition();
        int categoryId = categoryList.get(selectedPosition).getId();

        String tourName = tvTourName.getText().toString();
        String description = tvDes.getText().toString();
        String address = tvAddress.getText().toString();
        String timeTour = tvTimeTour.getText().toString();
        String start = tvStart.getText().toString();
        String end = tvEnd.getText().toString();
        String guideName = tvGuideName.getText().toString();
        String guidePhone = tvGuidePhone.getText().toString();
        String price = tvPrice.getText().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date());

        currentTour.setTour_name(tourName);
        currentTour.setDescription(description);
        currentTour.setAddress(address);
        currentTour.setTime_tour(timeTour);
        currentTour.setStart_date(start);
        currentTour.setEnd_date(end);
        currentTour.setGuide_name(guideName);
        currentTour.setGuide_phone(guidePhone);
        currentTour.setPrice(Float.parseFloat(price));
        currentTour.setCategory_id(categoryId);
        currentTour.setUpdate_at(currentDate);
        currentTour.setImage(selectedImageName);
        currentTour.setDelete_at(currentTour.getDelete_at());
        long result = db.update(currentTour);
        if (result != -1) {
            Toast.makeText(this, "Tour updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error updating tour", Toast.LENGTH_SHORT).show();
        }
    }
}
