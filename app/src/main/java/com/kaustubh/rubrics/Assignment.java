package com.kaustubh.rubrics;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static android.R.color.black;
import static android.R.color.white;

public class Assignment extends AppCompatActivity{

    String cours;
    int yearx,monthx,dayx,mont;
    static final int DIALOG_ID = 0;
    int hour_x , minute_x;
    final Calendar cal = Calendar.getInstance();

   EditText deadline;
    EditText time;
  //  String deadline = deadlines.getText().toString();


    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);


        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
      //  Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();

        yearx = cal.get(Calendar.YEAR);
        monthx=cal.get(Calendar.MONTH);
        dayx=cal.get(Calendar.DAY_OF_MONTH);
        hour_x=cal.get(Calendar.HOUR);
        minute_x=cal.get(Calendar.MINUTE);


        deadline = (EditText) findViewById(R.id.calender);
        time = (EditText) findViewById(R.id.editText);


            final ArrayList<String> list = helper.getcoursespinnerdata(pid);
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

           // Button bd = (Button) findViewById(R.id.calendar);

        deadline.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Always use a TextKeyListener when clearing a TextView to prevent android
                    // warnings in the log
                    new DatePickerDialog(Assignment.this,listner,yearx,monthx,dayx).show();

                }
            }
        });


        time.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Always use a TextKeyListener when clearing a TextView to prevent android
                    // warnings in the log
                    new TimePickerDialog(Assignment.this, listi, hour_x, minute_x, false).show();

                }
            }
        });
         //   Button bt = (Button) findViewById(R.id.time);

        /*    bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new TimePickerDialog(Assignment.this, listi, hour_x, minute_x, false).show();
                }
            });*/


            Button br = (Button) findViewById(R.id.submitst);
            br.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    final EditText anames = (EditText) findViewById(R.id.aname);


                    if( deadline.getText().toString().trim().equals("") && time.getText().toString().trim().equals("") && anames.getText().toString().trim().equals(""))
                    {
                        deadline.setError( "Deadline date" );
                        time.setError( "Deadline Time" );
                        anames.setError( "Assignment name" );

                        deadline.setHint("Deadline");
                        time.setHint("Time");
                        anames.setHint("Assignment name ");

                        deadline.setHintTextColor(getResources().getColor(black));
                        time.setHintTextColor(getResources().getColor(black));
                        anames.setHintTextColor(getResources().getColor(black));

                    }


                    else if( deadline.getText().toString().trim().equals("") || time.getText().toString().trim().equals("") || anames.getText().toString().trim().equals(""))
                    {

                        Toast.makeText(getApplicationContext(),"Enter all field",Toast.LENGTH_SHORT).show();

                    }

                    else {

                        //  EditText deadlines = (EditText) findViewById(R.id.editText);


                        String aname = anames.getText().toString();


                        int coid = helper.searchcoid(cours);
                        String assname = "Course_" + cours + "_" + coid + "_" + pid;

                        String tablename = helper.searchtable(assname);

                        if (assname.equals(tablename)) {
                            ContactAssignment ca = new ContactAssignment();
                            ca.setAssname(aname);
                            // ca.setTid(pid);
                            ca.setDay(dayx);
                            ca.setMonth(monthx);
                            ca.setYear(yearx);
                            ca.setHour(hour_x);
                            ca.setMinute(minute_x);
                            helper.insertassignment(ca, assname);
                            Toast.makeText(Assignment.this, "Assignment is created successfully", Toast.LENGTH_SHORT).show();
                            //   finish();

                        } else {

                            helper.createassignment(assname);

                            ContactAssignment ca = new ContactAssignment();
                            ca.setAssname(aname);
                            ca.setTid(pid);
                            ca.setDay(dayx);
                            ca.setMonth(monthx);
                            ca.setYear(yearx);
                            ca.setHour(hour_x);
                            ca.setMinute(minute_x);
                            helper.insertassignment(ca, assname);
                            Toast.makeText(Assignment.this, "Assignment is created successfully", Toast.LENGTH_SHORT).show();
                            //   finish();
                        }

              /*  Intent intent = new Intent(getApplicationContext(),BaseActivity.class);

                PendingIntent pintent = PendingIntent.getActivity(Assignment.this,0,intent,0);
                Notification noti = new Notification.Builder(Assignment.this).setTicker("Ticker Title").
                        setContentTitle("Welcome to Rubrics").setContentText("Deadline of "+aname+" has crossed!! Time to correct it.")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pintent).getNotification();
                noti.flags = Notification.FLAG_AUTO_CANCEL;

              //  noti.flags = Notification.DEFAULT_VIBRATE;
                NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                nm.notify(0,noti);*/

// Setting up the notification of the deadline

                        //   Calendar cal = Calendar.getInstance();
               /* cal.setTimeInMillis(System.currentTimeMillis());
                cal.clear();
                cal.set(yearx,monthx,dayx,2,26);*/


              /*  Long atime = new GregorianCalendar().getTimeInMillis()+5*100;

                Intent intent  = new Intent(getApplicationContext(),AlarmRubrics.class);
                AlarmManager alarmManager1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager1.set(AlarmManager.RTC_WAKEUP,atime,PendingIntent.getBroadcast(getApplicationContext(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT));*/


               /*Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                cal.clear();
                cal.set(yearx,monthx,dayx,hour_x,minute_x);
                Intent myIntent1 = new Intent(getApplicationContext(), AlarmRubrics.class);
                PendingIntent pendingIntent1 = PendingIntent.getBroadcast(getApplicationContext(), 1253, myIntent1,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager1.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent1);*/
                        // finish();


                        // cal.set(dayx, monthx, yearx);

               /* cal.set(Calendar.HOUR_OF_DAY,hour_x);
                cal.set(Calendar.MINUTE,minute_x);
                cal.set(Calendar.SECOND,0);*/
                        // cal.set(Calendar.AM_PM,Calendar.PM);


                        Calendar cal1 = Calendar.getInstance();
                        cal1.set(Calendar.DAY_OF_MONTH, dayx);
                        cal1.set(Calendar.MONTH, monthx);
                        cal1.set(Calendar.YEAR, yearx);
                        cal1.set(Calendar.HOUR_OF_DAY, hour_x);
                        cal1.set(Calendar.MINUTE, minute_x);

                        // cal1.set(Calendar.AM_PM,Calendar.PM);


              /*  cal.set(Calendar.HOUR_OF_DAY,hour_x);
                cal.set(Calendar.MINUTE,minute_x);*/


                        Intent myIntent1 = new Intent(getApplicationContext(), AlarmRubrics.class);
                        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(getApplicationContext(), 1253, myIntent1,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                        AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
                        alarmManager1.set(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), pendingIntent1);
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
            deadline.setText(dayx + "/"+ mont+"/"+yearx);


        }
    };

    TimePickerDialog.OnTimeSetListener listi = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x=hourOfDay;
            minute_x=minute;
            time.setText(hour_x + ":" +minute_x);

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
