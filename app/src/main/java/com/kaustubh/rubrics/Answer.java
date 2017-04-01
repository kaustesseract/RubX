package com.kaustubh.rubrics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Answer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Bundle bundle = getIntent().getExtras();
        final String uname = bundle.getString("uname");
        String text = bundle.getString("text");


        TextView username = (TextView) findViewById(R.id.choose);
        final EditText password = (EditText) findViewById(R.id.answer);

        username.setText(text);

        Button bt = (Button) findViewById(R.id.submit);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                



            }
        });


    }
}
