package com.kaustubh.rubrics;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class EditAccount extends AppCompatActivity {
DatabaseHelper db = new DatabaseHelper(this);
    int yearx,monthx,dayx,mont;
    String date;
    Calendar cal = Calendar.getInstance();

    EditText dobs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
        Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();

        yearx = cal.get(Calendar.YEAR);
        monthx=cal.get(Calendar.MONTH);
        dayx=cal.get(Calendar.DAY_OF_MONTH);

        final EditText user = (EditText) findViewById(R.id.users);
        final EditText email = (EditText) findViewById(R.id.emails);

        String[] c = db.getalluserdetails(pid);
        int[] dob = db.getdob(pid);

        user.setText(c[0]);
        email.setText(c[1]);


        dobs = (EditText) findViewById(R.id.dobs);

        dobs.setText(String.valueOf(dob[0])+"/"+String.valueOf(dob[1])+"/"+String.valueOf(dob[2]));

        dobs.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Always use a TextKeyListener when clearing a TextView to prevent android
                    // warnings in the log
                    new DatePickerDialog(EditAccount.this,listner,yearx,monthx,dayx).show();

                }
            }
        });

        Button bt = (Button) findViewById(R.id.subm);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = user.getText().toString();
                String em = email.getText().toString();

                db.getaccupdate(pid,uname,em,dayx,mont,yearx);

                Intent i = new Intent(getApplicationContext() , ViewAccount.class);
                startActivity(i);
                finish();
            }
        });



    }

    DatePickerDialog.OnDateSetListener listner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            yearx = year;
            monthx = month;
            mont = month + 1;
            dayx = dayOfMonth;
            dobs.setText(dayx + "/"+ mont+"/"+yearx);


        }
    };



}
