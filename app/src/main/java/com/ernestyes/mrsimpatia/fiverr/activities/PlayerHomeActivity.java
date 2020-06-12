package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ernestyes.mrsimpatia.fiverr.R;

public class PlayerHomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView onlinePlayers, emails, startGame, profile, redeem;
    private TextView point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_home);
        init();
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
                startActivity(new Intent(this, ArtActivity.class));
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
