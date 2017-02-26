package com.kaustubh.rubrics;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Assignment extends AppCompatActivity {

    String cours;
    int yearx,monthx,dayx;
    static final int DIALOG_ID = 0;
    final Calendar cal = Calendar.getInstance();

   EditText deadline;
  //  String deadline = deadlines.getText().toString();


    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);


        yearx = cal.get(Calendar.YEAR);
        monthx=cal.get(Calendar.MONTH);
        dayx=cal.get(Calendar.DAY_OF_MONTH);

        deadline = (EditText) findViewById(R.id.calender);



        ArrayList<String> list = helper.getcoursespinnerdata();
        Spinner sp = (Spinner) findViewById(R.id.cname);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(Assignment.this, R.layout.spinner_layout, R.id.txt, list);
        sp.setAdapter(adp);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                cours = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button bd = (Button) findViewById(R.id.calendar);

       bd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               new DatePickerDialog(Assignment.this,listner,yearx,monthx,dayx).show();
           }
       });


        Button br = (Button) findViewById(R.id.submit);
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  EditText deadlines = (EditText) findViewById(R.id.editText);

                EditText anames = (EditText) findViewById(R.id.aname);
                String aname = anames.getText().toString();

                EditText grades = (EditText) findViewById(R.id.editText6);
                int grade = Integer.parseInt(grades.getText().toString());


                int coid = helper.searchcoid(cours);
                String assname = "Course_"+cours+"_"+coid;

                helper.createassignment(assname);

                ContactAssignment ca = new ContactAssignment();
                ca.setAssname(aname);
                ca.setDay(dayx);
                ca.setMonth(monthx);
                ca.setYear(yearx);
                ca.setGrade(grade);
                helper.insertassignment(ca,assname);
                Toast.makeText(Assignment.this,"Assignment is created successfully",Toast.LENGTH_SHORT).show();
                finish();







            }
        });

    }

    DatePickerDialog.OnDateSetListener listner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            yearx = year;
            monthx = month + 1;
            dayx = dayOfMonth;
            deadline.setText(dayx + "/"+ monthx+"/"+yearx);

        }
    };
/*
    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(id==DIALOG_ID)
            return new DatePickerDialog(this , dpickerlistner , yearx,monthx,dayx );
            return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerlistner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
       public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            yearx = year;
            monthx = month + 1;
            dayx = dayOfMonth;
            String selectedDate = Integer.toString(dayx)+"/"+Integer.toString(monthx)+"/"+Integer.toString(yearx);
           // Toast.makeText(Assignment.this,dayx + "/"+ monthx+"/"+yearx,Toast.LENGTH_SHORT).show();
            String a = dayx + "/"+ monthx+"/"+yearx;
           // deadline.setText(new StringBuilder().append(dayx).append("/").append(monthx+1).append("/").append(yearx));
         deadline.setT

        }


    };*/


}
