package com.kaustubh.rubrics;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static android.view.Gravity.START;

public class Show_Assignment extends AppCompatActivity {


    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__assignment);
        Bundle bundle = getIntent().getExtras();
        String asstable = bundle.getString("asstable");

        context = this;
        DatabaseHelper datahelper = new DatabaseHelper(context);

        TableLayout tableLayout=(TableLayout)findViewById(R.id.table);

        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText={"ASSIGNMENT NAME" ," LAST DATE "," DEADLINE "};
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
            String selectQuery = "SELECT * FROM "+asstable;
            Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {

                    String ass_name= cursor.getString(cursor.getColumnIndex("ass_name"));
                    int day = cursor.getInt(cursor.getColumnIndex("day"));
                    int month = cursor.getInt(cursor.getColumnIndex("month"));
                    int year = cursor.getInt(cursor.getColumnIndex("year"));
                    int hour = cursor.getInt(cursor.getColumnIndex("hour"));
                    int minute = cursor.getInt(cursor.getColumnIndex("minute"));

                    TableRow row = new TableRow(context);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));

                    String Lastdate = day+"/"+month+1+"/"+year;
                    String deadline = hour+" : "+minute;

                    String[] colText={ass_name,Lastdate,deadline};

                    for(String text:colText) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.START);
                        tv.setTextColor(Color.WHITE);
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
