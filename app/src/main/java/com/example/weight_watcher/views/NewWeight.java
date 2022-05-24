package com.example.weight_watcher.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weight_watcher.Controllers.Database.Users_Database_Controller;
import com.example.weight_watcher.Controllers.Database.Weight_Database_Controller;
import com.example.weight_watcher.Model.User.User;
import com.example.weight_watcher.Model.User.Weight;
import com.example.weight_watcher.R;

import java.util.Date;

public class NewWeight extends AppCompatActivity {
    Date date;
    Double weight;
    Integer submitNewWeightButtonId = R.id.addWeightButton;
    Button submitButton;
    Weight_Database_Controller weightDatabase;
    Users_Database_Controller users_database_controller;
    User currentUser;
    String email;
    private SharedPreferences sharedPref;

    //Pulls the user from shared preferences and creates a new user
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_weight);
        Button submitNewWeightButton = (Button) findViewById(submitNewWeightButtonId);
        submitNewWeightButton.setOnClickListener(toMainPage);

        weightDatabase = new Weight_Database_Controller(getApplicationContext());
        users_database_controller = new Users_Database_Controller(getApplicationContext());
        sharedPref = getApplicationContext().getSharedPreferences(String.valueOf(R.string.userPreference), Context.MODE_PRIVATE);
        email = sharedPref.getString("User", null);

        users_database_controller.findAuthenticatedUser();
        weightDatabase.getUser();
        currentUser = new User(users_database_controller.results.firstName,users_database_controller.results.lastName,users_database_controller.results.email,users_database_controller.results.password,weightDatabase.results.currentWeight,weightDatabase.results.goalWeight,users_database_controller.results.phoneNumber);

    }

    //Adds new weight to the database and takes you back to the main page
    private View.OnClickListener toMainPage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView newWeight = (TextView) findViewById(R.id.newWeight);
            String weightDouble =newWeight.getText().toString();
            Weight newWeightAmount = new Weight(weightDouble,weightDatabase.results.initialWeight,weightDatabase.results.goalWeight);
            Long insertNum = weightDatabase.addNewWeight(currentUser,newWeightAmount);

            Intent intent = new Intent(NewWeight.this, Grid_Display.class);
            startActivity(intent);

        }
    };

}
