package com.example.tourbooking.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourbooking.R;
import com.example.tourbooking.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class RecomendAdapter extends RecyclerView.Adapter<RecomendAdapter.RecommendViewHolder> {
    private List<Tour> tourList;
    private ItemListener itemListener;
    private int selectedPosition = -1;

    public RecomendAdapter(ItemListener itemListener) {
        this.tourList = new ArrayList<>();
        this.itemListener = itemListener;
    }

    public void setTourList(List<Tour> tours) {
        this.tourList = tours;
        notifyDataSetChanged();
    }

    public Tour getItem(int position) {
        return tourList.get(position);
    }

    @NonNull
    @Override
    public RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend, parent, false);
        return new RecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendViewHolder holder, int position) {
        Tour tour = tourList.get(position);
        holder.tourName.setText(tour.getTour_name());
        holder.price.setText(String.valueOf(tour.getPrice()));
        int resourceId = holder.itemView.getContext().getResources().getIdentifier(tour.getImage(), "drawable", holder.itemView.getContext().getPackageName());
        holder.image.setImageResource(resourceId);
        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
            itemListener.onItemClick(tour);
        });

        // Change background color based on selection
        if (position == selectedPosition) {
            holder.itemView.setBackgroundColor(Color.parseColor("#E5E4E2")); // Highlight selected item
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT); // Reset background color
        }
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public class RecommendViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView tourName, price,location;
        private LinearLayout layout;

        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageCategory2);
            tourName = itemView.findViewById(R.id.textViewCategory);
            price = itemView.findViewById(R.id.priceCategory);
            location = itemView.findViewById(R.id.textLocation);
        }
    }

    public interface ItemListener {
        void onItemClick(Tour tour);
    }
}
