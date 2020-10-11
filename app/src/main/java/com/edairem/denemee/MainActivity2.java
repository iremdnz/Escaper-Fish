package com.edairem.denemee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Full screen
        /*this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);*/
        //getSupportActionBar().hide();
    }

    public void option1(View view) {
        intent = new Intent(getApplicationContext(),MainActivity3.class);
        intent.putExtra("speed",1000);
        startActivity(intent);
        finish();
    }

    public void option2(View view) {
        intent = new Intent(getApplicationContext(),MainActivity3.class);
        intent.putExtra("speed",800);
        startActivity(intent);
        finish();
    }

    public void option3(View view) {
        intent = new Intent(getApplicationContext(),MainActivity3.class);
        intent.putExtra("speed",500);
        startActivity(intent);
        finish();
    }

}