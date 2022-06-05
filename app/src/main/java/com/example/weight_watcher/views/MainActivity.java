package com.example.weight_watcher.views;
/*
SETS UP THE MAIN LOG IN PAGE
 */
import static com.example.weight_watcher.R.layout.login_page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weight_watcher.Controllers.Database.Users_Database_Controller;
import com.example.weight_watcher.Controllers.Database.Weight_Database_Controller;
import com.example.weight_watcher.Model.Notification.Notifications;
import com.example.weight_watcher.Model.User.User;
import com.example.weight_watcher.R;

public class MainActivity extends AppCompatActivity {
    public User currentUser;
    public Users_Database_Controller usersDatabase;
    public Weight_Database_Controller weightDatabase;
    public Notifications notifications;
    private Activity thisPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        final int logInPageId = login_page;
        setContentView(logInPageId);


        Button registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(toRegisterPage);
        Button submitLogin = (Button) findViewById(R.id.login_submit_button);
        submitLogin.setOnClickListener(toMainPage);

        weightDatabase = new Weight_Database_Controller(getApplicationContext());

        usersDatabase = new Users_Database_Controller(getApplicationContext());

        thisPage = this;

    }

    // Takes you to the Registration Page
    public View.OnClickListener toRegisterPage = v -> {

        Intent intent = new Intent(MainActivity.this, registration_page.class);
        startActivity(intent);

    };

    //Takes you to the Main Page of the app
    public View.OnClickListener toMainPage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            TextView usernameTextView = (TextView) findViewById(R.id.login_username);
            TextView usernamePassView = (TextView) findViewById(R.id.login_password);
            String email = usernameTextView.getText().toString();
            String password = usernamePassView.getText().toString();

            //Checks the database to see if the user is Authenticated

            Boolean isAuthenticatedUser = usersDatabase.checkAuthentication(email,password);
            usersDatabase.findUsersPhoneNumber(email);
            Boolean inDb = weightDatabase.checkForUserInDatabase(email);

            Log.v("User In DB", inDb.toString());
            //If the User is authenticated adds the user to the Shared preferences and sends you to the page
            if(isAuthenticatedUser) {

                String phoneNumber = usersDatabase.findUsersPhoneNumber(email);
                SharedPreferences sp = getApplicationContext().getSharedPreferences(String.valueOf(R.string.userPreference), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("User", email);
                editor.apply();
                String emailCurrentlyInPref = sp.getString("User","");
                Log.v("Email Currently In Pref",emailCurrentlyInPref);



                Intent twoFactorAuthentication = new Intent(MainActivity.this,Two_Factor.class);

                startActivity(twoFactorAuthentication);
            }
        }
    };



}