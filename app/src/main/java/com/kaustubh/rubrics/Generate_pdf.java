package com.kaustubh.rubrics;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
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

    String filenames;
    private static final int  REQUEST_PERMISSION = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_pdf);

        Bundle bundle = getIntent().getExtras();
        final String course = bundle.getString("course");
        final String clas = bundle.getString("class");
        final String Assignment = bundle.getString("assname");

        filenames =clas+"_"+course+"_"+Assignment;

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
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
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
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
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
//add paragraph to the document
            doc.add(new Paragraph(filenames));
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

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet firstSheet = workbook.createSheet("Sheet - No 1");
        HSSFSheet secondSheet = workbook.createSheet("Sheet - No 2");
        HSSFRow rowA = firstSheet.createRow(0);
        HSSFCell cellA = rowA.createCell(0);
        cellA.setCellValue(new HSSFRichTextString("Sheet One"));
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




}
