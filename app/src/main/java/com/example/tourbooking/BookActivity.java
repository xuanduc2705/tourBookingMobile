package com.example.tourbooking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tourbooking.dal.SQLiteHelper;
import com.example.tourbooking.fragment.HistoryFragment;
import com.example.tourbooking.model.Booking;
import com.example.tourbooking.model.Tour;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eNumOfP;
    private Button btBook,btCancel;
    private Tour tour;
    private TextView tvTourName,tvDes,tvAddress,tvStart,tvEnd,tvPrice,tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        btBook.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        Intent intent = getIntent();
        tour =(Tour)intent.getSerializableExtra("tour");

        tvTourName.setText(tour.getTour_name());
        tvDes.setText(tour.getDescription());
        tvAddress.setText(tour.getAddress());
        tvStart.setText(tour.getStart_date());
        tvEnd.setText(tour.getEnd_date());
        tvPrice.setText(String.valueOf(tour.getPrice()));


        eNumOfP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().isEmpty()) {
                    try {
                        int num = Integer.parseInt(s.toString());
                        float price = Float.parseFloat(tvPrice.getText().toString());
                        tvTotal.setText("$" + tong(num, price));
                    } catch (NumberFormatException e) {

                        tvTotal.setText("$0");
                    }
                } else {
                    tvTotal.setText("$0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });



    }

    private void initView() {

        tvTourName = findViewById(R.id.tvTourName);
        tvDes = findViewById(R.id.tvDescrip);
        tvAddress = findViewById(R.id.tvAddress);
        tvStart = findViewById(R.id.tvStart);
        tvEnd = findViewById(R.id.tvEnd);
        tvPrice = findViewById(R.id.tvPrice);
        eNumOfP = findViewById(R.id.numOfPeople);
        tvTotal= findViewById(R.id.tvTotal);

        btBook = findViewById(R.id.btBook);
        btCancel = findViewById(R.id.btCancel);

    }
    private float tong(int num, float price){

        return price * num;
    }



    @Override
    public void onClick(View v) {
        SQLiteHelper db = new SQLiteHelper(this);
        if (v == btBook) {
            int num = Integer.parseInt(eNumOfP.getText().toString());


            if (num <= 0 || num >10) {
                Toast.makeText(BookActivity.this, "Số người phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                return;
            }

            float price = Float.parseFloat(tvPrice.getText().toString());
            float total = price * num;
            LocalDate today = LocalDate.now();


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = today.format(formatter);
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
            int userId = sharedPreferences.getInt("userId", -1);
            Booking b = new Booking(formattedDate, num, 1, total, tour.getId(), userId);
            db.addBooking(b);
            Toast.makeText(BookActivity.this, "Book successful", Toast.LENGTH_SHORT).show();
            finish();
        }

        if (v == btCancel) {
            finish();
        }
    }

}