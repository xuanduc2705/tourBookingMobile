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

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Home");
                    tab.setIcon(R.drawable.ic_home);
                    break;
                case 1:
                    tab.setText("Search");
                    tab.setIcon(R.drawable.ic_search);
                    break;
//                case 2:
//                    tab.setText("Explore");
//                    tab.setIcon(R.drawable.ic_booking);
//                    break;
                case 2:
                    tab.setText("History");
                    tab.setIcon(R.drawable.ic_history);
                    break;
                case 3:
                    tab.setText("Account");
                    tab.setIcon(R.drawable.ic_account);
                    break;
            }
        }).attach();
    }
}
