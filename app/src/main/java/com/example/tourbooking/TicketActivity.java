package com.example.tourbooking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tourbooking.dal.SQLiteHelper;
import com.example.tourbooking.model.Booking;
import com.example.tourbooking.model.Tour;

public class TicketActivity extends AppCompatActivity {
    private SQLiteHelper db;
    private Button btCallGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ticket);

        db = new SQLiteHelper(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Booking booking = (Booking) getIntent().getSerializableExtra("booking");

        Tour tour = db.getTourById(booking.getTour_id());

        if (tour == null) {
            Toast.makeText(this, "Tour not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        TextView tvTourname = findViewById(R.id.tvTourname);
        TextView tvDuration = findViewById(R.id.tvDuration);
        TextView tvTotal = findViewById(R.id.tvTotal);
        TextView tvNumP = findViewById(R.id.tvNumP);
        TextView tvGuideName = findViewById(R.id.tvGuideName);
        TextView tvGuidePhone = findViewById(R.id.tvGuidePhone);
        TextView tvStartDate = findViewById(R.id.tvStartDate);
        TextView tvEndDate = findViewById(R.id.tvEndDate);
        ImageView tvImage = findViewById(R.id.tvImage);
        btCallGuide = findViewById(R.id.btCallGuide);

        tvTourname.setText(tour.getTour_name());
        tvDuration.setText("Duration: " + tour.getTime_tour());
        tvTotal.setText("Total: $" + booking.getTotal());
        tvNumP.setText("Num Of People: " + booking.getNumOfPeople());
        tvStartDate.setText("Start Date: " + tour.getStart_date());
        tvEndDate.setText("End Date: " + tour.getEnd_date());
        tvGuideName.setText("Name: " + tour.getGuide_name());
        tvGuidePhone.setText("Phone: " + tour.getGuide_phone());
        int resourceId = tvImage.getContext().getResources().getIdentifier(tour.getImage(), "drawable", tvImage.getContext().getPackageName());
        tvImage.setImageResource(resourceId);

        btCallGuide.setOnClickListener(v -> {
            String phoneNumber = tour.getGuide_phone();
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(callIntent);
            } else {
                Toast.makeText(TicketActivity.this, "Phone number not available", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
