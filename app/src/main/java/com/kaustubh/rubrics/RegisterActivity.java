package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
    }

    public void signup(View view)
    {
        if(view.getId()==R.id.register)
        {
            EditText username = (EditText) findViewById(R.id.username);
            EditText password = (EditText) findViewById(R.id.password);
            EditText email = (EditText) findViewById(R.id.email);
            EditText pass2 = (EditText) findViewById(R.id.pass2);

            String uname = username.getText().toString();
            String pass = password.getText().toString();
            String emai= email.getText().toString();
            String pas2 = pass2.getText().toString();

            if(!pass.equals(pas2))
            {
                Toast.makeText(getApplicationContext(), " Both passwords donot match", Toast.LENGTH_LONG).show();
            }

            else {

                Contact c = new Contact();
                c.setName(uname);
                c.setPassword(pass);
                c.setEmail(emai);

                helper.insertcontact(c);

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            }


        }
    }
}
