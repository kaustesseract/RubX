package com.kaustubh.rubrics;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddCourse extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
     private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_course);

        ArrayList<String> list = helper.getspinnerdata();
        Spinner sp = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adp= new ArrayAdapter<String>(AddCourse.this,R.layout.spinner_layout,R.id.txt,list);
        sp.setAdapter(adp);
         String clas = sp.getSelectedItem().toString();
        final String yo = clas;
       // Toast.makeText(getApplicationContext(), "Id is "+clas , Toast.LENGTH_LONG).show();




        Button bt = (Button)findViewById(R.id.submit);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* int clsid = helper.searchcid(clas);
                EditText cours = (EditText)findViewById(R.id.course);
                String course = cours.getText().toString();

                Contactcourse cr = new Contactcourse();
                cr.setCourse(course);
                cr.setClasid(clsid);
                helper.insertcourse(cr);
                Toast.makeText(getApplicationContext(), "Course created successfully" , Toast.LENGTH_LONG).show();
                finish();
                */
                Toast.makeText(getApplicationContext(), "Id is "+yo , Toast.LENGTH_LONG).show();







            }
        });






    }
}
