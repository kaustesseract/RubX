package com.kaustubh.rubrics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class Barchart extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);
        BarChart bar  = (BarChart) findViewById(R.id.bargraph);
        ArrayList<BarEntry> barchart = new ArrayList<>();
        barchart.add(new BarEntry(0f,44f));
        barchart.add(new BarEntry(1f,88f));
        barchart.add(new BarEntry(2f,66f));
        barchart.add(new BarEntry(3f,12f));
        barchart.add(new BarEntry(4f,19f));
        barchart.add(new BarEntry(5f,91f));
        BarDataSet bardata = new BarDataSet(barchart,"bardata");



        final ArrayList<String> Date = new ArrayList<>();
        Date.add("2/7/2016");
        Date.add("3/7/2016");
        Date.add("5/7/2016");
        Date.add("6/7/2016");
        Date.add("23/7/2016");
        Date.add("30/7/2016");
      //  final ArrayList<String> Date = helper.getspinnerdata();

        ArrayList<IBarDataSet> date = new ArrayList<>();
      date.add((IBarDataSet)bardata);

        BarData barData = new BarData(bardata);


        bar.setData(barData);
      //  bar.setFitBars(true);
      //  bar.setBorderWidth(1f);
        bar.setTouchEnabled(true);
        bar.setDragEnabled(true);
        bar.setScaleEnabled(true);


        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return Date.get((int)value);
            }

            // we don't draw numbers, so no decimal digits needed

        };


        XAxis xAxis = bar.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);




    }
}
