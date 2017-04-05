package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.color.black;
import static android.R.color.white;

public class Reneter extends AppCompatActivity {
DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reneter);

        Bundle bundle = getIntent().getExtras();
        final String question = bundle.getString("question");

        TextView questions = (TextView) findViewById(R.id.ques);

        questions.setText(question);

        final EditText ans = (EditText) findViewById(R.id.ans);

        Button bt = (Button) findViewById(R.id.submit);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( ans.getText().toString().trim().equals("") )
                {
                    ans.setError( "Enter answer" );


                    ans.setHint("Enter answer");

                    ans.setHintTextColor(getResources().getColor(black));
                }
                else {

                    String anss = ans.getText().toString();
                    String answers = helper.matchanswer(anss);

                    if (answers.equals("not")) {
                        Toast.makeText(getApplicationContext(), "The answer is incorrect", Toast.LENGTH_LONG).show();
                    } else {
                        Intent i = new Intent(getApplicationContext(), Renenterpass.class);
                        i.putExtra("answer", anss);
                        startActivity(i);
                        finish();
                    }
                }

            }
        });
    }
}
