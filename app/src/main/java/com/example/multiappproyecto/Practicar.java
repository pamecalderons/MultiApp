package com.example.multiappproyecto;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Practicar extends Activity implements View.OnClickListener {

    private ImageView tresvidas, iv_vidas;
    private MediaPlayer mp_pop, mp_practice;
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button b10;
    Button b11;
    ImageView iw;
    Switch aSwitch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicar);
        mp_practice = MediaPlayer.create(this, R.raw.practice);

        iv_vidas = (ImageView)findViewById(R.id.icon);

        aSwitch = (Switch) findViewById(R.id.switch2);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Música Encendida", Toast.LENGTH_SHORT).show();
                    mp_practice.start();
                    mp_practice.setLooping(true);
                } else {
                    Toast.makeText(getBaseContext(), "Música Apagada", Toast.LENGTH_SHORT).show();
                    mp_practice.pause();
                }
            }
        });


        b0 = (Button) findViewById(R.id.button0);
        b0.setOnClickListener(this);

        b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(this);

        b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(this);

        b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(this);

        b4 = (Button) findViewById(R.id.button4);
        b4.setOnClickListener(this);

        b5 = (Button) findViewById(R.id.button5);
        b5.setOnClickListener(this);

        b6 = (Button) findViewById(R.id.button6);
        b6.setOnClickListener(this);

        b7 = (Button) findViewById(R.id.button7);
        b7.setOnClickListener(this);

        b8 = (Button) findViewById(R.id.button8);
        b8.setOnClickListener(this);

        b9 = (Button) findViewById(R.id.button9);
        b9.setOnClickListener(this);

        b10 = (Button) findViewById(R.id.button10);
        b10.setOnClickListener(this);

        b11 = (Button) findViewById(R.id.button11);
        b11.setOnClickListener(this);

        iw = (ImageView) findViewById(R.id.icon);
        iv_vidas.setImageResource(R.drawable.icono);
    }



    @Override
    public void onClick(View v) {

        // TODO Auto-generated method stub
        if (v == b0) {
            iw.setImageResource(R.drawable.tablacero);
            mp_pop = MediaPlayer.create(this, R.raw.click);
            mp_pop.start();
        }
        if (v == b1) {
            iw.setImageResource(R.drawable.tablauno);
            mp_pop = MediaPlayer.create(this, R.raw.click);
            mp_pop.start();
        }
        if (v == b2) {
            iw.setImageResource(R.drawable.tablados);
            mp_pop = MediaPlayer.create(this, R.raw.click);
            mp_pop.start();
        }
        if (v == b3) {
            iw.setImageResource(R.drawable.tablatres);
            mp_pop = MediaPlayer.create(this, R.raw.click);
            mp_pop.start();
        }
        if (v == b4) {
            iw.setImageResource(R.drawable.tablacuatro);
            mp_pop = MediaPlayer.create(this, R.raw.click);
            mp_pop.start();
        }
        if (v == b5) {
            iw.setImageResource(R.drawable.tablacinco);
            mp_pop = MediaPlayer.create(this, R.raw.click);
            mp_pop.start();
        }
        if (v == b6) {
            iw.setImageResource(R.drawable.tablaseis);
            mp_pop = MediaPlayer.create(this, R.raw.click);
            mp_pop.start();
        }
        if (v == b7) {
            iw.setImageResource(R.drawable.tablasiete);
            mp_pop = MediaPlayer.create(this, R.raw.click);
            mp_pop.start();
        }
        if (v == b8) {
            iw.setImageResource(R.drawable.tablasocho);
            mp_pop = MediaPlayer.create(this, R.raw.click);
            mp_pop.start();
        }
        if (v == b9) {
            iw.setImageResource(R.drawable.tablasnueve);
            mp_pop = MediaPlayer.create(this, R.raw.click);
            mp_pop.start();
        }
        if (v == b10) {
            iw.setImageResource(R.drawable.tablasdiez);
            mp_pop = MediaPlayer.create(this, R.raw.click);
            mp_pop.start();
        }
        if (v == b11) {
            Intent siguiente = new Intent(this, MainActivity.class);
            mp_pop = MediaPlayer.create(this, R.raw.click);
            mp_pop.start();
            mp_practice.stop();
            startActivity(siguiente);
            finish();

        }


    }




    @Override
    public void onBackPressed() {

    }

    }
