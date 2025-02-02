package com.example.mtc_app.customer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mtc_app.R;
import com.example.mtc_app.customer.models.Order;
import com.google.android.material.button.MaterialButton;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Order> orderList;
    private Context context;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_card, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);

        // ✅ Ensure values are not null to avoid crashes
        String status = order.getStatus() != null ? order.getStatus() : "Unknown";
        String dispatchMode = order.getDispatchMode() != null ? order.getDispatchMode() : "N/A";
        String date = order.getDate() != null ? order.getDate() : "N/A";
        String segment = order.getSegment() != null ? order.getSegment() : "N/A";
        int price = order.getPrice(); // No need for null check


        // ✅ Set text safely
        holder.orderStatus.setText("Status: " + status);
        holder.dispatchMode.setText("Dispatch Mode: " + dispatchMode);
        holder.orderDate.setText("Date: " + date);
        holder.segment.setText("Segment: " + segment);
        holder.price.setText("Total Price: " + price);

        // ✅ Handle different status colors
        if ("Submitted".equalsIgnoreCase(status)) {
            holder.orderStatus.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_dark));
        } else if ("Pending".equalsIgnoreCase(status)) {
            holder.orderStatus.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_orange_dark));
        } else if ("Completed".equalsIgnoreCase(status)) {
            holder.orderStatus.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_blue_dark));
        } else {
            holder.orderStatus.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.darker_gray));
        }

//         ✅ Handle click on order details button
//        holder.orderDetailsButton.setOnClickListener(v -> {
//            Intent intent = new Intent(context, OrderDetailsActivity.class);
//            intent.putExtra("order_id", order.getId()); // Pass order ID
//            context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    // ✅ Search filter method (update filtered list)
    public void updateList(List<Order> newList) {
        orderList = newList;
        notifyDataSetChanged();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderStatus, dispatchMode, orderDate, segment , price;
        MaterialButton orderDetailsButton;
        CardView cardView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.orderCard);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            dispatchMode = itemView.findViewById(R.id.dispatchMode);
            orderDate = itemView.findViewById(R.id.orderDate);
            segment = itemView.findViewById(R.id.segment);
            price = itemView.findViewById(R.id.price);
            orderDetailsButton = itemView.findViewById(R.id.orderDetailsButton);
        }
    }
}
