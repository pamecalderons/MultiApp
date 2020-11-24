package com.example.multiappproyecto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Practicar extends Activity implements View.OnClickListener {

    private ImageView tresvidas, iv_vidas;
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
    ImageView iw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicar);

        iv_vidas = (ImageView)findViewById(R.id.icon);


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

        iw = (ImageView) findViewById(R.id.icon);
        iv_vidas.setImageResource(R.drawable.tresvidas);
    }

    @Override
    public void onClick(View v) {

        // TODO Auto-generated method stub
        if (v == b0) {
            iw.setImageResource(R.drawable.tablacero);
        }
        if (v == b1) {
            iw.setImageResource(R.drawable.tablauno);
        }
        if (v == b2) {
            iw.setImageResource(R.drawable.tablados);
        }
        if (v == b3) {
            iw.setImageResource(R.drawable.tres);
        }
        if (v == b4) {
            iw.setImageResource(R.drawable.cuatro);
        }
        if (v == b5) {
            iw.setImageResource(R.drawable.cinco);
        }
        if (v == b6) {
            iw.setImageResource(R.drawable.seis);
        }
        if (v == b7) {
            iw.setImageResource(R.drawable.siete);
        }
        if (v == b8) {
            iw.setImageResource(R.drawable.ocho);
        }
        if (v == b9) {
            iw.setImageResource(R.drawable.nueve);
        }
        if (v == b10) {
            iw.setImageResource(R.drawable.diez);
        }
    }



    }
