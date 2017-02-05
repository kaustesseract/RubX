package com.kaustubh.rubrics;

import android.content.Context;
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

import static android.R.attr.thumb;
import static android.view.Gravity.CENTER;

public class view_rubrics extends AppCompatActivity {
  //  DatabaseHelper datahelper = new DatabaseHelper(this);

    Context context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rubrics);

        Bundle bundle = getIntent().getExtras();
        String rubrics = bundle.getString("text");
        String rubric = rubrics+"row";
        context = this;
        DatabaseHelper datahelper = new DatabaseHelper(context);

        TableLayout tableLayout=(TableLayout)findViewById(R.id.tablelayout);
        // Add header row

        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText={"NAME OF THE STUDENT"};
        for(String c:headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(CENTER);
            tv.setTextSize(25);
           // tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);
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
                    int row_id= cursor.getInt(cursor.getColumnIndex("row_id"));
                    String rows_name= cursor.getString(cursor.getColumnIndex("rows"));
                   // String outlet_type= cursor.getString(cursor.getColumnIndex("outlet_type"));

                    // dara rows
                    TableRow row = new TableRow(context);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    String[] colText={rows_name};
                    for(String text:colText) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));


                        SeekBar view1 = new SeekBar(this);
                        view1.setMinimumWidth(400);
                        view1.setProgress(300);
                       // view1 = seekbar_inflate.inflate(R.layout.mysxm_control_coloumn, null, false);
                       /* view1.setLayoutParams (new TableRow.LayoutParams(
                                TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));*/

                    //    TableRow.LayoutParams ob = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                      //          TableRow.LayoutParams.WRAP_CONTENT,
                        //        1);
                        //view1.setMax(30000);


                        view1.setProgress(0);

                        view1.setPadding(25,25,25,25);




                        tv.setGravity(Gravity.START);
                        tv.setTextSize(25);
                        tv.setPadding(25, 25, 25, 25);
                        tv.setText(text);
                        row.addView(tv);
                        row.addView(view1);
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
