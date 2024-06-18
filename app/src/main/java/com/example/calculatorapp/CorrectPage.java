package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.animation.ScaleAnimation;

public class CorrectPage extends AppCompatActivity {
    TextView tvNum1, tvNum2, tvAns, level;
    ImageView stars1, stars2, stars3, trophy;
    ScaleAnimation scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_page);

        stars1 = (ImageView) findViewById(R.id.stars1);
        stars2 = (ImageView) findViewById(R.id.stars2);
        stars3 = (ImageView) findViewById(R.id.stars3);
        scale = new ScaleAnimation(0, 1, 0, 1);
        scale.setDuration(1500);
        stars1.startAnimation(scale);
        stars2.startAnimation(scale);
        stars3.startAnimation(scale);

        trophy = (ImageView) findViewById(R.id.trophy);
        trophy.setVisibility(View.INVISIBLE);

        tvNum1 = findViewById(R.id.tvNum1);
        tvNum2 = findViewById(R.id.tvNum2);
        tvAns = findViewById(R.id.tvAns);
        level = findViewById(R.id.level);
        Intent a2 = getIntent();
        Bundle b = a2.getExtras();

        if(b != null){
            String j = (String) b.get("input1");
            String x = (String) b.get("input2");
            String k = (String) b.get("output");
            String l = (String) b.get("levels");
            tvNum1.setText(j);
            tvNum2.setText(x);
            tvAns.setText(k);
            level.setText(l);

            if( l != null){
                trophy.setVisibility(View.VISIBLE);
            }


        }




    }




    public void onClickBtn(View v)
    {
        Intent b = new Intent(CorrectPage.this,MainActivity.class);
        Intent a2 = getIntent();
        b.putExtra("score", a2.getStringExtra("score"));
        startActivity(b);
    }




}