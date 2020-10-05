package com.edairem.denemee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity3 extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;
    int score;
    int temp; //for random
    int speed;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    SharedPreferences sharedPreferences;
    int storedScore;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        speed = intent.getIntExtra("speed",0);
        //initialize
        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        sharedPreferences = getApplication().getSharedPreferences("com.edairem.denemee", Context.MODE_PRIVATE);
        score = 0;
        temp = -1;
        hideImages();

        new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time: " + l/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time off");
                handler.removeCallbacks(runnable);
                for(ImageView image: imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity3.this);
                alert.setTitle("Restart");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        save();
                        Intent intent1 = new Intent(getApplicationContext(),MainActivity2.class);
                        startActivity(intent1);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(MainActivity3.this,"Game Over!",Toast.LENGTH_LONG).show();
                        save();
                        Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent1);
                    }
                });
                alert.show();
            }
        }.start();

    }

    public void increaseScore(View view) {
        score++;
        scoreText.setText("Score: " + score);
    }

    public void save() {
        storedScore = sharedPreferences.getInt("storedScore", 0);
        if(storedScore <= score) {
            sharedPreferences.edit().putInt("storedScore", score).apply();
        }
    }

    public void hideImages() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for(ImageView image: imageArray){ //hide images
                    image.setVisibility(View.INVISIBLE);
                }

                Random rnd = new Random();
                int i = rnd.nextInt(9);
                while(i == temp){
                    i = rnd.nextInt(9);
                }
                temp = i;
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, speed);

            }
        };
        handler.post(runnable);
    }

}