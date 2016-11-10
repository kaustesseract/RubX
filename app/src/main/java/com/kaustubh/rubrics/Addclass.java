package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.lang.String;

public class Addclass extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    public void add(View view)
    {
        EditText student = (EditText) findViewById(R.id.name);
        String studentc = student.getText().toString();

        EditText classn = (EditText) findViewById(R.id.classname);
        String classname = classn.getText().toString();

        EditText roll = (EditText) findViewById(R.id.roll);
        int rolln = Integer.parseInt(roll.getText().toString());

        Contact1 c1 = new Contact1();
        c1.setStudentc(studentc);
        c1.setClassname(classname);
        c1.setRoll(rolln);
        helper.insertclass(c1);
            finish();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addclass);
    }
}
