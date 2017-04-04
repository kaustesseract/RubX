package com.kaustubh.rubrics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.color.white;

public class AddStudentclass extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_studentclass);
        Button submit = (Button)findViewById(R.id.submitst);

        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
        Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          //      Bundle bundle = getIntent().getExtras();
         //       int message = bundle.getInt("pid");
            //    Toast.makeText(getApplicationContext(), "Id is "+message , Toast.LENGTH_LONG).show();

                EditText a = (EditText) findViewById(R.id.class1);
                EditText b = (EditText) findViewById(R.id.totstudent);

                if( a.getText().toString().trim().equals("") && b.getText().toString().trim().equals(""))
                {
                    a.setError( "Classname is required!" );
                    b.setError( "Count is required!" );

                    a.setHint("classname");
                    b.setHint("count of student");
                    a.setHintTextColor(getResources().getColor(white));
                    b.setHintTextColor(getResources().getColor(white));
                }

                else if(a.getText().toString().trim().equals("") || b.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter all fields",Toast.LENGTH_SHORT).show();
                }



                else {
                    String str1 = a.getText().toString().replaceAll("[-+.^:,~!@#$%^&*()_=;<>/`]","");

                    String str = str1 + "_" + pid;

                    String table = helper.searchtable(str);

                    if(table.equals(str))
                    {
                        Toast.makeText(getApplicationContext(),"Class already exists",Toast.LENGTH_SHORT).show();

                    }


                    int totalstudent = Integer.parseInt(b.getText().toString());


                    helper.createclass(str);
                    Contact2 c2 = new Contact2();
                    c2.setCname(str1);
                    c2.setTotalmarks(totalstudent);
                    c2.setTid(pid);

                    helper.inclass(c2);


                    Intent i = new Intent(getApplicationContext(), Types_Of_Addc.class);
                    i.putExtra("str", str);
                    i.putExtra("student", totalstudent);

                    startActivity(i);
                    finish();
                }
            }

        });


    }
    /*public void class1(View view) {
        if (view.getId() == R.id.submit) {
            EditText a = (EditText) findViewById(R.id.class1);
            String str = a.getText().toString();
            helper.createclass(str);

            Intent i = new Intent(getApplicationContext(), Addclass.class);
            i.putExtra("str",str);
            startActivity(i);
           // Toast.makeText(getApplicationContext(), "Id is "+id , Toast.LENGTH_LONG).show();


        }
    }*/


}

