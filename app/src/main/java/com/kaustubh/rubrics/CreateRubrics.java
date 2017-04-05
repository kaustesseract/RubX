package com.kaustubh.rubrics;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.color.black;
import static android.R.color.white;

public class CreateRubrics extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_rubrics);

        Button bt = (Button) findViewById(R.id.submitst);

        SharedPreferences pref = getSharedPreferences("info.conf", Context.MODE_PRIVATE);
        final int pid = pref.getInt("pid",0);
       // Toast.makeText(this, pid+"" , Toast.LENGTH_SHORT).show();





            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    final EditText rub = (EditText) findViewById(R.id.rubric);

                    final EditText grades = (EditText) findViewById(R.id.editText4);

                    final EditText number = (EditText) findViewById(R.id.number);

                    if( rub.getText().toString().trim().equals("") && grades.getText().toString().trim().equals("") &&  number.getText().toString().trim().equals(""))
                    {
                        rub.setError( "Rubrics name is required!" );
                        grades.setError( "Enter grade!" );
                        number.setError( "Enter number of criteria!" );

                        rub.setHint("Rubrics name");
                        grades.setHint("Enter grades");
                        number.setHint("Enter number of criterias");

                        rub.setHintTextColor(getResources().getColor(black));
                        grades.setHintTextColor(getResources().getColor(black));
                        number.setHintTextColor(getResources().getColor(black));
                    }

                    else if(rub.getText().toString().trim().equals("") || grades.getText().toString().trim().equals("") ||  number.getText().toString().trim().equals(""))
                    {
                        Toast.makeText(getApplicationContext(),"Enter all fields",Toast.LENGTH_SHORT).show();
                    }
                    else {

                        String rubr = rub.getText().toString().replaceAll("[-+.^:,~!@#$%^&*()_=;<>/`]", "");


                        int grade = Integer.parseInt(grades.getText().toString());

                /*EditText highs = (EditText) findViewById(R.id.high);
                int high = Integer.parseInt(highs.getText().toString());*/


                        int numbers = Integer.parseInt(number.getText().toString());


                        Contact3 c3 = new Contact3();
                        c3.setRubric(rubr);
                        c3.setTid(pid);
                        c3.setGrade(grade);
                        // c3.setLow(low);
                        //c3.setHigh(high);
                        //c3.setLimit(limi);
                        helper.insertrubrics(c3);

                        String row = rubr + "_row_" + pid;
                        // String column = rubr+"column";

                        String table = helper.searchtable(row);

                        if (row.equals(table))

                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(CreateRubrics.this);
                            builder.setMessage("Rubrics is already created. Do you want to edit it ?").setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent i = new Intent(getApplicationContext(), BaseActivity.class);
                                            startActivity(i);
                                            finish();
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.setTitle("ALERT !!");
                            alertDialog.show();

                        } else {

                            // helper.createcol(column);
                            helper.createrow(row);

                            Intent i = new Intent(getApplicationContext(), Add_Type_Rubrics.class);
                            i.putExtra("rubr", rubr);
                            i.putExtra("count", numbers);
                            i.putExtra("grade", grade);
                            // Toast.makeText(getApplicationContext(), "Id is "+column , Toast.LENGTH_LONG).show();
                            startActivity(i);
                            finish();

                        }
                    }

                }
            });

    }
}
