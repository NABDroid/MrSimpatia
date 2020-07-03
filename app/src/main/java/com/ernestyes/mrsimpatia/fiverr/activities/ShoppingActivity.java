package com.ernestyes.mrsimpatia.fiverr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ernestyes.mrsimpatia.fiverr.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShoppingActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout machineOne, machineTwo, machineThree, machineFour, machineFive, colorOne, colorTwo, colorThree, colorFour, colorFive;
    private ImageView previewIV;
    private TextView confirmTV, priceTV, equipTV, machine1ProiceTV, machine2ProiceTV, machine3ProiceTV, machine4ProiceTV,
            machine5ProiceTV, color1PriceTV, color2PriceTV, color3PriceTV, color4PriceTV, color5PriceTV;

    private int selectedProduct, points;
    private String uId;
    private DatabaseReference databaseReference;
    private boolean machine1, machine2, machine3, machine4, machine5, color1, color2, color3, color4, color5, purchedBefore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        getIntentExtras();
        init();
        determineProductsState();
        machineOne.setOnClickListener(this);
        machineTwo.setOnClickListener(this);
        machineThree.setOnClickListener(this);
        machineFour.setOnClickListener(this);
        machineFive.setOnClickListener(this);
        colorOne.setOnClickListener(this);
        colorTwo.setOnClickListener(this);
        colorThree.setOnClickListener(this);
        colorFour.setOnClickListener(this);
        colorFive.setOnClickListener(this);
        confirmTV.setOnClickListener(this);
        equipTV.setOnClickListener(this);
    }


    private void getIntentExtras() {
        Intent intent = getIntent();
        uId = intent.getStringExtra("uId");
        points = intent.getIntExtra("points", 0);
        machine1 = intent.getBooleanExtra("machine1", false);
        machine2 = intent.getBooleanExtra("machine2", false);
        machine3 = intent.getBooleanExtra("machine3", false);
        machine4 = intent.getBooleanExtra("machine4", false);
        machine5 = intent.getBooleanExtra("machine5", false);
        color1 = intent.getBooleanExtra("color1", false);
        color2 = intent.getBooleanExtra("color2", false);
        color3 = intent.getBooleanExtra("color3", false);
        color4 = intent.getBooleanExtra("color4", false);
        color5 = intent.getBooleanExtra("color5", false);
    }

    private void init() {
        machineOne = findViewById(R.id.machine1);
        machineTwo = findViewById(R.id.machine2);
        machineThree = findViewById(R.id.machine3);
        machineFour = findViewById(R.id.machine4);
        machineFive = findViewById(R.id.machine5);
        colorOne = findViewById(R.id.color1);
        colorTwo = findViewById(R.id.color2);
        colorThree = findViewById(R.id.color3);
        colorFour = findViewById(R.id.color4);
        colorFive = findViewById(R.id.color5);
        previewIV = findViewById(R.id.previewIV);
        confirmTV = findViewById(R.id.confirmTV);
        equipTV = findViewById(R.id.equipThis);
        priceTV = findViewById(R.id.priceTV);
        machine1ProiceTV = findViewById(R.id.machine1PriceTV);
        machine2ProiceTV = findViewById(R.id.machine2PriceTV);
        machine3ProiceTV = findViewById(R.id.machine3PriceTV);
        machine4ProiceTV = findViewById(R.id.machine4PriceTV);
        machine5ProiceTV = findViewById(R.id.machine5PriceTV);
        color1PriceTV = findViewById(R.id.color1PriceTV);
        color2PriceTV = findViewById(R.id.color2PriceTV);
        color3PriceTV = findViewById(R.id.color3PriceTV);
        color4PriceTV = findViewById(R.id.color4PriceTV);
        color5PriceTV = findViewById(R.id.color5PriceTV);
    }

    private void determineProductsState() {
        if (machine1) {
            machine1ProiceTV.setText("purchased");
        }
        if (machine2) {
            machine2ProiceTV.setText("purchased");
        }
        if (machine3) {
            machine3ProiceTV.setText("purchased");
        }
        if (machine4) {
            machine4ProiceTV.setText("purchased");
        }
        if (machine5) {
            machine5ProiceTV.setText("purchased");
        }
        if (color1) {
            color1PriceTV.setText("purchased");
        }
        if (color2) {
            color2PriceTV.setText("purchased");
        }
        if (color3) {
            color3PriceTV.setText("purchased");
        }
        if (color4) {
            color4PriceTV.setText("purchased");
        }
        if (color5) {
            color5PriceTV.setText("purchased");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.machine1:
                previewIV.setImageResource(R.drawable.cursor);
                priceTV.setText("5000/" + points);
                selectedProduct = 1;
                purchedBefore = machine1;
                if(machine1){
                    confirmTV.setVisibility(View.GONE);
                    equipTV.setVisibility(View.VISIBLE);
                } else {
                    confirmTV.setVisibility(View.VISIBLE);
                    equipTV.setVisibility(View.GONE);
                }
                break;

            case R.id.machine2:
                previewIV.setImageResource(R.drawable.machine_5);
                priceTV.setText("10000/" + points);
                selectedProduct = 2;
                purchedBefore = machine2;
                if(machine2){
                    confirmTV.setVisibility(View.GONE);
                    equipTV.setVisibility(View.VISIBLE);
                } else {
                    confirmTV.setVisibility(View.VISIBLE);
                    equipTV.setVisibility(View.GONE);
                }
                break;

            case R.id.machine3:
                previewIV.setImageResource(R.drawable.machine_2);
                priceTV.setText("20000/" + points);
                selectedProduct = 3;
                purchedBefore = machine3;
                if(machine3){
                    confirmTV.setVisibility(View.GONE);
                    equipTV.setVisibility(View.VISIBLE);
                } else {
                    confirmTV.setVisibility(View.VISIBLE);
                    equipTV.setVisibility(View.GONE);
                }
                break;

            case R.id.machine4:
                previewIV.setImageResource(R.drawable.machine_3);
                priceTV.setText("30000/" + points);
                selectedProduct = 4;
                purchedBefore = machine4;
                if(machine4){
                    confirmTV.setVisibility(View.GONE);
                    equipTV.setVisibility(View.VISIBLE);
                } else {
                    confirmTV.setVisibility(View.VISIBLE);
                    equipTV.setVisibility(View.GONE);
                }
                break;

            case R.id.machine5:
                previewIV.setImageResource(R.drawable.machine_4);
                priceTV.setText("40000/" + points);
                selectedProduct = 5;
                purchedBefore = machine5;
                if(machine5){
                    confirmTV.setVisibility(View.GONE);
                    equipTV.setVisibility(View.VISIBLE);
                } else {
                    confirmTV.setVisibility(View.VISIBLE);
                    equipTV.setVisibility(View.GONE);
                }
                break;

            case R.id.color1:
                previewIV.setImageResource(R.drawable.bottle_pink);
                priceTV.setText("5000/" + points);
                selectedProduct = 6;
                purchedBefore = color1;
                if(color1){
                    confirmTV.setVisibility(View.GONE);
                    equipTV.setVisibility(View.VISIBLE);
                } else {
                    confirmTV.setVisibility(View.VISIBLE);
                    equipTV.setVisibility(View.GONE);
                }
                break;

            case R.id.color2:
                previewIV.setImageResource(R.drawable.bottle_red);
                priceTV.setText("10000/" + points);
                selectedProduct = 7;
                purchedBefore = color2;
                if(color2){
                    confirmTV.setVisibility(View.GONE);
                    equipTV.setVisibility(View.VISIBLE);
                } else {
                    confirmTV.setVisibility(View.VISIBLE);
                    equipTV.setVisibility(View.GONE);
                }
                break;

            case R.id.color3:
                previewIV.setImageResource(R.drawable.bottle_pink);
                priceTV.setText("20000/" + points);
                selectedProduct = 8;
                purchedBefore = color3;
                if(color3){
                    confirmTV.setVisibility(View.GONE);
                    equipTV.setVisibility(View.VISIBLE);
                } else {
                    confirmTV.setVisibility(View.VISIBLE);
                    equipTV.setVisibility(View.GONE);
                }
                break;

            case R.id.color4:
                previewIV.setImageResource(R.drawable.bottle_red);
                priceTV.setText("30000/" + points);
                selectedProduct = 9;
                purchedBefore = color4;
                if(color4){
                    confirmTV.setVisibility(View.GONE);
                    equipTV.setVisibility(View.VISIBLE);
                } else {
                    confirmTV.setVisibility(View.VISIBLE);
                    equipTV.setVisibility(View.GONE);
                }
                break;

            case R.id.color5:
                previewIV.setImageResource(R.drawable.bottle_pink);
                priceTV.setText("40000/" + points);
                selectedProduct = 10;
                purchedBefore = color5;
                if(color5){
                    confirmTV.setVisibility(View.GONE);
                    equipTV.setVisibility(View.VISIBLE);
                } else {
                    confirmTV.setVisibility(View.VISIBLE);
                    equipTV.setVisibility(View.GONE);
                }
                break;

            case R.id.confirmTV:
                Toast.makeText(this, "Please Install the buying mechanism", Toast.LENGTH_SHORT).show();
                switch (selectedProduct) {
                    case 1:
                        databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId).child("machine1");
                        databaseReference.setValue(true);
                        break;
                    case 2:
                        databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId).child("machine2");
                        databaseReference.setValue(true);
                        break;
                    case 3:
                        databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId).child("machine3");
                        databaseReference.setValue(true);
                        break;
                    case 4:
                        databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId).child("machine4");
                        databaseReference.setValue(true);
                        break;
                    case 5:
                        databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId).child("machine5");
                        databaseReference.setValue(true);
                        saveSelectedElement(selectedProduct);
                        break;
                    case 6:
                        databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId).child("color1");
                        databaseReference.setValue(true);
                        break;
                    case 7:
                        databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId).child("color2");
                        databaseReference.setValue(true);
                        break;
                    case 8:
                        databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId).child("color3");
                        databaseReference.setValue(true);
                        break;
                    case 9:
                        databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId).child("color4");
                        databaseReference.setValue(true);
                        break;
                    case 10:
                        databaseReference = FirebaseDatabase.getInstance().getReference("Root").child("Players").child(uId).child("color5");
                        databaseReference.setValue(true);
                        break;
                    default:
                        confirmTV.setText("ChooseOne");
                }
                break;

            case R.id.equipThis:
                saveSelectedElement(selectedProduct);
                Toast.makeText(this, "Equiped", Toast.LENGTH_SHORT).show();


        }

    }

    private void saveSelectedElement(int selectedElementId){
        SharedPreferences sharedPreferences = getSharedPreferences("cursorPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("cursorId", selectedElementId);
        editor.commit();
    }
}
