package com.example.multiappproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.example.multiappproyecto.level.operation;
import com.example.multiappproyecto.spacedrepetition.ReminderBroadcast;

import java.util.HashMap;
import java.util.Map;

public class ScoreActivity extends AppCompatActivity {

    private TextView textViewName, textViewScore, textViewLevel;
    private ImageView imageViewFirstRandomNumber, imageViewSecondRandomNumber, imageViewVidas;
    private EditText editTextResult;
    private MediaPlayer mp, mp_great, mp_bad, mp_gameover, mp_click;


    int vidas = 4;
    String userName;
    Map<Integer, String> NumsHashMap = new HashMap<Integer, String>();
    Map<Integer, String> LivesHashMap = new HashMap<Integer, String>();

    user currentUser;
    Switch aSwitch;


    AdminSQLiteOpenHelper adminSQLiteOpenHelper;
    SQLiteDatabase databaseConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel1);
        adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "applicationDatabase", null, 1);
        databaseConnection = adminSQLiteOpenHelper.getWritableDatabase();

        NumsHashMap.put(0, "cero");
        NumsHashMap.put(1, "uno");
        NumsHashMap.put(2, "dos");
        NumsHashMap.put(3, "tres");
        NumsHashMap.put(4, "cuatro");
        NumsHashMap.put(5, "cinco");
        NumsHashMap.put(6, "seis");
        NumsHashMap.put(7, "siete");
        NumsHashMap.put(8, "ocho");
        NumsHashMap.put(9, "nueve");
        NumsHashMap.put(10, "diez");

        LivesHashMap.put(0, "cero");
        LivesHashMap.put(1, "unavida");
        LivesHashMap.put(2, "dosvidas");
        LivesHashMap.put(3, "tresvidas");
        LivesHashMap.put(4, "cuatrovidas");
        LivesHashMap.put(5, "cinco");

        mp = MediaPlayer.create(this, R.raw.goats);


        textViewName = (TextView)findViewById(R.id.textView_nombre1);
        textViewScore = (TextView)findViewById(R.id.textView_puntaje);
        textViewLevel = (TextView)findViewById(R.id.textView_level);
        imageViewVidas = (ImageView)findViewById(R.id.imageView_vidas);
        imageViewFirstRandomNumber = (ImageView)findViewById(R.id.imageView_NumUno);
        imageViewSecondRandomNumber = (ImageView)findViewById(R.id.imageView_NumDos);
        editTextResult = (EditText)findViewById(R.id.editText_Resultado);
        userName = getIntent().getStringExtra("jugador").trim();

        aSwitch = (Switch) findViewById(R.id.switch3);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Música Encendida", Toast.LENGTH_SHORT).show();
                    mp.start();
                    mp.setLooping(true);
                } else {
                    Toast.makeText(getBaseContext(), "Música Apagada", Toast.LENGTH_SHORT).show();
                    mp.pause();
                }
            }
        });

        StartApplication();
    }

    private void StartApplication() {


        loadUserFromDatabase();

        Log.i("cargando", "usuario creado o cargado");

        currentUser.loadScore();

        textViewScore.setText("Puntaje: "+ currentUser.getUserScore());
        textViewName.setText("Jugador: " + currentUser.getUserName());

        Log.i("cargando", "iniciando numero aleatorio");
        generateNewRandomNumber();
        Log.i("cargando", "iniciando updateUserlevel");

        updateTextViewLevel();


    }

    private void loadUserFromDatabase() {
        currentUser = new user(vidas, databaseConnection, userName);

        if (!currentUser.isUserCreated()){
            currentUser.insertNewUser();
        }
    }

    //accion del boton cuando le doy click
    public void checkUserAnswer(View view){
        String respuesta = editTextResult.getText().toString();
        editTextResult.setText("");

        if (respuesta.equals("")) {
            Toast.makeText(this, "Escribe una respuesta", Toast.LENGTH_SHORT).show();
            return;
        }

        int respuesta_jugador = Integer.parseInt(respuesta);

        if (respuesta_jugador == currentUser.currentLevel.getCurrentOperation().getResult()){
            correctUserAnswer(respuesta_jugador);
            Log.i("cargando", "Respuesta correcta!");
        } else {
            wrongUserAnswer();
        }

        generateNewRandomNumber();
        updateTextViewLevel();


        //chancho


        if (currentUser.getUserScore() == 4 ||
                currentUser.getUserScore() == 9 ||
                currentUser.getUserScore() == 14 ||
                currentUser.getUserScore() == 19){

            int nivelu = currentUser.getCurrentLevelNumber();

            Toast.makeText(this, "Muy bien!! se le recordará prácticar más adelante", Toast.LENGTH_SHORT).show();

            ///spacedrepetition spacedrepetition = new spacedrepetition();
            int level = currentUser.getCurrentUserLevelNumber();
            String message = "Usuario " + userName + " - " + "practique nivel: " + level + nivelu;

            createNotificationChannel();
            createCalendarNotification(message);
            StartApplication();
        }
    }


    private void wrongUserAnswer() {
        mp_bad = MediaPlayer.create(this, R.raw.mistake);
        mp_bad.start();
        reduceUserLives();

        if (currentUser.getCurrentLives() == 2){

            int nivelu = currentUser.getCurrentLevelNumber();

            Toast.makeText(this, "Se le va a notificar en 1 día que practique esta tabla, sus vidas son muy bajas", Toast.LENGTH_SHORT).show();

            ///spacedrepetition spacedrepetition = new spacedrepetition();
            int level = currentUser.getCurrentUserLevelNumber();
            String message = "Usuario " + userName + " - " + "practique nivel: " + level + nivelu;

            createNotificationChannel();
            createCalendarNotification(message);
        }

    }

    private void correctUserAnswer(int respuesta_jugador) {
        mp_great = MediaPlayer.create(this, R.raw.correct_1);
        mp_great.start();
        Toast.makeText(this, "Respuesta Correcta!! :)", Toast.LENGTH_SHORT).show();
        currentUser.incrementUserScore();
        textViewScore.setText("Puntaje:" + currentUser.getUserScore());
        updateUI();
    }

    private void reduceUserLives(){
        currentUser.decreaseLives();
        Log.i("reduceUserLives", "VIDAS: " + currentUser.getCurrentLives());
        Toast.makeText(this, "Te quedan " + currentUser.getCurrentLives() + " intentos", Toast.LENGTH_LONG).show();
        updateUI();

        if(currentUser.getCurrentLives() == 0){
            Log.i("reduceUserLives", "Cerrando pantalla: Vidas: " + currentUser.getCurrentLives());
            currentUser.resetLives();
            mp_gameover = MediaPlayer.create(this, R.raw.gameover1);
            mp_gameover.start();
            Toast.makeText(this, "Has perdido tus vidas", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            databaseConnection.close();
            mp.stop();
            startActivity(intent);
            finish();
        }
    }

    private void updateUI(){
        int idfirst = getResources().getIdentifier(LivesHashMap.get(currentUser.getCurrentLives()), "drawable", getPackageName());
        imageViewVidas.setImageResource(idfirst);
    }

    private void generateNewRandomNumber() {
        try {
            operation operation = currentUser.getCurrentLevel().getNextOperation();

            int firstRandomNumber = operation.getFistRandomNumber();
            int secondRandomNumber = operation.getSecondRandomNumber();

            int idfirst = getResources().getIdentifier(NumsHashMap.get(firstRandomNumber), "drawable", getPackageName());
            int idsecond = getResources().getIdentifier(NumsHashMap.get(secondRandomNumber), "drawable", getPackageName());

            imageViewFirstRandomNumber.setImageResource(idfirst);
            imageViewSecondRandomNumber.setImageResource(idsecond);

        }catch (Exception error){
            Log.e("generateNewRandomNumber", error.getMessage() + " - "+ "Generate random number no sirve.");
        }
    }

    private void updateTextViewLevel() {
        Log.i("cargando", "Actualizando Nivel! " + currentUser.getCurrentLevelNumber());
        textViewLevel.setText("Nivel: " + currentUser.getCurrentLevelNumber());
    }

    private void createCalendarNotification(String message) {
        Intent intent = new Intent(ScoreActivity.this , ReminderBroadcast.class);

        intent.putExtra("message", message);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(ScoreActivity.this, 0, intent, 0);

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

    public void back(View view) {
        Intent siguiente = new Intent(this, MainActivity.class);
        mp_click = MediaPlayer.create(this, R.raw.click);
        mp_click.start();
        mp.stop();
        startActivity(siguiente);
        finish();

    }



 @Override
    public void onBackPressed() {

    }

}

