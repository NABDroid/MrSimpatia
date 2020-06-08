package com.ernestyes.mrsimpatia.fiverr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseStoreActivity extends AppCompatActivity {
    private LinearLayout simpatiaLL, nabdRoidLL;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_store);
        init();
        simpatiaLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachStore(1);
            }
        });

        nabdRoidLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachStore(2);
            }
        });


    }

    private void init() {
        simpatiaLL = findViewById(R.id.simpatiaLL);
        nabdRoidLL = findViewById(R.id.nabdRoidLL);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Root");
    }


    private void attachStore(int storeId) {
        String storeName;
        switch (storeId){
            case 1:
                storeName = "Simpatia";
                break;
            case 2:
                storeName = "NABDroid";
                break;
            default:
                storeName = "No";


        }








        String uId = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference playersRef = databaseReference.child("Players").child(uId).child("store");
        playersRef.setValue(storeName).addOnCompleteListener(ChooseStoreActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(ChooseStoreActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(ChooseStoreActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

}
