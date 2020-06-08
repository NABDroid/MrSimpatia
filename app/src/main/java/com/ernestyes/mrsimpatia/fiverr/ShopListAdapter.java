package com.ernestyes.mrsimpatia.fiverr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder>  {



    private ArrayList<Store> stores;
    private Context context;


    public ShopListAdapter(ArrayList<Store> stores, Context context) {
        this.stores = stores;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Store store = stores.get(position);
        holder.storeName.setText(store.getStoreName());
        holder.authorName.setText(store.getAuthorName());
        holder.location.setText(store.getLocation());

    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView storeName, authorName, location;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.storeNameStoreLayoutET);
            authorName = itemView.findViewById(R.id.authorNameStoreLayoutET);
            location = itemView.findViewById(R.id.locationStoreLayoutET);
            cardView = itemView.findViewById(R.id.storeLayoutCV);

        }
    }
}
