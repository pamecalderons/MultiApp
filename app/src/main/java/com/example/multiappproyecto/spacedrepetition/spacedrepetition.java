package com.example.multiappproyecto.spacedrepetition;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;


import androidx.appcompat.app.AppCompatActivity;

import com.example.multiappproyecto.AdminSQLiteOpenHelper;
import com.example.multiappproyecto.MainActivity;

import static android.content.Context.ALARM_SERVICE;

public class spacedrepetition extends AppCompatActivity {

    public spacedrepetition(){
        createNotificationChannel();
        createCalendarNotification();
    }

    private void createCalendarNotification() {
        Intent intent = new Intent(spacedrepetition.this , ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(spacedrepetition.this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long currentTime = System.currentTimeMillis();
        long tenSecondsInMillis = 1000 * 10;

        alarmManager.set(AlarmManager.RTC_WAKEUP, currentTime + tenSecondsInMillis, pendingIntent);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "userChannel";
            String description = "notificaciones de app";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("pameChannel", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }




}
