package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.String;

import static android.R.color.white;

public class Addclass extends AppCompatActivity {
   // int i =1;

    DatabaseHelper helper = new DatabaseHelper(this);

   /* public void add(View view)
    {
        EditText student = (EditText) findViewById(R.id.name);
        String studentc = student.getText().toString();

        EditText email1 = (EditText) findViewById(R.id.classname);
        String email = email1.getText().toString();

        EditText roll = (EditText) findViewById(R.id.roll);
        int rolln = Integer.parseInt(roll.getText().toString());

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("str");
        Toast.makeText(getApplicationContext(), "Class is "+message , Toast.LENGTH_LONG).show();

        Contact1 c1 = new Contact1();
        c1.setStudentc(studentc);
        c1.setEmail(email);
        c1.setRoll(rolln);
        helper.insertclass(c1,message);
        //finish();

        student.setText("");
        email1.setText("");
        roll.setText("");
        //Intent i = new Intent(getApplicationContext(),Addclass.class);
        //startActivity(i);


    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addclass);



        Button bt = (Button)findViewById(R.id.but);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = getIntent().getExtras();
                String message = bundle.getString("str");
                int totalstudent = bundle.getInt("student");
                int i = bundle.getInt("increment");

                EditText student = (EditText) findViewById(R.id.cls_name);
                EditText email1 = (EditText) findViewById(R.id.email);
                EditText roll = (EditText) findViewById(R.id.roll);

                if( student.getText().toString().trim().equals("") && email1.getText().toString().trim().equals("") && email1.getText().toString().trim().equals(""))
                {
                    student.setError( "Student name is required!" );
                    email1.setError( "Email is required!" );
                    roll.setError( "Roll number is required!" );

                    student.setHint("classname");
                    email1.setHint("count of student");
                    roll.setHint("Roll No.");


                    student.setHintTextColor(getResources().getColor(white));
                    email1.setHintTextColor(getResources().getColor(white));
                    roll.setHintTextColor(getResources().getColor(white));
                }

                if( student.getText().toString().trim().equals("") || email1.getText().toString().trim().equals("") || roll.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter all fields",Toast.LENGTH_SHORT).show();

                }


                else {

                    String studentc = student.getText().toString();


                    String email = email1.getText().toString();


                    int rolln = Integer.parseInt(roll.getText().toString());


                    // Toast.makeText(getApplicationContext(), "Class is "+message , Toast.LENGTH_LONG).show();

                    Contact1 c1 = new Contact1();
                    c1.setStudentc(studentc);
                    c1.setEmail(email);
                    c1.setRoll(rolln);
                    helper.insertclass(c1, message);
                    //finish();

                    if (i != totalstudent) {
                        i++;
                        Intent l = new Intent(getApplicationContext(), Addclass.class);
                        l.putExtra("str", message);
                        l.putExtra("increment", i);
                        l.putExtra("student", totalstudent);
                        startActivity(l);
                    } else {
                        Intent y = new Intent(getApplicationContext(), BaseActivity.class);
                        startActivity(y);
                        finish();

                    }

                    //  student.setText("");
                    // email1.setText("");
                    // roll.setText("");

                }

            }
        });


    }
}
