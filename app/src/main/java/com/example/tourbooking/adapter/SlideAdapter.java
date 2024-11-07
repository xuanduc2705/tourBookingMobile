package com.example.tourbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourbooking.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder> {
    private final Context context;
    private final List<Integer> imageList;

    public SlideAdapter(Context context, List<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide_item, parent, false);
        return new SlideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    static class SlideViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView imageView;

        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.slide_image);
        }
    }
}
