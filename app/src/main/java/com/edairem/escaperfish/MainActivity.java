package com.edairem.escaperfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

// MAIN SCREEN
public class MainActivity extends AppCompatActivity {

    // Defining only preferences as global
    SharedPreferences sharedPreferences;
    private AdView mAdView;
    private int storedScore;
    private TextView highScoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing the mobil ads SDK
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        // Ad
        mAdView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Getting global preferences for previously saved high score
        sharedPreferences = getApplication().getSharedPreferences("com.arrow.escaperfish", Context.MODE_PRIVATE);
        storedScore = sharedPreferences.getInt("storedScore", 0);

        // Show high score
        highScoreText = findViewById(R.id.highScoreText);
        highScoreText.setText("High Score: " + storedScore);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Getting global preferences for previously saved high score
        sharedPreferences = getApplication().getSharedPreferences("com.arrow.escaperfish", Context.MODE_PRIVATE);
        storedScore = sharedPreferences.getInt("storedScore", 0);

        // Show high score
        highScoreText = findViewById(R.id.highScoreText);
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