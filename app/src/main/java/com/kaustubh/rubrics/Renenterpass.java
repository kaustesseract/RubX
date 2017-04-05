package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.color.black;
import static android.R.color.white;

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

                if( uname.getText().toString().trim().equals("") && pass.getText().toString().trim().equals(""))
                {
                    uname.setError( "Username is required!" );
                    pass.setError( "Password is required!" );

                    uname.setHint("Username");
                    pass.setHint("Password");
                    uname.setHintTextColor(getResources().getColor(black));
                    pass.setHintTextColor(getResources().getColor(black));
                }

                else if(uname.getText().toString().trim().equals("") || pass.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter all fields",Toast.LENGTH_SHORT).show();
                }

                else {
                    String unames = uname.getText().toString();
                    String passs = pass.getText().toString();

                    db.updatepass(answer, unames, passs);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    //   i.putExtra("answer",anss);
                    startActivity(i);
                    finish();
                }

            }
        });


    }
}
