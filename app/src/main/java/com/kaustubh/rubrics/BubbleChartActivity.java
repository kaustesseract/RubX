package com.kaustubh.rubrics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.*;
import android.graphics.*;
import android.os.*;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;


public class BubbleChartActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    private float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
    private String[] xData = {"Mitch", "Jessica" , "Mohammad" , "Kelsey", "Sam", "Robert", "Ashley"};
    DatabaseHelper db = new DatabaseHelper(this);

    int lower=0;
    int upper = 0;
    float lowers;
    float uppers;
            PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_chart);
        Bundle bundle = getIntent().getExtras();

        String gradetable = bundle.getString("gradetable");
        int seth = bundle.getInt("seth");
        //Toast.makeText(getApplicationContext(),gradetable,Toast.LENGTH_LONG).show();



        pieChart = (PieChart) findViewById(R.id.piechart);
        Description ds = new Description();
        ds.setText("Class");
        pieChart.setDescription(ds);
        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Chart Analysis");
        pieChart.setCenterTextSize(10);

        //addDataset();
        int total[] = db.gettotalmarks(gradetable);

        int count = db.gettotalcount(gradetable);

        for(int i=0;i<count;i++)
        {
            if(total[i] < seth)
            {
                lower=lower+1;
            }

        }
        upper = count - lower;

        lowers = (float) lower;
        uppers = (float) upper;



        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        yEntrys.add(new PieEntry(lowers , 1));
        yEntrys.add(new PieEntry(uppers , 2));
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Student marks");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();





        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    /* private void addDataset() {
       ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Student Names");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();







    }*/




}
