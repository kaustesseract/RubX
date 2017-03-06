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
import android.widget.Toast;

public class Grade_rubrics extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
     String rname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_rubrics);

        Bundle bundle = getIntent().getExtras();
       final String courses = bundle.getString("course");
        final String clas = bundle.getString("class");
        final String assname = bundle.getString("assname");
        String tgrade = "grade";
        final String course = courses + "_Grade";
        int coid = db.searchcoid(courses);



        db.open();
        Cursor cursor = db.showrubricslist();
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

                rname = text+"row";
                int rcount =  db.getrubricscount(rname);
                Toast.makeText(getApplicationContext(),"The count is "+rcount,Toast.LENGTH_LONG).show();

               // Toast.makeText(getApplicationContext(),course +" "+clas, Toast.LENGTH_SHORT).show();
            }
        });

        String assignmenttable = "Course_"+courses+"_"+coid;

        final int assid = db.searchassid(assname,assignmenttable);

        String tgradetable = db.searchtable(tgrade);
        String coursetable = db.searchtable(course);

        if(tgrade.equals(tgradetable))
        {

            // put if condition here tho check if course name already exists in the cname column
            if(course.equals(coursetable))
            {
                db.insertcourseassignment(assname,assid,course);

            }
            else {
                db.courseassignment(course);
           //     db.insertgrade(coid, course, tgrade);
                db.insertcourseassignment(assname,assid,course);
            }



        }

        else
        {
            db.creategradetable(tgrade);
            db.insertteacher(tgrade);
            db.insertgrade(coid,course,tgrade);

            //create tgrade table here because by default for the first time it will be created


        }






    }
}
