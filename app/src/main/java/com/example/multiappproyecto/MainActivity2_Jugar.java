package com.example.multiappproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2_Jugar extends AppCompatActivity {

    private EditText et_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__jugar);

        et_nombre = (EditText)findViewById(R.id.txt_nombre);

    }



    public void iniciar(View view) {

        String nombre = et_nombre.getText().toString();

        if (!nombre.isEmpty()) {



            Intent intent = new Intent(this, ScoreActivity.class);

            intent.putExtra("jugador", nombre);
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


