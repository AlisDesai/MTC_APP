package com.example.mtc_app.staff.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtc_app.R;

import java.util.List;

public class adapter_home extends RecyclerView.Adapter<adapter_home.HomeViewHolder> {

    private List<ItemData> dataItems; // We now use an ItemData class to hold title, subtitle, and icon data
    private Context context;
    private OnItemClickListener onItemClickListener;

    public adapter_home(List<ItemData> dataItems, Context context) {
        this.dataItems = dataItems;
        this.context = context;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_list, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        ItemData item = dataItems.get(position);
        holder.itemTitle.setText(item.getTitle());
        holder.itemSubtitle.setText(item.getSubtitle());
        holder.itemIcon.setImageResource(item.getIconResId());

        // Optionally, handle action icon if needed
        holder.itemActionIcon.setImageResource(R.drawable.ic_chevron_right); // Example

        holder.cardView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    // Set the item click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // ViewHolder class to hold individual item views
    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle, itemSubtitle;
        ImageView itemIcon, itemActionIcon;
        CardView cardView;

        public HomeViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemSubtitle = itemView.findViewById(R.id.itemSubtitle);
            itemIcon = itemView.findViewById(R.id.itemIcon);
            itemActionIcon = itemView.findViewById(R.id.itemActionIcon);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
