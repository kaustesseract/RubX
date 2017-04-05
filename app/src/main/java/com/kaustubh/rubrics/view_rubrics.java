package com.kaustubh.rubrics;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.thumb;
import static android.view.Gravity.CENTER;
import static android.view.Gravity.HORIZONTAL_GRAVITY_MASK;
import static android.view.Gravity.START;

public class view_rubrics extends AppCompatActivity {
  //  DatabaseHelper datahelper = new DatabaseHelper(this);


    Context context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rubrics);

        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
      //  Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();
        Bundle bundle = getIntent().getExtras();
        String rubrics = bundle.getString("text");
        String rubric = rubrics+"_row_"+pid;
        context = this;
        DatabaseHelper datahelper = new DatabaseHelper(context);

        TableLayout tableLayout=(TableLayout)findViewById(R.id.tablelayout);

        // Add header row

        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText={"CRITERIA","LOW LIMIT","HIGH LIMIT"};
        for(String c:headerText) {
            TextView tv2 = new TextView(this);
            tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setGravity(START);
            tv2.setTextSize(18);
            tv2.setTextColor(Color.BLUE);
            tv2.setPadding(0, 0, 0, 25);
            tv2.setText(c);
            rowHeader.addView(tv2,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        }
        tableLayout.addView(rowHeader);

        SQLiteDatabase db = datahelper.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();

        try
        {
            String selectQuery = "SELECT * FROM "+rubric;
            Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {
                    // Read columns data
                   // int row_id= cursor.getInt(cursor.getColumnIndex("row_id"));
                    String rows_name= cursor.getString(cursor.getColumnIndex("rname"));
                    int lweight= cursor.getInt(cursor.getColumnIndex("lweight"));
                    int hweight= cursor.getInt(cursor.getColumnIndex("hweight"));
                    TableRow row = new TableRow(context);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));

                    String[] colText={rows_name,lweight+"",hweight+""};
                    for(String text:colText) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.START);
                        tv.setTextColor(Color.BLACK);
                        tv.setTextSize(25);
                        tv.setPadding(0, 0, 0, 80);
                        tv.setText(text);


                     /*   final TextView tv1 = new TextView(this);
                        tv1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));

                        tv1.setGravity(Gravity.RIGHT);
                        tv1.setTextSize(25);
                        tv1.setTextColor(Color.WHITE);


                        SeekBar view1 = new SeekBar(this);
                       // view1 = seekbar_inflate.inflate(R.layout.mysxm_control_coloumn, null, false);
                        view1.setLayoutParams (new TableRow.LayoutParams(
                                TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        view1.setProgress(0);
                        view1.setPadding(25,25,25,25);
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
                        });*/



                        //row.addView(tv);

                        row.addView(tv,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                      //  row.addView(view1,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                      //  row.addView(tv1);
                    }
                    tableLayout.addView(row);

                }

            }
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
