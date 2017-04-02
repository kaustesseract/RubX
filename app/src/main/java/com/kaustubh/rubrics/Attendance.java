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
    public static final int requestcode = 1;
    private static final int  REQUEST_PERMISSION = 123;
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

        if(haspermission())
        {
            importcsv();



        }
        else
        {
            requestperms();
        }

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



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)

            return;

        switch (requestCode) {

            case requestcode:

                String filepath = data.getData().getPath();

                //     Toast.makeText(getApplicationContext(), filepath, Toast.LENGTH_SHORT).show();
                String line = "";

                try {

                    if (resultCode == RESULT_OK) {

                        try {

                            FileReader file = new FileReader(filepath);
                            BufferedReader buffer = new BufferedReader(file);

                            while ((line = buffer.readLine()) != null) {


                                String[] str = line.split(",",1);  // defining 3 columns with null or blank field //values acceptance

                                //Id, Company,Name,Price

                                String student = str[0].toString();


                                //String email = str[1].toString();

                                //String rolln = str[2].toString();

                                //   int rolln = Integer.parseInt(str[2].toString());



                                //                     int a = Integer.parseInt(str[2]);
                                // int rolln = 2;
                              //  String u = studentc+" "+email+" "+rolln;

                                //     Toast.makeText(getApplicationContext(),u,Toast.LENGTH_SHORT).show();

                                //    Toast.makeText(getApplicationContext(),rolln,Toast.LENGTH_SHORT).show();

                                db.insertatttable(tablename,student);
                                //   tv = (TextView)findViewById(R.id.showexclel);
                                //tv.setText(u);







                            }

                        }

                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }


                    }
                }
                catch(Exception ex)
                {

                }
        }
    }




    private boolean haspermission()
    {
        int res =0;

        String[] permission = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        for(String perms : permission)
        {
            res = checkCallingOrSelfPermission(perms);
            if(!(res== PackageManager.PERMISSION_GRANTED))
            {
                return false;

            }

        }
        return true;
    }



    private void requestperms()
    {
        String[] permission = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            requestPermissions(permission,REQUEST_PERMISSION);
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean allowed = true;

        switch (requestCode)
        {
            case REQUEST_PERMISSION :

                for(int res : grantResults)
                {
                    allowed = allowed && (res == PackageManager.PERMISSION_GRANTED);
                }
                break;

            default:
                allowed = false;
                break;
        }

        if(allowed)
        {
            importcsv();
        }
        else
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Toast.makeText(getApplicationContext(), "No Permission", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }

    public void importcsv()
    {
        Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);

        fileintent.setType("gagt/sdf");

        try {

            startActivityForResult(fileintent, requestcode);

        } catch (ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(),"No app found for importing the file.",Toast.LENGTH_SHORT).show();

        }

    }
}
