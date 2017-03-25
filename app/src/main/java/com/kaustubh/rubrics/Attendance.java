package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Attendance extends AppCompatActivity {

    private ActionBar actionBar;
    ArrayList<String> selectedItems = new ArrayList<>();
    DatabaseHelper db = new DatabaseHelper(this);
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        final String message = bundle.getString("text");

         count = db.getstudentcount(message);

        ListView list = (ListView) findViewById(R.id.check_list);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
      //  String[] items = {"Kaustubh", "Sushant", "Manish", "Kunal"};
        String[] items = db.getstudentname(message);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.checkbox, R.id.itm, items);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemselected = ((TextView) view).getText().toString();
                if (selectedItems.contains(itemselected)) {
                    selectedItems.remove(itemselected);

                }
                else
                {
                    selectedItems.add(itemselected);
                }

            }
        });
    }

    public void selected(View view)
    {

        int i=0;
        //String items = "";
        String[] items = new String[count];
        for(String item:selectedItems)
        {
                items[i]= item;
            i++;
        }
        for(int j=0;j<i;j++) {
            Toast.makeText(getApplicationContext(), items[j], Toast.LENGTH_SHORT).show();
        }
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
            Intent j = new Intent(getApplicationContext(), MainAttendance.class);
            startActivity(j);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
