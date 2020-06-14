package com.ernestyes.mrsimpatia.fiverr.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ernestyes.mrsimpatia.fiverr.R;
import com.ernestyes.mrsimpatia.fiverr.activities.ChooseStoreActivity;
import com.ernestyes.mrsimpatia.fiverr.Store;
import com.ernestyes.mrsimpatia.fiverr.activities.PlayerHomeActivity;
import com.ernestyes.mrsimpatia.fiverr.activities.WelcomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder>  {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ArrayList<Store> stores;
    private Context context;
    ChooseStoreActivity chooseStoreActivity;

    public ShopListAdapter(ArrayList<Store> stores, Context context, FirebaseAuth firebaseAuth, DatabaseReference databaseReference) {
        this.stores = stores;
        this.context = context;
        this.firebaseAuth = firebaseAuth;
        this.databaseReference = databaseReference;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Store store = stores.get(position);
        holder.storeName.setText(store.getStoreName());
        holder.authorName.setText(store.getAuthorName());
        holder.location.setText(store.getLocation());


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.relativeLayout.setBackgroundColor(Color.LTGRAY);
                writeChosenStoreIntoPlayersTable(store.getStoreName());

            }
        });
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView storeName, authorName, location;
        private RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.storeNameStoreLayoutET);
            authorName = itemView.findViewById(R.id.authorNameStoreLayoutET);
            location = itemView.findViewById(R.id.locationStoreLayoutET);
            relativeLayout = itemView.findViewById(R.id.storeLayoutRL);
        }
    }

    public void writeChosenStoreIntoPlayersTable(final String storeName){
        final String uId = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference playersRef = databaseReference.child("Players").child(uId).child("store");
        playersRef.setValue(storeName).addOnCompleteListener( new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    writeChosenStoreIntoStoreTable(storeName, uId);
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void writeChosenStoreIntoStoreTable(String storeName, String uId) {

        DatabaseReference storeRef = databaseReference.child("Stores").child(storeName).child("players").child(uId);
        storeRef.setValue(uId).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                
                context.startActivity(new Intent(context, PlayerHomeActivity.class));
                Toast.makeText(context, "Store Attached Successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
