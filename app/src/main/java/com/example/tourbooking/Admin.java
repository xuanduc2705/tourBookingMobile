package com.example.tourbooking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourbooking.adapter.ListTourAdminAdapter;
import com.example.tourbooking.dal.SQLiteHelper;
import com.example.tourbooking.model.Tour;

import java.util.List;

public class Admin extends AppCompatActivity implements ListTourAdminAdapter.OnItemClickListener {

    Button logout, userList, add;
    private RecyclerView recyclerViewAdmin;
    private ListTourAdminAdapter adapter;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        logout = findViewById(R.id.logout);
        userList = findViewById(R.id.viewUser);
        add = findViewById(R.id.addTour);

        logout.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("userId");
            editor.apply();

            Intent intent = new Intent(Admin.this, MainActivity.class);
            startActivity(intent);


            finish();
        });

        userList.setOnClickListener(v -> {
            Intent intent = new Intent(Admin.this, ListUsers.class);
            startActivity(intent);
        });

        add.setOnClickListener(v -> {
            Intent intent = new Intent(Admin.this, AddTourAdmin.class);
            startActivity(intent);
        });

        recyclerViewAdmin = findViewById(R.id.recyclerViewAdmin);
        recyclerViewAdmin.setLayoutManager(new LinearLayoutManager(this));

        db = new SQLiteHelper(this);
        List<Tour> tourList = db.getListTour();

        adapter = new ListTourAdminAdapter(this, tourList, this);
        recyclerViewAdmin.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Tour> tourList = db.getListTour();
        adapter.updateList(tourList);
    }

    @Override
    public void onItemClick(Tour tour) {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("tour", tour);
        startActivity(intent);
    }
}
