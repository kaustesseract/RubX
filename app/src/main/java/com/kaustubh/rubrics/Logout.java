package com.kaustubh.rubrics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * Created by KAUSTUBH on 11-01-2017.
 */
public class Logout extends AppCompatActivity{

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    
   /* SessionManager manager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new SessionManager();

        //Button button = (Button) findViewById(R.id.btn_logout);
                manager.setPreferences(Logout.this, "status", "0");
                finish();

    }

    //onBackpressed to stop user from going MainActivity to Login Screen on back button press
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }*/



}
