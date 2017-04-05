package com.kaustubh.rubrics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Generate_Assignment extends AppCompatActivity {

    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate__assignment);

        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
       //Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();


        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        DatabaseHelper db = new DatabaseHelper(this);
            Bundle bundle = getIntent().getExtras();
            final String course = bundle.getString("course");
            final String clas = bundle.getString("class");
            int coid = db.searchcoid(course);

            String table = "Course"+"_"+course+"_"+coid+"_"+pid;
            db.open();
            Cursor cursor = db.searchassign(table);
            startManagingCursor(cursor);
            String[] ar = new String[]{DatabaseHelper.COLUMN_ASSNAME};
            int[] name = new int[]{R.id.assig};
            SimpleCursorAdapter myadapter =
                    new SimpleCursorAdapter(
                            this,
                            R.layout.show_assignment,
                            cursor,
                            ar,
                            name,
                            0
                    );
            ListView ll = (ListView)findViewById(R.id.gass);
            ll.setAdapter(myadapter);

            ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    TextView textView = (TextView) view.findViewById(R.id.assig);
                    //  String list = (ll.getItemAtPosition(position));
                    String assname = textView.getText().toString();

                    Intent i = new Intent(getApplicationContext(), Generate_pdf.class);
                    i.putExtra("course", course);
                    i.putExtra("class", clas);
                    i.putExtra("assname", assname);
                    startActivity(i);
                    finish();
                }

            });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, BaseActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }



        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent j = new Intent(getApplicationContext(), BaseActivity.class);
            startActivity(j);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
