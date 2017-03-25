package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudentclass extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_studentclass);
        Button submit = (Button)findViewById(R.id.submitst);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          //      Bundle bundle = getIntent().getExtras();
         //       int message = bundle.getInt("pid");
            //    Toast.makeText(getApplicationContext(), "Id is "+message , Toast.LENGTH_LONG).show();

                    EditText a = (EditText) findViewById(R.id.class1);
                    String str = a.getText().toString();

                EditText b = (EditText) findViewById(R.id.totstudent);
                int totalstudent = Integer.parseInt(b.getText().toString());


                helper.createclass(str);
                Contact2 c2 = new Contact2();
                c2.setCname(str);
                c2.setTotalmarks(totalstudent);
               c2.setTid(3);

                helper.inclass(c2);

                    Intent i = new Intent(getApplicationContext(), Types_Of_Addc.class);
                    i.putExtra("str",str);
                    i.putExtra("student",totalstudent);

                    startActivity(i);
                finish();
            }

        });

    }
    /*public void class1(View view) {
        if (view.getId() == R.id.submit) {
            EditText a = (EditText) findViewById(R.id.class1);
            String str = a.getText().toString();
            helper.createclass(str);

            Intent i = new Intent(getApplicationContext(), Addclass.class);
            i.putExtra("str",str);
            startActivity(i);
           // Toast.makeText(getApplicationContext(), "Id is "+id , Toast.LENGTH_LONG).show();


        }
    }*/


}

