package com.example.tourbooking.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourbooking.DetailActivity;
import com.example.tourbooking.R;
import com.example.tourbooking.model.Tour;

import java.util.List;

public class ListTourAdapter extends RecyclerView.Adapter<ListTourAdapter.TourViewHolder> {
    private Context context;
    private List<Tour> tourList;

    public ListTourAdapter(Context context, List<Tour> tourList) {
        this.context = context;
        this.tourList = tourList;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tour_list_item, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        Tour tour = tourList.get(position);
        holder.tour_name.setText(tour.getTour_name());
        holder.price.setText(String.valueOf(tour.getPrice()) + " $");
        holder.location.setText(tour.getLocation());
        int resourceId = holder.itemView.getContext().getResources().getIdentifier(tour.getImage(), "drawable", holder.itemView.getContext().getPackageName());
        holder.image.setImageResource(resourceId);
        holder.btnSeeDetail.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("tour", tour);
            context.startActivity(intent);
        });
        Log.d("ListTourAdapter", "Binding tour: " + tour.getTour_name());
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    static class TourViewHolder extends RecyclerView.ViewHolder {
        TextView tour_name, location, price;
        ImageView image;
        View btnSeeDetail;
        TourViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageCategory3);
            tour_name = itemView.findViewById(R.id.textViewCategory1);
            location = itemView.findViewById(R.id.textLocation1);
            price = itemView.findViewById(R.id.priceCategory1);
            btnSeeDetail = itemView.findViewById(R.id.seeDetailButton); // Reference to "SEE DETAIL" button
        }
    }
}
