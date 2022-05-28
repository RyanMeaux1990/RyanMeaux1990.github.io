package com.example.weight_watcher.views;
/*
Controller for the class is located in the Controllers / Userinput package
 */

import static com.example.weight_watcher.R.layout;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weight_watcher.R;

public class Measurement_View extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int UDI = layout.user_data_inputs;
        setContentView(UDI);
        Button submitButton = (Button)findViewById(R.id.SubmitMeasurements);
        TextView weight = (TextView) findViewById(R.id.WeightMeasurement);
        TextView neck = (TextView) findViewById(R.id.NeckMeasurment);
        TextView biceps = (TextView) findViewById(R.id.BicepMeasurement);
        TextView chest = (TextView) findViewById(R.id.ChestMeasurment);
        TextView waist = (TextView) findViewById(R.id.WaistMeasurment);
        TextView legs = (TextView) findViewById(R.id.QuadricepMeasurment);
        Context currentContext = getApplicationContext();
    }

}
