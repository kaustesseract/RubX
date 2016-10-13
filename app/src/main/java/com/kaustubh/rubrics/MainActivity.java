package com.kaustubh.rubrics;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    void image()
    {
    //    ImageView img = (ImageView) findViewById(R.id.rubrics);
     //   img.animate().translationXBy(-1000f).setDuration(3000);


    }

    public void click(View view)
    {
        final EditText user = (EditText) findViewById(R.id.user);
        final EditText pass = (EditText) findViewById(R.id.pass);
       // String username = user.getText().toString();
       // String password = pass.getText().toString();

        if(user.getText().toString().equals("ka") && pass.getText().toString().equals("ka"))
        {
            Intent i = new Intent(getApplicationContext(),BaseActivity.class);
            startActivity(i);


        }

        else
        {
            Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_LONG).show();


        }



    }

    public void register(View view)
    {
        Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(i);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       // img.setTranslationX(1000f);



      //  Button  donebutton = (Button) findViewById(R.id.button);
       // donebutton.animate().alpha(1f).setDuration(6000);
        //donebutton.setVisibility(View.VISIBLE);
    }
}
