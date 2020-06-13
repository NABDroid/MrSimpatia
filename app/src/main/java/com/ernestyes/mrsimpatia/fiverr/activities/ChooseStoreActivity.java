package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ernestyes.mrsimpatia.fiverr.R;
import com.ernestyes.mrsimpatia.fiverr.adapters.ShopListAdapter;
import com.ernestyes.mrsimpatia.fiverr.Store;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChooseStoreActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private RecyclerView shopsRecyclerView;
    private ShopListAdapter shopListAdapter;
    private ArrayList<Store> stores;
    private FloatingActionButton fabAdd;
    private String storeName = "NoStore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_store);
        init();
        initRecyclerView();
        loadDataIntoRecyclerview();
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storeName !="NoStore"){

                } else {
                    Toast.makeText(ChooseStoreActivity.this, "Select a store", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }



    private void init() {
        fabAdd = findViewById(R.id.addFab);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Root");
        shopsRecyclerView = findViewById(R.id.shopListRecyclerView);
        stores = new ArrayList<>();
        shopListAdapter = new ShopListAdapter(stores, this, firebaseAuth, databaseReference);
    }

    private void initRecyclerView() {
        shopsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        shopsRecyclerView.setAdapter(shopListAdapter);
    }

    private void loadDataIntoRecyclerview() {
        DatabaseReference storeRef = databaseReference.child("Stores");
        storeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                stores.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Store store = data.getValue(Store.class);
                        stores.add(store);
                    }

                    shopListAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void setSelectedStore(String storeName) {
        this.storeName = storeName;

    }

}
