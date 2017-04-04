package com.kaustubh.rubrics;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainAttendance extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    int yearx,monthx,dayx,mont;
    Calendar cal = Calendar.getInstance();

    private ActionBar actionBar;
    String clas;
    String cours;
    EditText dob;
    int clsid;
    int coid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_attendance);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        yearx = cal.get(Calendar.YEAR);
        monthx=cal.get(Calendar.MONTH);
        dayx=cal.get(Calendar.DAY_OF_MONTH);
        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
        Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();

        ArrayList<String> list = db.getspinnerdata(pid);
        Spinner sp = (Spinner) findViewById(R.id.clas);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(MainAttendance.this, R.layout.spinner_layout, R.id.txt, list);
        sp.setAdapter(adp);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                clas = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayList<String> list1 = db.getcoursespinnerdata();
        Spinner sp1 = (Spinner) findViewById(R.id.course);
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(MainAttendance.this, R.layout.spinner_layout, R.id.txt, list1);
        sp1.setAdapter(adp1);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                cours = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dob = (EditText) findViewById(R.id.date);

        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Always use a TextKeyListener when clearing a TextView to prevent android
                    // warnings in the log
                    new DatePickerDialog(MainAttendance.this,listner,yearx,monthx,dayx).show();

                }
            }
        });


        Button bt = (Button) findViewById(R.id.sub);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 clsid = db.searchcid(clas);
                 coid = db.searchcoid(cours);

                String alldate = dayx+"/"+mont+"/"+yearx;


              boolean att =  db.insertattendance(clsid,coid,dayx,mont,yearx,alldate);

                if(att == true)
                {
                    Intent i = new Intent(getApplicationContext(),Presentabsent.class);

                    i.putExtra("clsid",clsid);
                    i.putExtra("coid",coid);
                    i.putExtra("day",dayx);
                    i.putExtra("month",mont);
                    i.putExtra("year",yearx);
                    i.putExtra("classname",clas);
                    startActivity(i);
                    finish();
                }

                else
                {
                    Toast.makeText(getApplicationContext(),"Didnt add" , Toast.LENGTH_LONG).show();
                }



            }
        });

      //  int clsid = db.searchcid(clas);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, BaseActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }



        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent j = new Intent(getApplicationContext(), BaseActivity.class);
            startActivity(j);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    DatePickerDialog.OnDateSetListener listner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            yearx = year;
            monthx = month;
            mont = month + 1;
            dayx = dayOfMonth;
            dob.setText(dayx + "/"+ mont+"/"+yearx);


        }
    };
}
