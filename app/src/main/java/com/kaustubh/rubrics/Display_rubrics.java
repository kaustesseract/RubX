package com.kaustubh.rubrics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Display_rubrics extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_rubrics);


        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
        Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();
        db.open();
        Cursor cursor = db.showrubricslist(pid);
        startManagingCursor(cursor);
        String[] ar = new String[]{DatabaseHelper.COLUMN_RNAME};
        int[] name = new int[]{R.id.ru};

        SimpleCursorAdapter myadapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.show_rubrics,
                        cursor,
                        ar,
                        name,
                        0
                );
        ListView ll = (ListView)findViewById(R.id.rubri);
        ll.setAdapter(myadapter);

        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.ru);
                //  String list = (ll.getItemAtPosition(position));
                String text = textView.getText().toString();

                Intent i = new Intent(getApplicationContext(), view_rubrics.class);
                i.putExtra("text",text);
                startActivity(i);
            }
        });
    }
}
