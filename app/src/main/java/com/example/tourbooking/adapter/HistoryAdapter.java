package com.example.tourbooking.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourbooking.R;
import com.example.tourbooking.dal.SQLiteHelper;
import com.example.tourbooking.fragment.HistoryFragment;
import com.example.tourbooking.model.Booking;
import com.example.tourbooking.model.Tour;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<Booking> bookingList;
    private Context context;
    private OnItemClickListener listener;
    private SQLiteHelper db;


    public HistoryAdapter(Context context, List<Booking> bookingList, OnItemClickListener listener) {
        this.context = context;
        this.bookingList = bookingList;
        this.listener = listener;
        this.db = new SQLiteHelper(context);
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.bind(booking, listener,db);
    }


    public void setList(List<Booking> list) {
        this.bookingList = list;
        notifyDataSetChanged();
    }

    public void setItemListener(HistoryFragment historyFragment) {
    }

    public interface OnItemClickListener {
        void onItemClick(Booking booking);
    }


    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView name,numP,date,total,status;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvTourName);
            numP = itemView.findViewById(R.id.tvNumOfPeople);
            date =itemView.findViewById(R.id.tvTourDate);
            total = itemView.findViewById(R.id.tvTotal);
            status = itemView.findViewById(R.id.tvStatus);

        }
        void bind(final Booking booking, final OnItemClickListener listener, SQLiteHelper db) {
            // Fetch the tour based on the booking's tour ID
            Tour t = db.getDetailTourById(booking.getTour_id());
            Log.d("HistoryAdapter", "Check: " + t );
            // Add log statements to verify fetched data
            Log.d("HistoryAdapter", "Binding tour: " + (t != null ? t.getTour_name() : "null"));
            Log.d("HistoryAdapter", "Booking details - NumPeople: " + booking.getNumOfPeople() +
                    ", Date: " + booking.getBookingDate() +
                    ", Total: " + booking.getTotal() +
                    ", Status: " + booking.getStatus());
            name.setText(t.getTour_name());
            numP.setText(String.valueOf(booking.getNumOfPeople()));
            date.setText(booking.getBookingDate());
            total.setText(String.valueOf(booking.getTotal()));
            status.setText(String.valueOf(booking.getStatus()));
            if (booking.getStatus() == 1) {
                status.setText("Success");
            } else {
                status.setText("Failed"); // or any other status message you want to display
            }
            itemView.setOnClickListener(v -> listener.onItemClick(booking));
        }

    }
    public void updateList(List<Booking> newList) {
        this.bookingList = newList;
        notifyDataSetChanged();
    }


}
