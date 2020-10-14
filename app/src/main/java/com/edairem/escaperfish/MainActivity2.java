package com.edairem.escaperfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

// DIFFICULTY CHOOSING SCREEN
public class MainActivity2 extends AppCompatActivity {

    Intent intent;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initializing the mobil ads SDK
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        // Ad
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    /*      onClick Methods for Difficulty Levels    */

    // The "speed" value of each option is used by Handler as delay, in the next activity
    // It's used for appearing speeds of objects on screen, differs by difficulty of game
    public void option_easy(View view) {
        intent = new Intent(getApplicationContext(),MainActivity3.class);
        intent.putExtra("speed",1000);
        startActivity(intent);
        finish();
    }

    public void option_medium(View view) {
        intent = new Intent(getApplicationContext(),MainActivity3.class);
        intent.putExtra("speed",800);
        startActivity(intent);
        finish();
    }

    public void option_hard(View view) {
        intent = new Intent(getApplicationContext(),MainActivity3.class);
        intent.putExtra("speed",600);
        startActivity(intent);
        finish();
    }

}