package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ernestyes.mrsimpatia.fiverr.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResultActivity extends AppCompatActivity {
    private DatabaseReference pointRef, levelRef;
    private String uId;
    private int result;
    private ImageView imageView;
    private TextView waitingForResultTextView;
    private Button continueButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        uId = intent.getStringExtra("uId");
        result = intent.getIntExtra("resultMessage", 0);
        init();



        if (result>=80){
            calculateLevel();
        } else{
            waitingForResultTextView.setVisibility(View.GONE);
            imageView.setImageResource(R.drawable.looser);
            continueButton.setVisibility(View.VISIBLE);
        }

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, PlayerHomeActivity.class);
                intent.putExtra("uId", uId);
                startActivity(intent);
            }
        });




    }




    private void init() {
        pointRef = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId).child("point");
        levelRef = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId).child("completedTask");
        imageView = findViewById(R.id.resultImageView);
        continueButton = findViewById(R.id.continueToGameButtonResultActivity);
        waitingForResultTextView = findViewById(R.id.waitingForResultTextView);
    }

    private void calculateLevel() {
        levelRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int level = Integer.parseInt(dataSnapshot.getValue().toString());
                level++;
                calculatePoint(level);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void calculatePoint(final int level) {
        pointRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int previousPoint = Integer.parseInt(dataSnapshot.getValue().toString());
                result = result*10 + previousPoint;
                updateLevelAndPoint(level, result);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateLevelAndPoint(int level, final int result) {
        levelRef.setValue(level).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pointRef.setValue(result).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        waitingForResultTextView.setVisibility(View.GONE);
                        imageView.setImageResource(R.drawable.winner);
                        continueButton.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }


}
