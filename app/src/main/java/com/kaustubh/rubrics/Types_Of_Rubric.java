package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Types_Of_Rubric extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    public void continous(View view)
    {

        Intent i = new Intent(getApplicationContext(),CreateRubrics.class);
        startActivity(i);
    }

    public void descrete(View view)
    {

        Intent i = new Intent(getApplicationContext(),Descrete_rubrics.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types__of__rubric);
    }
}
