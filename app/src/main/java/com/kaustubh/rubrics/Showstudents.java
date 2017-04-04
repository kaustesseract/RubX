package com.kaustubh.rubrics;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Showstudents extends AppCompatActivity {

    String tablename;
    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showstudents);
        Bundle bundle = getIntent().getExtras();
        final String course = bundle.getString("course");
        final String clas = bundle.getString("class");
        final String dates = bundle.getString("date");
        final String p = bundle.getString("p");

        String[] sep = dates.split("/");

        String day = sep[0];
        String month = sep[1];
        String year = sep[2];

        int clsid = db.searchcid(clas);
        int coid = db.searchcoid(course);

        tablename = "Attendance_"+clsid+"_"+coid+"_"+day+"_"+month+"_"+year+"_"+p;



        db.open();
        Cursor cursor = db.studentatt(tablename);
        startManagingCursor(cursor);
        String[] ar = new String[]{DatabaseHelper.COLUMN_STUDENT};
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
        ListView ll = (ListView)findViewById(R.id.student);
        ll.setAdapter(myadapter);

        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.stg);
                //  String list = (ll.getItemAtPosition(position));
                String dates = textView.getText().toString();
//                Intent i = new Intent(getApplicationContext(), Absentorpresent.class);
//                i.putExtra("course",course);
//                i.putExtra("class",clas);
//                i.putExtra("date",dates);
//                startActivity(i);

            }});
    }

}

