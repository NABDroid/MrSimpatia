package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ernestyes.mrsimpatia.fiverr.R;
import com.ernestyes.mrsimpatia.fiverr.Store;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewStoreActivity extends AppCompatActivity {
    private ImageView logoIV;
    private TextView pickLogoTV;
    private EditText storeNameET, authorNameET, emailET, locationET, passwordET;
    private Button createAndStart;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_store);
        init();
        createAndStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createStore();
            }
        });


    }


    private void init() {
        logoIV = findViewById(R.id.logoNewStoreIV);
        pickLogoTV = findViewById(R.id.pickLogoNewStoreTV);
        storeNameET = findViewById(R.id.storeNameNewStoreET);
        authorNameET = findViewById(R.id.authorNameNewStoreET);
        emailET = findViewById(R.id.authorEmailNewStoreET);
        locationET = findViewById(R.id.storeLocationNewStoreET);
        passwordET = findViewById(R.id.passwordNewStoreET);
        createAndStart = findViewById(R.id.createNewStoreButton);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Root");
    }


    private void createStore() {
        final String storeName, authorName, email, location, password;
        storeName = storeNameET.getText().toString().trim();
        authorName = authorNameET.getText().toString().trim();
        email = emailET.getText().toString().trim();
        location = locationET.getText().toString().trim();
        password = passwordET.getText().toString().trim();


        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(NewStoreActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(NewStoreActivity.this, "", Toast.LENGTH_SHORT).show();
                    saveInfo(storeName, authorName, email, location);
                } else {
                    String error = task.getException().toString().trim();
                    Toast.makeText(NewStoreActivity.this, "Message: " + error + "", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private void saveInfo(String storeName, String authorName, String email, String location) {
        Store store = new Store(storeName, authorName, email, location);

        DatabaseReference storeRef = databaseReference.child("Stores").child(storeName);
        storeRef.setValue(store).addOnCompleteListener(NewStoreActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(NewStoreActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(NewStoreActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
}
