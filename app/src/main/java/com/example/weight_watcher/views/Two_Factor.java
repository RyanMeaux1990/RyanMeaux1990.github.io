package com.example.weight_watcher.views;

import static com.example.weight_watcher.R.layout.two_factor_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weight_watcher.Controllers.Database.Users_Database_Controller;
import com.example.weight_watcher.Model.SharedPref.Preferences;
import com.example.weight_watcher.R;

public class Two_Factor extends AppCompatActivity {
    private String email;
    private Users_Database_Controller Database;
    private String phoneNumber;
    private String DatabasePhoneNumber;

    public View.OnClickListener toMainPage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText textField = (EditText) findViewById(R.id.phoneNumberConfirmation);
            String enteredNumber = textField.getText().toString();

            Boolean isAuthenticated = CheckForPhoneComifrmation(enteredNumber,DatabasePhoneNumber);


            if(isAuthenticated) {
                Intent intent = new Intent(Two_Factor.this, Grid_Display.class);
                startActivity(intent);
            }
        }
    };

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Database = new Users_Database_Controller(getApplicationContext());
        setContentView(two_factor_page);

        Button submitButton = (Button)findViewById(R.id.submission);
        submitButton.setOnClickListener(toMainPage);

        Preferences preferences = new Preferences(this.getApplicationContext());
        email = preferences.getString("email");
        DatabasePhoneNumber = GetPhoneNumber(email);



    }


    private String GetPhoneNumber(String email){
        phoneNumber = Database.findUsersPhoneNumber(email);
        return phoneNumber;
    }

    private Boolean CheckForPhoneComifrmation(String numberEntered, String numberFromDatabase){

        if(numberFromDatabase.equals(numberEntered)){
            return true;
        }else{
            return false;
        }
    }




}
