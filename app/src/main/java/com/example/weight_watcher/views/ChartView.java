package com.example.weight_watcher.views;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weight_watcher.Controllers.Charts.Barchart;
import com.example.weight_watcher.Controllers.Database.Weight_Database_Controller;
import com.example.weight_watcher.Model.SharedPref.Preferences;
import com.example.weight_watcher.R;

public class ChartView extends AppCompatActivity{

Weight_Database_Controller weightDB;
Preferences preferences;
String email;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.data_mining_view);

        Preferences preferences = new Preferences(this);
        email = preferences.getString("User");

        weightDB = new Weight_Database_Controller(this);

        Cursor chartData = weightDB.GetChartData(email);

        View chartView = findViewById(R.id.chartSubView);
        Barchart barChart = new Barchart(chartData,chartView,this);

        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.chartView);

        this.setContentView(barChart.lineChart);

    }

}
