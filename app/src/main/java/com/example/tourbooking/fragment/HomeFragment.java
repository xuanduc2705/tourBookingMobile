package com.example.tourbooking.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourbooking.R;
import com.example.tourbooking.adapter.RecycleViewAdapter;
import com.example.tourbooking.dal.SQLiteHelper;
import com.example.tourbooking.model.Category;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RecycleViewAdapter.ItemListener {
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout for fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView and adapter
        recyclerView = view.findViewById(R.id.recyclerView);
        db = new SQLiteHelper(getContext());
db.clearCategories();
        // Insert dummy data if needed
        List<Category> categoryList1 = new ArrayList<>();
        categoryList1.add(new Category(1, "beach", "Biển", "28/10/2024", "28/10/2024", "28/10/2024"));
        categoryList1.add(new Category(2, "eco", "Sinh tha", "29/10/2024", "29/10/2024", "29/10/2024"));
        categoryList1.add(new Category(3, "mountain", "Đồi núi", "30/10/2024", "30/10/2024", "30/10/2024"));
        categoryList1.add(new Category(4, "resort", "Nghỉ dưỡng", "30/10/2024", "30/10/2024", "30/10/2024"));
        categoryList1.add(new Category(5, "tradition", "Văn hóa", "30/10/2024", "30/10/2024", "30/10/2024"));

        // Insert categories into the database
        db.addCategories(categoryList1);

        // Retrieve list from database and set adapter
        List<Category> categoryList = db.getAllBookings();
        adapter = new RecycleViewAdapter(this); // Pass 'this' as the ItemListener
        adapter.setList(categoryList);

        Log.d("HomeFragment", "Category list size: " + categoryList.size());

        // Set layout manager and adapter for RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(Category category) {
        // Handle item click if additional actions are needed
        Log.d("HomeFragment", "Item clicked: " + category.getName());
    }
}
