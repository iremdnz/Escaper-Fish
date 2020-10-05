package com.edairem.denemee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int storedScore;
    TextView highScoreText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        highScoreText = findViewById(R.id.highScoreText);
        sharedPreferences = getApplication().getSharedPreferences("com.edairem.denemee", Context.MODE_PRIVATE);//minik bir veritabanı veren obje
        storedScore = sharedPreferences.getInt("storedScore", 0);
        highScoreText.setText("High Score: " + storedScore);
    }

    public void startGame(View view) { //button's onClick method
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }

    public void exitGame(View view) {
        finishAffinity();
    }

}