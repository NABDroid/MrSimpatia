package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ernestyes.mrsimpatia.fiverr.R;
import com.ernestyes.mrsimpatia.fiverr.ResultActivity;

public class ArtActivity extends AppCompatActivity implements View.OnClickListener {
    private PaintView paintView;
    private int defaultColor;
    private Button blackButton, redButton, greenButton, blueButton, submitButton;
    private ImageView undoIV, redoIV, fatLineIV, thinLineIV, demoImage;
    int currentLevel = 49;
    private Bitmap refBitmap;
    private String uId;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art);
        init();

        Intent artIntent = getIntent();
        currentLevel = artIntent.getIntExtra("currentLevel", 50);
        uId = artIntent.getStringExtra("uId");
        Toast.makeText(this, "currentLevel " + currentLevel, Toast.LENGTH_SHORT).show();

        chooseTask();

        paintView.initialise(refBitmap);
        paintView.setStrokeWidth(10);
        blackButton.setOnClickListener(this);
        redButton.setOnClickListener(this);
        blueButton.setOnClickListener(this);
        greenButton.setOnClickListener(this);
        redoIV.setOnClickListener(this);
        undoIV.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        fatLineIV.setOnClickListener(this);
        thinLineIV.setOnClickListener(this);
    }


    private void init() {
        demoImage = findViewById(R.id.showDemo);
        paintView = findViewById(R.id.paintView);
        blackButton = findViewById(R.id.black_color);
        redButton = findViewById(R.id.red_color);
        greenButton = findViewById(R.id.green_color);
        blueButton = findViewById(R.id.blue_color);
        fatLineIV = findViewById(R.id.fatLineIV);
        thinLineIV = findViewById(R.id.thinLineIV);
        undoIV = findViewById(R.id.undoIV);
        redoIV = findViewById(R.id.redoIV);
        submitButton = findViewById(R.id.submitButton);
    }

    private void chooseTask() {
        switch (currentLevel) {
            case 1:
                refBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_1);
                refBitmap = Bitmap.createScaledBitmap(refBitmap, 512, 512, false);
                demoImage.setImageResource(R.drawable.level_1);
                break;

            case 2:
                refBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_2);
                refBitmap = Bitmap.createScaledBitmap(refBitmap, 512, 512, false);
                demoImage.setImageResource(R.drawable.level_2);
                break;

            case 3:
                refBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_3);
                refBitmap = Bitmap.createScaledBitmap(refBitmap, 512, 512, false);
                demoImage.setImageResource(R.drawable.level_3);
                break;

            case 4:
                refBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_4);
                refBitmap = Bitmap.createScaledBitmap(refBitmap, 512, 512, false);
                demoImage.setImageResource(R.drawable.level_4);
                break;

            case 5:
                refBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_5);
                refBitmap = Bitmap.createScaledBitmap(refBitmap, 512, 512, false);
                demoImage.setImageResource(R.drawable.level_5);
                break;

            case 6:
                refBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_6);
                refBitmap = Bitmap.createScaledBitmap(refBitmap, 512, 512, false);
                demoImage.setImageResource(R.drawable.level_6);
                break;

            case 7:
                refBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_7);
                refBitmap = Bitmap.createScaledBitmap(refBitmap, 512, 512, false);
                demoImage.setImageResource(R.drawable.level_7);
                break;

            case 8:
                refBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_8);
                refBitmap = Bitmap.createScaledBitmap(refBitmap, 512, 512, false);
                demoImage.setImageResource(R.drawable.level_8);
                break;

            case 9:
                refBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_9);
                refBitmap = Bitmap.createScaledBitmap(refBitmap, 512, 512, false);
                demoImage.setImageResource(R.drawable.level_9);
                break;

            case 10:
                refBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_10);
                refBitmap = Bitmap.createScaledBitmap(refBitmap, 512, 512, false);
                demoImage.setImageResource(R.drawable.level_10);
                break;

            case 11:
                refBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_11);
                refBitmap = Bitmap.createScaledBitmap(refBitmap, 512, 512, false);
                demoImage.setImageResource(R.drawable.level_11);
                break;

            case 12:
                refBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_12);
                refBitmap = Bitmap.createScaledBitmap(refBitmap, 512, 512, false);
                demoImage.setImageResource(R.drawable.level_12);
                break;

            default:
                refBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level_13);
                refBitmap = Bitmap.createScaledBitmap(refBitmap, 512, 512, false);
                demoImage.setImageResource(R.drawable.level_13);
                Toast.makeText(this, "Completed", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.black_color:
                defaultColor = Color.BLACK;
                paintView.setColor(defaultColor);
                break;
            case R.id.red_color:
                defaultColor = Color.rgb(214, 42, 42);
                paintView.setColor(defaultColor);
                break;

            case R.id.green_color:
                defaultColor = Color.rgb(76, 175, 80);
                paintView.setColor(defaultColor);
                break;

            case R.id.blue_color:
                defaultColor = Color.rgb(63, 81, 181);
                paintView.setColor(defaultColor);
                break;

            case R.id.undoIV:
                paintView.undo();
                break;

            case R.id.redoIV:
                paintView.redo();
                break;

            case R.id.submitButton:
                checkResult();
                break;

            case R.id.fatLineIV:
                paintView.setStrokeWidth(40);
                break;

            case R.id.thinLineIV:
                paintView.setStrokeWidth(10);
                break;
        }

    }

    private void checkResult() {
        int result = paintView.getResult();
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("resultMessage", result);
        intent.putExtra("uId", uId);
        startActivity(intent);
    }
}
