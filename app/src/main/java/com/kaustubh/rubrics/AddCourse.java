package com.kaustubh.rubrics;

import android.content.Context;
import android.content.SharedPreferences;
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

import static android.R.color.white;

public class AddCourse extends AppCompatActivity {

    String selection;
    String clas;

    DatabaseHelper helper = new DatabaseHelper(this);
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_course);


        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
        Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();


        ArrayList<String> list = helper.getspinnerdata(pid);
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

                if( cours.getText().toString().trim().equals("") )
                {
                    cours.setError( "Classname is required!" );

                    cours.setHint("classname");
                    cours.setHintTextColor(getResources().getColor(white));
                }

                else {
                    String course = cours.getText().toString().replaceAll("[-+.^:,~!@#$%^&*()_=;<>/`]","");



                    Contactcourse cr = new Contactcourse();
                    cr.setCourse(course);
                    cr.setTid(pid);
                    cr.setClasid(clsid);

                    helper.insertcourse(cr);
                }
                Toast.makeText(getApplicationContext(), "Course created successfully", Toast.LENGTH_LONG).show();
                finish();


            }
        });


    }
}


