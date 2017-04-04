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

public class Grade_Students extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade__students);
        DatabaseHelper db = new DatabaseHelper(this);
        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
        Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();

        db.open();
        Cursor cursor = db.showclasslist(pid);
        startManagingCursor(cursor);
        String[] ar = new String[]{DatabaseHelper.COLUMN_CLASS};
        int[] name = new int[]{R.id.stugrade};

        SimpleCursorAdapter myadapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.display_gradeclass,
                        cursor,
                        ar,
                        name,
                        0
                );
        ListView ll = (ListView)findViewById(R.id.stug);
        ll.setAdapter(myadapter);

        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.stugrade);
                //  String list = (ll.getItemAtPosition(position));
                String text = textView.getText().toString();
               Intent i = new Intent(getApplicationContext(), Grade_Course.class);
                i.putExtra("text",text);
                startActivity(i);

            }});
    }
}
