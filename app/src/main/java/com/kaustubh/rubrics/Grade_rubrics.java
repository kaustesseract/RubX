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

import java.lang.reflect.Array;
import java.util.Arrays;

public class Grade_rubrics extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    String rname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_rubrics);

        Bundle bundle = getIntent().getExtras();

        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
        final String courses = bundle.getString("course");
        final String clas = bundle.getString("class");
        final String assname = bundle.getString("assname");
        String tgrade = "grade";
        final String course = courses + "_Grade"+"_"+pid;
        int coid = db.searchcoid(courses);
        int classid = db.searchcid(clas);
        String assignmenttable = "Course_"+courses+"_"+coid+"_"+pid;
        int assid = db.searchassid(assname,assignmenttable);
        final int put = 0;

        final String studentgrade = "Studentgrade_"+classid+"_"+coid+"_"+assid+"_"+pid;



        final String clase = clas+"_"+pid;

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

                rname = text+"_row_"+pid;
                int rcount =  db.getrubricscount(rname);
                int i;
                //  Toast.makeText(getApplicationContext(),"The count is "+rcount,Toast.LENGTH_LONG).show();

                //  String[] rubname = new String[r];

                String rubname[] = db.getrubricsparam(rname);


                String studgrade = db.searchtable(studentgrade);

                if(studentgrade.equals(studgrade))
                {
//                    Intent intent = new Intent(getApplicationContext(), Start_Grading.class);
//                    intent.putExtra("class",clas);
//                    intent.putExtra("rubname",rname);
//                    intent.putExtra("int",put);
//                    intent.putExtra("gradetable",studentgrade);
//                    intent.putExtra("courses",courses);
//                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Table already exists",Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(getApplicationContext(), BaseActivity.class);
                   // in.putExtra("text",courses);
                    startActivity(in);
                    finish();

                }
                else {
                    Toast.makeText(getApplicationContext(),"Table already ",Toast.LENGTH_SHORT).show();

                    db.createstartgrade(rubname, studentgrade, rcount);
                    Intent intent = new Intent(getApplicationContext(), Start_Grading.class);
                    intent.putExtra("class",clase);
                    intent.putExtra("rubname",rname);
                    intent.putExtra("int",put);
                    intent.putExtra("gradetable",studentgrade);
                    intent.putExtra("courses",courses);
                    startActivity(intent);
                }



            }
        });




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
