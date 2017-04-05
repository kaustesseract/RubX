package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.color.black;

public class Set_TH extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set__th);
        Bundle bundle = getIntent().getExtras();
        final String gradetable = bundle.getString("gradetable");


        final EditText seths = (EditText) findViewById(R.id.seth);
        Button submitst = (Button) findViewById(R.id.submitst);

        submitst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(seths.getText().toString().trim().equals(""))
                {
                    seths.setError( "Enter marks!" );

                    seths.setHintTextColor(getResources().getColor(black));
                }

                else {

                    int seth = Integer.parseInt(seths.getText().toString());

                    Intent i = new Intent(getApplicationContext(), BubbleChartActivity.class);
                    i.putExtra("gradetable", gradetable);
                    i.putExtra("seth", seth);
                    startActivity(i);
                }

            }
        });
    }
}
