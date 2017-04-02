package com.kaustubh.rubrics;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Addmorcsv extends AppCompatActivity {

    public static final int requestcode = 1;
    private static final int  REQUEST_PERMISSION = 123;
    String tablename;
    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmorcsv);

        Bundle bundle = getIntent().getExtras();

        final int clsid = bundle.getInt("clsid");
        final int coid = bundle.getInt("coid");
        final int day = bundle.getInt("day");
        final int month = bundle.getInt("month");
        final int year = bundle.getInt("year");
        final String p = bundle.getString("p");
        final String clas = bundle.getString("classname");

        tablename = "Attendance_"+clsid+"_"+coid+"_"+day+"_"+month+"_"+year+"_"+p;


        Button bt = (Button) findViewById(R.id.addm);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Attendance.class);
                i.putExtra("clsid",clsid);
                i.putExtra("coid",coid);
                i.putExtra("day",day);
                i.putExtra("month",month);
                i.putExtra("year",year);
                i.putExtra("p",p);
                i.putExtra("classname",clas);
                startActivity(i);

                }

        });

        Button bto = (Button) findViewById(R.id.impcsv);

        bto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(haspermission())
                {
                    importcsv();



                }
                else
                {
                    requestperms();
                }

            }
        });


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
