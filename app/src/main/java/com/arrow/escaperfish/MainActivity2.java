package com.arrow.escaperfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// DIFFICULTY CHOOSING SCREEN
public class MainActivity2 extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
        intent.putExtra("speed",500);
        startActivity(intent);
        finish();
    }

}