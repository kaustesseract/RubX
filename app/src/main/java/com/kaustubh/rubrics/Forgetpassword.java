package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                String uname = unames.getText().toString();
                String email = emails.getText().toString();

                String question = helper.getunamemail(uname , email);

                if(question.equals("not"))
                {
                    Toast.makeText(getApplicationContext(),"The useranme or email is incorrect" , Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent i = new Intent(getApplicationContext(),Reneter.class);
                    i.putExtra("question",question);
                    startActivity(i);
                    finish();
                }


            }
        });


    }
}
