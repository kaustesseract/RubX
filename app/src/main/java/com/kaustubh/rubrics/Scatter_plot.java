package com.kaustubh.rubrics;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Random;

public class Scatter_plot extends AppCompatActivity {

    PointsGraphSeries<DataPoint> xySeries;//holds data and appends to the graph
    GraphView mScatterPlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scatter_plot);
        mScatterPlot = (GraphView)findViewById(R.id.scatter);

        createscatterplot();
    }

    private void createscatterplot() {

        xySeries = new PointsGraphSeries();

        ArrayList<XYValue> xyvaluearray = new ArrayList<>();
        double start = -100;
        double end = 100;

        for(int i=0;i<40;i++)
        {
            double randomx = new Random().nextDouble();
            double randomy = new Random().nextDouble();
            double x = start + (randomx * (end - start));
            double y = start + (randomy * (end - start));
            xyvaluearray.add(new XYValue(x,y));
        }

        xyvaluearray = sortArray(xyvaluearray);

        //xyvalueArray = sortArray(xyValueArray);
        //add the data to the series
        for(int i = 0;i <xyvaluearray.size(); i++) {
            double x = xyvaluearray.get(i).getX();
            double y = xyvaluearray.get(i).getY();
            xySeries.appendData(new DataPoint(x, y), true, 1000);
        }

            //set some properties
            xySeries.setShape(PointsGraphSeries.Shape.RECTANGLE);
            xySeries.setColor(Color.BLUE);
            xySeries.setSize(20f);

            mScatterPlot.getViewport().setScalable(true);
            mScatterPlot.getViewport().setScalableY(true);
            mScatterPlot.getViewport().setScrollable(true);
            mScatterPlot.getViewport().setScrollableY(true);

            //set manual x bounds
            mScatterPlot.getViewport().setYAxisBoundsManual(true);
            mScatterPlot.getViewport().setMaxY(150);
            mScatterPlot.getViewport().setMinY(-150);

            //set manual y bounds
            mScatterPlot.getViewport().setXAxisBoundsManual(true);
            mScatterPlot.getViewport().setMaxX(150);
            mScatterPlot.getViewport().setMinX(-150);

            mScatterPlot.addSeries(xySeries);

    }

    private ArrayList<XYValue> sortArray(ArrayList<XYValue> array) {
          /*
        //Sorts the xyValues in Ascending order to prepare them for the PointsGraphSeries<DataSet>
         */
        int factor = Integer.parseInt(String.valueOf(Math.round(Math.pow(array.size(),2))));
        int m = array.size()-1;
        int count = 0;
       // Log.d(TAG, "sortArray: Sorting the XYArray.");

        while(true){
            m--;
            if(m <= 0){
                m = array.size() - 1;
            }
         //   Log.d(TAG, "sortArray: m = " + m);
            try{
                //print out the y entrys so we know what the order looks like
                //Log.d(TAG, "sortArray: Order:");
                //for(int n = 0;n < array.size();n++){
                //Log.d(TAG, "sortArray: " + array.get(n).getY());
                //}
                double tempY = array.get(m-1).getY();
                double tempX = array.get(m-1).getX();
                if(tempX > array.get(m).getX() ){
                    array.get(m-1).setY(array.get(m).getY());
                    array.get(m).setY(tempY);
                    array.get(m-1).setX(array.get(m).getX());
                    array.get(m).setX(tempX);
                }
                else if(tempY == array.get(m).getY()){
                    count++;
             //       Log.d(TAG, "sortArray: count = " + count);
                }

                else if(array.get(m).getX() > array.get(m-1).getX()){
                    count++;
              //      Log.d(TAG, "sortArray: count = " + count);
                }
                //break when factorial is done
                if(count == factor ){
                    break;
                }
            }catch(ArrayIndexOutOfBoundsException e){
             //   Log.e(TAG, "sortArray: ArrayIndexOutOfBoundsException. Need more than 1 data point to create Plot." +
                 //       e.getMessage());
                break;
            }
        }
        return array;

    }
}
