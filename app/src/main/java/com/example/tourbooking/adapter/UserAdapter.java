package com.example.tourbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourbooking.R;
import com.example.tourbooking.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private Context context;
    private OnItemClickListener listener;

    public UserAdapter(Context context, List<User> userList, OnItemClickListener listener) {
        this.context = context;
        this.userList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user, listener);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, phone, dob, address;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            email = itemView.findViewById(R.id.tvEmail);
            phone = itemView.findViewById(R.id.tvPhone);
            dob = itemView.findViewById(R.id.tvDob);
            address = itemView.findViewById(R.id.tvAddress);
        }

        public void bind(final User user, final OnItemClickListener listener) {
            name.setText(user.getName());
            email.setText(user.getEmail());
            phone.setText(user.getPhone());
            dob.setText(user.getDob());
            address.setText(user.getAddress());

            itemView.setOnClickListener(v -> listener.onItemClick(user));
        }
    }

    public void updateList(List<User> newList) {
        this.userList = newList;
        notifyDataSetChanged();
    }
}
