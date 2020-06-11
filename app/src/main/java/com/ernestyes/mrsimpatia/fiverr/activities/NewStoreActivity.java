package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ernestyes.mrsimpatia.fiverr.R;
import com.ernestyes.mrsimpatia.fiverr.pojo.Store;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewStoreActivity extends AppCompatActivity {
    private TextView loginTV;
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
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewStoreActivity.this, LoginActivity.class));
            }
        });


    }


    private void init() {
        loginTV = findViewById(R.id.loginTextViewNewStoreActivity);
        storeNameET = findViewById(R.id.storeNameEditTextNewStore);
        authorNameET = findViewById(R.id.authorNameEditTextNewStore);
        emailET = findViewById(R.id.emailEditTextNewStore);
        locationET = findViewById(R.id.locationEditTextNewStore);
        passwordET = findViewById(R.id.passwordEditTextNewStore);
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
        Store store = new Store(storeName, authorName, email, location, 0, 0);

        DatabaseReference storeRef = databaseReference.child("Stores").child(storeName);
        storeRef.setValue(store).addOnCompleteListener(NewStoreActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    writeInAllUserNode();
                } else {
                    Toast.makeText(NewStoreActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }


    private void writeInAllUserNode() {
        String uId = firebaseAuth.getUid();
        DatabaseReference allUsers = databaseReference.child("AllUsers").child(uId);
        allUsers.setValue("Store").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(NewStoreActivity.this, "successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NewStoreActivity.this, MainActivity.class));
            }
        });
    }




}
