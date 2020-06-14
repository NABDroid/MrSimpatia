package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ernestyes.mrsimpatia.fiverr.R;
import com.ernestyes.mrsimpatia.fiverr.Store;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreHomeActivity extends AppCompatActivity {
    private String uId, storeName;
    private TextView storeNameTV, emailTV, authorTV, locationTV, totalPlayerTV;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_home);
        Intent intent = getIntent();
        uId = intent.getStringExtra("uId");
        storeName = intent.getStringExtra("role");
        init();
        getDataFromDB();
    }

    private void init() {
        storeNameTV = findViewById(R.id.storeNameTextViewStoreHome);
        emailTV = findViewById(R.id.emailTextViewStoreHome);
        authorTV = findViewById(R.id.authorNameTextViewStoreHome);
        locationTV = findViewById(R.id.locationTextViewStoreHome);
        totalPlayerTV = findViewById(R.id.totalPlayerTextViewStoreHome);
        databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("Stores").child(storeName);
    }

    private void getDataFromDB() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Store store = dataSnapshot.getValue(Store.class);
                showData(store);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showData(Store store) {

        if (store != null) {
            String storeName, email, author, location, totalplayer;
            storeName = store.getStoreName();
            email = store.getEmail();
            author = store.getAuthorName();
            location = store.getLocation();
            totalplayer = "Total Player: " + store.getTotalPlayers();

            storeNameTV.setText(storeName);
            authorTV.setText(author);
            locationTV.setText(location);
            totalPlayerTV.setText(totalplayer);
            emailTV.setText(email);

        }
    }
}
