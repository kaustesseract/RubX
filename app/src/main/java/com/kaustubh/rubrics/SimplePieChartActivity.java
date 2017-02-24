package com.kaustubh.rubrics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.*;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
//import com.kaustubh.rubrics.pie.*;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.*;

public class SimplePieChartActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_pie_chart);

        double x , y;

        x = -5;

        GraphView simplepie = (GraphView) findViewById(R.id.simplexy);
        series = new LineGraphSeries<DataPoint>();
        for(int i = 0; i<100 ; i++)
        {
            x=x+0.1;
            y = Math.sin(x);
            series.appendData(new DataPoint(x,y) , true, 100);
        }
        simplepie.addSeries(series);


    }
}
