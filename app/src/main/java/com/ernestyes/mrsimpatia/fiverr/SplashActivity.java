package com.ernestyes.mrsimpatia.fiverr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
            }
        });
        thread.start();
    }


    private void doWork(){
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();






        for (int progress = 1; progress<=10; progress = progress+1){
            try {
                Thread.sleep(20);
                if (progress>=10){

                    if (currentUser != null){
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    } else{
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                    }

                }
            } catch (InterruptedException e) {
                if (currentUser != null){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else{
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                }
            }
        }
    }

}
