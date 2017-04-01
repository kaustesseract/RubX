package com.kaustubh.rubrics;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.method.TextKeyListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    EditText dobs;
    int yearx,monthx,dayx,mont;
    String date;
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        yearx = cal.get(Calendar.YEAR);
        monthx=cal.get(Calendar.MONTH);
        dayx=cal.get(Calendar.DAY_OF_MONTH);


        //Button bd = (Button) findViewById(R.id.date);
        dobs = (EditText) findViewById(R.id.text_display);

     /*   bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this,listner,yearx,monthx,dayx).show();
            }
        });*/

        dobs.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Always use a TextKeyListener when clearing a TextView to prevent android
                    // warnings in the log
                    new DatePickerDialog(RegisterActivity.this,listner,yearx,monthx,dayx).show();

                }
            }
        });


        Button btt = (Button) findViewById(R.id.sub);

        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getApplicationContext(),Choose_question.class);
                startActivity(in);
                finish();

            }
        });



        Button bt  = (Button) findViewById(R.id.register);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText pass2 = (EditText) findViewById(R.id.pass2);






      //  final int phone = Integer.parseInt(phones.getText().toString());






        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String uname = username.getText().toString();
                final String pass = password.getText().toString();
                final String emai = email.getText().toString();
                final String pas2 = pass2.getText().toString();


               // int dob = Integer.parseInt(dobs.getText().toString());

                if (!pass.equals(pas2)) {
                    Toast.makeText(getApplicationContext(), " Both passwords donot match", Toast.LENGTH_LONG).show();
                } else {

                    Contact c = new Contact();
                    c.setName(uname);
                    c.setPassword(pass);
                    c.setEmail(emai);
                    c.setDate(dayx);
                    c.setMonth(monthx);
                    c.setYear(yearx);

                    helper.insertcontact(c);

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();

                }

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




   /* public void signup(View view)
    {
        if(view.getId()==R.id.register)
        {
            EditText username = (EditText) findViewById(R.id.username);
            EditText password = (EditText) findViewById(R.id.password);
            EditText email = (EditText) findViewById(R.id.email);
            EditText pass2 = (EditText) findViewById(R.id.pass2);
            EditText phones = (EditText) findViewById(R.id.phone);
            dobs = (TextView) findViewById(R.id.dd);


            String uname = username.getText().toString();
            String pass = password.getText().toString();
            String emai= email.getText().toString();
            String pas2 = pass2.getText().toString();
            int phone = Integer.parseInt(phones.getText().toString());
            int dob = Integer.parseInt(dobs.getText().toString());

            if(!pass.equals(pas2))
            {
                Toast.makeText(getApplicationContext(), " Both passwords donot match", Toast.LENGTH_LONG).show();
            }

            else {

                Contact c = new Contact();
                c.setName(uname);
                c.setPassword(pass);
                c.setEmail(emai);
                c.setPhone(phone);
                c.setDob(dob);

                helper.insertcontact(c);

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            }


        }
    }*/
}
