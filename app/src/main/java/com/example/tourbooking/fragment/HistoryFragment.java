package com.example.tourbooking.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;

import com.example.tourbooking.LoginActivity;
import com.example.tourbooking.R;
import com.example.tourbooking.TicketActivity;
import com.example.tourbooking.adapter.HistoryAdapter;
import com.example.tourbooking.dal.SQLiteHelper;
import com.example.tourbooking.model.Booking;

import java.util.List;

public class HistoryFragment extends Fragment {
    private HistoryAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    private Button btLogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();
        updateHistoryList();
    }

    private void updateHistoryList() {
        int userId = getContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE).getInt("userId", -1);
        if (userId != -1) {
            List<Booking> list = db.getAllBooking(userId);
            adapter.updateList(list);
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout layoutNotLoggedIn = view.findViewById(R.id.layout_not_logged_in);
        LinearLayout layoutLoggedIn = view.findViewById(R.id.layout_logged_in);
        recyclerView = view.findViewById(R.id.recyclerView);

        db = new SQLiteHelper(getContext());

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);

        if (userId != -1) {
            layoutLoggedIn.setVisibility(View.VISIBLE);
            layoutNotLoggedIn.setVisibility(View.GONE);
            List<Booking> list = db.getAllBooking(userId);

            adapter = new HistoryAdapter(getContext(), list, new HistoryAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Booking booking) {

                    Intent intent = new Intent(getContext(), TicketActivity.class);

                    intent.putExtra("booking", booking);
                    startActivity(intent);
                }
            });

            LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }else{
            layoutLoggedIn.setVisibility(View.GONE);
            layoutNotLoggedIn.setVisibility(View.VISIBLE);

            Button btLogin = view.findViewById(R.id.btLogin);
            btLogin.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            });
        }

    }
}