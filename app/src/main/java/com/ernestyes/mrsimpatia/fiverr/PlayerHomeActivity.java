package com.ernestyes.mrsimpatia.fiverr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class PlayerHomeActivity extends AppCompatActivity {
    private TextView playerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_home);
        init();
    }

    private void init() {
        playerInfo = findViewById(R.id.playerInfoTV);
    }
}
