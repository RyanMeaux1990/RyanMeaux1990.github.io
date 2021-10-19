package com.example.weight_watcher.views;

import static java.lang.Double.valueOf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.weight_watcher.Controllers.Database.Weight_Database_Controller;
import com.example.weight_watcher.Model.GridViewRows.Row;
import com.example.weight_watcher.R;

public class Update_Database_Entry extends AppCompatActivity {
    public TextView weightTextField;
    public TextView dateTextField;
    public Button submit;
    public Button cancel;
    public String user;
    public Intent intent;
    public TextView updateTextView;
    public String action;
    public Row selectedRow;
    SharedPreferences sharedPref;
    private String weight;
    private String date;
    private String goal;
    private String id;
    private Weight_Database_Controller weight_database_controller;
    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_values);
        dateTextField =(TextView) findViewById(R.id.updateDateTextField);
        weightTextField =(TextView) findViewById(R.id.updateWeightTextField);
        submit = (Button) findViewById(R.id.submitUpdateButton);
        cancel = (Button) findViewById(R.id.cancelUpdateButton);
        updateTextView = (TextView) findViewById(R.id.updateTextViewHead);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        weight_database_controller = new Weight_Database_Controller(getApplicationContext());
        intent = new Intent(Update_Database_Entry.this,Grid_Display.class);
        setUpdateTextViews();


    }

    //Sets up the Text Views based on the tag of the clicked button
    private void setUpdateTextViews() {
        SharedPreferences sharedPref = getBaseContext().getSharedPreferences(String.valueOf(R.string.userPreference), Context.MODE_PRIVATE);


        date = sharedPref.getString("Date", null);
        weight = sharedPref.getString("Weight", null);
        action = sharedPref.getString("Action", null);
        user = sharedPref.getString("User", null);
        goal = sharedPref.getString("Goal",null);
        id = sharedPref.getString("ID",null);
        Log.v("before Delete",user);
        Log.v(action,"action received");

        weightTextField.setText(weight);
        dateTextField.setText(date);
        cancel.setOnClickListener(cancelClicked);



        if (action.equals("D")) {
            Log.v("In Delete",user);
            submit.setOnClickListener(deleteVal);
            updateTextView.setText("Delete Row");
            submit.setText("Confirm");
            weightTextField.setText(String.valueOf(weight));
            dateTextField.setText(date);


        }

        if(action.equals("E")){
            submit.setOnClickListener(submitEdit);
            updateTextView.setText("Update Row");
            submit.setText("Submit");
            weightTextField.setText(String.valueOf(weight));
            dateTextField.setText(date);

        }
    }

    //Returns you to the Main page
    View.OnClickListener cancelClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(intent);
        }
    };

    //Submits edit to the database
    View.OnClickListener submitEdit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String dateVal = String.valueOf(dateTextField.getText());
            String weightVal = weightTextField.getText().toString();
            Double newWeight = valueOf(weightVal);
            Double newGoal = valueOf(goal);


            Row selectedRow = new Row(Integer.valueOf(id),user,weight,valueOf(goal),date);
            Long submit = weight_database_controller.updateSelectedRow(selectedRow,dateVal,newWeight);
            startActivity(intent);

        }
    };

    //Removes the row from the database
    View.OnClickListener deleteVal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Row selectedRow = new Row(Integer.valueOf(id),user, weight, valueOf(goal),date);

            Integer submit = weight_database_controller.deleteSelectedRow(selectedRow);

            Intent toMainPage = new Intent(Update_Database_Entry.this,Grid_Display.class);
            startActivity(toMainPage);















        }
    };

}
