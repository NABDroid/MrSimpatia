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

import com.ernestyes.mrsimpatia.fiverr.pojo.Player;
import com.ernestyes.mrsimpatia.fiverr.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegActivity extends AppCompatActivity {
    private EditText firstNameET, lastNameET, emailET, passwordET, retypePasswordET;
    private Button registerButton;
    private TextView goForLoginTV;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        init();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        goForLoginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegActivity.this, LoginActivity.class));
            }
        });
    }


    private void init() {
        firstNameET = findViewById(R.id.firstNameEditTextRegActivity);
        lastNameET = findViewById(R.id.lastNameEditTextRegActivity);
        emailET = findViewById(R.id.emailEditTextRegActivity);
        passwordET = findViewById(R.id.passwordEditTextRegActivity);
        retypePasswordET = findViewById(R.id.retypePasswordEditTextRegActivity);
        registerButton = findViewById(R.id.registerButtonRegActivity);
        goForLoginTV = findViewById(R.id.goForLogin);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Root");
    }

    private void register() {
        String firstName, lastName, name, email, password, retypePassword;

        firstName = firstNameET.getText().toString().trim();
        lastName = lastNameET.getText().toString().trim();
        name = firstName+" "+lastName;
        email = emailET.getText().toString().trim().toLowerCase();
        password = passwordET.getText().toString().trim();
        retypePassword = retypePasswordET.getText().toString().trim();

        if (password.equals(retypePassword)) {
            createUser(name, email, password);
        } else {
            Toast.makeText(this, "Password Didn't matched", Toast.LENGTH_SHORT).show();
        }


    }


    private void createUser(final String name, final String email, final String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegActivity.this, "", Toast.LENGTH_SHORT).show();
                            saveInfo(name, email);
                        } else {
                            String error = task.getException().toString().trim();
                            Toast.makeText(RegActivity.this, "Message: " + error + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void saveInfo(String name, String email) {
        final String uId = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference playersRef = databaseReference.child("Players").child(uId);
        Player player = new Player(name, email, "No Store",0,0 );
        playersRef.setValue(player).addOnCompleteListener(RegActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    writeInAllUserNode(uId);
                } else {
                    Toast.makeText(RegActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void writeInAllUserNode(String uId) {
        DatabaseReference allUsers = databaseReference.child("AllUsers").child(uId);
        allUsers.setValue("Player").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                startActivity(new Intent(RegActivity.this, ChooseStoreActivity.class));
            }
        });
    }
}
