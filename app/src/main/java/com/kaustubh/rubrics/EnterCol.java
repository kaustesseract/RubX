package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterCol extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_col);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("rubr");

       // final String mcol = message + "column";
        final String mrow = message + "row";
        // final String mcolumn = message+"_column";

        Button bt = (Button) findViewById(R.id.submit);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText rows = (EditText) findViewById(R.id.crow);
                String row = rows.getText().toString();

                EditText lweights = (EditText) findViewById(R.id.loweight);
                int lweight = Integer.parseInt(lweights.getText().toString());

                EditText hweights = (EditText) findViewById(R.id.hweight);
                int hweight = Integer.parseInt(hweights.getText().toString());

               /* EditText priority = (EditText) findViewById(R.id.editText6);
                int prior = Integer.parseInt(priority.getText().toString());*/

                Contact4 c4 = new Contact4();
                c4.setRow(row);
                c4.setLweight(lweight);
                c4.setHweight(hweight);
                helper.insertRow(c4, mrow);
                rows.setText("");
                lweights.setText("");
                hweights.setText("");
            }
        });

       /* Button next = (Button)findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EnterRowCol.class);
                i.putExtra("rubr",mrow);
                // Toast.makeText(getApplicationContext(), "Id is "+pid , Toast.LENGTH_LONG).show();
                startActivity(i);
                finish();
            }
        });*/
    }


}
