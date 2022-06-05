package com.example.weight_watcher.views;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weight_watcher.Controllers.Database.Weight_Database_Controller;
import com.example.weight_watcher.Model.Database.Cursors.Users_Weight_DB_Results;
import com.example.weight_watcher.Model.SharedPref.Preferences;
import com.example.weight_watcher.R;
public class ChartView extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

Weight_Database_Controller weightDB;
Preferences preferences;
String email;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.data_mining_view);

        Preferences preferences = new Preferences(this.getApplicationContext());
        email = preferences.getString("User");

        weightDB = new Weight_Database_Controller(this.getApplicationContext());

        Spinner spinner = findViewById(R.id.textSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.measurement_values, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();

        Users_Weight_DB_Results[] chartData = this.weightDB.GetChartData(email);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
