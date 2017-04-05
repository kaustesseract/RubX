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

public class TH_SelectAssignment extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_th__select_assignment);
        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
       // Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();

        Bundle bundle = getIntent().getExtras();
        final String course = bundle.getString("course");
        final String clas = bundle.getString("class");
        final int clsid = db.searchcid(clas);
        final int coid = db.searchcoid(course);
        //String assignmenttable = "Course_"+courses+"_"+coid;

        final String table = "Course"+"_"+course+"_"+coid+"_"+pid;
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
        ListView ll = (ListView)findViewById(R.id.thassignment);
        ll.setAdapter(myadapter);

        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.assig);
                //  String list = (ll.getItemAtPosition(position));
                String assname = textView.getText().toString();
                int assid = db.searchassid(assname,table);
                String grade = "Studentgrade_"+clsid+"_"+coid+"_"+assid+"_"+pid;

                Intent i = new Intent(getApplicationContext(), Set_TH.class);
                i.putExtra("gradetable",grade);

                startActivity(i);

            }

        });

    }
}
