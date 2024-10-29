package com.example.tourbooking.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourbooking.R;
import com.example.tourbooking.model.Category;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<Category> list;
    private ItemListener itemListener;
    private int selectedPosition = -1;
    private int lastSelectedPosition = -1;

    public RecycleViewAdapter(ItemListener itemListener) {
        this.list = new ArrayList<>();
        this.itemListener = itemListener;
    }

    public void setList(List<Category> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Category getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Category category = list.get(position);
        holder.name.setText(category.getName());
        int resourceId = holder.itemView.getContext().getResources().getIdentifier(category.getImage(), "drawable", holder.itemView.getContext().getPackageName());
        holder.image.setImageResource(resourceId);
        holder.itemView.setOnClickListener(v -> {
            lastSelectedPosition = selectedPosition;
            selectedPosition = position;
            notifyItemChanged(lastSelectedPosition);
            notifyItemChanged(selectedPosition);

        });
        if (position == selectedPosition) {
            holder.name.setVisibility(View.VISIBLE); // Show the name
            holder.itemView.setBackgroundColor(Color.parseColor("#2196F3")); // Change background color to blue
        } else {
            holder.name.setVisibility(View.GONE); // Hide the name
            holder.itemView.setBackgroundColor(Color.TRANSPARENT); // Reset background color
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private LinearLayout layout;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageCategory);
            name = itemView.findViewById(R.id.nameCategory);
            name.setVisibility(View.GONE); // Initially hidden
        }
    }

    public interface ItemListener {
        void onItemClick(Category category);
    }
}
