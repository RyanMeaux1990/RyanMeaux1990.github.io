package com.example.weight_watcher.views;
/*

SET UP THE MAIN GRID DISPLAY
HANDLED BY THE MAIN GRID VIEW CONTROLLER CLASS

 */
import static com.example.weight_watcher.R.layout.home_page;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.weight_watcher.Controllers.Controller.Main_Grid_View;
import com.example.weight_watcher.Controllers.Database.Users_Database_Controller;
import com.example.weight_watcher.Controllers.Database.Weight_Database_Controller;
import com.example.weight_watcher.Model.Adapters.Adapter;
import com.example.weight_watcher.Model.Database.Cursors.Users_Weight_DB_Results;
import com.example.weight_watcher.Model.Channels.channel;
import com.example.weight_watcher.Model.SharedPref.Preferences;
import com.example.weight_watcher.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Grid_Display extends AppCompatActivity {


    private static final int NOTIFICATION_ID = 0;
    public Weight_Database_Controller weightDatabase;
    public Users_Weight_DB_Results weightDbResults;
    public Users_Database_Controller usersDb;
    public FloatingActionButton actionButton;
    public Intent intent;
    public Intent secondIntent;
    public Main_Grid_View main_grid_view;
    public String email;
    public TextView welcomeText;
    public TextView progressText;
    public GridView grid;
    public Adapter adapter;
    public View headerView;
    public Activity activity;
    public channel channels;
    public Preferences preferences;


    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(home_page);
        this.activity = this;
        this.preferences = new Preferences(this.getApplicationContext());

        intent = new Intent(Grid_Display.this, Update_Database_Entry.class);
        actionButton = (FloatingActionButton) findViewById(R.id.addNewWeight);
        usersDb = new Users_Database_Controller(getApplicationContext());
        weightDatabase = new Weight_Database_Controller(getApplicationContext());


        welcomeText = (TextView) findViewById(R.id.welcomeText);
        progressText = (TextView) findViewById(R.id.progressText);

        this.grid = (GridView) findViewById(R.id.grid);
        main_grid_view = new Main_Grid_View(this);

        this.main_grid_view.setEmail();
        this.main_grid_view.findAuthenticatedUser();
        this.main_grid_view.getAllGridRows();
        this.adapter = new Adapter(this, this.main_grid_view.rows);
        this.main_grid_view.setAdapter();
        main_grid_view.setUIValues();




    }

    //Creates the Notification once the user hits their goal

    public void createNotification(){
        this.channels = new channel(this);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "goal hit");
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setContentTitle(getString(R.string.app_name));
        builder.setContentText("Congratulations on reaching your goal");
        builder.setPriority(NotificationCompat.PRIORITY_LOW);
        Notification notification = builder
                .build();

        // Get compatibility NotificationManager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Post notification using ID.  If same ID, this notification replaces previous one
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

}







