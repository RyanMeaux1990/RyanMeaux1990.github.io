package com.example.weight_watcher.views;
/*
Controller for the class is located in the Controllers / Userinput package
 */

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weight_watcher.Controllers.User_Input.User_Input;
import com.example.weight_watcher.R;

public class Measurement_View extends AppCompatActivity {
    public Button submitButton;
    public Button cancelButton;
    public TextView weight;
    public TextView neck;
    public TextView biceps;
    public TextView chest;
    public TextView waist;
    public TextView legs;

    public CharSequence weightText;
    public CharSequence neckText;
    public CharSequence bicepText;
    public CharSequence chestText;
    public CharSequence waistText;
    public CharSequence legText;
    User_Input inputController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int UDI = R.layout.user_data_inputs;
        setContentView(UDI);
        submitButton = (Button)findViewById(R.id.SubmitMeasurements);
        cancelButton = (Button)findViewById(R.id.CancelButton);
        weight = (TextView) findViewById(R.id.WeightMeasurement);
        neck = (TextView) findViewById(R.id.NeckMeasurment);
        biceps = (TextView) findViewById(R.id.BicepMeasurement);
        chest = (TextView) findViewById(R.id.ChestMeasurment);
        waist = (TextView) findViewById(R.id.WaistMeasurment);
        legs = (TextView) findViewById(R.id.QuadricepMeasurment);


        inputController = new User_Input(this);





    }
    public void setValues(){
        inputController.weight = Double.valueOf(String.valueOf(weight.getText()));
        inputController.neck = Double.valueOf(String.valueOf(neck.getText()));
        inputController.bicep = Double.valueOf(String.valueOf(biceps.getText()));
        inputController.chest = Double.valueOf(String.valueOf(chest.getText()));
        inputController.waist = Double.valueOf(String.valueOf(waist.getText()));
        inputController.leg = Double.valueOf(String.valueOf(legs.getText()));


    }


}
