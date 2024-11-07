package com.example.tourbooking.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourbooking.DetailActivity;
import com.example.tourbooking.R;
import com.example.tourbooking.adapter.RecomendAdapter;
import com.example.tourbooking.adapter.RecycleViewAdapter;
import com.example.tourbooking.adapter.SlideAdapter;
import com.example.tourbooking.dal.SQLiteHelper;
import com.example.tourbooking.model.Category;
import com.example.tourbooking.model.Tour;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements RecycleViewAdapter.ItemListener, RecomendAdapter.ItemListener {
    private RecycleViewAdapter adapter;
    private RecomendAdapter recommendAdapter;
    private RecyclerView recyclerView, recyclerViewSliderRecommend;
    private SQLiteHelper db;
    private ViewPager2 viewPagerSlider;
    private SlideAdapter slideAdapter;
    private List<Integer> sliderImages;
    private int currentPage = 0;
    private final long slideInterval = 3000L;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPagerSlider = view.findViewById(R.id.viewPagerSlider);
        recyclerViewSliderRecommend = view.findViewById(R.id.recyclerViewSliderRecommend);
        recyclerView = view.findViewById(R.id.recyclerView);
        db = new SQLiteHelper(getContext());

        db.addSampleTours();
        List<Category> categoryList = db.getAllCategories();
        adapter = new RecycleViewAdapter(this);
        adapter.setList(categoryList);

        Log.d("HomeFragment", "Category list size: " + categoryList.size());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        recommendAdapter = new RecomendAdapter(new RecomendAdapter.ItemListener() {
            @Override
            public void onItemClick(Tour tour) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("tour", tour);
                startActivity(intent);
            }
        });
        recyclerViewSliderRecommend.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewSliderRecommend.setAdapter(recommendAdapter);


        List<Tour> tourList = db.getListTourShow();
        recommendAdapter.setTourList(tourList);

        sliderImages = new ArrayList<>();
        sliderImages.add(R.drawable.travel);
        sliderImages.add(R.drawable.travel2);
        sliderImages.add(R.drawable.travel3);

        slideAdapter = new SlideAdapter(getContext(), sliderImages);
        viewPagerSlider.setAdapter(slideAdapter);

        setupAutoSlide();

        return view;
    }

    private void setupAutoSlide() {
        final Handler handler = new Handler(Looper.getMainLooper());
        Timer timer = new Timer();

        TimerTask update = new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    if (currentPage == sliderImages.size()) {
                        currentPage = 0;
                    }
                    viewPagerSlider.setCurrentItem(currentPage++, true);
                });
            }
        };

        timer.schedule(update, slideInterval, slideInterval);
    }

    @Override
    public void onItemClick(Category category) {
        Log.d("HomeFragment", "Category item clicked: " + category.getName());
    }

    @Override
    public void onItemClick(Tour tour) {
        Log.d("HomeFragment", "Tour item clicked: " + tour.getTour_name());
    }
}

