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
import com.example.weight_watcher.Model.Measurements.Measurements;
import com.example.weight_watcher.Model.User.User;
import com.example.weight_watcher.R;

public class registration_page extends AppCompatActivity {
    TextView firstName;
    TextView lastName;
    TextView email;
    TextView password;
    TextView confirmPassword;
    TextView currentWeight;
    TextView goalWeight;
    TextView phone;
    String firstNameText;
    String lastNameText;
    String emailText;
    String passwordText;
    String confrimedPasswordText;
    Double currentWeightText;
    Double goalWeightText;
    String phoneNumberText;
    Weight_Database_Controller weightDb;
    Users_Database_Controller users_database_controller;
    Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);

        Button submitButton = (Button)findViewById(R.id.submit_registration_button);
        submitButton.setOnClickListener(toLoginPage);

        cancelButton = findViewById(R.id.registrationCancelButton);
        cancelButton.setOnClickListener(this.toMainPage);

        weightDb = new Weight_Database_Controller(getApplicationContext());
        users_database_controller = new Users_Database_Controller(getApplicationContext());
    }
//Sets the values of the registration pages text values
    public void setTextViewNumbers(){
        firstName = (TextView)findViewById(R.id.register_first_name);
        lastName = (TextView) findViewById(R.id.register_last_name);
        email = (TextView) findViewById(R.id.register_email);
        password = (TextView) findViewById(R.id.register_password);
        phone = (TextView)findViewById(R.id.phone_number);
        confirmPassword = (TextView) findViewById(R.id.confirm_password);
        currentWeight = (TextView) findViewById(R.id.registration_current_weight);
        goalWeight = (TextView) findViewById(R.id.registration_goal_weight);
        firstNameText = firstName.getText().toString();
        lastNameText = lastName.getText().toString();
        emailText = email.getText().toString();
        passwordText = password.getText().toString();
        confrimedPasswordText = confirmPassword.getText().toString();
        currentWeightText = Double.valueOf(currentWeight.getText().toString());
        goalWeightText = Double.valueOf(goalWeight.getText().toString());
        phoneNumberText = phone.getText().toString();




    }
    //Checks to ensure the entered passwords match
    private Boolean checkPasswordMatches(){

        if(confrimedPasswordText.equals(passwordText)){
            return true;
        }

        if(passwordText != confrimedPasswordText){
            return false;
        }
        return false;
    }
    //Registers a new user within the database and sets all the initial values
    public void registerNewUser(){
        setTextViewNumbers();

        User currentUser = new User(firstNameText,lastNameText,emailText,passwordText,String.valueOf(currentWeightText),Double.valueOf(goalWeightText),phoneNumberText);
        Measurements defaultMeasurements = new Measurements();
        defaultMeasurements.weight = Double.parseDouble(currentUser.weight.currentWeight);
        Boolean isUserInDb = users_database_controller.checkForUserInDatabase(currentUser.userCredentials.username);

        if(isUserInDb == false) {
            SharedPreferences sharedPref = getBaseContext().getSharedPreferences(String.valueOf(R.string.userPreference), Context.MODE_PRIVATE);
            sharedPref.edit().putString("User",firstNameText).commit();

            long newUserId = users_database_controller.addNewUserToDataBase(currentUser);
            long weightDBID = weightDb.addNewUser(currentUser,defaultMeasurements);

        }





    }
    //Takes you to the Login page
    private View.OnClickListener toLoginPage = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            registerNewUser();

                Intent intent = new Intent(registration_page.this, MainActivity.class);
                startActivity(intent);


        }
    };
    //Takes you back to main page
    public View.OnClickListener toMainPage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent toMainPage = new Intent(registration_page.this,MainActivity.class);
            startActivity(toMainPage);
        }
    };

}
