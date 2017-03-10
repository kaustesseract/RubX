package com.kaustubh.rubrics;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import static android.view.Gravity.START;

public class Start_Grading extends AppCompatActivity {

    HashMap<String,String> asdfg ;
    int i=0;
    int k=0;
    int w=0;
    Context context;
    String sname;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__grading);

        context = this;
        asdfg = new HashMap<>();

        Bundle bundle = getIntent().getExtras();
        final String courses = bundle.getString("courses");
        final String rubname = bundle.getString("rubname");
        final String clas = bundle.getString("class");
        final String gradetable  = bundle.getString("gradetable");

       final int put = bundle.getInt("int");



        final DatabaseHelper datahelper = new DatabaseHelper(context);
       // int coid = datahelper.searchcoid(courses);
       // int classid = datahelper.searchcid(clas);
        TableLayout tableLayout = (TableLayout) findViewById(R.id.startgrading);

        final int j = datahelper.getstudentcount(clas);
        i = put;


       // for (i = 0; i < j;) {

            sname = datahelper.getstudentname(i, clas);


            TableRow rowHeader = new TableRow(context);
            rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
            rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            String[] headerText = {sname};
            for (String c : headerText) {
                TextView tv2 = new TextView(context);
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv2.setGravity(START);
                tv2.setTextSize(18);
                tv2.setTextColor(Color.BLUE);
                tv2.setPadding(0, 0, 0, 25);
                tv2.setText(c);
                rowHeader.addView(tv2);
            }
            tableLayout.addView(rowHeader);


          /*  Button gsubmit = (Button) findViewById(R.id.gsubmit);


            gsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  int i = 1;
                    // int j = datahelper.getstudentcount(clas);


                /*    while (i < j) {
                        sname = datahelper.getstudentname(i, clas);
                        Toast.makeText(getApplication(), sname, Toast.LENGTH_SHORT).show();

                        i = i + 1;
                    }
                    if (i == j) {
                        Toast.makeText(getApplication(), "Students are finsihed", Toast.LENGTH_SHORT).show();
                    }

                    if(i+1<j) {
                        int a[]= new int[count];
                        String a12[] =new String[count];
                        for(int sa=0;sa<count;sa++){

                            a[sa]=Integer.parseInt(asdfg.get("value"+sa));
                            a12[sa]=asdfg.get("name"+sa);

                        }

                        Toast.makeText(getApplicationContext(),a12[0]+":"+a[0]+"\n"+a12[1]+":"+a[1]+"\n"+a12[2]+":"+a[2],Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), Start_Grading.class);
                        intent.putExtra("int", i + 1);
                        intent.putExtra("rubname", rubname);
                        intent.putExtra("class", clas);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Students are finished!",Toast.LENGTH_SHORT);
                        Intent intents = new Intent(getApplicationContext(), BaseActivity.class);
                        startActivity(intents);

                    }




                }
            });*/





        SQLiteDatabase db = datahelper.getReadableDatabase();
            // Start the transaction.
            db.beginTransaction();

            try {
                String selectQuery = "SELECT * FROM " + rubname;
                Cursor cursor = db.rawQuery(selectQuery, null);
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        // Read columns data
                        // int row_id= cursor.getInt(cursor.getColumnIndex("row_id"));
                        String rows_name = cursor.getString(cursor.getColumnIndex("rname"));
                        int rows_lweight = cursor.getInt(cursor.getColumnIndex("lweight"));
                        int rows_hweight = cursor.getInt(cursor.getColumnIndex("hweight"));
                        TableRow row = new TableRow(context);
                        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                                TableLayout.LayoutParams.WRAP_CONTENT));
                        String[] colText = {rows_name};

                        for (String text : colText) {
                          //  String sw = "tv"+count;
                            TextView tv = new TextView(context);
                            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.WRAP_CONTENT));
                            tv.setGravity(Gravity.START);
                            tv.setTextColor(Color.WHITE);
                            tv.setTextSize(25);
                            tv.setPadding(0, 0, 0, 80);
                            tv.setText(text);

                            final TextView tv1 = new TextView(context);
                            tv1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.WRAP_CONTENT));

                            tv1.setGravity(Gravity.RIGHT);
                            tv1.setTextSize(25);
                            tv1.setTextColor(Color.WHITE);
                            tv1.setId(count);
                            asdfg.put("name"+tv1.getId(),text);

                            SeekBar view1 = new SeekBar(context);
                            // view1 = seekbar_inflate.inflate(R.layout.mysxm_control_coloumn, null, false);
                            view1.setLayoutParams(new TableRow.LayoutParams(
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.WRAP_CONTENT));
                            view1.setProgress(0);
                            view1.setPadding(25, 25, 25, 25);
                           //  view1.setMinimumHeight(rows_hweight);
                            view1.setMax(rows_hweight);
                             //   view1.setMinimumWidth(rows_lweight);


                            view1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                @Override
                                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                                    tv1.setText(String.valueOf(progress));

                                    asdfg.put("value"+tv1.getId(),tv1.getText().toString().trim());

                                }

                                @Override
                                public void onStartTrackingTouch(SeekBar seekBar) {

                                }

                                @Override
                                public void onStopTrackingTouch(SeekBar seekBar) {

                                }
                            });


                            //row.addView(tv);

                            row.addView(tv, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                            row.addView(view1, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                            row.addView(tv1);
                            count++;

                        }
                        tableLayout.addView(row);

                    }

                }

               // TableRow row = new TableRow(this);

        Button button = new Button(this);
        button.setText("submit");
        button.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,         TableRow.LayoutParams.WRAP_CONTENT));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(i+1<j) {
                            int marks[]= new int[count];
                            String cname[] =new String[count];
                            for(int sa=0;sa<count;sa++){

                                marks[sa]=Integer.parseInt(asdfg.get("value"+sa));
                                cname[sa]=asdfg.get("name"+sa);

                            }

                            for(int o=0;o<count;o++)
                            {
                                w = w + marks[o];
                            }


                            datahelper.insertgrades(put,cname,marks,w,gradetable,count);

                          //  Toast.makeText(getApplicationContext(),cname[0]+":"+marks[0]+"\n"+cname[1]+":"+marks[1]+"\n"+cname[2]+":"+marks[2],Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), Start_Grading.class);
                            intent.putExtra("int", i + 1);
                            intent.putExtra("rubname", rubname);
                            intent.putExtra("class", clas);
                            intent.putExtra("courses",courses);
                            intent.putExtra("gradetable",gradetable);
                            startActivity(intent);
                        }
                        else
                        {
                            int marks[]= new int[count];
                            String cname[] =new String[count];
                            for(int sa=0;sa<count;sa++){

                                marks[sa]=Integer.parseInt(asdfg.get("value"+sa));
                                cname[sa]=asdfg.get("name"+sa);

                            }

                            for(int o=0;o<count;o++)
                            {
                                w = w + marks[o];
                            }


                            datahelper.insertgrades(put,cname,marks,w,gradetable,count);
                           // datahelper.insertgrades(put,cname,marks,w,gradetable,count);
                            Intent intents = new Intent(getApplicationContext(), BaseActivity.class);
                            startActivity(intents);

                        }



                    }
                });
                //row.addView(button);
        tableLayout.addView(button);

                db.setTransactionSuccessful();




        }
            catch (SQLiteException e)
            {
                e.printStackTrace();

            }
            finally
            {
                db.endTransaction();
                // End the transaction.
                db.close();
                // Close database
            }
            }
        }





