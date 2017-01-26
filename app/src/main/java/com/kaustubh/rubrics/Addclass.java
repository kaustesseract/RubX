package com.kaustubh.rubrics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.String;

public class Addclass extends AppCompatActivity {

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

                EditText student = (EditText) findViewById(R.id.cls_name);
                String studentc = student.getText().toString();

                EditText email1 = (EditText) findViewById(R.id.email);
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

            }
        });


    }
}
