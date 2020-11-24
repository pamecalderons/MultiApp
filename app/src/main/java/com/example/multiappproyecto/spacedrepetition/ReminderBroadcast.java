package com.example.multiappproyecto.spacedrepetition;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.multiappproyecto.R;

public class ReminderBroadcast extends BroadcastReceiver {

    public ReminderBroadcast(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String message = intent.getStringExtra("message");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "pameChannel")
                .setSmallIcon(R.drawable.tresvidas)
                .setContentTitle("My notification")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, builder.build());
    }

}
