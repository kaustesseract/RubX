package com.kaustubh.rubrics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterRowCol extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_row_col);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("rubr");

        final String mrow = message+"row";
        final String mcol = message+"col";

        Button bt = (Button) findViewById(R.id.submitst);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText rows = (EditText) findViewById(R.id.row);
                String row = rows.getText().toString();

                EditText columns = (EditText) findViewById(R.id.col);
                String column = columns.getText().toString();

               /* EditText lweights = (EditText) findViewById(R.id.lweight);
                String lweight = lweights.getText().toString();

                EditText hweights = (EditText) findViewById(R.id.hweight);
                String hweight = hweights.getText().toString();*/

                Contact4 c4 = new Contact4();
                c4.setRow(row);
                helper.insertdrow(c4,mrow);

                Contact4 c5 = new Contact4();
                c5.setColumn(column);
                helper.insertdcol(c5,mcol);

             /*   Contact4 c5 = new Contact4();
                c5.setColumn(column);
                helper.insertcolumn(c5,mcolumn,lweight,hweight);*/

                rows.setText("");
                /*columns.setText("");
                lweights.setText("");
                hweights.setText("");*/



            }
        });


    }
}
