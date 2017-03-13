package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateRubrics extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_rubrics);

        Button bt = (Button) findViewById(R.id.submitst);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText rub = (EditText) findViewById(R.id.rubric);
                String rubr = rub.getText().toString();

                EditText grades = (EditText) findViewById(R.id.editText4);
                int grade = Integer.parseInt(grades.getText().toString());

                /*EditText highs = (EditText) findViewById(R.id.high);
                int high = Integer.parseInt(highs.getText().toString());*/




                Contact3 c3 = new Contact3();
                c3.setRubric(rubr);
                c3.setGrade(grade);
               // c3.setLow(low);
                //c3.setHigh(high);
                //c3.setLimit(limi);
                helper.insertrubrics(c3);

                String row = rubr+"row";
               // String column = rubr+"column";

                helper.createrow(row);
               // helper.createcol(column);

                Intent i = new Intent(getApplicationContext(), EnterCol.class);
                i.putExtra("rubr",rubr);
                // Toast.makeText(getApplicationContext(), "Id is "+column , Toast.LENGTH_LONG).show();
                startActivity(i);
                finish();




            }
        });
    }
}
