package com.kaustubh.rubrics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Email_assignment extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_assignment);

        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
        Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();

        Bundle bundle = getIntent().getExtras();
        final String courses = bundle.getString("course");
        final String clas = bundle.getString("class");
        final String table = bundle.getString("table");
        final String course = courses + "_Grade";
        final int coid = db.searchcoid(course);
        final int classid = db.searchcid(clas);

      //  String table = "Course"+"_"+courses+"_"+coid+"_"+pid;


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
        ListView ll = (ListView)findViewById(R.id.assignments);
        ll.setAdapter(myadapter);

        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.assig);
                //  String list = (ll.getItemAtPosition(position));
                String assname = textView.getText().toString();
                String assignmenttable = "Course_"+courses+"_"+coid;
                int assid = db.searchassid(assname,assignmenttable);
                boolean sent = false;
                Log.d("Status:", "Inside onclick");

                final String studentgrade = "Studentgrade_"+classid+"_"+coid+"_"+assid;

               // Intent i = new Intent(getApplicationContext(), BaseActivity.class);
               /* i.putExtra("course",course);
                i.putExtra("class",clas);
                i.putExtra("assname",assname);*/

                int count = db.gettotalcount(studentgrade);

               // Toast.makeText(getApplicationContext(),count+"" , Toast.LENGTH_SHORT).show();

                int[] roll = db.getstudentroll(studentgrade);
                int[] gettotalmarks = db.gettotalmarks(studentgrade);

             /*   for(int k=0;k<count;k++)
                {
                    Toast.makeText(getApplicationContext(),roll[k]+"" , Toast.LENGTH_SHORT).show();
                }*/

                String[] email = db.getemail(clas,roll);

                for(int k=0;k<count-1;k++)
                {
                    try {
                        String recipient = email[k];
                        int marks = gettotalmarks[k];
                        GMAILSender sender = new GMAILSender(recipient, "Your total marks", "Your total marks is "+marks);
                        sent = sender.send();

                    } catch (Exception e) {
                        Log.e("SendMail", e.getMessage(), e);
                    }
                    if (sent == true){
                        Toast.makeText(getApplicationContext(),"Email sent", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Not sent", Toast.LENGTH_SHORT).show();


                }
//                    Toast.makeText(getApplicationContext(),email[k] , Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(),gettotalmarks[k]+"" , Toast.LENGTH_SHORT).show();
                }


//                startActivity(i);



        });
    }
}
