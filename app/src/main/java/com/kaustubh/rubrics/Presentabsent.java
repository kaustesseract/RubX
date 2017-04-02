package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;


public class Presentabsent extends AppCompatActivity {
    String p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentabsent);

        Bundle bundle = getIntent().getExtras();

        final int clsid = bundle.getInt("clsid");
        final int coid = bundle.getInt("coid");
        final int day = bundle.getInt("day");
       final int month = bundle.getInt("month");
       final int year = bundle.getInt("year");
        final String clas = bundle.getString("classname");

        Button bt = (Button) findViewById(R.id.present);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = "p";
                Intent i = new Intent(getApplicationContext(),Addmorcsv.class);
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

        Button bto = (Button) findViewById(R.id.absent);
        bto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = "a";
                Intent j = new Intent(getApplicationContext(),Addmorcsv.class);
                j.putExtra("clsid",clsid);
                j.putExtra("coid",coid);
                j.putExtra("day",day);
                j.putExtra("month",month);
                j.putExtra("year",year);
                j.putExtra("p",p);
                j.putExtra("classname",clas);
                startActivity(j);


            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent j = new Intent(getApplicationContext(), BaseActivity.class);
            startActivity(j);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
