package com.edairem.escaperfish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

// HELP (HOW TO PLAY) SCREEN
public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    // onClick method to close help menu
    public void close(View view) { finish(); }
}