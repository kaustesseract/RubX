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

public class Viewatcourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewatcourse);

        DatabaseHelper db = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        final String message = bundle.getString("text");

        int cid = db.searchcid(message);

        db.open();
        Cursor cursor = db.getcourseid(cid);
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
        ListView ll = (ListView)findViewById(R.id.courseat);
        ll.setAdapter(myadapter);

        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.stg);
                //  String list = (ll.getItemAtPosition(position));
                String course = textView.getText().toString();
                Intent i = new Intent(getApplicationContext(), Grade_assignment.class);
                i.putExtra("course",course);
                i.putExtra("class",message);
                startActivity(i);

            }});
    }
}
