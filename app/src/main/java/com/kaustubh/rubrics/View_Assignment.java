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

public class View_Assignment extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__assignment);
        DatabaseHelper db = new DatabaseHelper(this);

        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
   //     Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();
        db.open();
        Cursor cursor = db.showcourselist(pid);
        startManagingCursor(cursor);
        String[] ar = new String[]{DatabaseHelper.COLUMN_CONAME};
        int[] name = new int[]{R.id.cours};

        SimpleCursorAdapter myadapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.display_courses,
                        cursor,
                        ar,
                        name,
                        0
                );
        ListView ll = (ListView)findViewById(R.id.listcourse);
        ll.setAdapter(myadapter);
        // db.close();

        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.cours);
                //  String list = (ll.getItemAtPosition(position));
                String text = textView.getText().toString();

                int coid = helper.searchcoid(text);

                String asstable = "Course_"+text+"_"+coid+"_"+pid;
                // Toast.makeText(getApplicationContext(), "Class "+text  , Toast.LENGTH_LONG).show();
                // System.out.println("Choosen Country = : " + list);
                  Intent i = new Intent(getApplicationContext(), Show_Assignment.class);
                i.putExtra("asstable",asstable);
                // Toast.makeText(getApplicationContext(), "Id is "+pid , Toast.LENGTH_LONG).show();
                 startActivity(i);

            }});

    }
}
