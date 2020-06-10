package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ernestyes.mrsimpatia.fiverr.R;

public class WelcomeActivity extends AppCompatActivity {
    private Button playerButton, storeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        playerButton = findViewById(R.id.playerButtonWelcomeActivity);
        storeButton = findViewById(R.id.storeButtonWelcomeActivity);

        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        });


        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, NewStoreActivity.class));
            }
        });
    }
}
