package com.example.multiappproyecto;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multiappproyecto.level.level;
import com.example.multiappproyecto.level.operation;

import java.util.HashMap;
import java.util.Map;

public class SpacedActivity extends AppCompatActivity {

    level currentLevel;

    private TextView textViewName, textViewScore, textViewLevel;
    private ImageView imageViewFirstRandomNumber, imageViewSecondRandomNumber, imageViewVidas;
    private EditText editTextResult;


    int userScore, vidas = 5;
    String userName, string_score, string_vidas;
    Map<Integer, String> NumsHashMap = new HashMap<Integer, String>();
    Map<Integer, String> LivesHashMap = new HashMap<Integer, String>();

    user currentUser;


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

        LivesHashMap.put(1, "unavida");
        LivesHashMap.put(2, "dosvidas");
        LivesHashMap.put(3, "tresvidas");
        LivesHashMap.put(4, "cuatro");
        LivesHashMap.put(5, "cinco");

        textViewScore = (TextView)findViewById(R.id.textView_puntaje);
        textViewLevel = (TextView)findViewById(R.id.textView_level);
        imageViewVidas = (ImageView)findViewById(R.id.imageView_vidas);
        imageViewFirstRandomNumber = (ImageView)findViewById(R.id.imageView_NumUno);
        imageViewSecondRandomNumber = (ImageView)findViewById(R.id.imageView_NumDos);
        editTextResult = (EditText)findViewById(R.id.editText_Resultado);

        currentUser = new user(5, databaseConnection, userName);

        if (!currentUser.isUserCreated()){
            currentUser.insertNewUser();
        }

        Log.i("cargando", "usuario creado o cargado");


        currentUser.loadScore();

        textViewScore.setText("Puntaje: "+ currentUser.getUserScore());
        textViewName.setText("Jugador: " + currentUser.getUserName());

        Log.i("cargando", "iniciando numero aleatorio");

        generateNewRandomNumber();

        Log.i("cargando", "iniciando updateUserlevel");
        updateUserLevel();
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

        if (respuesta_jugador == currentLevel.getCurrentOperation().getResult()){
            correctUserAnswer(respuesta_jugador);
        } else {
            wrongUserAnswer(respuesta_jugador);
        }

        generateNewRandomNumber();
        updateUserLevel();
    }

    private void wrongUserAnswer(int respuesta_jugador) {
        reduceUserLifes();
    }

    private void correctUserAnswer(int respuesta_jugador) {
        Toast.makeText(this, "Respuesta Correcta!! :)", Toast.LENGTH_SHORT).show();
        updateUserScore();
        currentUser.updateUserScoreDatabase();
        textViewScore.setText("Puntaje:" + currentUser.getUserScore());
    }

    private void updateUserLevel() {
        textViewLevel.setText("Nivel: " + currentUser.getCurrentLevelNumber());
    }


    private void updateUserScore() {
        currentUser.getCurrentLevel().incrementScore();
    }

    private void reduceUserLifes(){
        currentUser.decreaseLives();
        Toast.makeText(this, "Te quedan " + currentUser.getCurrentLives() + " intentos", Toast.LENGTH_LONG).show();
        int idfirst = getResources().getIdentifier(LivesHashMap.get(currentUser.getCurrentLives()), "drawable", getPackageName());

        imageViewVidas.setImageResource(idfirst);

        if(currentUser.getCurrentLives() == 0){
            Toast.makeText(this, "Has perdido tus vidas", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
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

}

