package com.example.tourbooking;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourbooking.adapter.UserAdapter;
import com.example.tourbooking.dal.SQLiteHelper;
import com.example.tourbooking.model.User;

import java.util.List;

public class ListUsers extends AppCompatActivity implements UserAdapter.OnItemClickListener {
    private RecyclerView recyclerViewUser;
    private UserAdapter adapter;
    private SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_users);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setContentView(R.layout.activity_list_users);

        recyclerViewUser = findViewById(R.id.recyclerViewUser);
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(this));

        db = new SQLiteHelper(this);

        List<User> userList = db.getUsersNonAdmin();

        adapter = new UserAdapter(this, userList, this);
        recyclerViewUser.setAdapter(adapter);
    }

    @Override
    public void onItemClick(User user) {

    }
}