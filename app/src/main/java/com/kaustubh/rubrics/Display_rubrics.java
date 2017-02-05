package com.kaustubh.rubrics;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Display_rubrics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_rubrics);
        DatabaseHelper db = new DatabaseHelper(this);

        db.open();
        Cursor cursor = db.showrubricslist();
        startManagingCursor(cursor);
        String[] ar = new String[]{DatabaseHelper.COLUMN_RNAME};
        int[] name = new int[]{R.id.show};

        SimpleCursorAdapter myadapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.show_rubrics,
                        cursor,
                        ar,
                        name,
                        0
                );
        ListView ll = (ListView)findViewById(R.id.rubri);
        ll.setAdapter(myadapter);
    }
}
