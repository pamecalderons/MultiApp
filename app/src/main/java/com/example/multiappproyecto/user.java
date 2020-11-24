package com.example.multiappproyecto;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multiappproyecto.spacedrepetition.ReminderBroadcast;
import com.example.multiappproyecto.spacedrepetition.spacedrepetition;

import com.example.multiappproyecto.level.level;

public class user extends AppCompatActivity {

    int currentLives;
    int userScore;
    level currentLevel;
    SQLiteDatabase databaseConnection;
    String userName;

    public user(int Lives, SQLiteDatabase DatabaseConnection,String UserName) {
        currentLives = Lives;
        databaseConnection = DatabaseConnection;
        userName = UserName;
        loadScore();
        currentLevel = new level(userScore);
    }

    public user(int Lives, SQLiteDatabase DatabaseConnection,String UserName, int currentLevelNumber){
        currentLives = Lives;
        databaseConnection = DatabaseConnection;
        userName = UserName;
        currentLevel = new level(currentLevelNumber);
        loadScore();
    }

    public boolean isUserCreated(){
        try {
            Cursor cursor = databaseConnection.rawQuery("SELECT nombre, score FROM users WHERE nombre = '" + userName + "'", null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                return true;
            }

            return false;
        }catch (Exception error){
            Log.e("isUserCreated", error.getMessage() + " - "+ "La tabla no pudo ser consultada, para saber si usuario existe.");
            return false;
        }
    }

    public void insertNewUser(){

        try {
            ContentValues insertion = new ContentValues();
            insertion.put("nombre", userName);
            insertion.put("score", 0);

            databaseConnection.insert("users", null, insertion);

        }catch (Exception error){
            Log.e("insertNewUser", error.getMessage() + " - "+ "ERROR: Usuario no pudo ser creado.");
        }
    }

    public void updateUserScoreDatabase() {
        try {

            Log.i("cargando", "Actualizando puntaje en DB!");

            ContentValues modificacion = new ContentValues();
            modificacion.put("nombre", userName);
            modificacion.put("score", userScore);
            modificacion.put("level", currentLevel.getCurrentLevelNumberScored(userScore));

            databaseConnection.update("users", modificacion, "nombre='" + userName + "'", null);

        }catch (Exception error){
            System.out.println(error.getMessage() + "hola pame");
        }
    }

    public String getUserName(){
        return userName;
    }

    public void resetLives(){
        currentLives = 4;
    }

    public void decreaseLives(){
        currentLives--;
    }

    public int getCurrentLives(){
        return currentLives;
    }

    public void incrementUserScore(){
        userScore++;
        updateUserScoreDatabase();
        updateUserLives();
    }

    private void updateUserLives() {

        switch (userScore){
            case 5:
                resetLives();
                break;
            case 10:
                resetLives();
                break;
            case 15:
                resetLives();
                break;
            case 20:
                resetLives();
                break;
            case 25:
                resetLives();
                break;

        }
    }

    public int getUserScore(){
        return userScore;
    }

    public void loadScore() {
        try {

            Log.i("cargando", "leyendo puntaje del usuario");

            Cursor cursor = databaseConnection.rawQuery("SELECT nombre, score FROM users WHERE nombre = '" + userName + "'", null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                userScore =  Integer.parseInt(cursor.getString(1));
                Log.i("cargando", "usuario encontrado puntaje:" + userScore);
                return;
            }

            userScore = 0;
            Log.i("cargando", "usuario NO encontrado puntaje:" + userScore);
        }catch (Exception error){
            Log.e("loadScore", error.getMessage() + " - "+ "La tabla no pudo ser consultada, para saber si usuario existe.");
        }
    }

    public int getCurrentLevelNumber(){
        return currentLevel.getCurrentLevelNumberScored(userScore);
    }

    public level getCurrentLevel(){
        currentLevel = new level(userScore);

        return currentLevel;
    }

    public int getCurrentUserLevelNumber(){
        currentLevel = new level(userScore);

        return currentLevel.getCurrentLevelNumber();
    }

    private void createCalendarNotification() {
        Intent intent = new Intent(user.this , ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(user.this, 0, intent, 0);

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
