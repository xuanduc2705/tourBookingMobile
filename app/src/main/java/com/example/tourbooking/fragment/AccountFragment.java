package com.example.tourbooking.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tourbooking.LoginActivity;
import com.example.tourbooking.MainActivity;
import com.example.tourbooking.R;
import com.example.tourbooking.dal.SQLiteHelper;
import com.example.tourbooking.model.Tour;
import com.example.tourbooking.model.User;

public class AccountFragment extends Fragment implements View.OnClickListener {

    private Button btChangeInfo,btLogout,btLogin;

    private TextView tvName,tvEmail,tvPass,tvPhone,tvAddress,tvDob;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout layoutNotLoggedIn = view.findViewById(R.id.layout_not_logged_in);
        LinearLayout layoutLoggedIn = view.findViewById(R.id.layout_logged_in);

        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvPass = view.findViewById(R.id.tvPass);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvAddress = view.findViewById(R.id.tvAddress);
        tvDob = view.findViewById(R.id.tvDob);
        btChangeInfo = view.findViewById(R.id.btChangeInfo);
        btLogout = view.findViewById(R.id.btLogout);

        db = new SQLiteHelper(getContext());
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);

        if (userId != -1) {
            layoutLoggedIn.setVisibility(View.VISIBLE);
            layoutNotLoggedIn.setVisibility(View.GONE);

            User u = db.getUserById(userId);
            tvName.setText(u.getName());
            tvEmail.setText(u.getEmail());
            tvPass.setText(u.getPassword());
            tvPhone.setText(u.getPhone());
            tvAddress.setText(u.getAddress());
            tvDob.setText(u.getDob());

            btChangeInfo.setOnClickListener(this);
            btLogout.setOnClickListener(this);
        } else {
            layoutLoggedIn.setVisibility(View.GONE);
            layoutNotLoggedIn.setVisibility(View.VISIBLE);

            Button btLogin = view.findViewById(R.id.btLogin);
            btLogin.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            });
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btLogout) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("userId");
            editor.apply();

            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);

            getActivity().finish();
        }
    }
}
