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
            TextView tv2 = new TextView(this);
            tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setGravity(START);
            tv2.setTextSize(18);
            tv2.setTextColor(Color.BLUE);
            tv2.setPadding(0, 0, 0, 20);
            tv2.setText(c);
            rowHeader.addView(tv2);
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
                    String rows_name= cursor.getString(cursor.getColumnIndex("rows"));
                    TableRow row = new TableRow(context);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));

                    String[] colText={rows_name};
                    for(String text:colText) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.START);
                        tv.setTextColor(Color.WHITE);
                        tv.setTextSize(25);
                        //tv.setPadding(5, 5, 5, 5);
                        tv.setText(text);



                        SeekBar view1 = new SeekBar(this);
                       // view1 = seekbar_inflate.inflate(R.layout.mysxm_control_coloumn, null, false);
                        view1.setLayoutParams (new TableRow.LayoutParams(
                                TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        view1.setProgress(0);
                        view1.setPadding(25,25,25,25);
                        // view1.setMinimumHeight(300);
                        // view1.setMax(150);
                        //    view1.setMinimumWidth(280);



                        TextView tv1 = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));

                        tv1.setGravity(Gravity.RIGHT);
                        tv1.setTextSize(25);
                        tv1.setTextColor(Color.WHITE);

                        tv1.setText("Excellent");



                        row.addView(tv,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                        row.addView(view1,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
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

    }
}
