package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    private Logout session;
    int tid;

    void image()
    {
    //    ImageView img = (ImageView) findViewById(R.id.rubrics);
     //   img.animate().translationXBy(-1000f).setDuration(3000);


    }

   /* public void click(View view)
    {
        if(view.getId() == R.id.submit)
        {
            EditText a = (EditText) findViewById(R.id.user);
            String str = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.pass);
            String pass = b.getText().toString();
            session = new Logout(this);

            String password = helper.search(str);
            tid = helper.searchId(str);
            //String passn = helper.search(password);

            if (pass.equals(password))
            {
                session.setLgi(true);
                Intent i = new Intent(getApplicationContext(), BaseActivity.class);
              //  Toast.makeText(getApplicationContext(), "Id is "+id , Toast.LENGTH_LONG).show();
                startActivity(i);
                finish();




            }
            else
            {
                Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_LONG).show();


            }
        }



    }*/

    public void register(View view)
    {
        Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(i);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button submit = (Button)findViewById(R.id.submitst);
        session = new Logout(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText a = (EditText) findViewById(R.id.user);
                String str = a.getText().toString();
                EditText b = (EditText) findViewById(R.id.pass);
                String pass = b.getText().toString();
                String password = helper.search(str);
                int pid = helper.searchId(str);

                if (pass.equals(password))
                {
                    session.setLgi(true);
                    Intent i = new Intent(getApplicationContext(), BaseActivity.class);
                    i.putExtra("pid",pid);
                   // Toast.makeText(getApplicationContext(), "Id is "+pid , Toast.LENGTH_LONG).show();
                    startActivity(i);
                    finish();




                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_LONG).show();



                }
            }

        });







       // Intent i = new Intent(getApplicationContext(), BaseActivity.class);
       // i.putExtra("tid",tid);
        //startActivity(i);



       // img.setTranslationX(1000f);



      //  Button  donebutton = (Button) findViewById(R.id.button);
       // donebutton.animate().alpha(1f).setDuration(6000);
        //donebutton.setVisibility(View.VISIBLE);
    }

}
