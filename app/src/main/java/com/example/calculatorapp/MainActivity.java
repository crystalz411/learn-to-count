package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.view.MotionEvent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast; //Pop a message
import android.graphics.Color;
import android.view.animation.ScaleAnimation;
import java.util.Random;
import android.content.Intent;
import android.os.Vibrator;
import android.media.MediaPlayer;



public class MainActivity extends AppCompatActivity {
    TextView tvNum1, tvNum2, tvAns;
    ImageView bird1, bird2, bird3, bird4, bird5, bird6, bird7, bird8, bird9;
    Vibrator vibrator;
    ImageView cross;
    ScaleAnimation scale;
    MediaPlayer incorrectSound, correctSound;

    int l=0;
    int gameCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        incorrectSound = MediaPlayer.create(this, R.raw.losing_drums);


       correctSound = MediaPlayer.create(this, R.raw.level_completed);


        cross = (ImageView) findViewById(R.id.cross);
        cross.setVisibility(View.INVISIBLE);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        tvNum1 = findViewById(R.id.tvNum1);
        tvNum2 = findViewById(R.id.tvNum2);
        tvAns = findViewById(R.id.tvAns);



         bird1 = (ImageView) findViewById(R.id.bird1);
         bird2 = (ImageView) findViewById(R.id.bird2);
         bird3 = (ImageView) findViewById(R.id.bird3);
         bird4 = (ImageView) findViewById(R.id.bird4);
         bird5 = (ImageView) findViewById(R.id.bird5);
         bird6 = (ImageView) findViewById(R.id.bird6);
         bird7 = (ImageView) findViewById(R.id.bird7);
         bird8 = (ImageView) findViewById(R.id.bird8);
         bird9 = (ImageView) findViewById(R.id.bird9);

        bird1.setOnTouchListener(handleTouch);
        bird2.setOnTouchListener(handleTouch);
        bird3.setOnTouchListener(handleTouch);
        bird4.setOnTouchListener(handleTouch);
        bird5.setOnTouchListener(handleTouch);
        bird6.setOnTouchListener(handleTouch);
        bird7.setOnTouchListener(handleTouch);
        bird8.setOnTouchListener(handleTouch);
        bird9.setOnTouchListener(handleTouch);

        run_reset();




    }

    void run_reset(){

        Random r = new Random();

        int num1 = r.nextInt(5)+1;
        int num2 = r.nextInt(4)+1;

        tvNum1.setText("" +num1);
        tvNum2.setText("" +num2);

        tvAns.setText("");


    }

    public void clear(View v){
        run_reset();
    }

    void printAns(String a){

        tvAns.setText(a);
    }

    public void one(View v){printAns("1");}
    public void two(View v){
        printAns("2");
    }
    public void three(View v){
        printAns("3");
    }
    public void four(View v){
        printAns("4");
    }
    public void five(View v){
        printAns("5");
    }
    public void six(View v){
        printAns("6");
    }
    public void seven(View v){
        printAns("7");
    }
    public void eight(View v){
        printAns("8");
    }
    public void nine(View v){
        printAns("9");
    }

//    void play_wrong_sound(){
//        incorrectSound.setLooping(false);
//        incorrectSound.setVolume(100,100);
//    }

    void wrong_answer(){
        incorrectSound.start();
        scale = new ScaleAnimation(0, 1, 0, 1);
        scale.setDuration(900);
        cross.startAnimation(scale);


    };

    public void submit(View v){
        int num1 = Integer.parseInt(tvNum1.getText().toString());
        int num2 = Integer.parseInt(tvNum2.getText().toString());
        int ans = num1 + num2;
        int get_users_ans = Integer.parseInt(tvAns.getText().toString());


        if(ans == get_users_ans){
            gameCount++;
           String input1 = tvNum1.getText().toString();
            String input2 = tvNum2.getText().toString();
            String output = String.valueOf(ans);


            Intent a = new Intent(MainActivity.this,CorrectPage.class);
            a.putExtra("input1", input1);
            a.putExtra("input2", input2);
            a.putExtra("output", output);

            if(gameCount > 0){
                Intent b2 = getIntent();
                String lev = b2.getStringExtra("score");
                if(lev != null) {
                    l = Integer.parseInt(lev);
                }
                else{
                    l=0;
                }
                l++;
                String score = String.valueOf(l);
                a.putExtra("score", score);

                if(Integer.parseInt(score) >= 3){
                    String levels = "1";
                    a.putExtra("levels", levels);
                }


            }

            correctSound.start();
            startActivity(a);
        }
        else {
            tvAns.setText("");
            wrong_answer();
            vibrator.vibrate(400);
//            run_reset();
//            Toast.makeText(this, "INCORRECT!!!",Toast.LENGTH_SHORT).show();
        }
    }



    public void testing(View v){

        Log.i( "Button", "Button is pressed");
//        myText.setTextColor(Color.parseColor("#bdbdbd"));
        Toast.makeText(this, "Button is pressed",Toast.LENGTH_SHORT).show();
//        Random r = new Random();int number = r.nextInt(9)+1;
    }





    private View.OnTouchListener handleTouch = new View.OnTouchListener() {
        float dX, dY;
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    dX = view.getX() - event.getRawX();
                    dY = view.getY() - event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    view.animate()
                            .x(event.getRawX() + dX)
                            .y(event.getRawY() + dY)
                            .setDuration(0)
                            .start();
                    break;
                default:
                    return false;
            }
            return true;
        }
    };



}