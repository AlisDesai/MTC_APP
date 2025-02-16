package com.example.mtc_app.staff.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtc_app.R;
import com.example.mtc_app.staff.staff_detailed_page;

import java.util.List;

public class adapter_home extends RecyclerView.Adapter<adapter_home.ViewHolder> {
    private List<ItemData> itemList;
    private Context context;

    public adapter_home(List<ItemData> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemData item = itemList.get(position);
        holder.itemTitle.setText(item.getCustomerName());
        holder.itemSubtitle.setText("Status: " + item.getStatus());
        holder.itemSampleName.setText("Sample: " + item.getSampleName());

        // Handle click event
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, staff_detailed_page.class);
            intent.putExtra("orderId", item.getOrderId());
            intent.putExtra("customerName", item.getCustomerName());
            intent.putExtra("status", item.getStatus());
            intent.putExtra("sampleName", item.getSampleName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle, itemSubtitle, itemSampleName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemSubtitle = itemView.findViewById(R.id.itemSubtitle);
            itemSampleName = itemView.findViewById(R.id.itemSampleName);
        }
    }
}
