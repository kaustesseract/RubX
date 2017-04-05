package com.kaustubh.rubrics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.color.black;
import static android.R.color.white;

public class EnterCol extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

   // Spinner spinner;
    String lweight;
    int lweights;

    String hweight;
    int hweights;
    Integer item[];
    ArrayAdapter<Integer> adapter1;
    Integer[] q;

  //  int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_col);

        Bundle bundle = getIntent().getExtras();
        final String message = bundle.getString("rubr");
        final int count = bundle.getInt("count");
        final int i = bundle.getInt("int");
        final int grade = bundle.getInt("grade");

        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
       // Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();


        Spinner spinner = (Spinner) findViewById(R.id.loweight);

        Integer items[] = new Integer[]{0};
        //   String items[] = {"0"};

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(EnterCol.this, R.layout.spinner_layout, R.id.txt, items);
        spinner.setAdapter(adapter);

      /*  spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lweight =parent.getItemAtPosition(position).toString();


               // Toast.makeText(getApplicationContext(),lweight,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                lweight = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        q = new Integer[grade];
        ArrayList<Integer> list = new ArrayList<>();
        for (int y = 1; y <= grade; y++) {

            list.add(new Integer(y));


            //  Toast.makeText(getApplicationContext(),q[y]+"",Toast.LENGTH_SHORT).show();

        }


        Spinner spinner1 = (Spinner) findViewById(R.id.hweight);


        //  Toast.makeText(getApplicationContext(),q[y]+"",Toast.LENGTH_SHORT).show();


        item = new Integer[]{0};
        adapter1 = new ArrayAdapter<Integer>(EnterCol.this, R.layout.spinner_layout, R.id.txt, list);

        spinner1.setAdapter(adapter1);


        //   String items[] = {"0"};


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hweight = parent.getItemAtPosition(position).toString();


                // Toast.makeText(getApplicationContext(),lweight,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //


        // final String mcolumn = message+"_column";

        Button bt = (Button) findViewById(R.id.submitst);


            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final EditText rows = (EditText) findViewById(R.id.crow);

                    if (rows.getText().toString().trim().equals("")) {
                        rows.setError("Enter rubrics criteria!");


                        rows.setHint("Criteria(Aim,Theory)");
                        rows.setHintTextColor(getResources().getColor(black));
                    }

                    else {

                        int k = i;
                        int grades = grade;

                        lweights = Integer.parseInt(lweight);
//                Toast.makeText(getApplicationContext(),lweights+"",Toast.LENGTH_LONG).show();

                        hweights = Integer.parseInt(hweight);
                        //              Toast.makeText(getApplicationContext(),hweights+"",Toast.LENGTH_SHORT).show();


                        // final String mcol = message + "column";
                        final String mrow = message + "_row_" + pid;


                        int next = grades - hweights;


                        //  spinner = (Spinner) findViewById(R.id.hweight);


                        String row = rows.getText().toString();

                        //     EditText lweights = (EditText) findViewById(R.id.loweight);
                        //   int lweight = Integer.parseInt(lweights.getText().toString());

                        // EditText hweights = (EditText) findViewById(R.id.hweight);
                        //int hweight = Integer.parseInt(hweights.getText().toString());

               /* EditText priority = (EditText) findViewById(R.id.editText6);
                int prior = Integer.parseInt(priority.getText().toString());*/

                        Contact4 c4 = new Contact4();
                        c4.setRow(row);
                        c4.setLweight(lweights);
                        c4.setHweight(hweights);
                        helper.insertRow(c4, mrow);


                        if (k != count) {
                            k++;
                            Intent j = new Intent(getApplicationContext(), EnterCol.class);
                            j.putExtra("rubr", message);
                            j.putExtra("count", count);
                            j.putExtra("int", k);
                            j.putExtra("grade", next);
                            startActivity(j);
                            finish();


                        } else {
                            Intent t = new Intent(getApplicationContext(), MainRubrics.class);
                            startActivity(t);
                            finish();
                        }
                        //      rows.setText("");
                        //    lweights.setText("");
                        //  hweights.setText("");


                    }
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
