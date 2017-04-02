package com.kaustubh.rubrics;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Attendance extends AppCompatActivity {

    private ActionBar actionBar;
    ArrayList<String> selectedItems = new ArrayList<>();
    DatabaseHelper db = new DatabaseHelper(this);
    int count;
    int clsid;
    int coid;
    int day;
    int month;
    int year;
     String p;
    String clas;
    String tablename;
    String[] items;
    String students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

          clsid = bundle.getInt("clsid");
         coid = bundle.getInt("coid");
        day = bundle.getInt("day");
        month = bundle.getInt("month");
       year = bundle.getInt("year");
        p = bundle.getString("p");
         clas = bundle.getString("classname");

        tablename = "Attendance_"+clsid+"_"+coid+"_"+day+"_"+month+"_"+year+"_"+p;

        db.createatttable(tablename);



      //  final String message = bundle.getString("text");

         count = db.getstudentcount(clas);

        ListView list = (ListView) findViewById(R.id.check_list);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
      //  String[] items = {"Kaustubh", "Sushant", "Manish", "Kunal"};
        String[] items = db.getstudentname(clas);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.checkbox, R.id.itm, items);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemselected = ((TextView) view).getText().toString();
                if (selectedItems.contains(itemselected)) {
                    selectedItems.remove(itemselected);

                }
                else
                {
                    selectedItems.add(itemselected);
                }

            }
        });
    }

    public void selected(View view)
    {

        int i=0;
        //String items = "";
        items = new String[count];
        for(String item:selectedItems)
        {
                items[i]= item;
            i++;
        }
        for(int j=0;j<i;j++) {
            students = items[i];
            db.insertatttable(tablename , students);
        }
        Intent j = new Intent(getApplicationContext(), MainAttendance.class);
        startActivity(j);
        finish();


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
            Intent j = new Intent(getApplicationContext(), MainAttendance.class);
            startActivity(j);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }



}
