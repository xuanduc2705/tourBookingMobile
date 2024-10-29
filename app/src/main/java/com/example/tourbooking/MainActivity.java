package com.example.tourbooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tourbooking.fragment.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        // Thiết lập adapter cho ViewPager
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Home");
                    tab.setIcon(R.drawable.ic_home); // Đặt icon cho tab Home
                    break;
                case 1:
                    tab.setText("Tìm kiếm");
                    tab.setIcon(R.drawable.ic_search); // Đặt icon cho tab Tìm kiếm
                    break;
                case 2:
                    tab.setText("Đơn của tôi");
                    tab.setIcon(R.drawable.ic_booking); // Đặt icon cho tab Lịch sử
                    break;
                case 3:
                    tab.setText("Lịch sử");
                    tab.setIcon(R.drawable.ic_history); // Đặt icon cho tab Lịch sử
                    break;
                case 4:
                    tab.setText("Tài khoản");
                    tab.setIcon(R.drawable.ic_account); // Đặt icon cho tab Lịch sử
                    break;
            }
        }).attach();
    }
}
