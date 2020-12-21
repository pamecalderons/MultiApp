package com.example.multiappproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multiappproyecto.spacedrepetition.ReminderBroadcast;

public class MainActivity extends AppCompatActivity {


    private TextView tv_bestScore;
    private MediaPlayer mp_click, mp_intro;
    Switch aSwitch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_foreground);

        mp_intro = MediaPlayer.create(this, R.raw.intro);

        aSwitch = (Switch) findViewById(R.id.switch1);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Música Encendida", Toast.LENGTH_SHORT).show();

                    mp_intro.start();
                    mp_intro.setLooping(true);
                } else {
                    Toast.makeText(getBaseContext(), "Música Apagada", Toast.LENGTH_SHORT).show();
                    mp_intro.pause();
                }
            }
        });










        tv_bestScore = (TextView)findViewById(R.id.textView_BestScore);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "applicationDatabase", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        Cursor consulta = BD.rawQuery("select nombre, max(score) from users;", null);

        if(consulta.moveToFirst()){

            String temp_nobre = consulta.getString(0);
            String temp_score = consulta.getString(1);

            tv_bestScore.setText("Record: " + temp_score + " de " + temp_nobre);


            BD.close();

        } else {
            BD.close();
        }


        //createNotificationChannel();
        //createCalendarNotification();

    }

    private void createCalendarNotification() {
        Intent intent = new Intent(MainActivity.this , ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

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



    public void jugar(View view) {
        Intent siguiente = new Intent(this, MainActivity2_Jugar.class);
        mp_click = MediaPlayer.create(this, R.raw.click);
        mp_click.start();
        mp_intro.stop();
        startActivity(siguiente);

        finish();

    }

    public void practicar(View view) {
        Intent siguiente = new Intent(this, Practicar.class);
        mp_click = MediaPlayer.create(this, R.raw.click);
        mp_click.start();
        mp_intro.stop();
        startActivity(siguiente);
        finish();

    }

    public void exit(View view) {
        mp_intro.stop();
        finish();
    }


    @Override
    public void onBackPressed() {

    }

}