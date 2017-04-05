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

public class Forgetpassword extends AppCompatActivity {
DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

       final  EditText unames = (EditText) findViewById(R.id.funame);
        final EditText emails = (EditText) findViewById(R.id.femail);

        Button forget = (Button) findViewById(R.id.submit);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( unames.getText().toString().trim().equals("") && emails.getText().toString().trim().equals(""))
                {
                    unames.setError( "Username is required!" );
                    emails.setError( "Email is required!" );


                    unames.setHintTextColor(getResources().getColor(black));
                    emails.setHintTextColor(getResources().getColor(black));
                }

                else if(unames.getText().toString().trim().equals("") || emails.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter all fields",Toast.LENGTH_SHORT).show();
                }

                else {

                    String uname = unames.getText().toString();
                    String email = emails.getText().toString();

                    String question = helper.getunamemail(uname, email);

                    if (question.equals("not")) {
                        Toast.makeText(getApplicationContext(), "The useranme or email is incorrect", Toast.LENGTH_LONG).show();
                    } else {
                        Intent i = new Intent(getApplicationContext(), Reneter.class);
                        i.putExtra("question", question);
                        startActivity(i);
                        finish();
                    }


                }
            }
        });


    }
}
