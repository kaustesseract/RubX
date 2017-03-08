package com.kaustubh.rubrics;

import android.app.ActivityManager;
import android.content.Context;
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

import static android.view.Gravity.START;

public class Start_Grading extends AppCompatActivity {

    int i=0;
    Context context;
    String sname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__grading);







        context = this;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Bundle bundle = getIntent().getExtras();
                String rubname = bundle.getString("rubname");
                final String clas = bundle.getString("class");

                DatabaseHelper datahelper = new DatabaseHelper(context);
                TableLayout tableLayout = (TableLayout) findViewById(R.id.startgrading);

                int j = datahelper.getstudentcount(clas);




        while (i < j) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sname = datahelper.getstudentname(i, clas);


            TableRow rowHeader = new TableRow(context);
            rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
            rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            String[] headerText = {"Name"};
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

            Button gsubmit = (Button) findViewById(R.id.gsubmit);

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
                    }*/
                    i++;

                }
            });


        }


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
                        TableRow row = new TableRow(context);
                        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                                TableLayout.LayoutParams.WRAP_CONTENT));
                        String[] colText = {rows_name};
                        for (String text : colText) {
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

                            SeekBar view1 = new SeekBar(context);
                            // view1 = seekbar_inflate.inflate(R.layout.mysxm_control_coloumn, null, false);
                            view1.setLayoutParams(new TableRow.LayoutParams(
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.WRAP_CONTENT));
                            view1.setProgress(0);
                            view1.setPadding(25, 25, 25, 25);
                            // view1.setMinimumHeight(300);
                            view1.setMax(50);
                            //    view1.setMinimumWidth(280);


                            view1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                @Override
                                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                    tv1.setText(String.valueOf(progress));
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
                        }
                        tableLayout.addView(row);

                    }

                }


      /*  Button button = new Button(this);
        button.setText("submit");
        button.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,         TableRow.LayoutParams.WRAP_CONTENT));
        tableLayout.addView(button);*/

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
        };
        Thread myThread = new Thread(runnable);
        myThread.start();


    }
}
