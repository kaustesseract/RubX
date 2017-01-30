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

        final String mrow = message+"_row";
        final String mcolumn = message+"_column";

        Button bt = (Button) findViewById(R.id.submit);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText rows = (EditText) findViewById(R.id.row);
                String row = rows.getText().toString();

                EditText columns = (EditText) findViewById(R.id.column);
                String column = columns.getText().toString();

                EditText weights = (EditText) findViewById(R.id.weight);
                String weight = weights.getText().toString();

                Contact4 c4 = new Contact4();
                c4.setRow(row);
                helper.insertrow(c4,mrow);

                Contact4 c5 = new Contact4();
                c5.setColumn(column);
                helper.insertcolumn(c5,mcolumn,weight);

                rows.setText("");
                columns.setText("");
                weights.setText("");



            }
        });


    }
}
