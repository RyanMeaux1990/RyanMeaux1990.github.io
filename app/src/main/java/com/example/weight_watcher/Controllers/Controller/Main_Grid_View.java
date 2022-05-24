package com.example.weight_watcher.Controllers.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.weight_watcher.Model.Adapters.Adapter;
import com.example.weight_watcher.Model.GridViewRows.Row;
import com.example.weight_watcher.Model.Notification.Notifications;
import com.example.weight_watcher.Model.Toasts.App_Toasts.App_Toast;
import com.example.weight_watcher.Model.User.User;
import com.example.weight_watcher.R;
import com.example.weight_watcher.views.Grid_Display;
import com.example.weight_watcher.views.NewWeight;
import com.example.weight_watcher.views.Update_Database_Entry;

public class Main_Grid_View {

    public Grid_Display gridDisplay;
    public Adapter adapter;
    public Update_Database_Entry updates;
    public SharedPreferences sharedPref;
    public String email;
    public Intent toUpdateDatabase;
    public Row[] rows;
    private TextView welcomeText;
    private TextView progressText;
    private Button editButton;
    private Button deleteButton;
    private User currentUser;
    private final GridView gridView;
    private Notifications notification;
    private Double poundsLeft;


    public Main_Grid_View(Grid_Display display) {
        this.gridDisplay = display;
        this.gridView = gridDisplay.grid;
        this.gridDisplay.actionButton.setOnClickListener(toAddNewWeight);
        sharedPref = this.gridDisplay.getSharedPreferences(String.valueOf(R.string.userPreference), Context.MODE_PRIVATE);


    }

    public void setEmail() {
        this.email = this.gridDisplay.usersDb.email;

    }

    public void setAdapter() {

        this.adapter = gridDisplay.adapter;
        this.adapter.gridView = this;
        this.gridView.setAdapter(this.adapter);


    }

    public void getAllGridRows() {
        Notifications notifications = new Notifications(gridDisplay.activity);
        this.rows = this.gridDisplay.weightDatabase.getAllUserWeights(email);


    }

    public void findAuthenticatedUser() {
        this.gridDisplay.usersDb.findAuthenticatedUser();

        this.gridDisplay.weightDatabase.getUser();

        this.currentUser = new User(this.gridDisplay.usersDb.results.firstName,
                this.gridDisplay.usersDb.results.lastName,
                this.gridDisplay.usersDb.results.email,
                this.gridDisplay.usersDb.results.password,
                String.valueOf(this.gridDisplay.weightDatabase.results.currentWeight),
                Double.valueOf(this.gridDisplay.weightDatabase.results.goalWeight),
                this.gridDisplay.usersDb.results.phoneNumber);

    }

    public void setUIValues() {

        Double current = Double.valueOf(this.currentUser.weight.currentWeight);
        Double goal = this.currentUser.weight.goalWeight;

        Notifications notifications = new Notifications(gridDisplay.activity);
        Log.v(String.valueOf(notifications.hasFilePermissions()),"Access");

        poundsLeft = current - goal;

        this.gridDisplay.welcomeText.setText("Hello, " + currentUser.firstName);
        this.gridDisplay.progressText.setText(poundsLeft+" left to go");

        if (poundsLeft <= 0) {
            Activity thisPage = this.gridDisplay;
            Boolean hasAccess = notifications.hasFilePermissions();
            if(hasAccess.equals(true)) {
                this.gridDisplay.createNotification();
            } else {

                App_Toast newToast = new App_Toast("You have reached your goal",
                        10, gridDisplay.getApplicationContext());
            }
        }

    }

    public View.OnClickListener toEditAndDelete = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tag = (String) v.getTag();
            Integer rowNumber = Integer.valueOf(tag.substring(0, tag.length() - 1));
            String action = String.valueOf(tag.charAt(tag.length() - 1));
            Row selectedRow = rows[rowNumber];

            gridDisplay.preferences.putString("ID",String.valueOf(selectedRow.ID));
            gridDisplay.preferences.putString("Date",selectedRow.date);
            gridDisplay.preferences.putString("Action",action);
            gridDisplay.preferences.putString("Goal", String.valueOf(selectedRow.goalWeight));
            gridDisplay.preferences.putString("Weight", String.valueOf(selectedRow.weightThatDay));

            gridDisplay.startActivityFromChild(updates, gridDisplay.intent, 1);

        }
    };

    public View.OnClickListener toAddNewWeight = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(gridDisplay, NewWeight.class);
            gridDisplay.startActivity(intent);


        }


    };


}
