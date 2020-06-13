package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ernestyes.mrsimpatia.fiverr.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private  FirebaseUser currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        choseUserMode();

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doWork();
//            }
//        });
//        thread.start();
    }


    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
    }

    private void choseUserMode() {

        if (currentUser != null){
            final String uId = firebaseAuth.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("AllUsers").child(uId);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String role = dataSnapshot.getValue().toString();
                        if (role.equals("Player")){
                            Intent intent = new Intent(SplashActivity.this, PlayerHomeActivity.class);
                            intent.putExtra("uId", uId);
                            startActivity(intent);

                        } else if (role.equals("Store")){

                            Intent intent = new Intent(SplashActivity.this, StoreHomeActivity.class);
                            intent.putExtra("uId", uId);
                            startActivity(intent);

                        } else {
                            Toast.makeText(SplashActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else{
            startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));

        }
    }


}
