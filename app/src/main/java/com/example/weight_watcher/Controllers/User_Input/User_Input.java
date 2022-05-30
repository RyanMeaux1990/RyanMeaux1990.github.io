package com.example.weight_watcher.Controllers.User_Input;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import com.example.weight_watcher.Controllers.Database.Users_Database_Controller;
import com.example.weight_watcher.Controllers.Database.Weight_Database_Controller;
import com.example.weight_watcher.Model.Database.Cursors.Users_Database_Results;
import com.example.weight_watcher.Model.Database.Cursors.Users_Weight_DB_Results;
import com.example.weight_watcher.Model.Measurements.Measurements;
import com.example.weight_watcher.Model.User.User;
import com.example.weight_watcher.Model.User.Weight;
import com.example.weight_watcher.R;
import com.example.weight_watcher.views.Grid_Display;
import com.example.weight_watcher.views.Measurement_View;

/*
Controls the Users Input Screen
 */
public class User_Input {
    Measurement_View measurement_view;
    Users_Database_Controller database_controller;
    Weight_Database_Controller weight_database_controller;
    Users_Database_Results results;
    Users_Weight_DB_Results weightResults;
    String userEmail;
    User currentUser;
    Measurements measurements;
    public Double weight;
    public Double chest;
    public Double neck;
    public Double bicep;
    public Double waist;
    public Double leg;

   public User_Input(Measurement_View view){
       this.measurement_view = view;
       this.database_controller = new Users_Database_Controller(this.measurement_view.getApplicationContext());
       this.weight_database_controller = new Weight_Database_Controller(view);
       SharedPreferences sharedPref = view.getBaseContext().getSharedPreferences(String.valueOf(R.string.userPreference), Context.MODE_PRIVATE);
       this.userEmail = sharedPref.getString("email","");
       measurement_view.submitButton.setOnClickListener(AddToDBAndReturnHome);
   }
//Convience Fucntion to get all text and add To DB
   public void GetMeasurementsAndAddToDB(){
       SharedPreferences sp = measurement_view.getApplicationContext().getSharedPreferences(String.valueOf(R.string.userPreference), Context.MODE_PRIVATE);
       userEmail = sp.getString("User","");
       GetMeasurmentText();
       GetUser(userEmail);
       SendMeasurementsToDatabase();
   }

   //Gets all Measurements in the Text Box's on the page
   public void GetMeasurmentText(){
       this.measurement_view.setValues();

   }

   //Sends Measurments to DB
   public void SendMeasurementsToDatabase(){
       Weight newWeight = new Weight(String.valueOf(weight),currentUser.weight.initialWeight,currentUser.weight.goalWeight);
       Log.v("Neck",String.valueOf(neck));
       Log.v("chest",String.valueOf(chest));
       Log.v("Bicep",String.valueOf(bicep));
       Log.v("waist",String.valueOf(waist));
       Log.v("leg",String.valueOf(leg));
       Log.v("Current",String.valueOf(newWeight.currentWeight));
       Log.v("Goal",String.valueOf(newWeight.goalWeight));


       measurements = new Measurements(neck,chest,bicep,waist,leg,newWeight);

       weight_database_controller.addNewWeight(currentUser, measurements);
   }

   //Gets the users from Both DB
   private void GetUser(String usersEmail){
       this.results = database_controller.GetUser(usersEmail);
       this.weightResults = weight_database_controller.getLastEntry(usersEmail);
       currentUser = new User(results.firstName, results.lastName, results.email, results.password, weightResults.currentWeight,weightResults.goalWeight,results.phoneNumber);
       Log.v("Get User",currentUser.firstName);
   }

    //On Click Listener For Submit Button
    public View.OnClickListener AddToDBAndReturnHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GetMeasurmentText();
            GetMeasurementsAndAddToDB();
            Intent backHome = new Intent(measurement_view, Grid_Display.class);
            measurement_view.startActivity(backHome);
        }
    };

}
