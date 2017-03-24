package com.kaustubh.rubrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Set_TH extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set__th);
        Bundle bundle = getIntent().getExtras();
        final String gradetable = bundle.getString("gradetable");



        Button submitst = (Button) findViewById(R.id.submitst);

        submitst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText seths = (EditText) findViewById(R.id.seth);
                int seth = Integer.parseInt(seths.getText().toString());

                Intent i = new Intent(getApplicationContext(), BubbleChartActivity.class);
                i.putExtra("gradetable",gradetable);
                i.putExtra("seth",seth);
                startActivity(i);

            }
        });
    }
}
