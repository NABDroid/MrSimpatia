package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ernestyes.mrsimpatia.fiverr.AlertReceiver;
import com.ernestyes.mrsimpatia.fiverr.Player;
import com.ernestyes.mrsimpatia.fiverr.R;
import com.ernestyes.mrsimpatia.fiverr.RedeemPointsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class PlayerHomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView onlinePlayersIV, emailsIV, startGameIV, profileIV, redeemIV;
    private TextView pointTV;
    private Player player;
    private int currentLevel, point;
    private String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_home);
        init();
        setReminderForNextTask();
        Intent incomingIntent = getIntent();
        uId = incomingIntent.getStringExtra("uId");
        getPlayerProfileFromDB(uId);

        onlinePlayersIV.setOnClickListener(this);
        emailsIV.setOnClickListener(this);
        startGameIV.setOnClickListener(this);
        profileIV.setOnClickListener(this);
        redeemIV.setOnClickListener(this);
    }


    private void init() {
        onlinePlayersIV = findViewById(R.id.onlinePlayersImageViewPlayerHome);
        emailsIV = findViewById(R.id.mailImageViewPlayerHome);
        startGameIV = findViewById(R.id.startGameImageViewPlayerHome);
        profileIV = findViewById(R.id.profileImageViewPlayerHome);
        redeemIV = findViewById(R.id.redeemImageViewPlayerHome);
        pointTV = findViewById(R.id.pointsTextViewPlayerHome);
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
        point = player.getPoint();
        String pointStr = Integer.toString(point);
        pointTV.setText("Balance " + pointStr);
        currentLevel = player.getCompletedTask() + 1;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.onlinePlayersImageViewPlayerHome:
                Toast.makeText(this, "onlinePlayersImageViewPlayerHome", Toast.LENGTH_SHORT).show();
                break;

            case R.id.mailImageViewPlayerHome:
                startActivity(new Intent(this, EmailActivity.class));
                break;

            case R.id.startGameImageViewPlayerHome:
                Intent startGameIntent = new Intent(this, ArtActivity.class);
                startGameIntent.putExtra("currentLevel", currentLevel);
                startGameIntent.putExtra("uId", uId);
                startActivity(startGameIntent);
                break;

            case R.id.profileImageViewPlayerHome:
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("uId", uId);
                startActivity(intent);
                Toast.makeText(this, "profileImageViewPlayerHome", Toast.LENGTH_SHORT).show();
                break;

            case R.id.redeemImageViewPlayerHome:
                Intent redeemIntent = new Intent(this, RedeemPointsActivity.class);
                redeemIntent.putExtra("uId", uId);
                startActivity(redeemIntent);
                Toast.makeText(this, "redeemImageViewPlayerHome", Toast.LENGTH_SHORT).show();
                break;
        }

    }




    private void setReminderForNextTask() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        Long timeMS = date.getTime();

        calendar.setTimeInMillis(timeMS);



        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent morningIntent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, morningIntent, 0);
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }


        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_HOUR, pendingIntent);

    }
}
