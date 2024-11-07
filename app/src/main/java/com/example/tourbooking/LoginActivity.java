package com.example.tourbooking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tourbooking.dal.SQLiteHelper;
import com.example.tourbooking.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eEmail, ePass;
    private Button btSignIn;
    private FloatingActionButton fabBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        btSignIn.setOnClickListener(this);

        fabBack.setOnClickListener(v -> {
            // Go back to HomeFragment
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("navigateTo", "HomeFragment");
            startActivity(intent);
            finish();
        });
    }

    private void initView() {
        eEmail = findViewById(R.id.tvEmail);
        ePass = findViewById(R.id.tvPass);
        btSignIn = findViewById(R.id.btSignIn);
        fabBack = findViewById(R.id.fab);
    }

    @Override
    public void onClick(View v) {
        if(v==btSignIn){
            String email = eEmail.getText().toString();
            String pass = ePass.getText().toString();

            SQLiteHelper db = new SQLiteHelper(this);
            boolean isLoggedIn = db.login(email, pass);

            if (isLoggedIn) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                User u = db.getUserByEmail(email);
                editor.putInt("userId", u.getId());
                editor.apply();

                Intent intent = new Intent(this, MainActivity.class);
                if (u.isAdmin()) {
                    intent = new Intent(this, Admin.class);
                } else {
                    intent = new Intent(this, MainActivity.class);
                }
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Email hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onSignUpClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
