package com.example.tourbooking;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tourbooking.model.Tour;

public class DetailActivity extends AppCompatActivity {
    private TextView detailIDText, detailLocationText, detailTimetext, detailStartTimetext, detailEndTimetext, detailDescriptionText, detailPricetext;
    private ImageView detailImage;
    private Button detailButtonBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Tour tour = (Tour) getIntent().getSerializableExtra("tour");

        detailImage = findViewById(R.id.detailImage);
        detailIDText = findViewById(R.id.detailIDText);
        detailLocationText = findViewById(R.id.detailLocationText);
        detailTimetext = findViewById(R.id.detailTimetext);
        detailStartTimetext = findViewById(R.id.detailStartTimetext);
        detailEndTimetext = findViewById(R.id.detailEndTimetext);
        detailDescriptionText = findViewById(R.id.detailDescriptionText);
        detailPricetext = findViewById(R.id.detailPricetext);
        detailButtonBook = findViewById(R.id.detailButtonBook);

        if (tour != null) {
            int resourceId = detailImage.getContext().getResources().getIdentifier(tour.getImage(), "drawable", detailImage.getContext().getPackageName());
            detailImage.setImageResource(resourceId);
            detailIDText.setText(tour.getTour_name());
            detailLocationText.setText(tour.getLocation());
            detailTimetext.setText(tour.getTime_tour());
            detailStartTimetext.setText(tour.getStart_date());
            detailEndTimetext.setText(tour.getEnd_date());
            detailDescriptionText.setText(tour.getDescription());
            detailPricetext.setText("$" + tour.getPrice());
        }

        detailButtonBook.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
            int userId = sharedPreferences.getInt("userId", -1);

            if (userId != -1) {
                Intent intent = new Intent(DetailActivity.this, BookActivity.class);
                intent.putExtra("tour", tour);
                startActivity(intent);
            }else {
                new AlertDialog.Builder(DetailActivity.this)
                        .setMessage("You need to sign in to do this action?")
                        .setPositiveButton("Login", (dialog, which) -> {
                            Intent loginIntent = new Intent(DetailActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                        })
                        .setNegativeButton("Cancel", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();

            }
        });
    }
}
