package com.kaustubh.rubrics;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class welcomeActivity extends AppCompatActivity {
/*
    void image()
    {
        ImageView img = (ImageView) findViewById(R.id.rubrics);
           img.animate().rotationBy(180f).setDuration(1000);


    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ImageView img = (ImageView) findViewById(R.id.rubrics);
       // img.animate().translationXBy(-1000f).setDuration(1000);
        Handler hn = new Handler();
        hn.postDelayed(new Runnable() {
            @Override
            public void run() {

              //  image();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));


            }
        },4000);
    }
}
