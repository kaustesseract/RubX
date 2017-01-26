package com.kaustubh.rubrics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Class_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);



/*
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

        for (int i = 0; i < 4; i++) {
            Button btn = new Button(this);
            btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            btn.setId(i);
            btn.setText("Button " + i);

            btn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                   // Toast.makeText(MainActivity.this, "Button " + v.getId() + " clicked", Toast.LENGTH_SHORT).show();

                }
            });

            ll.addView(btn);

        }
    }

}*/


}
}
