package com.example.weight_watcher.Controllers.Charts;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.weight_watcher.Model.Database.Cursors.Users_Weight_DB_Results;
import com.example.weight_watcher.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Barchart {

    private LineChart chart;
    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;
    public Users_Weight_DB_Results[] data;
    private View ChartView;
    public BarChart lineChart;
    Float month;
    LocalDate date;
    double weight;
    double neck;
    double chest;
    double biceps;
    double waist;
    double legs;
    Cursor chartData;

    List<BarEntry> entries;
    private Object result;

    public Barchart(Cursor data, View charView, Context context){

        chartData = data;
        ChartView = charView;
        lineChart = new BarChart(context);
        GetData();
    }

    public void GetData(){

        entries = new ArrayList<BarEntry>();


        for(int i = 0; i < chartData.getCount(); ++i){

            Users_Weight_DB_Results result = new Users_Weight_DB_Results(chartData);            FormatData(result);
            chartData.moveToPosition(i);
            String extracted = chartData.getString(4);


            BarEntry nextEntry = new BarEntry(i, Float.valueOf(extracted));
            Log.v("Chart Date",result.currentWeight);
            entries.add(nextEntry);
            BarDataSet dataSet = new BarDataSet(entries, "Weight"); // add entries to dataset
            dataSet.setColor(R.color.purple_700);



            BarData lineData = new BarData(dataSet);
            lineChart.setData(lineData);
            lineChart.setAutoScaleMinMaxEnabled(true);

                chartData.moveToPosition(i);


        }

        lineChart.setDrawBarShadow(true);

        lineChart.setY(0);

        lineChart.setDrawValueAboveBar(false);
        lineChart.invalidate();
    }

private void FormatData(Users_Weight_DB_Results result){

        month = Float.valueOf(result.date.substring(0,1));
       /* Log.v
        if(String.valueOf(month.charAt(0)) == "0"){
            month = String.valueOf(month.charAt(1));
        }
        CharSequence day = result.date.substring(2,3);
        CharSequence year = result.date.substring(4,7);


        date = LocalDate.of(Integer.valueOf(String.valueOf(year)),Integer.valueOf(String.valueOf(month)),Integer.valueOf(String.valueOf(day)));
      */
       weight = ConvertToDouble(result.currentWeight);

       biceps = result.bicep;
       chest = result.chest;
       neck = result.neck;
       waist = result.waist;
       legs = result.leg;

}

private double ConvertToDouble(String number){
        return Double.valueOf(number);
}


}
