package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ernestyes.mrsimpatia.fiverr.AdaptarForOnlinePlayers;
import com.ernestyes.mrsimpatia.fiverr.OnlineStatus;
import com.ernestyes.mrsimpatia.fiverr.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DualModeActivity extends AppCompatActivity {
    private Button soloButton, dualButton;
    private int currentLevel;
    private String uId;
    private DatabaseReference databaseReference;
    private ArrayList<OnlineStatus> onlineStatusArrayList;
    private AdaptarForOnlinePlayers adaptarForOnlinePlayers;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_mode);
        init();
        Intent artIntent = getIntent();
        currentLevel = artIntent.getIntExtra("currentLevel", 50);
        uId = artIntent.getStringExtra("uId");

        soloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startGameIntent = new Intent(DualModeActivity.this, ArtActivity.class);
                startGameIntent.putExtra("currentLevel", currentLevel);
                startGameIntent.putExtra("uId", uId);
                startActivity(startGameIntent);
            }
        });

        dualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findOnlinePlayers();
            }
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.onlineRecyclerView);
        soloButton = findViewById(R.id.soloButton);
        dualButton = findViewById(R.id.dualButton);
        databaseReference = FirebaseDatabase.getInstance().getReference("Root");
        onlineStatusArrayList = new ArrayList<>();
        adaptarForOnlinePlayers = new AdaptarForOnlinePlayers(onlineStatusArrayList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptarForOnlinePlayers);

    }


    private void findOnlinePlayers() {
        final DatabaseReference onlinePlayers = databaseReference.child("Online");
        onlinePlayers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(DualModeActivity.this, "OnlineUser Available", Toast.LENGTH_SHORT).show();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                         OnlineStatus onlineStatus = data.getValue(OnlineStatus.class);
                         if (onlineStatus.isOnlineStatus()){
                             onlineStatusArrayList.add(onlineStatus);
                         }
                    }
                    adaptarForOnlinePlayers.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
