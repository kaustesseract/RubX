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

public class Grade_assignment extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_assignment);
        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
        Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();

        Bundle bundle = getIntent().getExtras();
        final String course = bundle.getString("course");
        final String clas = bundle.getString("class");
        int coid = db.searchcoid(course);

        String table = "Course" + "_" + course + "_" + coid+"_"+pid;

      //  Toast.makeText(this, table , Toast.LENGTH_SHORT).show();

        try {

            db.open();
            Cursor cursor = db.searchassign(table);

            startManagingCursor(cursor);
            String[] ar = new String[]{DatabaseHelper.COLUMN_ASSNAME};
            int[] name = new int[]{R.id.assig};
            SimpleCursorAdapter myadapter =
                    new SimpleCursorAdapter(
                            this,
                            R.layout.show_assignment,
                            cursor,
                            ar,
                            name,
                            0
                    );
            ListView ll = (ListView) findViewById(R.id.assign);
            ll.setAdapter(myadapter);

            ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    TextView textView = (TextView) view.findViewById(R.id.assig);
                    //  String list = (ll.getItemAtPosition(position));
                    String assname = textView.getText().toString();

                    Intent i = new Intent(getApplicationContext(), Grade_rubrics.class);
                    i.putExtra("course", course);
                    i.putExtra("class", clas);
                    i.putExtra("assname", assname);
                    startActivity(i);

                }

            });
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Please add Assignment", Toast.LENGTH_SHORT).show();
           // Intent i = new Intent(getApplicationContext(), Assignment.class);
         //   startActivity(i);
            //Please put dialog box here
        }
    }

}
