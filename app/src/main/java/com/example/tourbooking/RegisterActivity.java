package com.example.tourbooking;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.tourbooking.model.Tour;
import com.example.tourbooking.model.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eEmail,ePass,eAddress,ePhone,eDob,eName;
    private Button btSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        btSignUp.setOnClickListener(this);
    }

    private void initView() {

        eEmail = findViewById(R.id.tvEmail);
        ePass = findViewById(R.id.tvPass);
        eAddress = findViewById(R.id.tvAddress);
        ePhone = findViewById(R.id.tvPhone);
        eDob = findViewById(R.id.tvDob);
        eName = findViewById(R.id.tvName);

        btSignUp = findViewById(R.id.btSignUp);

    }


    @Override
    public void onClick(View v) {
        if (v == btSignUp) {
            String email = eEmail.getText().toString();
            String pass = ePass.getText().toString();
            String address = eAddress.getText().toString();
            String phone = ePhone.getText().toString();
            String dob = eDob.getText().toString();
            String name = eName.getText().toString();

            User u = new User(address, dob, email, "image", false, name, pass, phone, 1);
            SQLiteHelper db = new SQLiteHelper(this);

            long result = db.addUser(u);

            if (result != -1) {
                Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Error while signing up", Toast.LENGTH_SHORT).show();
            }
        }
    }



}