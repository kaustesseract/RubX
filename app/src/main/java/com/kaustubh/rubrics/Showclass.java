package com.kaustubh.rubrics;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Showclass extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showclass);
        TextView show = (TextView) findViewById(R.id.ru);
        DatabaseHelper db = new DatabaseHelper(this);
        Bundle bundle = getIntent().getExtras();
        String cla = bundle.getString("text");

        db.open();
        String data = db.searchclass(cla);
        //db.close();
        show.setTypeface(null, Typeface.ITALIC);
        show.setText(data);




    }


}
