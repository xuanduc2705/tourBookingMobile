package com.example.tourbooking.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.tourbooking.R;
import com.example.tourbooking.adapter.ListTourAdapter;
import com.example.tourbooking.dal.SQLiteHelper;
import com.example.tourbooking.model.Category;
import com.example.tourbooking.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private RecyclerView recyclerViewTourList;
    private ListTourAdapter adapter;
    private EditText editTextTitle, editTextMinPrice, editTextMaxPrice;
    private Spinner spinnerCategory;
    private Button buttonFilter;
    private SQLiteHelper sqLiteHelper;

    private int selectedCategoryId = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerViewTourList = view.findViewById(R.id.recyclerViewTourList);
        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextMinPrice = view.findViewById(R.id.editTextMinPrice);
        editTextMaxPrice = view.findViewById(R.id.editTextMaxPrice);
        spinnerCategory = view.findViewById(R.id.spinnerCategory);
        buttonFilter = view.findViewById(R.id.buttonFilter);
        sqLiteHelper = new SQLiteHelper(getContext());

        setupCategorySpinner();
        setupRecyclerView();

        loadTours("", -1, 0, Double.MAX_VALUE);

        buttonFilter.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString();
            int minPrice = editTextMinPrice.getText().toString().isEmpty() ? 0 : Integer.parseInt(editTextMinPrice.getText().toString());
            int maxPrice = editTextMaxPrice.getText().toString().isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(editTextMaxPrice.getText().toString());
            if (minPrice > maxPrice) {
                Toast.makeText(getContext(), "Minimum price cannot be greater than maximum price.", Toast.LENGTH_SHORT).show();
                return;
            }
            loadTours(title, selectedCategoryId, minPrice, maxPrice);
        });

        return view;
    }

    private void setupRecyclerView() {
        recyclerViewTourList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ListTourAdapter(getContext(), sqLiteHelper.getFilteredTours("", -1, 0, Double.MAX_VALUE));
        recyclerViewTourList.setAdapter(adapter);
    }

    private void setupCategorySpinner() {
        List<Category> categories = sqLiteHelper.getAllCategories();
        List<String> categoryNames = new ArrayList<>();

        for (Category category : categories) {
            categoryNames.add(category.getName());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categoryNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerAdapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategoryId = categories.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }


    private void loadTours(String title, int categoryId, double minPrice, double maxPrice) {
        List<Tour> tourList = sqLiteHelper.getFilteredTours(title, categoryId, minPrice, maxPrice);
        adapter = new ListTourAdapter(getContext(), tourList);
        recyclerViewTourList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewTourList.setAdapter(adapter);
    }
}
