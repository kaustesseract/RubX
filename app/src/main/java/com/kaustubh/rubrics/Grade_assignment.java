package com.kaustubh.rubrics;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Grade_assignment extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_assignment);
        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
       // Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();

        Bundle bundle = getIntent().getExtras();
        final String course = bundle.getString("course");
        final String clas = bundle.getString("class");
        final int coid = db.searchcoid(course);
        final int classid = db.searchcid(clas);

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

                    int coid = db.searchcoid(course);

                    String assignmenttable = "Course_"+course+"_"+coid+"_"+pid;
                    int assid = db.searchassid(assname,assignmenttable);
                    final String studentgrade = "Studentgrade_"+classid+"_"+coid+"_"+assid+"_"+pid;
                    String studgrade = db.searchtable(studentgrade);

//                if(studentgrade.equals(studgrade))
//                {
//                /*    Intent intent = new Intent(getApplicationContext(), Start_Grading.class);
//                    intent.putExtra("class",clas);
//                    intent.putExtra("rubname",rname);
//                    intent.putExtra("int",put);
//                    intent.putExtra("gradetable",studentgrade);
//                    intent.putExtra("courses",courses);
//                    startActivity(intent);*/
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(Grade_assignment.this);
//                    builder.setMessage("Grading has already done. Do you want to edit ?").setCancelable(false)
//                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Intent i = new Intent(getApplicationContext(),BaseActivity.class);
//                                    startActivity(i);
//                                    finish();
//                                }
//                            })
//                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.cancel();
//                                }
//                            });
//
//                    AlertDialog alertDialog = builder.create();
//                    alertDialog.setTitle("ALERT !!");
//                    alertDialog.show();
//
//
//                    //CORRECTION DONE IN ALERT BOX
//                }




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
            Intent i = new Intent(getApplicationContext(), Grade_Course.class);
            startActivity(i);
            finish();
            //Please put dialog box here
        }
    }

}
