package com.kaustubh.rubrics;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddCourse extends AppCompatActivity {

    String selection;
    String clas;

    DatabaseHelper helper = new DatabaseHelper(this);
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_course);

        ArrayList<String> list = helper.getspinnerdata();
        Spinner sp = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(AddCourse.this, R.layout.spinner_layout, R.id.txt, list);
        sp.setAdapter(adp);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                clas = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button bt = (Button) findViewById(R.id.submitst);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(getApplicationContext(), "Id is "+selectedItem , Toast.LENGTH_LONG).show();
                int clsid = helper.searchcid(clas);
                EditText cours = (EditText) findViewById(R.id.course);
                String course = cours.getText().toString();

                Contactcourse cr = new Contactcourse();
                cr.setCourse(course);
                cr.setClasid(clsid);
                helper.insertcourse(cr);
                Toast.makeText(getApplicationContext(), "Course created successfully", Toast.LENGTH_LONG).show();
                finish();


            }
        });


    }
}


