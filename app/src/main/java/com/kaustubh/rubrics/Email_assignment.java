package com.kaustubh.rubrics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Email_assignment extends AppCompatActivity {

    private static final int  REQUEST_PERMISSION = 123;

    String studentgrade;
    String[] to;
    String filenames;
    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_assignment);

        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
      //  Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();

        Bundle bundle = getIntent().getExtras();
        final String courses = bundle.getString("course");
        final String clase = bundle.getString("class");
        final String table = bundle.getString("table");
        final String course = courses + "_Grade";
        final int coid = db.searchcoid(course);
        final String clas = clase+"_"+pid;
        final int classid = db.searchcid(clas);


      //  String table = "Course"+"_"+courses+"_"+coid+"_"+pid;





        db.open();
        Cursor cursor = db.searchassign(table);
        startManagingCursor(cursor);
        String[] ar = new String[]{DatabaseHelper.COLUMN_ASSNAME};
        int[] name = new int[]{R.id.assig};
        SimpleCursorAdapter myadapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.show_assignment,
                        cursor,
                        ar,
                        name,
                        0
                );
        ListView ll = (ListView)findViewById(R.id.assignments);
        ll.setAdapter(myadapter);

        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.assig);
                //  String list = (ll.getItemAtPosition(position));
                String assname = textView.getText().toString();
             //   String assignmenttable = "Course_"+courses+"_"+coid;

                String assignmenttable = "Course_"+courses+"_"+coid+"_"+pid;

               // int assid = db.searchassid(assname,assignmenttable);
                boolean sent = false;
                Log.d("Status:", "Inside onclick");


                int assid = db.searchassid(assname,assignmenttable);

                filenames =clas+"_"+courses+"_"+assname+"_"+pid;

         //       studentgrade = "Studentgrade_"+classid+"_"+coid+"_"+assid+"_"+pid;

                 studentgrade = "Studentgrade_"+classid+"_"+coid+"_"+assid+"_"+pid;

               // Intent i = new Intent(getApplicationContext(), BaseActivity.class);
               /* i.putExtra("course",course);
                i.putExtra("class",clas);
                i.putExtra("assname",assname);*/

                int count = db.gettotalcount(studentgrade);

               // Toast.makeText(getApplicationContext(),count+"" , Toast.LENGTH_SHORT).show();

                int[] roll = db.getstudentroll(studentgrade);
                int[] gettotalmarks = db.gettotalmarks(studentgrade);

             /*   for(int k=0;k<count;k++)
                {
                    Toast.makeText(getApplicationContext(),roll[k]+"" , Toast.LENGTH_SHORT).show();
                }*/

                String[] email = db.getemail(clas,roll);

                if(haspermission())
                {
                    makepdf();

                    try {
                        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Rubrics", filenames+".pdf");
                        Uri path = Uri.fromFile(filelocation);
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("vnd.android.cursor.dir/email");
//                        for(int j=0;j<count;j++) {
//                            to = email[j];
//                        }
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
                        emailIntent.putExtra(Intent.EXTRA_STREAM, path);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, filenames);
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                        // startActivityForResult(emailIntent,1);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }


                }
                else
                {
                    requestperms();
                }
/////////
//                for(int k=0;k<count-1;k++)
//                {
//                    try {
//                        String recipient = email[k];
//                        int marks = gettotalmarks[k];
//                        GMAILSender sender = new GMAILSender(recipient, "Your total marks", "Your total marks is "+marks);
//                        sent = sender.send();
//
//                    } catch (Exception e) {
//                        Log.e("SendMail", e.getMessage(), e);
//                    }
//                    if (sent == true){
//                        Toast.makeText(getApplicationContext(),"Email sent", Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                        Toast.makeText(getApplicationContext(),"Not sent", Toast.LENGTH_SHORT).show();
//
//
//                }
//                    Toast.makeText(getApplicationContext(),email[k] , Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(),gettotalmarks[k]+"" , Toast.LENGTH_SHORT).show();
                }


//                startActivity(i);



        });
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
            makepdf();

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





    private void makepdf()
    {

        File Dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Rubrics");
        if(!Dir.exists())
        {
            Dir.mkdir();
            //    Toast.makeText(getApplicationContext(),"Not present",Toast.LENGTH_SHORT).show();
        }
        else {
            //     Toast.makeText(getApplicationContext(),"Already exits",Toast.LENGTH_SHORT).show();
        }
        File file = new File(Dir,filenames+".pdf");


        //     File Root = Environment.getExternalStorageDirectory();
        try {
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
//create pdf writer instance
            PdfWriter.getInstance(doc, new FileOutputStream(file));
//open the document for writing
            doc.open();

            int markscount = db.gettotalcount(studentgrade);


            int line = 5;



            int[] marks = db.gettotalmarks(studentgrade);
            int[] roll = db.getstudentroll(studentgrade);
            //    String[] name = db.getstname(clas,roll);

            doc.add(new Paragraph("ROLL NO. "+" TOTAL MARKS", FontFactory.getFont(FontFactory.COURIER,30)));
//add paragraph to the document
            for(int i=0;i<markscount;i++) {
                doc.add(new Paragraph( String.valueOf(roll[i]) +  "                         "+String.valueOf(marks[i]), FontFactory.getFont(FontFactory.TIMES_ROMAN,25)));
            }
            //doc.add(new Paragraph("jh"));
//close the document
            doc.close();

        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
