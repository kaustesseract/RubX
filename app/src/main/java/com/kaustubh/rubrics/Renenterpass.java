package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Renenterpass extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renenterpass);
        Bundle bundle = getIntent().getExtras();
        final String answer = bundle.getString("answer");

        final EditText uname = (EditText) findViewById(R.id.uname);
        final EditText pass = (EditText) findViewById(R.id.upass);

        Button bt = (Button) findViewById(R.id.subs);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String unames = uname.getText().toString();
                String passs = pass.getText().toString();

                db.updatepass(answer,unames,passs);
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
             //   i.putExtra("answer",anss);
                startActivity(i);
                finish();

            }
        });


    }
}
