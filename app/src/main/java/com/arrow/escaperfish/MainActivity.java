package com.arrow.escaperfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// MAIN SCREEN
public class MainActivity extends AppCompatActivity {

    // Defining only preferences as global
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting a global preferences for a previously saved score
        sharedPreferences = getApplication().getSharedPreferences("com.arrow.escaperfish", Context.MODE_PRIVATE);//minik bir veritabanı veren obje
        int storedScore = sharedPreferences.getInt("storedScore", 0);

        // Show high score
        TextView highScoreText = findViewById(R.id.highScoreText);
        highScoreText.setText("High Score: " + storedScore);
    }

    // onClick method for Start button
    public void startGame(View view) {
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }

    // onClick method for Exit button
    public void exitGame(View view) {
        finishAffinity();
    }

    // onClick method for Help (a.k.a. How to Play) button
    public void help(View view) {
        Intent intent = new Intent(MainActivity.this,MainActivity4.class);
        startActivity(intent);
    }
}