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

public class TH_ShowCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_th__show_course);
        DatabaseHelper db = new DatabaseHelper(this);

        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
        Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();
        Bundle bundle = getIntent().getExtras();
        final String classname = bundle.getString("classname");

        int cid = db.searchcid(classname);

        db.open();
        Cursor cursor = db.getcourseid(cid,pid);
        startManagingCursor(cursor);
        String[] ar = new String[]{DatabaseHelper.COLUMN_CONAME};
        int[] name = new int[]{R.id.stg};

        SimpleCursorAdapter myadapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.display_coursegrade,
                        cursor,
                        ar,
                        name,
                        0
                );
        ListView ll = (ListView)findViewById(R.id.thcourse);
        ll.setAdapter(myadapter);

        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.stg);
                //  String list = (ll.getItemAtPosition(position));
                String course = textView.getText().toString();
                Intent i = new Intent(getApplicationContext(), TH_SelectAssignment.class);
                i.putExtra("course",course);
                i.putExtra("class",classname);
                startActivity(i);

            }});
    }
}
