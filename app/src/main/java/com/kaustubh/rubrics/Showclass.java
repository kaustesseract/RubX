package com.kaustubh.rubrics;

import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Showclass extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showclass);
        TextView show = (TextView) findViewById(R.id.show);
        DatabaseHelper db = new DatabaseHelper(this);

        db.open();
        String data = db.searchclass();
        db.close();
        show.setTypeface(null, Typeface.ITALIC);
        show.setText(data);




    }


}
