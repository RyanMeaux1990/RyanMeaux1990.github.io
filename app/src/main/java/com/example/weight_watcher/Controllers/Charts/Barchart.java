package com.example.weight_watcher.Controllers.Charts;

import android.content.Context;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.weight_watcher.Model.Database.Cursors.Users_Weight_DB_Results;
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
    LocalDate date;
    double weight;
    double neck;
    double chest;
    double biceps;
    double waist;
    double legs;
    List<BarEntry> entries;
    private Object result;

    public Barchart(Users_Weight_DB_Results[] ChartData, View charView, Context context){

        data = ChartData;
        ChartView = charView;
        lineChart = new BarChart(context);

        GetData();
    }

    public void GetData(){

        entries = new ArrayList<BarEntry>();

        for(int i = 0; i < data.length; ++i){

            Users_Weight_DB_Results result = data[i];
            FormatData(result);

            BarEntry nextEntry = new BarEntry(i, (float) biceps);

            entries.add(nextEntry);
            BarDataSet dataSet = new BarDataSet(entries, "Biceps"); // add entries to dataset
            dataSet.setColor(0);
            dataSet.setValueTextColor(5);

            BarData lineData = new BarData(dataSet);
            lineChart.setData(lineData);
            lineChart.invalidate(); // refresh


        }

        lineChart.setBackgroundColor(8735);
        lineChart.setMaxVisibleValueCount(10);
        lineChart.invalidate();
    }

private void FormatData(Users_Weight_DB_Results result){
       /*
        String month = String.valueOf(result.date.substring(0,1));
        Log.v
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
