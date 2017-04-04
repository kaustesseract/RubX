package com.kaustubh.rubrics;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Types_Of_Addc extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    public static final int requestcode = 1;
    private static final int  REQUEST_PERMISSION = 123;
    String message1;
    String message;
    int w=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types__of__addc);

        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("str");
        final int totalstudent = bundle.getInt("student");
        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        int pid = pref.getInt("pid",0);
        Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();

       // message = message1+"_"+pid;

        Button bt = (Button) findViewById(R.id.addm);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Addclass.class);
                i.putExtra("str",message);
                i.putExtra("student",totalstudent);
                i.putExtra("increment",w);
                startActivity(i);

            }
        });

        Button bto = (Button) findViewById(R.id.addcsv);
        bto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           /*     Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);

                fileintent.setType("gagt/sdf");

                try {

                    startActivityForResult(fileintent, requestcode);

                } catch (ActivityNotFoundException e) {

                    Toast.makeText(getApplicationContext(),"No app found for importing the file.",Toast.LENGTH_SHORT).show();

                }
                */
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


                                String[] str = line.split(",",4);  // defining 3 columns with null or blank field //values acceptance

                                //Id, Company,Name,Price

                                String studentc = str[0].toString();


                                String email = str[1].toString();

                                String rolln = str[2].toString();

                             //   int rolln = Integer.parseInt(str[2].toString());



           //                     int a = Integer.parseInt(str[2]);
                               // int rolln = 2;
                                String u = studentc+" "+email+" "+rolln;

                           //     Toast.makeText(getApplicationContext(),u,Toast.LENGTH_SHORT).show();

                            //    Toast.makeText(getApplicationContext(),rolln,Toast.LENGTH_SHORT).show();
                                Contact1 c1 = new Contact1();
                                c1.setStudentc(studentc);
                                c1.setEmail(email);
                                try {
                                    c1.setRoll(Integer.parseInt(rolln));
                                   // c1.setRoll(rolln);
                                }
                                catch (NumberFormatException nfe) {
                                    Toast.makeText(getApplicationContext(),"THERE IS A NUMBER FORMAT PROBLEM WITH YOUR CSV. ",Toast.LENGTH_SHORT).show();
                                }
                                helper.insertclass(c1, message);
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
