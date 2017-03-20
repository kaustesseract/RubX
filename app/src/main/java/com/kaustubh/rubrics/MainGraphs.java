package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainGraphs extends AppCompatActivity {

    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_graphs);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        Button buto = (Button) findViewById(R.id.sxy);

        buto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(getApplicationContext(),SimplePieChartActivity.class);
                startActivity(intent);
            }
        });

        Button but = (Button) findViewById(R.id.bubble1);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(getApplicationContext(),TH_SelectClass.class);
                startActivity(intent);
            }
        });

        Button buts = (Button) findViewById(R.id.barplot1);

        buts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(getApplicationContext(),TM_Select_Class.class);
                startActivity(intent);
            }
        });

        Button scatter = (Button) findViewById(R.id.scatter);

        scatter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(getApplicationContext(),Scatter_plot.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, BaseActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }



        return super.onOptionsItemSelected(item);
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
