package com.kaustubh.rubrics;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
    String clas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_rubrics);

        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
       // Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();
        Bundle bundle = getIntent().getExtras();
       final String courses = bundle.getString("course");
         String clasi = bundle.getString("class");
       clas = clasi+"_"+pid;

        final String assname = bundle.getString("assname");
        String tgrade = "grade_"+pid;
        final String course = courses + "_Grade";
        int coid = db.searchcoid(courses);
        int classid = db.searchcid(clas);
        String assignmenttable = "Course_"+courses+"_"+coid+"_"+pid;
        int assid = db.searchassid(assname,assignmenttable);
        final int put = 0;

        final String studentgrade = "Studentgrade_"+classid+"_"+coid+"_"+assid+"_"+pid;




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
                /*    Intent intent = new Intent(getApplicationContext(), Start_Grading.class);
                    intent.putExtra("class",clas);
                    intent.putExtra("rubname",rname);
                    intent.putExtra("int",put);
                    intent.putExtra("gradetable",studentgrade);
                    intent.putExtra("courses",courses);
                    startActivity(intent);*/

                    AlertDialog.Builder builder = new AlertDialog.Builder(Grade_rubrics.this);
                    builder.setMessage("Grading has already done. Do you want to edit ?").setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(getApplicationContext(),BaseActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.setTitle("ALERT !!");
                    alertDialog.show();


                    //CORRECTION DONE IN ALERT BOX
                }
                else {
                    db.createstartgrade(rubname, studentgrade, rcount);
                    Intent intent = new Intent(getApplicationContext(), Start_Grading.class);
                    intent.putExtra("class",clas);
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
//                Toast.makeText(this,"Table already exists",Toast.LENGTH_SHORT).show();

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
