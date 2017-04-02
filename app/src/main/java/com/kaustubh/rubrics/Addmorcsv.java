package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Addmorcsv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmorcsv);

        Bundle bundle = getIntent().getExtras();

        final int clsid = bundle.getInt("clsid");
        final int coid = bundle.getInt("coid");
        final int day = bundle.getInt("day");
        final int month = bundle.getInt("month");
        final int year = bundle.getInt("year");
        final String p = bundle.getString("p");
        final String clas = bundle.getString("classname");



        Button bt = (Button) findViewById(R.id.addm);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Attendance.class);
                i.putExtra("clsid",clsid);
                i.putExtra("coid",coid);
                i.putExtra("day",day);
                i.putExtra("month",month);
                i.putExtra("year",year);
                i.putExtra("p",p);
                i.putExtra("classname",clas);
                startActivity(i);

                }

        });


    }
}
