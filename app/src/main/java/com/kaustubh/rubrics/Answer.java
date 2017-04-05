package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.color.black;
import static android.R.color.white;

public class Answer extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Bundle bundle = getIntent().getExtras();
        final String uname = bundle.getString("uname");
        final String text = bundle.getString("text");


        final TextView username = (TextView) findViewById(R.id.choose);
        final EditText password = (EditText) findViewById(R.id.answer);

        username.setText(text);

        Button bt = (Button) findViewById(R.id.submit);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if( username.getText().toString().trim().equals("") )
                {
                    username.setError( "Enter answer" );


                    username.setHint("Enter answer");

                    username.setHintTextColor(getResources().getColor(black));
                }

                else {


                    String pass = password.getText().toString();

                    Contact c = new Contact();

                    c.setForget(text);
                    c.setAnswer(pass);
                    //   c.setName(uname);

                    db.insertanswerques(c, uname);

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    ;
                    startActivity(i);
                    finish();


                }




            }
        });


    }
}
