package com.kaustubh.rubrics;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.START;

public class Show_AllGrades extends AppCompatActivity {

    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__all_grades);
        Bundle bundle = getIntent().getExtras();
        String course = bundle.getString("course");
        String clas = bundle.getString("class");
        String assname = bundle.getString("assname");

        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
     //   Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();


        int coid = databaseHelper.searchcoid(course);
        int classid = databaseHelper.searchcid(clas);


        String assignmenttable = "Course_"+course+"_"+coid+"_"+pid;

        int assid = databaseHelper.searchassid(assname,assignmenttable);

        String gradetable = "Studentgrade_"+classid+"_"+coid+"_"+assid+"_"+pid;

        String tableattr[] = databaseHelper.getgradeparam(gradetable);

        int getgradecolumncount = databaseHelper.getgradecolumncount(gradetable);

        int o = getgradecolumncount - 1;

        String a[] = new String[o];

        int rows[] = new int[o];


        int k=0;
        int t;

        for(int j=1;j<getgradecolumncount;j++)
        {
            a[k] = tableattr[j];
            k++;

        }

        TableLayout tableLayout = (TableLayout)findViewById(R.id.showtable);

        TableRow rowHeader = new TableRow(this);

        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        for(String c:a)
        {
            TextView tv2 = new TextView(this);
            tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setGravity(CENTER);
            tv2.setTextSize(18);
            tv2.setTextColor(Color.BLUE);
            tv2.setPadding(0, 0, 0, 25);
            tv2.setText(c);
            rowHeader.addView(tv2,new TableRow.LayoutParams(1, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        }
        tableLayout.addView(rowHeader);

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();

        try
        {
            String selectQuery = "SELECT * FROM "+gradetable;
            Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.getCount() >0) {
                while (cursor.moveToNext()) {


                    for (t = 0; t < o; t++) {
                        rows[t] = cursor.getInt(cursor.getColumnIndex(a[t]));
                     //   Toast.makeText(getApplicationContext(),rows[t],Toast.LENGTH_SHORT);
                    }
                    TableRow row = new TableRow(this);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    for (int text : rows) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.START);
                        tv.setTextColor(Color.BLACK);
                        tv.setTextSize(25);
                        tv.setPadding(0, 0, 0, 80);
                        tv.setText(Integer.toString(text));


                        row.addView(tv, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

                    }
                    tableLayout.addView(row);

                    //  row.addView(view1,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                    //  row.addView(tv1);
                }
            }
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
