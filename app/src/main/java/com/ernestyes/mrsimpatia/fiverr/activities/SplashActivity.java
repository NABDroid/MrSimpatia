package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
                           checkAssignedStore(uId);

                        } else {
                            Intent intent = new Intent(SplashActivity.this, StoreHomeActivity.class);
                            intent.putExtra("uId", uId);
                            intent.putExtra("role", role);
                            startActivity(intent);

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

    private void checkAssignedStore(final String uId) {
        DatabaseReference playersStoreRef = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId).child("associatedStore");

        playersStoreRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String storeName = dataSnapshot.getValue().toString();
                if (storeName.equals("No Store")){
                    Intent intent = new Intent(SplashActivity.this, ChooseStoreActivity.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(SplashActivity.this, PlayerHomeActivity.class);
                    intent.putExtra("uId", uId);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


}
