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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText emailET, passwordET;
    private Button loginBTN;
    private TextView goForSignUpTV;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        goForSignUpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
            }
        });
    }

    private void init() {
        emailET = findViewById(R.id.emailEditTextLoginActivity);
        passwordET = findViewById(R.id.passwordEditTextLoginActivity);
        loginBTN = findViewById(R.id.loginButtonLoginActivity);
        goForSignUpTV = findViewById(R.id.registrationTextViewLoginActivity);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void login() {
        String email, password;
        email = emailET.getText().toString().trim().toLowerCase();
        password = passwordET.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String uId = firebaseAuth.getUid();
                            choseUserMode(uId);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Email and password didn't matched", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    private void choseUserMode(String uId) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("AllUsers").child(uId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String role = dataSnapshot.getValue().toString();
                    if (role.equals("Player")){
                        startActivity(new Intent(LoginActivity.this, PlayerHomeActivity.class));
                    } else if (role.equals("Store")){
                        startActivity(new Intent(LoginActivity.this, StoreHomeActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }

}
