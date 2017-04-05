package com.kaustubh.rubrics;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import android.os.Environment;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.itextpdf.text.Font.ITALIC;


public class Generate_pdf extends AppCompatActivity {

    String clas;
    String filenames;
    private static final int  REQUEST_PERMISSION = 123;
    String studentgrade;
    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_pdf);
        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
        Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();


        Bundle bundle = getIntent().getExtras();
        final String courses = bundle.getString("course");
         clas = bundle.getString("class");
        final String assname = bundle.getString("assname");

        final String course = courses + "_Grade";
        int coid = db.searchcoid(courses);
        int classid = db.searchcid(clas);

        String assignmenttable = "Course_"+courses+"_"+coid+"_"+pid;
        int assid = db.searchassid(assname,assignmenttable);

        filenames =clas+"_"+courses+"_"+assname+"_"+pid;

         studentgrade = "Studentgrade_"+classid+"_"+coid+"_"+assid+"_"+pid;

        Button but = (Button) findViewById(R.id.gpdf);

        Button bto = (Button) findViewById(R.id.gexcel);

        but.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                if(haspermission())
                {
                    makepdf();

                    try {
                        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Rubrics", filenames+".pdf");
                        Uri path = Uri.fromFile(filelocation);
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("vnd.android.cursor.dir/email");
                        String to[] = {"kaustubhironmaiden@gmail.com"};
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
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





            }
        });

        bto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(haspermission())
                {
                    makeexcel();
                    try {
                        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Rubrics", filenames+".xls");
                        Uri path = Uri.fromFile(filelocation);
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("vnd.android.cursor.dir/email");
                        String to[] = {"kaustubhironmaiden@gmail.com"};
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
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

            }
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
            makeexcel();
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

            doc.add(new Paragraph("ROLL NO. "+" TOTAL MARKS",FontFactory.getFont(FontFactory.COURIER,30)));
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


    private void makeexcel()
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

        int markscount = db.gettotalcount(studentgrade);

        int[] marks = db.gettotalmarks(studentgrade);
        int[] roll = db.getstudentroll(studentgrade);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet firstSheet = workbook.createSheet("Sheet - No 1");
        HSSFSheet secondSheet = workbook.createSheet("Sheet - No 2");
        HSSFRow rowd = firstSheet.createRow(0);
        HSSFCell celld = rowd.createCell(0);
        celld.setCellValue(new HSSFRichTextString("ROLL NO."));

       // HSSFRow rowc = firstSheet.createRow(0);
        HSSFCell cellc = rowd.createCell(1);
        cellc.setCellValue(new HSSFRichTextString("TOTAL MARKS"));

        for (int i=1; i<markscount; i++) {
            HSSFRow rowA = firstSheet.createRow(i);
            HSSFCell cellA = rowA.createCell(0);
            cellA.setCellValue(new HSSFRichTextString(String.valueOf(roll[i])));
            HSSFCell cellB = rowA.createCell(1);
            cellB.setCellValue(new HSSFRichTextString(String.valueOf(marks[i])));
        }
//        for (int i=0; i<markscount; i++) {
//            HSSFRow rowA = firstSheet.createRow(i);
//            HSSFCell cellA = rowA.createCell(1);
//            cellA.setCellValue(new HSSFRichTextString(String.valueOf(marks[i])));
//        }
        HSSFRow rowB = secondSheet.createRow(0);
        HSSFCell cellB = rowB.createCell(0);
        cellB.setCellValue(new HSSFRichTextString("Sheet two"));



        FileOutputStream fos = null;
        try {
            String str_path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Rubrics";
            File file ;
            file = new File(str_path, filenames + ".xls");


            fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
           // Toast.makeText(Generate_pdf.this, "Excel Sheet Generated", Toast.LENGTH_SHORT).show();
        }


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
             Intent j = new Intent(getApplicationContext(), BaseActivity.class);
             startActivity(j);

        }
        return super.onKeyDown(keyCode, event);
    }





}
