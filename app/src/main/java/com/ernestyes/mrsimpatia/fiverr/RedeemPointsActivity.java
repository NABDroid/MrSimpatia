package com.ernestyes.mrsimpatia.fiverr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RedeemPointsActivity extends AppCompatActivity {
    private TextView storeNameTV, storeAuthorNameTV, storeLocationTV, storeEmailTV, userNameTV, pointsTV, redeemInstructionTV;
    private Button redeemButton;
    private String uId;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_points);
        Intent intent = getIntent();
        uId = intent.getStringExtra("uId");
        init();
        getPlayerInfo();
        redeemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RedeemPointsActivity.this, "Redeem", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void init() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Root");
        storeNameTV = findViewById(R.id.storeNameTVRedeemActivity);
        storeAuthorNameTV = findViewById(R.id.authoreNameTVRedeemActivity);
        storeLocationTV = findViewById(R.id.locationTVRedeemActivity);
        storeEmailTV = findViewById(R.id.storeEmailTVRedeemActivity);
        userNameTV = findViewById(R.id.userNameTVRedeemActivity);
        pointsTV = findViewById(R.id.totalPointsTVRedeemActivity);
        redeemInstructionTV = findViewById(R.id.redeemInstructionTVRedeemActivity);
        redeemButton = findViewById(R.id.redeemButton);
    }


    private void getPlayerInfo() {
        DatabaseReference playersRef = databaseReference.child("Players").child(uId);
        playersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Player player = dataSnapshot.getValue(Player.class);
                    Toast.makeText(RedeemPointsActivity.this, "getPlayerInfo", Toast.LENGTH_SHORT).show();
                    getStoreInfo(player);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getStoreInfo(final Player player) {
        String associatedStore = player.getAssociatedStore();

        DatabaseReference storeRef = databaseReference.child("Stores").child(associatedStore);
        storeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Store store = dataSnapshot.getValue(Store.class);
                    processData(player, store);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    private void processData(Player player, Store store) {
        String storeName, storeAuthorName, storeLocation, storeEmail, userName, redeemInstruction;
        int points;

        storeName = store.getStoreName();
        storeAuthorName = store.getAuthorName();
        storeLocation = store.getLocation();
        storeEmail = store.getEmail();

        userName = player.getName();
        points = player.getPoint();

        storeNameTV.setText(storeName);
        storeAuthorNameTV.setText(storeAuthorName);
        storeLocationTV.setText(storeLocation);
        storeEmailTV.setText(storeEmail);
        userNameTV.setText(userName);



        redeemInstructionTV.setText(storeName);




    }
}
