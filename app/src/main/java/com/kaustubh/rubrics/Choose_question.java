package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Choose_question extends AppCompatActivity {

    private ActionBar actionBar;
    ArrayList<String> selectedItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_question);
        Bundle bundle = getIntent().getExtras();
        final String uname = bundle.getString("uname");
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        ListView list = (ListView) findViewById(R.id.forget);
        String[] items = {"What is your favourite car ?", "What is your favourite food ?", "Who is your favourite player ?", "What is your favourite TV series ?"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.display_classes,R.id.cls_name,items);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view.findViewById(R.id.cls_name);
                //  String list = (ll.getItemAtPosition(position));
                String text = textView.getText().toString();
                Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), Answer.class);
                i.putExtra("uname",uname);
                i.putExtra("text",text);
                startActivity(i);
                finish();

            }
        });






    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, RegisterActivity.class);
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
            Intent j = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(j);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
