package com.example.weight_watcher.Model.Notification;
/*
DEFINES THE SUCCESS NOTIFICATION RECEIVED WHEN GOAL IS REACHED
 */
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



public class Notifications {
    Activity activity;
    private final int REQUEST_NOTIFICATION_CODE = 3;

    public Notifications(Activity activities) {
        this.activity = activities;
        hasFilePermissions();
    }

    public boolean hasFilePermissions() {
        String NOTIFICATION_PERMISSION = Manifest.permission.ACCESS_NOTIFICATION_POLICY;
        if (ContextCompat.checkSelfPermission(this.activity, NOTIFICATION_PERMISSION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, NOTIFICATION_PERMISSION)) {
                Log.v("Show", "Show");

            } else {
                ActivityCompat.requestPermissions(this.activity,
                        new String[]{NOTIFICATION_PERMISSION}, REQUEST_NOTIFICATION_CODE);
            }
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_NOTIFICATION_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted!
                } else {
                    // Permission denied!
                }
                return;
            }
        }
    }
}