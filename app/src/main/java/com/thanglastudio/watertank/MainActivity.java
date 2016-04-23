package com.thanglastudio.watertank;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mainLayout;
    private LineChart mChart;
    float[] datas=null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Bundle bundle=getIntent().getExtras();
//        String data1=bundle.getString("data1");
//        String data2=bundle.getString("data1");
//        String data3=bundle.getString("data1");

        mainLayout=(RelativeLayout)findViewById(R.id.mainLayout);
        
//        datas[0]=Float.parseFloat(data1);
//        datas[1]=Float.parseFloat(data2);
//        datas[2]=Float.parseFloat(data3);

        //create line chart
        mChart= new LineChart(this);

        //adding line chart to main layout
        mainLayout.addView(mChart, new AbsListView.LayoutParams
                (AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));

        //customize line chart
        mChart.setDescription("");
        mChart.setNoDataTextDescription("No data for the moment");

        //enable value highlighting
        mChart.setHighlightPerTapEnabled(true);
        mChart.setHighlightPerDragEnabled(true);

        //enable touch
        mChart.setTouchEnabled(true);

        //enabling scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);

        //enable pinch zoom
        mChart.setPinchZoom(true);

        //alternative background color
        mChart.setBackgroundColor(Color.WHITE);


        // This is for data
        LineData data =new LineData();
        data.setValueTextColor(Color.BLUE);

        //add data to line chart
        mChart.setData(data);

        //Get Legend Object
        Legend l= new Legend();

        //customize legend
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);

        XAxis x1=mChart.getXAxis();
        x1.setTextColor(Color.BLUE);
        x1.setDrawGridLines(false);
        x1.setAvoidFirstLastClipping(true);

        YAxis y1=mChart.getAxisLeft();
        y1.setTextColor(Color.LTGRAY);
        y1.setAxisMaxValue(150f);
        y1.setDrawGridLines(true);

        YAxis y12= mChart.getAxisRight();
        y12.setEnabled(false);


    }

    //Now we create method to feed entry to chart
    public void addEntry(){
        LineData data=mChart.getData();
        if(data!=null){
            LineDataSet set = (LineDataSet) data.getDataSetByIndex(0);

            if(set== null){
                //creation if null
                set=createSet();
                data.addDataSet(set);
            }

            //add a new random variable
            data.addXValue("");
            data.addEntry(new Entry((float)(Math.random()*120)+5f,set.getEntryCount()),0);

            //enable a way for chart to know when data changes

            mChart.notifyDataSetChanged();

            //limit number of visible entries
            mChart.setVisibleXRange(2,6);

            //scroll to the last entry
            mChart.moveViewToX(data.getXValCount()-7);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


        // Now we simulate real time data addition

        new Thread(new Runnable() {
            @Override
            public void run() {

                // add 100 entries
                for ( int i = 0; i < 100; i++) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();
                        }
                    });
                    //pause between adds
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        //Manage error
                    }


                }
            }
        }).start();
    }

    private LineDataSet createSet() {

        LineDataSet set= new LineDataSet(null,"NEPAL STOCK MARKET");
        set.setDrawCubic(true);
        set.setCubicIntensity(0.2f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(ColorTemplate.getHoloBlue());
        set.setLineWidth(2f);
        set.setCircleRadius(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244,117,177));
        set.setValueTextColor(Color.RED);
        set.setValueTextSize(10f);
        return set;
    }
}
