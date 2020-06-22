package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ernestyes.mrsimpatia.fiverr.R;

public class EmailActivity extends AppCompatActivity {
    private TextView acceptTV, declineTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        acceptTV = findViewById(R.id.acceptTV);
        declineTV = findViewById(R.id.declineTV);

        acceptTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmailActivity.this, ArtActivity.class));
            }
        });

        declineTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmailActivity.this, PlayerHomeActivity.class));
            }
        });
    }
}
