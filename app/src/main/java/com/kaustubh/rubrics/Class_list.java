package com.kaustubh.rubrics;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Class_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        DatabaseHelper db = new DatabaseHelper(this);

        db.open();
        Cursor cursor = db.showclasslist();
        startManagingCursor(cursor);
        String[] ar = new String[]{DatabaseHelper.COLUMN_CLASS};
        int[] name = new int[]{R.id.cls_name};

        SimpleCursorAdapter myadapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.display_classes,
                        cursor,
                        ar,
                        name,
                        0
                );
        ListView ll = (ListView)findViewById(R.id.classlist);
        ll.setAdapter(myadapter);
        db.close();
      //  show.setTypeface(null, Typeface.ITALIC);
        //show.setText(data);





}
}
