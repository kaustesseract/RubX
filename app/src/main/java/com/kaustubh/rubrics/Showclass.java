package com.kaustubh.rubrics;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.START;

public class Showclass extends AppCompatActivity {

Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showclass);


        TextView show = (TextView) findViewById(R.id.ru);
      //  DatabaseHelper db = new DatabaseHelper(this);
        Bundle bundle = getIntent().getExtras();
        String cla = bundle.getString("text");
       /* context = this;
        DatabaseHelper datahelper = new DatabaseHelper(context);

       // Toast.makeText(getApplicationContext(), "Id is "+cla , Toast.LENGTH_LONG).show();


       /* db.open();
        String data = db.searchclass(cla);
        //db.close();
        show.setTypeface(null, Typeface.ITALIC);
        show.setText(data);
        TableLayout tableLayout = (TableLayout)findViewById(R.id.tablelayout);


        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText={"ROLL NO.","STUDENT NAME","EMAIL"};
        for(String c:headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);
        }
        tableLayout.addView(rowHeader);

       // context = this;


        SQLiteDatabase db = datahelper.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();



        try
        {
            String selectQuery = "SELECT * FROM "+cla;
            Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {
                    // Read columns data
                    int roll= cursor.getInt(cursor.getColumnIndex("roll"));
                    String student= cursor.getString(cursor.getColumnIndex("student"));
                    String email= cursor.getString(cursor.getColumnIndex("email"));


                    // dara rows
                    TableRow row = new TableRow(context);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    String[] colText={roll+"",student,email};
                    for(String text:colText) {
                        TextView tv1 = new TextView(this);
                        tv1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv1.setGravity(Gravity.CENTER);
                        tv1.setTextSize(16);
                        tv1.setPadding(5, 5, 5, 5);
                        tv1.setText(text);
                        row.addView(tv1);
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



*/




        context = this;
        DatabaseHelper datahelper = new DatabaseHelper(context);

        TableLayout tableLayout=(TableLayout)findViewById(R.id.tablel);

        // Add header row

        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText={"ROLL NO.","NAME","EMAIL"};
        for(String c:headerText) {
            TextView tv2 = new TextView(this);
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

        SQLiteDatabase db = datahelper.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();

        try
        {
            String selectQuery = "SELECT * FROM "+cla;
            Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {
                    // Read columns data
                    // int row_id= cursor.getInt(cursor.getColumnIndex("row_id"));
                    int roll= cursor.getInt(cursor.getColumnIndex("roll"));
                    String student= cursor.getString(cursor.getColumnIndex("student"));
                    String email= cursor.getString(cursor.getColumnIndex("email"));
                    TableRow row = new TableRow(context);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));

                    String[] colText={roll+"",student,email};
                    for(String text:colText) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.START);
                        tv.setTextColor(Color.BLACK);
                        tv.setTextSize(18);
                        tv.setPadding(0, 0, 0, 80);
                        tv.setText(text);



                    //    row.addView(tv);

                        row.addView(tv,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

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
