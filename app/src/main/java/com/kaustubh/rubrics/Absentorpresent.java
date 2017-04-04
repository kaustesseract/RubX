package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Absentorpresent extends AppCompatActivity {

     String p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absentorpresent);
        Bundle bundle = getIntent().getExtras();
        final String course = bundle.getString("course");
        final String clas = bundle.getString("class");
        final String dates = bundle.getString("date");


        Button bt = (Button) findViewById(R.id.present);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p="p";
                Intent i = new Intent(getApplicationContext(),Showstudents.class);
                i.putExtra("course",course);
                i.putExtra("class",clas);
                i.putExtra("date",dates);
                i.putExtra("p",p);
                startActivity(i);


            }
        });

        Button bto = (Button) findViewById(R.id.absent);
        bto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p="a";
                Intent i = new Intent(getApplicationContext(),Showstudents.class);
                i.putExtra("course",course);
                i.putExtra("class",clas);
                i.putExtra("date",dates);
                i.putExtra("p",p);
                startActivity(i);

            }
        });
    }
}
