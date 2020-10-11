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

    CountDownTimer yourCountDownTimer;
    TextView timeText;
    TextView scoreText;
    int score;
    int temp, temp2; //for random
    int speed;
    int bombCount;
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
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView imageView17;
    ImageView imageView18;
    ImageView[] bombArray;
    ImageView heart1;
    ImageView heart2;
    ImageView heart3;
    SharedPreferences sharedPreferences;
    int storedScore;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //Full screen
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        //getSupportActionBar().hide();
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
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView15 = findViewById(R.id.imageView15);
        imageView16 = findViewById(R.id.imageView16);
        imageView17 = findViewById(R.id.imageView17);
        imageView18 = findViewById(R.id.imageView18);
        bombArray = new ImageView[] {imageView10,imageView12,imageView11,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18};
        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);
        sharedPreferences = getApplication().getSharedPreferences("com.edairem.denemee", Context.MODE_PRIVATE);
        score = 0;
        temp = -1;
        temp2 = -1;
        bombCount = 3;
        hideImages();

        yourCountDownTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                timeText.setText(":" + l/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time off");
                handler.removeCallbacks(runnable);
                for(ImageView image: imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                for(ImageView image: bombArray){ //hide bombs
                    image.setVisibility(View.INVISIBLE);
                }
                gameOver();
            }
        }.start();

    }

    public void increaseScore(View view) {
        score++;
        scoreText.setText(":" + score);
    }

    public void liveAmount(View view) {
        bombCount--;
        if(bombCount == 2) {
            heart3.setVisibility(View.INVISIBLE);
        }
        else if(bombCount == 1) {
            heart2.setVisibility(View.INVISIBLE);
        }
        else { //bombCount == 0
            heart1.setVisibility(View.INVISIBLE);
            yourCountDownTimer.cancel();
            handler.removeCallbacks(runnable);
            for(ImageView image: imageArray){ //hide fishs
                image.setVisibility(View.INVISIBLE);
            }
            for(ImageView image: bombArray){ //hide bombs
                image.setVisibility(View.INVISIBLE);
            }
            gameOver();
            Toast.makeText(MainActivity3.this,"Game Over!",Toast.LENGTH_LONG).show();
        }
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

                for(ImageView image: imageArray){ //hide fishs
                    image.setVisibility(View.INVISIBLE);
                }

                for(ImageView image: bombArray){ //hide bombs
                    image.setVisibility(View.INVISIBLE);
                }

                Random rnd = new Random();
                int choice = rnd.nextInt(100);

                if(choice >= 33) {
                    int fish = rnd.nextInt(9);
                    while(fish == temp){
                        fish = rnd.nextInt(9);
                    }
                    temp = fish;
                    imageArray[fish].setVisibility(View.VISIBLE);
                }
                else {
                    int bomb = rnd.nextInt(9);
                    while(bomb == temp2){
                        bomb = rnd.nextInt(9);
                    }
                    temp2 = bomb;
                    bombArray[bomb].setVisibility(View.VISIBLE);
                }
                handler.postDelayed(this, speed);

            }
        };
        handler.post(runnable);
    }

    public void gameOver() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity3.this);
        alert.setCancelable(false);
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
                save();
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);
            }
        });
        alert.show();
    }

}