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

public class Viewatdates extends AppCompatActivity {
DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewatdates);
        Bundle bundle = getIntent().getExtras();
        final String course = bundle.getString("course");
        final String clas = bundle.getString("class");

        int coid = db.searchcoid(course);
        int clid = db.searchcid(clas);

        db.open();
        Cursor cursor = db.getdate(clid,coid);
        startManagingCursor(cursor);
        String[] ar = new String[]{DatabaseHelper.COLUMN_ALLDATE};
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
        ListView ll = (ListView)findViewById(R.id.dates);
        ll.setAdapter(myadapter);

        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.stg);
                //  String list = (ll.getItemAtPosition(position));
                String dates = textView.getText().toString();
                Intent i = new Intent(getApplicationContext(), Absentorpresent.class);
                i.putExtra("course",course);
                i.putExtra("class",clas);
                i.putExtra("date",dates);
                startActivity(i);

            }});
    }

}
