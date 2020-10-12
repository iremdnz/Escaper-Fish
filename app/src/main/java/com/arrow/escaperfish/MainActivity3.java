package com.arrow.escaperfish;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

// GAME SCREEN
public class MainActivity3 extends AppCompatActivity {

    // Defining visuals
    private TextView timeText, scoreText;
    private ImageView[] imageArray, bombArray;
    private ImageView heart1, heart2, heart3;

    private int score; // Score to be kept during gameplay only
    private int fish_prev, bomb_prev; // To keep a track of previous places of fishes and bombs
    private int speed, remaining_lives;

    // Defining preferences as well as an integer to save a possible high score later on
    SharedPreferences sharedPreferences;
    int storedScore;

    // Requirements for gameplay, timer, sounds etc.
    private CountDownTimer yourCountDownTimer;
    private Handler handler;
    private Runnable runnable;
    private MediaPlayer fish_snd, bomb_snd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        sharedPreferences = getApplication().getSharedPreferences("com.arrow.escaperfish", Context.MODE_PRIVATE);

        // Initializing visuals first
        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);

        imageArray = new ImageView[9]; bombArray = new ImageView[9];
        fill_imageArrays();

        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);

        // Initializing sound effects
        fish_snd = MediaPlayer.create(this, R.raw.blop);
        bomb_snd = MediaPlayer.create(this, R.raw.bom);

        // Getting speed according to difficulty level chosen by user
        Intent intent = getIntent();
        speed = intent.getIntExtra("speed",0);

        // Initializing rest of the variables
        score = 0;
        fish_prev = bomb_prev = -1;
        remaining_lives = 3;

        // Starting game (Time: 60 seconds)
        gameStart();
        yourCountDownTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                timeText.setText(":" + l/1000);
            }

            @Override  // Game over by running out of remaining time, hiding fishes and bombs
            public void onFinish() {
                handler.removeCallbacks(runnable);
                for(ImageView image: imageArray) image.setVisibility(View.INVISIBLE);
                for(ImageView image: bombArray)  image.setVisibility(View.INVISIBLE);
                gameOver();
            }
        }.start();
    }

    // Increasing score when player taps on a fish
    public void increaseScore(View view) {
        fish_snd.start();
        score++;
        scoreText.setText(":" + score);
    }

    // Decreasing lives or checking remaining lives if player taps on a bomb
    public void liveAmount(View view) {
        bomb_snd.start();
        remaining_lives--;
        if       (remaining_lives == 2)  heart3.setVisibility(View.INVISIBLE);
        else if  (remaining_lives == 1)  heart2.setVisibility(View.INVISIBLE);
        else {   // No lives left
            heart1.setVisibility(View.INVISIBLE);

            // Stopping game and timer
            yourCountDownTimer.cancel();
            handler.removeCallbacks(runnable);

            // Hiding fishes and bombs, ending game
            for(ImageView image: imageArray) image.setVisibility(View.INVISIBLE);
            for(ImageView image: bombArray)  image.setVisibility(View.INVISIBLE);
            gameOver();
            Toast.makeText(MainActivity3.this,"Game Over!",Toast.LENGTH_LONG).show();
        }
    }

    // Saving a high score if it's higher than previous one
    private void save() {
        storedScore = sharedPreferences.getInt("storedScore", 0);
        if(storedScore <= score) {
            sharedPreferences.edit().putInt("storedScore", score).apply();
        }
    }

    // Gameplay
    public void gameStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                // Hide all fishes and bombs first
                for(ImageView image: imageArray) image.setVisibility(View.INVISIBLE);
                for(ImageView image: bombArray)  image.setVisibility(View.INVISIBLE);

                Random rnd = new Random();
                int choice = rnd.nextInt(101); // Probability between 0 - 100

                // %67 probability for a fish to appear
                if(choice >= 33) {
                    int fish = rnd.nextInt(9);
                    while(fish == fish_prev) // To make sure make fish appear at a different place each time
                        fish = rnd.nextInt(9);
                    fish_prev = fish;
                    imageArray[fish].setVisibility(View.VISIBLE);
                }
                // %33 probability for a bomb to appear
                else {
                    int bomb = rnd.nextInt(9);
                    while(bomb == bomb_prev) // To make sure make bomb appear at a different place each time
                        bomb = rnd.nextInt(9);
                    bomb_prev = bomb;
                    bombArray[bomb].setVisibility(View.VISIBLE);
                }
                handler.postDelayed(this, speed);
            }
        };
        handler.post(runnable);
    }

    // Shows an alert for player to restart the game or not
    private void gameOver() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity3.this);
        alert.setCancelable(false);

        alert.setTitle("Restart");
        alert.setMessage("Are you sure to restart game?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                save();
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                save();
                finish();
            }
        });
        alert.show();
    }

    // Filling imageView arrays
    private void fill_imageArrays() {
        // ImageViews for Fishes
        imageArray[0] = findViewById(R.id.imageView); imageArray[1] = findViewById(R.id.imageView2); imageArray[2] = findViewById(R.id.imageView3);
        imageArray[3] = findViewById(R.id.imageView4); imageArray[4] = findViewById(R.id.imageView5); imageArray[5] = findViewById(R.id.imageView6);
        imageArray[6] = findViewById(R.id.imageView7); imageArray[7] = findViewById(R.id.imageView8); imageArray[8] = findViewById(R.id.imageView9);

        // ImageViews for bombs
        bombArray[0] = findViewById(R.id.imageView10); bombArray[1] = findViewById(R.id.imageView11); bombArray[2] = findViewById(R.id.imageView12);
        bombArray[3] = findViewById(R.id.imageView13); bombArray[4] = findViewById(R.id.imageView14); bombArray[5] = findViewById(R.id.imageView15);
        bombArray[6] = findViewById(R.id.imageView16); bombArray[7] = findViewById(R.id.imageView17); bombArray[8] = findViewById(R.id.imageView18);
    }
}