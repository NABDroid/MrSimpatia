package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ernestyes.mrsimpatia.fiverr.Player;
import com.ernestyes.mrsimpatia.fiverr.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlayerHomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView onlinePlayers, emails, startGame, profile, redeem;
    private TextView point;
    private Player player;
    private int currentLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_home);
        init();
        Intent incomingIntent = getIntent();
        String uId = incomingIntent.getStringExtra("uId");
        getPlayerProfileFromDB(uId);

        onlinePlayers.setOnClickListener(this);
        emails.setOnClickListener(this);
        startGame.setOnClickListener(this);
        profile.setOnClickListener(this);
        redeem.setOnClickListener(this);
    }


    private void init() {
        onlinePlayers = findViewById(R.id.onlinePlayersImageViewPlayerHome);
        emails = findViewById(R.id.mailImageViewPlayerHome);
        startGame = findViewById(R.id.startGameImageViewPlayerHome);
        profile = findViewById(R.id.profileImageViewPlayerHome);
        redeem = findViewById(R.id.redeemImageViewPlayerHome);
        point = findViewById(R.id.pointsTextViewPlayerHome);
    }

    private void getPlayerProfileFromDB(String uId) {


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    player = dataSnapshot.getValue(Player.class);
                    processPlayerData();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void processPlayerData() {
        String pointStr = Integer.toString(player.getPoint());
        point.setText("Balance " + pointStr);
        currentLevel = player.getCompletedTask() + 1;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.onlinePlayersImageViewPlayerHome:
                Toast.makeText(this, "onlinePlayersImageViewPlayerHome", Toast.LENGTH_SHORT).show();
                break;

            case R.id.mailImageViewPlayerHome:
                Toast.makeText(this, "mailImageViewPlayerHome", Toast.LENGTH_SHORT).show();
                break;

            case R.id.startGameImageViewPlayerHome:
                Intent startGameIntent = new Intent(this, ArtActivity.class);
                startGameIntent.putExtra("currentLevel", currentLevel);
                startActivity(startGameIntent);
                break;

            case R.id.profileImageViewPlayerHome:
                Toast.makeText(this, "profileImageViewPlayerHome", Toast.LENGTH_SHORT).show();
                break;

            case R.id.redeemImageViewPlayerHome:
                Toast.makeText(this, "redeemImageViewPlayerHome", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
