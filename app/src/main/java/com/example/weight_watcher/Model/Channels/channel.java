package com.example.weight_watcher.Model.Channels;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.NotificationChannel;
import android.app.NotificationManager;

import com.example.weight_watcher.R;
import com.example.weight_watcher.views.Grid_Display;

public class channel {

  String CHANNEL_ID = "goal hit";

    public channel(Grid_Display display) {

        String name = String.valueOf(R.string.channel_name);
        String description = String.valueOf(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);

        // Register channel with system
        NotificationManager manager1 = getSystemService(display.getApplicationContext(),NotificationManager.class);
        manager1.createNotificationChannel(channel);
    }
}
