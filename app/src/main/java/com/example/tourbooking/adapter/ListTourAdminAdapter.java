package com.example.tourbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourbooking.R;
import com.example.tourbooking.model.Tour;

import java.util.List;

public class ListTourAdminAdapter extends RecyclerView.Adapter<ListTourAdminAdapter.ListTourAdminViewHolder> {

    private List<Tour> tourList;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Tour tour);
    }

    public ListTourAdminAdapter(Context context, List<Tour> tourList, OnItemClickListener listener) {
        this.context = context;
        this.tourList = tourList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListTourAdminViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour, parent, false);
        return new ListTourAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTourAdminViewHolder holder, int position) {
        Tour tour = tourList.get(position);

        holder.name.setText(tour.getTour_name());
        holder.price.setText(String.valueOf(tour.getPrice()));
        holder.date.setText(tour.getStart_date() + " - " + tour.getEnd_date());
        holder.guide.setText(tour.getGuide_name());
        int imageResId = context.getResources().getIdentifier(tour.getImage(), "drawable", context.getPackageName());
        if (imageResId != 0) {
            holder.image.setImageResource(imageResId);
        } else {
            holder.image.setImageResource(R.drawable.hathu); // Use a placeholder image if not found
        }
        holder.itemView.setOnClickListener(v -> listener.onItemClick(tour));
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public static class ListTourAdminViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, date,guide;
        ImageView image;

        public ListTourAdminViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvTourName);
            price = itemView.findViewById(R.id.tvTourPrice);
            date = itemView.findViewById(R.id.tvDate);
            guide = itemView.findViewById(R.id.tvGuideName);
            image = itemView.findViewById(R.id.ivTourImage);
        }
    }

    public void updateList(List<Tour> newTourList) {
        this.tourList = newTourList;
        notifyDataSetChanged();
    }
}
