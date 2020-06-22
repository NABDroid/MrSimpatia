package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ernestyes.mrsimpatia.fiverr.Player;
import com.ernestyes.mrsimpatia.fiverr.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private TextView userNameTV, storeTV, emailTV, pointTV, levelTV;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        Intent intent = getIntent();
        String uId = intent.getStringExtra("uId");
        getDataFromDB(uId);
    }


    private void init() {
        userNameTV = findViewById(R.id.userNameTextViewProAct);
        storeTV = findViewById(R.id.storeTextViewProAct);
        emailTV = findViewById(R.id.emailTextViewProAct);
        pointTV = findViewById(R.id.pointsTextViewProAct);
        levelTV = findViewById(R.id.levelTextViewProAct);
    }

    private void getDataFromDB(String uId) {
        if(uId!=null){
            databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId);

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Player player = dataSnapshot.getValue(Player.class);
                    setDataIntoFields(player);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

    }

    private void setDataIntoFields(Player player) {
        int level = player.getCompletedTask()+1;
        userNameTV.setText(player.getName());
        storeTV.setText(player.getAssociatedStore());
        emailTV.setText(player.getEmail());
        pointTV.setText("Point "+player.getPoint());
        levelTV.setText("Level "+level);
    }
}
