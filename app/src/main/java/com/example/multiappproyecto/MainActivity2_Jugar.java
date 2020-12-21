package com.example.multiappproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity2_Jugar extends AppCompatActivity {



    private EditText et_nombre;
    private MediaPlayer mp_click, mp_wait;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__jugar);

        et_nombre = (EditText)findViewById(R.id.txt_nombre);
        mp_wait = MediaPlayer.create(this, R.raw.wait);


        aSwitch = (Switch) findViewById(R.id.switch4);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Música Encendida", Toast.LENGTH_SHORT).show();
                    mp_wait.start();
                    mp_wait.setLooping(true);
                } else {
                    Toast.makeText(getBaseContext(), "Música Apagada", Toast.LENGTH_SHORT).show();
                    mp_wait.pause();
                }
            }
        });



    }
    public void back(View view) {
        Intent siguiente = new Intent(this, MainActivity.class);
        mp_click = MediaPlayer.create(this, R.raw.click);
        mp_click.start();
        mp_wait.stop();
        startActivity(siguiente);
        finish();
    }

    public void iniciar(View view) {

        String nombre = et_nombre.getText().toString();

        if (!nombre.isEmpty()) {



            Intent intent = new Intent(this, ScoreActivity.class);

            intent.putExtra("jugador", nombre);
            mp_click = MediaPlayer.create(this, R.raw.click);
            mp_click.start();

            mp_wait.stop();
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Primero debes ingresar tu nombre", Toast.LENGTH_SHORT).show();

            et_nombre.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            inm.showSoftInput(et_nombre, InputMethodManager.SHOW_IMPLICIT);


        }
        }

    @Override
    public void onBackPressed() {

    }


    }


